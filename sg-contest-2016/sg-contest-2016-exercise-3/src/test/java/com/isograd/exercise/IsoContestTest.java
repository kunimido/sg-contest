package com.isograd.exercise;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IsoContestTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void run(final int parameter) throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        new IsoContest(getInputStream(parameter), new PrintStream(out)).run();
        assertEquals(getExpected(parameter), out.toString());
    }

    private InputStream getInputStream(final int parameter) {
        return getClass().getClassLoader().getResourceAsStream("input" + parameter + ".txt");
    }

    private String getExpected(final int parameter) throws IOException {
        final InputStream in = getClass().getClassLoader().getResourceAsStream("output" + parameter + ".txt");
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[4096];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        return out.toString();
    }
}