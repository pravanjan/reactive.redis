package example.reactive.redis.controller;

import example.reactive.redis.repository.model.Coffee;
import example.reactive.redis.services.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;
    @PostMapping("/coffee")
    public Mono<Boolean> add(@RequestBody final Coffee coffee) {
        return coffeeService.add(coffee);
    }

    @PutMapping("/coffee")
    public Mono<Boolean> put(Coffee coffee) {
        return coffeeService.add(coffee);
    }
    @GetMapping("/coffees")
    public Flux<Coffee> all() {
        return coffeeService.all();
    }
}
