package io.qubite.tomoko.example.bookstore.patch;

import io.qubite.tomoko.jackson.PatchParser;
import io.qubite.tomoko.patch.Patch;
import io.qubite.tomoko.patcher.Patcher;

public class BookController {

    private final PatchParser parser;
    private final Patcher bookPatcher;

    public BookController(PatchParser parser, Patcher bookPatcher) {
        this.parser = parser;
        this.bookPatcher = bookPatcher;
    }

    public void processPatchRequest(String requestBody) {
        Patch patch = parser.parse(requestBody);
        bookPatcher.execute(patch);
    }

}
