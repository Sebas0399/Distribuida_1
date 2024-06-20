package org.example.books.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.example.books.db.Book;

@ApplicationScoped
@Transactional
public class BooksRepository implements PanacheRepositoryBase<Book,Integer> {
}
