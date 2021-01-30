package br.com.devcave.ratelimiter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RateLimiterApplication

fun main(args: Array<String>) {
	runApplication<RateLimiterApplication>(*args)
}
