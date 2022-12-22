package br.com.moser.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Juliano Moser
 */
@Tag(name = "Foo bar")
@Slf4j
@RestController
@RequestMapping("book-service")
public class FooBarController {


    @GetMapping("/foo-bar")
    @Operation(summary = "Foo bar")
//    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
//    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String fooBar() {
        log.info("Request to Foo-Bar is Received!");
//        var response = new RestTemplate()
//                .getForEntity("http://localhost:8080/foo-bar", String.class);
//        return response.getBody();
        return "Foo-Bar!!";
    }

    public String fallbackMethod(Exception ex) {
        return "fallbackMethod foo-bar!!";
    }
}
