package banking;

import java.util.Scanner;

public class BankSystemProcess {
    public void menu (DataBase connect) {
        BankSystem account1 = new BankSystem();
        while (true) {
            System.out.print("1. Create an account\n2. Log into account\n0. Exit\n");
            int input = Integer.parseInt(input());
            switch (input) {
                case 1:
                    account1.createAccount();
                    System.out.print("Your card number: \n" + account1.getCard() + "\n");
                    System.out.print("Your card PIN: \n" + account1.getPin() + "\n");
                    connect.insertAccount(account1.getCard(), account1.getPin(), account1.getBalance());
                    break;
                case 2:
                    System.out.print("Enter your card number: \n");
                    long card = Long.parseLong(input());
                    System.out.print("Enter your PIN: \n");
                    int pin = Integer.parseInt(input());
                    if (connect.login(card, pin)) {
                        System.out.print("You have successfully logged in!\n");
                        logged(connect, card);
                    } else {
                        System.out.print("Wrong card number or PIN!\n");
                    }
                    break;
                case 3:
                    connect.selectAll();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
            if (input == 0) {
                break;
            }
        }
    }

    public void logged (DataBase connect, long account) {
        while (true) {
            System.out.print("1. Balance\n2. Add income\n" +
                    "3. Do transfer\n" +
                    "4. Close account\n" +
                    "5. Log out\n" +
                    "0. Exit\n");
            int input = Integer.parseInt(input());
            switch (input) {
                case 1:
                    System.out.printf("Balance: %d%n", connect.viewBalance(account));
                    break;
                case 2:
                    System.out.print("Enter income:\n");
                    int income = Integer.parseInt(input());
                    int newBalance = income + connect.viewBalance(account);
                    connect.addIncome(account, newBalance);
                    System.out.print("Income was added!\n");
                    break;
                case 3:
                    System.out.print("Transfer\nEnter card number:\n");
                    long toCard = Long.parseLong(input());
                    if ((BankSystem.checkCardLuhn(toCard) || toCard == 2222222222222222L) && toCard != account && connect.cardExist(toCard)) {
                        System.out.print("Enter how much money you want to transfer:\n");
                        int transferSum = Integer.parseInt(input());
                        if (transferSum < connect.viewBalance(account)) {
                            connect.makeTransfer(account, toCard, transferSum);
                            System.out.print("Success!\n");
                        } else {
                            System.out.print("Not enough money!\n");
                        }
                    } else if (toCard == account) {
                        System.out.print("You can't transfer money to the same account!\n");
                    } else if (!BankSystem.checkCardLuhn(toCard)) {
                        System.out.print("Probably you made a mistake in the card number. Please try again!\n");
                    } else if (!connect.cardExist(toCard)) {
                        System.out.print("Such a card does not exist.\n");
                    } else {
                        System.out.print("Probably you made a mistake in the card number. Please try again!\n");
                    }
                    break;
                case 4:
                    connect.deleteAccount(account);
                    System.out.print("The account has been closed!\n");
                    input = 5;
                    break;
                case 5:
                case 0:
                    break;

            }
            if (input == 5 || input == 0) {
                break;
            }
        }
    }

    public String input () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
