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
        this.card = generateCardLuhn(random);
    }

    public long generateCard (Random random) {
        String bin = "400000";
        return Long.parseLong(bin + (random.nextInt(800000000) + 100000000) + random.nextInt(9));
    }

    public long generateCardLuhn (Random random) {
        String bin = "400000";
        String card = bin + (random.nextInt(800000000) + 100000000);
        int checkSum;
        int sum = 0;
        String[] cardArray = card.split("");

        for (int i = 0; i < cardArray.length; i++) {
            if (i % 2 == 0) {
                if (Integer.parseInt(cardArray[i]) * 2 > 9){
                    sum += Integer.parseInt(cardArray[i]) * 2 - 9;
                } else {
                    sum += Integer.parseInt(cardArray[i]) * 2;
                }
            } else {
                sum += Integer.parseInt(cardArray[i]);
            }
        }
        //System.out.println(sum);
        //System.out.println(sum % 10);

        if (sum % 10 == 0) {
            checkSum = 0;
        } else {
            checkSum = 10 - (sum % 10);
        }

        return Long.parseLong(card + checkSum);
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
