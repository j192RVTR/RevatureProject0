package com.UI;

import com.DAO.*;

import java.util.Arrays;

public class EmployeeMenuContext extends ConsoleContext{
    Employee employee;

    @Override
    public ConsoleContext eval() {
        System.out.println("You are in the Employee main menu.");
        System.out.println("Press 1 to approve or reject an account.");
        System.out.println("Press 2 to view customer account balances.");
        System.out.println("Press 3 to view a log of all transactions.");

        return ConsoleContext.parseNextContextFromScanner(Arrays.asList(
                this,
                new ApplicationDecisionContext(),
                new CustomerSelectionContext(),
                new TransactionListContext()
        ));
    }

    @Override
    public boolean validate() {
        EmployeeDao dao = EmployeeDaoFactory.getEmployeeDao();
        System.out.println("Enter username.");
        String username = scanner.nextLine();
        System.out.println("Enter password.");
        String password = scanner.nextLine();
        Employee employee= dao.getEmployeeByUsernameAndPassword(username, password);
        if(employee == null){
            return false;
        }
        this.employee = employee;
        System.out.println("Welcome " + employee.getName() + "!");
        return true;
    }
}
