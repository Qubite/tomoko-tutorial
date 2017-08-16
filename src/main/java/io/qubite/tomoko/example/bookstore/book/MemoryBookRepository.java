package io.qubite.tomoko.example.bookstore.book;

import java.util.ArrayList;
import java.util.List;

public class MemoryBookRepository implements BookRepository {

    private final List<Book> books;

    public MemoryBookRepository(List<Book> books) {
        this.books = books;
    }

    public static MemoryBookRepository instance() {
        return new MemoryBookRepository(new ArrayList<Book>());
    }

    public void add(Book book) {
        books.add(book);
    }

    public Book getByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        throw new IllegalArgumentException("Could not find a book corresponding to the provided ISBN.");
    }

}
