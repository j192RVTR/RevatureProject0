package com.UI;

public class CustomerMenuContextFactory {
    private static CustomerMenuContext customerMenuContext = null;
    private CustomerMenuContextFactory(){}
    public static CustomerMenuContext getCustomerMenuContext(){
        if(customerMenuContext != null)
            return customerMenuContext;
        customerMenuContext = new CustomerMenuContext();
        return customerMenuContext;
    }

    public static void setCustomerMenuContext(CustomerMenuContext customerMenuContext) {
        CustomerMenuContextFactory.customerMenuContext = customerMenuContext;
    }
}
