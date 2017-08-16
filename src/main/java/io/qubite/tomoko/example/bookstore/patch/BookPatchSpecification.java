package io.qubite.tomoko.example.bookstore.patch;

import io.qubite.tomoko.example.bookstore.book.BookRepository;
import io.qubite.tomoko.example.bookstore.book.Comment;
import io.qubite.tomoko.patch.CommandType;
import io.qubite.tomoko.specification.annotation.*;

@PatcherConfiguration("/books/{bookId}")
public class BookPatchSpecification {

    private static final String BOOK_ID_PARAMETER = "bookId";
    private static final String COMMENT_ID_PARAMETER = "commentId";

    private final BookRepository bookRepository;

    public BookPatchSpecification(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PatcherHandler(action = CommandType.ADD, path = "/title")
    public void updateTitle(@Parameter(BOOK_ID_PARAMETER) String isbn, String newTitle) {
        bookRepository.getByISBN(isbn).setTitle(newTitle);
    }

    @AddHandler("/description")
    public void updateDescription(@Parameter(BOOK_ID_PARAMETER) String isbn, String newDescription) {
        bookRepository.getByISBN(isbn).setDescription(newDescription);
    }

    @AddHandler("/author/firstName")
    public void updateAuthorFirstName(@Parameter(BOOK_ID_PARAMETER) String isbn, String firstName) {
        bookRepository.getByISBN(isbn).getAuthor().setFirstName(firstName);
    }

    @AddHandler("/author/lastName")
    public void updateAuthorLastName(@Parameter(BOOK_ID_PARAMETER) String isbn, String lastName) {
        bookRepository.getByISBN(isbn).getAuthor().setLastName(lastName);
    }

    @AddHandler("/comments/-")
    public void addComment(@Parameter(BOOK_ID_PARAMETER) String isbn, Comment comment) {
        bookRepository.getByISBN(isbn).addComment(comment);
    }

    @AddHandler("/comments/{commentId:[0-9]+}")
    public void updateComment(@Parameter(BOOK_ID_PARAMETER) String isbn, @Parameter(COMMENT_ID_PARAMETER) int commentId, String content) {
        bookRepository.getByISBN(isbn).getComment(commentId).setContent(content);
    }

    @RemoveHandler("/comments/{commentId:[0-9]+}")
    public void removeComment(@Parameter(BOOK_ID_PARAMETER) String isbn, @Parameter(COMMENT_ID_PARAMETER) int commentId) {
        bookRepository.getByISBN(isbn).removeComment(commentId);
    }

}
