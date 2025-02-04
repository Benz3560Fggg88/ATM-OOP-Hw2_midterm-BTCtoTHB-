package ATM_Lab2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        // Step 1: Setup Manager
        if (atm.getManager() == null) {
            System.out.println("No manager found. Please create a manager account.");

            String idCard, fullName, gender, loginID, password;

            while (true) {
                System.out.print("Enter Manager ID Card (13 digits): ");
                idCard = scanner.nextLine();
                if (idCard.matches("\\d{13}")) break;
                System.out.println("Invalid ID Card. Please enter exactly 13 digits.");
            }

            System.out.print("Enter Manager Full Name: ");
            fullName = scanner.nextLine();

            while (true) {
                System.out.print("Enter Manager Gender (male/female): ");
                gender = scanner.nextLine().toLowerCase();
                if (gender.equals("male") || gender.equals("female")) break;
                System.out.println("Invalid gender. Please enter 'male' or 'female'.");
            }

            while (true) {
                System.out.print("Enter Manager Login ID (13 digits): ");
                loginID = scanner.nextLine();
                if (loginID.matches("\\d{13}")) break;
                System.out.println("Invalid Login ID. Please enter exactly 13 digits.");
            }

            System.out.print("Enter Manager Password: ");
            password = scanner.nextLine();

            Manager manager = new Manager(idCard, fullName, gender, loginID, password);
            atm.setManager(manager);
            System.out.println("Manager account created successfully!");
        }

        // Step 2: Manager Login
        while (true) {
            System.out.println("\nManager Login");
            System.out.print("Enter Login ID: ");
            String loginID = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (atm.getManager().getLoginID().equals(loginID) &&
                atm.getManager().authenticate(password)) {
                System.out.println("Manager login successful!");
                break;
            } else {
                System.out.println("Invalid Login ID or Password. Please try again.");
            }
        }

        // Step 3: Set BTC Rate                            /////////////////////////////////////////////////
        System.out.println("\nPlease enter BTC rate (1 BTC to THB): ");
        double btcRate = Double.parseDouble(scanner.nextLine());
        atm.setBtcRate(btcRate);
        System.out.println("BTC rate set successfully!");

        // Step 4: Add Accounts
        System.out.print("\nEnter the number of accounts to create (max 5): ");
        int numAccounts = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numAccounts; i++) {
            System.out.println("Enter details for account " + (i + 1));

            String idCard, fullName, gender, loginID, password;

            while (true) {
                System.out.print("ID Card (13 digits): ");
                idCard = scanner.nextLine();
                if (idCard.matches("\\d{13}")) break;
                System.out.println("Invalid ID Card. Please enter exactly 13 digits.");
            }

            System.out.print("Full Name: ");
            fullName = scanner.nextLine();

            while (true) {
                System.out.print("Gender (male/female): ");
                gender = scanner.nextLine().toLowerCase();
                if (gender.equals("male") || gender.equals("female")) break;
                System.out.println("Invalid gender. Please enter 'male' or 'female'.");
            }

            while (true) {
                System.out.print("Login ID (13 digits): ");
                loginID = scanner.nextLine();
                if (loginID.matches("\\d{13}")) break;
                System.out.println("Invalid Login ID. Please enter exactly 13 digits.");
            }

            System.out.print("Password: ");
            password = scanner.nextLine();
            System.out.print("Initial Balance (THB): ");
            double balance = Double.parseDouble(scanner.nextLine());

            atm.accounts.add(new Account(idCard, fullName, gender, loginID, password, balance));
        }

        // Step 5: After Account Creation
        while (true) {
            System.out.println("\nWhat do you want to do next?");
            System.out.println("1. Go to ATM ComputerThanyaburi Bank");
            System.out.println("2. Exit the ATM system");
            System.out.print("Choose an option: ");
            int managerChoice = Integer.parseInt(scanner.nextLine());

            if (managerChoice == 1) {
                break; // ไปหน้า ATM
            } else if (managerChoice == 2) {
                System.out.println("ATM system shutting down...");
                System.exit(0);
            } else {
                System.out.println("Invalid option. Please enter 1 or 2.");
            }
        }

        // Main Menu Loop
        while (true) {
            System.out.println("\nATM ComputerThanyaburi Bank");
            System.out.print("Enter Account Login ID: ");
            String loginID = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            Account currentAccount = null;
            for (Account acc : atm.accounts) {
                if (acc.getLoginID().equals(loginID) && acc.authenticate(password)) {
                    currentAccount = acc;
                    break;
                }
            }

            if (currentAccount == null) {
                System.out.println("Invalid Login ID or Password.");
                continue;
            }

            while (true) {
                System.out.println("\nMenu Service");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());

                if (option == 1) {
                    // **Check Balance**
                    System.out.println("\nCheck Balance");
                    System.out.println("1. Balance in THB");
                    System.out.println("2. Balance in BTC");
                    System.out.print("Choose an option: ");
                    int balanceOption = Integer.parseInt(scanner.nextLine());

                    if (balanceOption == 1) {
                        System.out.println("Balance in THB: " + currentAccount.getBalance());
                    } else if (balanceOption == 2) {            ///////////////////////////////////////////////////////////////
                        double btcBalance = currentAccount.getBalance() / atm.getBtcRate();
                        double remainingTHB = currentAccount.getBalance() % atm.getBtcRate();
                        System.out.println("Balance in BTC: " + btcBalance);
                        System.out.println("Remaining in THB: " + remainingTHB);
                    } else {
                        System.out.println("Invalid option. Please enter 1 or 2.");
                    }

                } else if (option == 2) { ///////////////////////////////////////////////////////////////
                    // **Withdraw**
                    System.out.println("\nWithdraw");
                    System.out.println("1. Withdraw in THB");
                    System.out.println("2. Withdraw in BTC");
                    System.out.print("Choose an option: ");
                    int withdrawOption = Integer.parseInt(scanner.nextLine());

                    if (withdrawOption == 1) {
                        System.out.print("Enter amount to withdraw (THB): ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        if (currentAccount.withdraw(amount)) {
                            System.out.println("Successfully withdrawn " + amount + " THB.");
                        } else {
                            System.out.println("Insufficient balance.");
                        }
                    } else if (withdrawOption == 2) {
                        System.out.print("Enter amount to withdraw (BTC): ");
                        double btcAmount = Double.parseDouble(scanner.nextLine());
                        double thbEquivalent = btcAmount * atm.getBtcRate();
                        if (currentAccount.withdraw(thbEquivalent)) {
                            System.out.println("Successfully withdrawn " + btcAmount + " BTC.");
                        } else {
                            System.out.println("Insufficient balance.");
                        }
                    } else {
                        System.out.println("Invalid option. Please enter 1 or 2.");
                    }

                } else if (option == 3) {
                    // **Deposit**
                    System.out.println("\nDeposit");
                    System.out.println("1. Deposit in THB");
                    System.out.println("2. Deposit in BTC");
                    System.out.print("Choose an option: ");
                    int depositOption = Integer.parseInt(scanner.nextLine());

                    if (depositOption == 1) {
                        System.out.print("Enter amount to deposit (THB): ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        currentAccount.deposit(amount);
                        System.out.println("Successfully deposited " + amount + " THB.");
                    } else if (depositOption == 2) {
                        System.out.print("Enter amount to deposit (BTC): ");
                        double btcAmount = Double.parseDouble(scanner.nextLine());
                        double thbEquivalent = btcAmount * atm.getBtcRate();
                        currentAccount.deposit(thbEquivalent);
                        System.out.println("Successfully deposited " + btcAmount + " BTC.");
                    } else {
                        System.out.println("Invalid option. Please enter 1 or 2.");
                    }

                } else if (option == 4) {
                    // **Transfer**
                    System.out.print("Enter target account Login ID: ");
                    String targetLoginID = scanner.nextLine();
                    Account targetAccount = null;
                    for (Account acc : atm.accounts) {
                        if (acc.getLoginID().equals(targetLoginID)) {
                            targetAccount = acc;
                            break;
                        }
                    }

                    if (targetAccount != null) {
                        System.out.print("Enter amount to transfer (THB): ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        if (currentAccount.transfer(targetAccount, amount)) {
                            System.out.println("Successfully transferred " + amount + " THB to " + targetAccount.getFullName());
                        } else {
                            System.out.println("Insufficient balance.");
                        }
                    } else {
                        System.out.println("Target account not found.");
                    }

                } else if (option == 5) {
                    System.out.println("Exiting to the main menu...");
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            }

            // **เพิ่มส่วนนี้เพื่อวนกลับไปถามว่าต้องการทำอะไรต่อ**
            while (true) {
                System.out.println("\nWhat do you want to do next?");
                System.out.println("1. Go to ATM ComputerThanyaburi Bank");
                System.out.println("2. Exit the ATM system");
                System.out.print("Choose an option: ");
                int accountChoice = Integer.parseInt(scanner.nextLine());

                if (accountChoice == 1) {
                    break; // กลับไปหน้า ATM
                } else if (accountChoice == 2) {
                    System.out.println("ATM system shutting down...");
                    System.exit(0);
                } else {
                    System.out.println("Invalid option. Please enter 1 or 2.");
                }
            }
        }
    }
}
