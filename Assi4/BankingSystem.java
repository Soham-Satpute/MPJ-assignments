import java.io.*;
import java.util.Scanner;

// ============================================================
//  CUSTOM EXCEPTION CLASSES
// ============================================================

// Exception 1: Thrown when opening balance is less than Rs.1000
class MinimumBalanceException extends Exception {
    public MinimumBalanceException(String message) {
        super(message);
    }
}

// Exception 2: Thrown when withdrawal amount exceeds balance
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Exception 3: Thrown when Customer ID is not in range 1–20
class InvalidCustomerIDException extends Exception {
    public InvalidCustomerIDException(String message) {
        super(message);
    }
}

// Exception 4: Thrown when a negative amount is entered
class NegativeAmountException extends Exception {
    public NegativeAmountException(String message) {
        super(message);
    }
}

// ============================================================
//  CUSTOMER CLASS  (stores one customer's data)
// ============================================================
class Customer {
    int    cid;     // Customer ID  (1–20)
    String cname;   // Customer name
    double amount;  // Account balance

    // Constructor
    Customer(int cid, String cname, double amount) {
        this.cid    = cid;
        this.cname  = cname;
        this.amount = amount;
    }
}

// ============================================================
//  MAIN BANKING SYSTEM CLASS
// ============================================================
public class BankingSystem {

    static Scanner sc = new Scanner(System.in);

    // The file where all customer records are saved
    static final String FILE_NAME = "bank_records.txt";

