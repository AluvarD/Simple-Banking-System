package banking;

import java.util.Random;

public class BankSystem {
    long card;
    int pin;
    int balance;

    public BankSystem() {
    }

    public void createAccount () {
        Random random = new Random();
        this.pin = random.nextInt(8000) + 1000;
        this.card = generateCard(random);
    }

    public long generateCard (Random random) {
        String bin = "400000";
        return Long.parseLong(String.valueOf(bin) + (random.nextInt(800000000) + 100000000) + random.nextInt(9));
    }

    public long getCard() {
        return this.card;
    }

    public int getPin() {
        return this.pin;
    }

    public int getBalance() {
        return balance;
    }
}
