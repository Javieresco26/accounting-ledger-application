package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;

import static java.lang.String.format;

public class LedgerApp {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        String choice;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts[0].equalsIgnoreCase("date")) {
                    continue;
                }

                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transaction t = new Transaction(LocalDate.parse(date),LocalTime.parse(time), description, vendor, amount);
                transactions.add(t);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
        System.out.println("Loaded: " + transactions.size() + " transactions");

        //HOME SCREEN

            while (true) {

                System.out.println("\n=== HOME SCREEN ===");
                System.out.println("D) Add Deposit");
                System.out.println("P) Make Payment");
                System.out.println("L) Ledger");
                System.out.println("X) Exit");
                System.out.print("Choose an option: ");

                choice = scanner.nextLine().toUpperCase();

                if (choice.equals("X")) {
                    System.out.println("Goodbye!");
                    break;
                }

                if (choice.equals("L")) {
                    runLedgerScreen();
                }
                else if (choice.equals("D")) {
                    addDeposit();
                }

                else if (choice.equals("P")) {
                    makePayment();
                }
                else {
                    System.out.println("Invalid option");
                }
            }
        }
        //// LEDGER SCREEN
    public static void runLedgerScreen(){
        String choice;
        boolean running = true;

        while (running) {
            System.out.println("\n=== LEDGER SCREEN ===");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposit");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");

            choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    for (Transaction t : transactions) {
                        System.out.printf("%s %s | %s | %s | $%.2f%n",
                                t.getDate(),
                                t.getTime(),
                                t.getDescription(),
                                t.getVendor(),
                                t.getAmount());
                    }
                    break;

                case "P":
                    for (Transaction t : transactions) {
                        if (t.getAmount()<0) {
                            System.out.printf("%s %s | %s | %s | $%.2f%n",
                                    t.getDate(),
                                    t.getTime(),
                                    t.getDescription(),
                                    t.getVendor(),
                                    t.getAmount());
                        }
                    }
                    break;
                case "D":
                    for (Transaction t : transactions) {
                        if (t.getAmount()>0) {
                            System.out.printf("%s %s | %s | %s | $%.2f%n",
                                    t.getDate(),
                                    t.getTime(),
                                    t.getDescription(),
                                    t.getVendor(),
                                    t.getAmount());
                        }
                    }
                    break;
                case "R":
                    runReportsScreen();

                case "H":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }

        }
    }

    private static void runReportsScreen() {
        String choice;
        boolean running = true;

        while (running) {
            System.out.println("\n=== Reports SCREEN ===");
            System.out.println("1)  Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4)Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose an option: ");

        }
        choice = scanner.nextLine().toUpperCase();

        switch (choice) {
            case "1":
                runMonthToDateReport();
                break;

            case "2":
                runPreviousMonth();
                break;
            case "3":
                runYearTodate();
                break;
            case "4":
                runPreviousYear();
                break;

            case "5":
                runSearchByVendor();
                break;

            case "0":
                running = false;
                break;

            default:
                System.out.println("Invalid option");
                break;
        }
    }

    private static void runSearchByVendor() {
    }

    private static void runPreviousYear() {
    }

    private static void runYearTodate() {
    }

    private static void runPreviousMonth() {
    }

    private static void runMonthToDateReport() {
    }

    //// DEPOSIT
    public static void addDeposit() {

            LocalDate date = LocalDate.now();

            LocalTime time = LocalTime.now();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            Transaction deposit = new Transaction(date, time, description, vendor, amount);
            transactions.add(deposit);
            saveTransaction(deposit);
            System.out.println("Deposit added.");




    }
    /// payment
    public static void makePayment() {

        System.out.print("Enter date: ");
        String date = scanner.nextLine(); //Fix later. Do excatly as deposit screen

        System.out.print("Enter time: ");
        String time = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -amount;
        Transaction payment = new Transaction(LocalDate.parse(date), LocalTime.parse(time), description, vendor, amount);
        transactions.add(payment);
        saveTransaction(payment);
        System.out.println("Payment added.");

    }
    /// save transactions / add to csv file
    public static void saveTransaction(Transaction t) {
        DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            FileWriter writer = new FileWriter("transactions.csv", true);

            writer.write(
                    format("%s|%s|%s|%s|%.2f\n",t.getDate(),
                            t.getTime().format(timeFormater),
                            t.getDescription(),
                            t.getVendor(),
                            t.getAmount())
            );

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving transaction");
        }
    }


}

