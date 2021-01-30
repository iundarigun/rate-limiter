package br.com.devcave.ratelimiter.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.RedisScript
import java.time.Duration
import java.util.UUID

class RateLimiter(
    private val redisTemplate: RedisTemplate<Any, Long>,
    private val rateLimiterScript: RedisScript<Long>,
    private val name: String,
    private val durationInSeconds: Long,
    private val windowSize: Long
) {

    fun canConsume(): Boolean {
        return redisTemplate.execute(
            rateLimiterScript,
            listOf(name),
            UUID.randomUUID(),
            System.nanoTime(),
            Duration.ofSeconds(durationInSeconds).toNanos(),
            windowSize
        ) > 0
    }
}