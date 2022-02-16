package com.DAO;

import java.sql.Timestamp;

public class Transfer {
    private int id;
    private int from_id;
    private int to_id;
    public enum Status {
        OPEN, ACCEPTED, REJECTED, SUCCESS, FAIL
    }
    private Status status;
    private double amount;
    private Timestamp trans_date;

    public Transfer(int id, int from_id, int to_id, Status status, double amount, Timestamp trans_date) {
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.status = status;
        this.amount = amount;
        this.trans_date = trans_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "----------Transfer Details----------" +
                "\nTransferNumber: " + id +
                "\nSource AccountNumber: " + from_id +
                "\nDestination AccountNumber: " + to_id +
                "\nStatus: " + status +
                "\nAmount: " + amount +
                "\nDate: " + trans_date;
    }
}
