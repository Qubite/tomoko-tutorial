package io.qubite.tomoko.example.bookstore.book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {

    private String isbn;
    private String title;
    private Author author;
    private String description;
    private List<Comment> comments;

    Book(String isbn, String title, Author author, String description, List<Comment> comments) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
        this.comments = comments;
    }

    public static Book of(String isbn, String title, String authorFirstName, String authorLastName) {
        return new Book(isbn, title, new Author(authorFirstName, authorLastName), "", new ArrayList<Comment>());
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(int index) {
        comments.remove(index);
    }

    public Comment getComment(int commentId) {
        return comments.get(commentId);
    }

}
