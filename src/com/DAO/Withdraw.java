package com.DAO;

import java.sql.Timestamp;

public class Withdraw {
    private int id;
    private int account_id;
    private double amount;
    private Timestamp trans_date;
    public enum Status {PENDING, SUCCESS, FAIL}
    private Status status;

    public Withdraw(int id, int account_id, double amount, Timestamp trans_date, Status status) {
        this.id = id;
        this.account_id = account_id;
        this.amount = amount;
        this.trans_date = trans_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTrans_date() {
        return trans_date;
    }

    public void setTrans_date(Timestamp trans_date) {
        this.trans_date = trans_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "----------Withdraw Details----------" +
                "\nWithdrawNumber: " + id +
                "\nAccountNumber: " + account_id +
                "\nAmount: " + amount +
                "\nDate: " + trans_date +
                "\nStatus: " + status;
    }
}
