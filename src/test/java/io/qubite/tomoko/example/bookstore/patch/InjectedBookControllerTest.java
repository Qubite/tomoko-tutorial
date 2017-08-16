package io.qubite.tomoko.example.bookstore.patch;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.qubite.tomoko.example.bookstore.book.Book;
import io.qubite.tomoko.example.bookstore.book.BookRepository;
import io.qubite.tomoko.example.bookstore.book.Comment;
import io.qubite.tomoko.example.bookstore.guice.BookstoreModule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InjectedBookControllerTest {

    private BookController controller;
    private BookRepository repository;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new BookstoreModule());
        controller = injector.getInstance(BookController.class);
        repository = injector.getInstance(BookRepository.class);
    }

    @Test
    public void executeUpdateTitle() throws Exception {
        Book patchedBook = Book.of("978-83-942488-1-9", "Unknown title", "Richie", "Rich");
        patchedBook.addComment(new Comment("Unknown author", "Unknown content"));
        repository.add(patchedBook);
        controller.processPatchRequest(JsonReaderUtil.fromFile("/updateTitle.json"));
        assertEquals("Fight Club", patchedBook.getTitle());
    }
}
