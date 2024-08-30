package example.reactive.redis.config;

import example.reactive.redis.repository.model.Coffee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {



    public <T> ReactiveRedisOperations<String, T> redisOperations(ReactiveRedisConnectionFactory factory, Class<T> type) {
        // JSON serializer for values of type T
        Jackson2JsonRedisSerializer<T> serializer = new Jackson2JsonRedisSerializer<>(type);

        // Serialization context builder
        RedisSerializationContext.RedisSerializationContextBuilder<String, T> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        // Setting the value serializer to use JSON
        RedisSerializationContext<String, T> context = builder.value(serializer).build();

        // Create the ReactiveRedisTemplate with the custom serialization context
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveRedisOperations<String, Coffee> coffeeRedisSetup(ReactiveRedisConnectionFactory factory) {
        return redisOperations(factory, Coffee.class);
    }

}
