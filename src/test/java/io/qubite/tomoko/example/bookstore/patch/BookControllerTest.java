package io.qubite.tomoko.example.bookstore.patch;

import io.qubite.tomoko.example.bookstore.book.Book;
import io.qubite.tomoko.example.bookstore.book.BookRepository;
import io.qubite.tomoko.example.bookstore.book.MemoryBookRepository;
import io.qubite.tomoko.example.bookstore.book.Comment;
import io.qubite.tomoko.jackson.JacksonTomoko;
import io.qubite.tomoko.patcher.Patcher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookControllerTest {

    @Test
    public void executeUpdateTitle() throws Exception {
        BookRepository repository = MemoryBookRepository.instance();
        BookController bookController = prepareControllerWithScannedPatcher(repository);
        Book patchedBook = Book.of("978-83-942488-1-9", "Unknown title", "Richie", "Rich");
        patchedBook.addComment(new Comment("Unknown author", "Unknown content"));
        repository.add(patchedBook);
        bookController.processPatchRequest(JsonReaderUtil.fromFile("/updateTitle.json"));
        assertEquals("Fight Club", patchedBook.getTitle());
    }

    @Test
    public void executeUpdateTitleAndDescriptionSeparate() throws Exception {
        BookRepository repository = MemoryBookRepository.instance();
        BookController bookController = prepareControllerWithScannedPatcher(repository);
        Book patchedBook = Book.of("978-83-942488-1-9", "Unknown title", "Richie", "Rich");
        patchedBook.addComment(new Comment("Unknown author", "Unknown content"));
        repository.add(patchedBook);
        bookController.processPatchRequest(JsonReaderUtil.fromFile("/updateTitleAndDescriptionSeparate.json"));
        assertEquals("Fight Club", patchedBook.getTitle());
        assertEquals("Book about fighting in a club.", patchedBook.getDescription());
    }

    @Test
    public void executeUpdateTitleAndDescriptionCombined() throws Exception {
        BookRepository repository = MemoryBookRepository.instance();
        BookController bookController = prepareControllerWithScannedPatcher(repository);
        Book patchedBook = Book.of("978-83-942488-1-9", "Unknown title", "Richie", "Rich");
        patchedBook.addComment(new Comment("Unknown author", "Unknown content"));
        repository.add(patchedBook);
        bookController.processPatchRequest(JsonReaderUtil.fromFile("/updateTitleAndDescriptionCombined.json"));
        assertEquals("Fight Club", patchedBook.getTitle());
        assertEquals("Book about fighting in a club.", patchedBook.getDescription());
    }

    @Test
    public void executeAddComment() throws Exception {
        BookRepository repository = MemoryBookRepository.instance();
        BookController bookController = prepareControllerWithScannedPatcher(repository);
        Book patchedBook = Book.of("978-83-942488-1-9", "Unknown title", "Richie", "Rich");
        patchedBook.addComment(new Comment("Unknown author", "Unknown content"));
        repository.add(patchedBook);
        bookController.processPatchRequest(JsonReaderUtil.fromFile("/addComment.json"));
        assertEquals(2, patchedBook.getComments().size());
        assertEquals("John Doe", patchedBook.getComments().get(1).getAuthor());
    }

    @Test
    public void executeRemoveComment() throws Exception {
        BookRepository repository = MemoryBookRepository.instance();
        BookController bookController = prepareControllerWithScannedPatcher(repository);
        Book patchedBook = Book.of("978-83-942488-1-9", "Unknown title", "Richie", "Rich");
        patchedBook.addComment(new Comment("Unknown author", "Unknown content"));
        repository.add(patchedBook);
        bookController.processPatchRequest(JsonReaderUtil.fromFile("/removeComment.json"));
        assertEquals(0, patchedBook.getComments().size());
    }

    private BookController prepareControllerWithScannedPatcher(BookRepository bookRepository) {
        JacksonTomoko tomoko = JacksonTomoko.instance();
        BookPatchSpecification bookPatchSpecification = new BookPatchSpecification(bookRepository);
        Patcher patcher = tomoko.scanPatcher(bookPatchSpecification);
        return new BookController(tomoko.patchParser(), patcher);
    }

}
