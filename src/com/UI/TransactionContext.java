package com.UI;

import java.util.Arrays;

public class TransactionContext extends ConsoleContext{
    @Override
    public ConsoleContext eval() {
        System.out.println("Press 1 to make a deposit.");
        System.out.println("Press 2 to make a withdrawal.");
        System.out.println("Press 3 to return to customer main menu.");

        return parseNextContextFromScanner(Arrays.asList(
           this,
           new DepositContext(),
           new WithdrawContext(),
           CustomerMenuContextFactory.getCustomerMenuContext()
        ));
    }
}
