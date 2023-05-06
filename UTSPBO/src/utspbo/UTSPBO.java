/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package utspbo;
import java.time.LocalDateTime;
import java.util.Scanner;
/**
 *
 * @author ASUS
 */
public class UTSPBO {

    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private LocalDateTime registrationDate;

    public UTSPBO(String accountHolderName, double balance) {
        this.accountNumber = generateAccountNumber();
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.registrationDate = LocalDateTime.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit of " + amount + " completed successfully.");
    }

    public void send(double amount, UTSPBO recipient) {
        if (amount > balance) {
            System.out.println("Error: insufficient funds.");
        } else {
            balance -= amount;
            recipient.balance += amount;
            System.out.println("Transfer of " + amount + " to account " + recipient.getAccountNumber() + " completed successfully.");
        }
    }

    private static String generateAccountNumber() {
        int accountNumber;
        do {
            accountNumber = (int) (Math.random() * 900000) + 100000;
        } while (accountNumberExists(Integer.toString(accountNumber)));
        return Integer.toString(accountNumber);
    }

    private static boolean accountNumberExists(String accountNumber) {
        // untuk cek apakah nomor akun sudah ada
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UTSPBO[] accounts = new UTSPBO[10];
        int numAccounts = 0;

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Open a new account");
            System.out.println("2. View account balance");
            System.out.println("3. Deposit funds");
            System.out.println("4. Send funds to another account");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (numAccounts >= accounts.length) {
                        System.out.println("Error: maximum number of accounts reached.");
                    } else {
                        System.out.println("Enter your name:");
                        String name = scanner.next();
                        System.out.println("Enter initial balance:");
                        double balance = scanner.nextDouble();
                        accounts[numAccounts] = new UTSPBO(name, balance);
                        System.out.println("Account opened successfully. ");
                        System.out.println("Your account number is " + accounts[numAccounts].getAccountNumber());
                        System.out.println("Your account name is " + accounts[numAccounts].getAccountHolderName());
                        System.out.println("Your balance is " + accounts[numAccounts].getBalance());
                        System.out.println("Your registration date is " + accounts[numAccounts].getRegistrationDate());
                        System.out.println("\n");

                        numAccounts++;
                    }
                    break;
                case 2:
                    System.out.println("Enter account number:");
                    String accountNumber = scanner.next();
                    UTSPBO account = findAccount(accounts, numAccounts, accountNumber);
                    if (account == null) {
                        System.out.println("Error: account not found.");
                    } else {
                        System.out.println("Balance for account " + accountNumber + ": " + account.getBalance());
                    }
                    break;
                case 3:
                    System.out.println("Enter account number:");
                    accountNumber = scanner.next();
                    account = findAccount(accounts, numAccounts, accountNumber);
                    if (account == null) {
                        System.out.println("Error: account not found.");
                    } else {
                        System.out.println("Enter amount to deposit:");
                        double amount = scanner.nextDouble();
                        account.deposit(amount);
                        System.out.println("New balance for account " + accountNumber + ": " + account.getBalance());
                    }
                    break;
                case 4:
                    System.out.println("Enter sender account number:");
                    String senderAccountNumber = scanner.next();
                    UTSPBO sender = findAccount(accounts, numAccounts, senderAccountNumber);
                    if (sender == null) {
                        System.out.println("Error: account not found.");
                    } else {
                        System.out.println("Enter recipient account number:");
                        String recipientAccountNumber = scanner.next();
                        UTSPBO recipient = findAccount(accounts, numAccounts, recipientAccountNumber);
                        if (recipient == null) {
                            System.out.println("Error: account not found.");
                        } else {
                            System.out.println("Enter amount to send:");
                            double amount = scanner.nextDouble();
                            sender.send(amount, recipient);
                            System.out.println("New balance for account " + senderAccountNumber + ": " + sender.getBalance());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Error: invalid option.");
            }
        }
    }

    private static UTSPBO findAccount(UTSPBO[] accounts, int numAccounts, String accountNumber) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }
  }

