package banking;

import java.util.Scanner;

public class BankSystemProcess {
    public void menu () {
        BankSystem account1 = new BankSystem();
        while (true) {
            System.out.print("1. Create an account\n2. Log into account\n0. Exit\n");
            int input = Integer.parseInt(input());
            switch (input) {
                case 1:
                    account1.createAccount();
                    System.out.print("Your card number: \n" + account1.getCard() + "\n");
                    System.out.print("Your card PIN: \n" + account1.getPin() + "\n");
                    break;
                case 2:
                    System.out.print("Enter your card number: \n");
                    long card = Long.parseLong(input());
                    System.out.print("Enter your PIN: \n");
                    int pin = Integer.parseInt(input());
                    if (login(account1, card, pin)) {
                        System.out.print("You have successfully logged in!\n");
                        logged(account1);
                    } else {
                        System.out.print("Wrong card number or PIN!\n");
                    }
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

    public boolean login (BankSystem account, long card, int pin) {
        return card == account.getCard() && pin == account.getPin();
    }

    public void logged (BankSystem account) {
        while (true) {
            System.out.print("1. Balance\n2. Log out\n0. Exit\n");
            int input = Integer.parseInt(input());
            switch (input) {
                case 1:
                    System.out.print("Balance: " + account.getBalance() + "\n");
                    break;
                case 2:
                case 0:
                    break;

            }
            if (input == 2 || input == 0) {
                break;
            }
        }
    }

    public String input () {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
