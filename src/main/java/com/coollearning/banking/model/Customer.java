package com.coollearning.banking.model;

public class Customer {
    private String accountNum;
    private String password;
    private Double balance;

    public Customer(String accountNum, String password) {
        this.accountNum = accountNum;
        this.password = password;
        this.balance = (double) 0;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "accountNum='" + accountNum + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void addBalance(double amountToDeposit) {
        this.balance += amountToDeposit;
    }

    public boolean removeBalance(double amountToWithdraw) {
        if (amountToWithdraw > this.balance) {
            return false;
        } else {
            this.balance -= amountToWithdraw;
            return true;
        }
    }
}
