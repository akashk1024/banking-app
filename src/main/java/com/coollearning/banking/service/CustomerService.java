package com.coollearning.banking.service;

import com.coollearning.banking.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private Map<String, Customer> customerList = new HashMap<>();

    public CustomerService() {
        customerList.put("Test", new Customer("Test", "0"));
        customerList.put("JSFB12345678", new Customer("JSFB12345678", "00000000"));
        customerList.put("JSFB12345679", new Customer("JSFB12345679", "00000009"));
        customerList.put("JSFB12345680", new Customer("JSFB12345680", "00000080"));
    }

    public boolean validatePassword(String accountNum, String enteredPassword) {
        return enteredPassword.equals(customerList.get(accountNum).getPassword());
    }

    public void deposit(String accountNum, double amountToDeposit) {
        Customer customer = customerList.get(accountNum);
        if (customer == null) {
            System.out.println("Mentioned customer is not registered");
        } else {
            customer.addBalance(amountToDeposit);
        }
    }

    public boolean withdraw(String accountNum, double amountToWithdraw) {
        Customer customer = customerList.get(accountNum);
        return customer.removeBalance(amountToWithdraw);
    }

    public double checkBalance(String accountNum) {
        Customer customer = customerList.get(accountNum);
        return customer.getBalance();
    }

    public void transfer(String accountNum, String accountToTransfer, double amountToTransfer) {
        Customer customer = customerList.get(accountToTransfer);
        if (customer == null) {
            System.out.println("Mentioned customer is not registered");
        } else {
            customer.addBalance(amountToTransfer);
            withdraw(accountNum, amountToTransfer);
        }
    }
}
