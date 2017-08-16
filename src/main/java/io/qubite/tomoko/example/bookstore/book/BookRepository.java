package io.qubite.tomoko.example.bookstore.book;

public interface BookRepository {

    Book getByISBN(String isbn);

    void add(Book book);

}
