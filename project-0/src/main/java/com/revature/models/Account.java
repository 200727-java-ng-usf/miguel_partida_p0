package com.revature.models;

import java.util.Objects;

public class Account {

    private Integer id;
    private String account_name;
    private double balance;

    public Account() {
    }

    public Account( String account_name, double balance) {
        this.account_name = account_name;
        this.balance = balance;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Double.compare(account.balance, balance) == 0 &&
                Objects.equals(account_name, account.account_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account_name, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account_name='" + account_name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
