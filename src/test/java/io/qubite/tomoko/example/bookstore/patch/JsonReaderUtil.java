package io.qubite.tomoko.example.bookstore.patch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class JsonReaderUtil {

    public static String fromFile(String resourceName) {
        InputStream inputStream = JsonReaderUtil.class.getResourceAsStream(resourceName);
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }

}
