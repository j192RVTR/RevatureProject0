package com.UI;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.*;

public class StartContextTest {
    @Test
    public void eval() {
        String first = "1";
        String second = "2";
        String third = "3";
        ConsoleContext.scanner = new Scanner(new ByteArrayInputStream(first.getBytes(StandardCharsets.UTF_8)));
        StartContext startContext = new StartContext();
        assertTrue(startContext.eval() instanceof LoginContext);
        ConsoleContext.scanner = new Scanner(new ByteArrayInputStream(second.getBytes(StandardCharsets.UTF_8)));
        assertTrue(startContext.eval() instanceof RegistrationContext);
        ConsoleContext.scanner = new Scanner(new ByteArrayInputStream(third.getBytes(StandardCharsets.UTF_8)));
        assertTrue(startContext.eval() instanceof StartContext);
    }
}