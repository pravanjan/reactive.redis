package example.reactive.redis.controller;

import example.reactive.redis.repository.model.Coffee;
import example.reactive.redis.services.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;
    @GetMapping("/coffees")
    public Flux<Coffee> all() {
        return coffeeService.all();
    }
}
