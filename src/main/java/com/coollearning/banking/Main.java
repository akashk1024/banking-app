package com.coollearning.banking;

import com.coollearning.banking.service.CustomerService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        CustomerService customerService = new CustomerService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the login page");
        System.out.println("Enter the bank Account no.");
        String accountNum = scanner.nextLine();
        System.out.println("Enter the password for the corresponding account no.");
        String password = scanner.nextLine();
        boolean isPasswordCorrect = customerService.validatePassword(accountNum, password);
        File file = new File("transactions.txr");
        if (!isPasswordCorrect) {
            System.out.println("Invalid Credentials");
        } else {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            System.out.println("!!!!! Welcome to JSF Bank !!!! ");
            showAllOptions();
            int operationToPerform = scanner.nextInt();
            while (operationToPerform != 0) {
                switch (operationToPerform) {
                    case 1:
                        System.out.println("Enter the amount you want to deposit");
                        double amountToDeposit = scanner.nextDouble();
                        customerService.deposit(accountNum, amountToDeposit);
                        System.out.println("Amount " + amountToDeposit + " deposited successfully");
                        bufferedWriter.append("Amount ").append(String.valueOf(amountToDeposit)).append(" deposited successfully");
                        showAllOptions();
                        operationToPerform = scanner.nextInt();
                        break;
                    case 2:
                        System.out.println("Enter the amount you want to withdraw");
                        double amountToWithdraw = scanner.nextDouble();
                        boolean success = customerService.withdraw(accountNum, amountToWithdraw);
                        if (success) {
                            System.out.println("Amount " + amountToWithdraw + " withdrawl successfully");
                            bufferedWriter.append("Amount ").append(String.valueOf(amountToWithdraw)).append(" withdrawl successfully");
                        } else {
                            System.out.println("FAILED!!! Deposit some money.");
                        }
                        showAllOptions();
                        operationToPerform = scanner.nextInt();
                        break;
                    case 3:
                        System.out.println("Enter the OTP");
                        Random random = new Random();
                        int otp = 999 + random.nextInt(9000);
                        System.out.println(otp);
                        int otpEntered = scanner.nextInt();
                        if (otp != otpEntered) {
                            System.out.println("Invalid OTP");
                        } else {
                            System.out.println("Enter the amount and bank no. to which you want to transfer");
                            System.out.print("Account no: ");
                            String accountToTransfer = scanner.next();
                            System.out.print("Amount: ");
                            double amountToTransfer = scanner.nextDouble();
                            customerService.transfer(accountNum, accountToTransfer, amountToTransfer);
                            System.out.println("Amount " + amountToTransfer + " transferred successfully to " + accountToTransfer);
                            bufferedWriter.append("Amount ").append(String.valueOf(amountToTransfer)).append(" transferred successfully to ").append(accountToTransfer);
                            showAllOptions();
                            operationToPerform = scanner.nextInt();
                            break;
                        }
                    case 4:
                        System.out.println("Dear Customer, your current account balance is " + customerService.checkBalance(accountNum));
                        showAllOptions();
                        operationToPerform = scanner.nextInt();
                        break;
                    default:
                        showAllOptions();
                        break;
                }
            }
            System.out.println("Exited Successfully.\nThank you _/\\_. Come Again");


        }
    }
    private static void showAllOptions() {
        System.out.println("-------------------------------");
        System.out.println("Enter the operation you want to perform");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawl");
        System.out.println("3. Tranfer\n4. Check Account Balance\n0. Logout");
        System.out.println("-------------------------------");
    }
}
