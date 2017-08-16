package io.qubite.tomoko.example.bookstore.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.qubite.tomoko.jackson.JacksonTomoko;
import io.qubite.tomoko.jackson.PatchParser;

import javax.inject.Singleton;

public class TomokoModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    JacksonTomoko providesTomoko() {
        return JacksonTomoko.instance();
    }

    @Provides
    @Singleton
    PatchParser providesPatchParser(JacksonTomoko tomoko) {
        return tomoko.patchParser();
    }

}
