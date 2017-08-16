package io.qubite.tomoko.example.bookstore.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.qubite.tomoko.example.bookstore.book.BookRepository;
import io.qubite.tomoko.example.bookstore.book.MemoryBookRepository;
import io.qubite.tomoko.example.bookstore.patch.BookController;
import io.qubite.tomoko.example.bookstore.patch.BookPatchSpecification;
import io.qubite.tomoko.jackson.JacksonTomoko;
import io.qubite.tomoko.jackson.PatchParser;
import io.qubite.tomoko.patcher.Patcher;

import javax.inject.Singleton;

public class BookstoreModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new TomokoModule());
        bind(BookRepository.class).to(MemoryBookRepository.class);
    }

    @Provides
    @Singleton
    Patcher providesBookPatcher(JacksonTomoko tomoko, BookPatchSpecification bookPatchSpecification) {
        return tomoko.scanPatcher(bookPatchSpecification);
    }

    @Provides
    @Singleton
    MemoryBookRepository providesBookRepository() {
        return MemoryBookRepository.instance();
    }

    @Provides
    @Singleton
    BookPatchSpecification providesBookPatchSpecification(BookRepository bookRepository) {
        return new BookPatchSpecification(bookRepository);
    }

    @Provides
    BookController providesBookController(PatchParser patchParser, Patcher bookPatcher) {
        return new BookController(patchParser, bookPatcher);
    }

}
