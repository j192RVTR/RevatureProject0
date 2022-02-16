package com.DAO;

public class Employee {
    private int id;
    private String username;
    private String password;
    private String name;

    public Employee(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "----------Employee Details----------" +
                "\nEmployeeNumber=" + id +
                "\nUsername: '" + username + '\'' +
                "\nPassword: '" + "****" + '\'' +
                "\nname: '" + name + '\'';
    }
}
