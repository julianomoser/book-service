package br.com.moser.controller;

import br.com.moser.model.Book;
import br.com.moser.proxy.CambioProxy;
import br.com.moser.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Juliano Moser
 */
@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;
    @Autowired
    private BookService bookService;

    @Autowired
    private CambioProxy cambioProxy;

    @Operation(summary = "Find specific book by your ID")
    @GetMapping("/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id,
                         @PathVariable("currency") String currency) {

        Book book = bookService.findById(id);

        var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port + " FEIGN");
        book.setPrice(cambio.getConvertedValue());
        return book;
    }
}
