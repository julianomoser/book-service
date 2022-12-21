package br.com.moser.controller;

import br.com.moser.model.Book;
import br.com.moser.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id,
                         @PathVariable("currency") String currency) {

        Book book = bookService.findById(id);
        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        return book;
    }
}
