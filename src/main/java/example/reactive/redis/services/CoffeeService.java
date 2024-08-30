package example.reactive.redis.services;

import example.reactive.redis.repository.model.Coffee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoffeeService {

    private final ReactiveRedisOperations<String, Coffee> coffeeOps;
    public Flux<Coffee> all() {
        log.info("All coffees requested");
        return coffeeOps.keys("*")
                .flatMap(coffeeOps.opsForValue()::get);
    }

    public Mono<Boolean> add(Coffee coffee) {
        return coffeeOps.opsForValue().set(coffee.id(), coffee);
    }
}
