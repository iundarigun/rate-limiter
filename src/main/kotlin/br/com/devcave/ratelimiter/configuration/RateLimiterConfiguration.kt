package br.com.devcave.ratelimiter.configuration

import br.com.devcave.ratelimiter.service.RateLimiter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.RedisScript
import org.springframework.data.redis.serializer.RedisSerializer

@Configuration
class RateLimiterConfiguration(
    @Value("\${rate-limiter-sample.window-duration-in-seconds}")
    private val durationInSeconds: Long,
    @Value("\${rate-limiter-sample.window-size}")
    private val windowSize: Long
) {
    @Bean
    fun rateLimiterScript(): RedisScript<Long> {
        val classPathResource = ClassPathResource("scripts/rate-limiter.lua")
        return RedisScript.of(classPathResource, Long::class.java)
    }

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<Any, Long> =
        RedisTemplate<Any, Long>().also {
            it.keySerializer = RedisSerializer.string()
            it.valueSerializer = RedisSerializer.json()
            it.setConnectionFactory(redisConnectionFactory)
        }

    @Bean
    fun rateLimiterSample(
        redisTemplate: RedisTemplate<Any, Long>,
        rateLimiterScript: RedisScript<Long>
    ): RateLimiter = RateLimiter(
        redisTemplate, rateLimiterScript,
        "rate-limiter-sample", durationInSeconds, windowSize
    )
}