package com.UI;

import java.util.Arrays;

public class StartContext extends ConsoleContext{

    @Override
    public ConsoleContext eval() {
        System.out.println("Welcome to J Bank!");
        System.out.println("Press 1 if logging in as a Customer or Employee.");
        System.out.println("Press 2 if registering as a new Customer.");
        return parseNextContextFromScanner(Arrays.asList(this, new LoginContext(), new RegistrationContext()));
    }
}