    // ----------------------------------------------------------
    // MENU  –  shows options to the user
    // ----------------------------------------------------------
    static void showMenu() {
        System.out.println("\n========== BANKING SYSTEM MENU ==========");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. View All Accounts");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // ----------------------------------------------------------
    // 1.  CREATE ACCOUNT  –  validates all rules, then writes
    //     the new customer to the file
    // ----------------------------------------------------------
    static void createAccount() {
        System.out.println("\n--- Create New Account ---");
        try {
            System.out.print("Enter Customer ID (1-20): ");
            int cid = Integer.parseInt(sc.nextLine().trim());

            // Rule 3: cid must be 1–20
            if (cid < 1 || cid > 20) {
                throw new InvalidCustomerIDException(
                    "Customer ID must be between 1 and 20. You entered: " + cid);
            }

            System.out.print("Enter Customer Name: ");
            String cname = sc.nextLine().trim();

            System.out.print("Enter Initial Deposit Amount (min Rs.1000): ");
            double amount = Double.parseDouble(sc.nextLine().trim());

            // Rule 4: amount must be positive
            if (amount < 0) {
                throw new NegativeAmountException(
                    "Amount cannot be negative. You entered: " + amount);
            }

            // Rule 1: opening balance must be >= 1000
            if (amount < 1000) {
                throw new MinimumBalanceException(
                    "Minimum opening balance is Rs.1000. You entered: Rs." + amount);
            }

            // All validations passed – save to file
            writeToFile(new Customer(cid, cname, amount));
            System.out.println("Account created successfully for " + cname + "!");

        } catch (MinimumBalanceException | InvalidCustomerIDException |
                 NegativeAmountException e) {
            // Our own custom exceptions
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            // User typed letters instead of a number
            System.out.println("Error: Please enter a valid number.");

        } catch (IOException e) {
            // File could not be written
            System.out.println("File Error: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------
    // 2.  DEPOSIT  –  adds money to an existing account
    // ----------------------------------------------------------
    static void depositMoney() {
        System.out.println("\n--- Deposit Money ---");
        try {
            System.out.print("Enter Customer ID: ");
            int cid = Integer.parseInt(sc.nextLine().trim());

            // Find the customer in the file
            Customer customer = findCustomer(cid);
            if (customer == null) {
                System.out.println("Error: No account found with ID " + cid);
                return;
            }

            System.out.print("Enter Deposit Amount: ");
            double depositAmt = Double.parseDouble(sc.nextLine().trim());

            // Rule 4: must be positive
            if (depositAmt < 0) {
                throw new NegativeAmountException(
                    "Deposit amount cannot be negative.");
            }
            if (depositAmt == 0) {
                throw new NegativeAmountException(
                    "Deposit amount must be greater than zero.");
            }

            // Update balance
            customer.amount += depositAmt;
            updateFile(customer);

            System.out.println("Deposited Rs." + depositAmt +
                " successfully. New Balance: Rs." + customer.amount);

        } catch (NegativeAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------
    // 3.  WITHDRAW  –  removes money if sufficient balance
    // ----------------------------------------------------------
    static void withdrawMoney() {
        System.out.println("\n--- Withdraw Money ---");
        try {
            System.out.print("Enter Customer ID: ");
            int cid = Integer.parseInt(sc.nextLine().trim());

            Customer customer = findCustomer(cid);
            if (customer == null) {
                System.out.println("Error: No account found with ID " + cid);
                return;
            }

            System.out.print("Enter Withdrawal Amount: ");
            double wthAmt = Double.parseDouble(sc.nextLine().trim());

            // Rule 4: must be positive
            if (wthAmt <= 0) {
                throw new NegativeAmountException(
                    "Withdrawal amount must be greater than zero.");
            }

            // Rule 2: cannot withdraw more than balance
            if (wthAmt > customer.amount) {
                throw new InsufficientFundsException(
                    "Insufficient funds! Your balance is Rs." + customer.amount +
                    " but you tried to withdraw Rs." + wthAmt);
            }

            // Update balance
            customer.amount -= wthAmt;
            updateFile(customer);

            System.out.println("Withdrawn Rs." + wthAmt +
                " successfully. Remaining Balance: Rs." + customer.amount);

        } catch (InsufficientFundsException | NegativeAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------
    // 4.  VIEW ALL ACCOUNTS  –  reads and prints the file
    // ----------------------------------------------------------
    static void viewAllAccounts() {
        System.out.println("\n--- All Account Records ---");
        File file = new File(FILE_NAME);

        if (!file.exists() || file.length() == 0) {
            System.out.println("No records found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.printf("%-10s %-20s %-15s%n", "Cust ID", "Name", "Balance");
            System.out.println("------------------------------------------");

            String line;
            while ((line = br.readLine()) != null) {
                // Each line is stored as: cid,cname,amount
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    System.out.printf("%-10s %-20s Rs.%-12s%n",
                        parts[0], parts[1], parts[2]);
                }
            }

        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------
    // HELPER: Write a new customer record to the file
    // ----------------------------------------------------------
    static void writeToFile(Customer c) throws IOException {
        // FileWriter with 'true' means APPEND mode (don't overwrite existing records)
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(c.cid + "," + c.cname + "," + c.amount);
            bw.newLine();  // go to next line for the next record
        }
    }

    // ----------------------------------------------------------
    // HELPER: Find a customer by ID  (reads the file line by line)
    // ----------------------------------------------------------
    static Customer findCustomer(int cid) throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && Integer.parseInt(parts[0]) == cid) {
                    return new Customer(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Double.parseDouble(parts[2])
                    );
                }
            }
        }
        return null;  // not found
    }

    // ----------------------------------------------------------
    // HELPER: Update an existing record in the file
    //         Reads all lines, rewrites them with the updated one
    // ----------------------------------------------------------
    static void updateFile(Customer updated) throws IOException {
        File file   = new File(FILE_NAME);
        File temp   = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == updated.cid) {
                    // Replace this line with the updated data
                    bw.write(updated.cid + "," + updated.cname + "," + updated.amount);
                } else {
                    bw.write(line);   // keep unchanged
                }
                bw.newLine();
            }
        }

        // Replace original file with the updated temp file
        file.delete();
        temp.renameTo(file);
    }

    // ----------------------------------------------------------
    // MAIN METHOD  –  entry point of the program
    // ----------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("Welcome to the Banking System!");
        int choice = 0;

        while (choice != 5) {
            showMenu();

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-5.");
                continue;
            }

            switch (choice) {
                case 1: createAccount();  break;
                case 2: depositMoney();   break;
                case 3: withdrawMoney();  break;
                case 4: viewAllAccounts(); break;
                case 5: System.out.println("Thank you! Goodbye."); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
