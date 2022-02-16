package com.DAO;

import java.sql.Timestamp;

public class Account {
    private int id;
    private int cust_id;
    private double amount;
    private int approval_id;
    public enum Status {
        PENDING, APPROVED, DENIED, CLOSED
    }
    private Status status;
    Timestamp approval_date;

    public Account(int id, int cust_id, double amount, int approval_id, Status status, Timestamp approval_date) {
        this.id = id;
        this.cust_id = cust_id;
        this.amount = amount;
        this.approval_id = approval_id;
        this.status = status;
        this.approval_date = approval_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getApproval_id() {
        return approval_id;
    }

    public void setApproval_id(int approval_id) {
        this.approval_id = approval_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(Timestamp approval_date) {
        this.approval_date = approval_date;
    }

    @Override
    public String toString() {
        return "----------Account Details----------" +
                "\nAccountNumber: " + id +
                "\nCustomerID: " + cust_id +
                "\nBalance: " + amount +
                "\nStatus: " + status;
    }
}
