package io.qubite.tomoko.example.bookstore.patch;

import io.qubite.tomoko.jackson.JacksonTomoko;
import io.qubite.tomoko.patcher.Patcher;
import io.qubite.tomoko.specification.dsl.HandlerConfigurationDSL;
import io.qubite.tomoko.specification.dsl.PathDSL;

public class PatcherFactory {

    private static final String BOOK_ID_PARAMETER = "bookId";
    private static final String COMMENT_ID_PARAMETER = "commentId";

    /**
     * Alternative way of registering handlers.
     *
     * @param tomoko
     * @param specification
     * @return
     */
    public Patcher createPatcher(JacksonTomoko tomoko, BookPatchSpecification specification) {
        HandlerConfigurationDSL dsl = tomoko.specificationDsl();
        PathDSL commonPath = dsl.path("/books/{" + BOOK_ID_PARAMETER + "}");
        commonPath.path("/title").handleAdd(specification::updateTitle).firstArgument(BOOK_ID_PARAMETER).register();
        commonPath.path("/description").handleAdd(specification::updateDescription).firstArgument(BOOK_ID_PARAMETER).register();
        commonPath.path("/author/firstName").handleAdd(specification::updateAuthorFirstName).firstArgument(BOOK_ID_PARAMETER).register();
        commonPath.path("/author/lastName").handleAdd(specification::updateAuthorLastName).firstArgument(BOOK_ID_PARAMETER).register();
        commonPath.path("/comments/-").handleAdd(specification::addComment).firstArgument(BOOK_ID_PARAMETER).register();
        commonPath.path("/comments/{commentId:[0-9]+}").handleAdd(specification::updateComment).firstArgument(BOOK_ID_PARAMETER)
                .secondArgument(COMMENT_ID_PARAMETER).register();
        commonPath.path("/comments/{commentId:[0-9]+}").handleRemove(specification::removeComment).firstArgument(BOOK_ID_PARAMETER)
                .secondArgument(COMMENT_ID_PARAMETER).register();
        return dsl.toPatcher();
    }

}