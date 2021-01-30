package br.com.devcave.ratelimiter.controller

import br.com.devcave.ratelimiter.service.RateLimiter
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("rate-limiter")
class RateLimiterController(
    private val rateLimiterSample: RateLimiter
) {

    @GetMapping
    fun canConsume(): Boolean {
        return rateLimiterSample.canConsume()
    }
}