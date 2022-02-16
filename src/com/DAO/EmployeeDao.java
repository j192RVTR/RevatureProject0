package com.DAO;

public interface EmployeeDao {
    void addEmployee(Employee e);
    void updateEmployee(Employee e);
    void deleteEmployee(int id);
    Employee getEmployeeByUsernameAndPassword(String username, String password);
}
