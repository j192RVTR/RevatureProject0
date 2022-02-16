package com.UI;

public class EmployeeMenuContextFactory {
    private static EmployeeMenuContext employeeMenuContext = null;
    private EmployeeMenuContextFactory(){}
    public static EmployeeMenuContext getEmployeeMenuContext(){
        if(employeeMenuContext != null)
            return employeeMenuContext;
        employeeMenuContext = new EmployeeMenuContext();
        return employeeMenuContext;
    }

    public static void setEmployeeMenuContext(EmployeeMenuContext employeeMenuContext) {
        EmployeeMenuContextFactory.employeeMenuContext = employeeMenuContext;
    }
}
