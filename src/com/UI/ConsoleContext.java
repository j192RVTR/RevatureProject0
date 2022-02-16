package com.UI;

import java.util.List;
import java.util.Scanner;

public abstract class ConsoleContext {
    protected boolean flag = true;
    static Scanner scanner;

    static{
        scanner = new Scanner(System.in);
    }


    public ConsoleContext(){
        super();
    }

    public boolean getFlag(){
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static ConsoleContext parseNextContextFromScanner(List<? extends ConsoleContext> rets){
        System.out.println("Press -1 to Exit.");
        int ret;
        ConsoleContext context = rets.get(0);
        if(scanner.hasNextLine()){
            String line = scanner.nextLine();
            try {
                ret = Integer.parseInt(line);
                if (ret == -1){
                    context.setFlag(false);
                    return context;
                }
                context = rets.get(ret);
            }
            catch (NumberFormatException | IndexOutOfBoundsException nfe){
                System.out.println("That wasn't a valid input.");
            }
        }
        return context;
    }

    public ConsoleContext returnToCustomer(){
        System.out.println("Returning to customer main menu.");
        return CustomerMenuContextFactory.getCustomerMenuContext();
    }

    public ConsoleContext returnToEmployee(){
        System.out.println("Returning to employee main menu.");
        return EmployeeMenuContextFactory.getEmployeeMenuContext();
    }

    public abstract ConsoleContext eval();
    public boolean validate(){
        return true;
    }
}
