# Rate Limiter

This repo aims to implement a distributed rate limiter, using Redis. This implementation is a lazy copy for [Leonardo Ferreira's implementation](https://github.com/LeonardoFerreiraa/poc/tree/master/rate-limiter), based on [this post](https://levelup.gitconnected.com/implementing-a-sliding-log-rate-limiter-with-redis-and-golang-79db8a297b9e).

### Problem description
Sometimes you need to control some kind of throughput for a certain window time. For example, you can't call some API more than 10 times for second. 

We have some options to do this on a single instance application (resilient4j), but today is usual to have more than one instance of the same application running on a cluster.

This project implement a solution using the Redis capabilities

### Other options
- bucket4j: https://github.com/vladimir-bukhtoyarov/bucket4j
- ratelimitj: https://github.com/mokies/ratelimitj

### References
* https://github.com/LeonardoFerreiraa/poc/tree/master/rate-limiter
* https://levelup.gitconnected.com/implementing-a-sliding-log-rate-limiter-with-redis-and-golang-79db8a297b9e
