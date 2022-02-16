package com.UI;

import java.util.Arrays;

public class LoginContext extends ConsoleContext{
    @Override
    public ConsoleContext eval() {
        System.out.println("Press 1 if logging in as a Customer.");
        System.out.println("Press 2 if logging in as an Employee.");
        System.out.println("Press 3 if registering as a new Customer.");
        ConsoleContext context = ConsoleContext.parseNextContextFromScanner(Arrays.asList(
                this,
                new CustomerMenuContext(),
                new EmployeeMenuContext(),
                new RegistrationContext()
        ));
        if(context.validate())
            return context;
        return this;
    }
}
