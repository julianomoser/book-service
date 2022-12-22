package br.com.moser.controller;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Juliano Moser
 */
@Slf4j
@RestController
@RequestMapping("book-service")
public class FooBarController {


    @GetMapping("/foo-bar")
    @Retry(name = "foo-bar")
    public String fooBar() {
        log.info("Request to Foo-Bar is Received!");
        var response = new RestTemplate()
                .getForEntity("http://localhost:8080/foo-bar", String.class);
        return response.getBody();
    }
}
