package br.com.moser.repository;

import br.com.moser.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Juliano Moser
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
