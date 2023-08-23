package com.example.userservice.configurations;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.core.IntervalFunction;

@Configuration
public class CircuitBreakerConfiguration {
    @Bean
    public CircuitBreaker circuitBreaker(){
      return CircuitBreaker.of("config-cb", this.buildConfig1());
    }

    @Bean
    public CircuitBreaker carsCircuitBreaker() {
        return CircuitBreaker.of("carsCB", buildConfig1());
    }
    
    @Bean
    public CircuitBreaker motosCircuitBreaker() {
        return CircuitBreaker.of("motosCB", buildConfig2());
    }
    
    @Bean
    public CircuitBreaker todosCircuitBreaker() {
        return CircuitBreaker.of("todosCB", buildConfig3());
    }
    
    CircuitBreakerConfig buildConfig1(){
          return CircuitBreakerConfig.custom()//retornamos luego de crar una configuracion customizada
            .slidingWindowType(SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(4)//default
            .failureRateThreshold(50f)//si el 50% de 4 peticiones son fallidas el circuitbreaker pasara a estado abierto
            .waitDurationInOpenState(Duration.ofSeconds(5))//tiempo de espera para ir al estado medio habierto
            .permittedNumberOfCallsInHalfOpenState(2) // numero permitido de llamadas en el estado medio abierto
            .writableStackTraceEnabled(false)// no va a emitir demasiados log cuando el cb tiene el control
            .build();
    }
    CircuitBreakerConfig buildConfig2(){
          return CircuitBreakerConfig.custom()
            .slidingWindowType(SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(4)//default
            .failureRateThreshold(50f)
            .permittedNumberOfCallsInHalfOpenState(2)
            .waitIntervalFunctionInOpenState(
                IntervalFunction.ofExponentialBackoff(Duration.ofSeconds(2), 3, Duration.ofSeconds(100))
            )
            .automaticTransitionFromOpenToHalfOpenEnabled(true)
            .writableStackTraceEnabled(true)
            .build();

    }
    CircuitBreakerConfig buildConfig3(){
          return CircuitBreakerConfig.custom()
            .slidingWindowType(SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(4)//default
            .failureRateThreshold(50f)
            .waitDurationInOpenState(Duration.ofSeconds(10))//no menos de 5 default 60 tiempo ene stado medio abierto
            .permittedNumberOfCallsInHalfOpenState(3) //llamadas permitidas en estado medio abierto
            .slowCallRateThreshold(20f)// si el 20% de las llamadas sobre pasan los 2 segundos se llama al circuit
            .slowCallDurationThreshold(Duration.ofSeconds(2)) // 
            .writableStackTraceEnabled(false)
            .build();

    }
}
 