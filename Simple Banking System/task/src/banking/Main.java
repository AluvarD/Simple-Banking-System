package banking;

public class Main {
    public static void main(String[] args) {
        DataBase connect = new DataBase("card.s3db");
        new BankSystemProcess().menu(connect);
        //new BankSystemProcessGui();
    }
}