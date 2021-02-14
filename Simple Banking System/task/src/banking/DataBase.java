package banking;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    /*
    CREATE TABLE "card" (
	    "id"	INTEGER NOT NULL UNIQUE,
	    "number"	TEXT,
	    "pin"	TEXT,
	    "balance"	INTEGER NOT NULL DEFAULT 0,
	    PRIMARY KEY("id" AUTOINCREMENT)
    );
     */

    public String url;
    public static SQLiteDataSource dataSource = new SQLiteDataSource();

    public DataBase(String url) {
        this.url = "jdbc:sqlite:Simple Banking System/task/src/banking/db/" + url;
    }

    public void selectAll() {
        dataSource.setUrl(this.url);
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                System.out.println("Connection is valid.");
                Statement statement = connection.createStatement();
                try (ResultSet result = statement.executeQuery("select * from card")){
                    while (result.next()) {
                        int id = result.getInt("id");
                        long account = result.getLong("number");
                        int pin = result.getInt("pin");
                        int balance = result.getInt("balance");

                        System.out.printf("ID %d%n", id);
                        System.out.printf("\tCard: %s%n", account);
                        System.out.printf("\tPIN: %s%n", pin);
                        System.out.printf("\tBalance: %s%n", balance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAccount(long account, int pin, int balance) {
        dataSource.setUrl(this.url);
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                //System.out.println("Connection is valid.");
                Statement statement = connection.createStatement();
                statement.executeUpdate("insert into card (number, pin, balance) values (" + account + ", " + pin + ", " + balance + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(long card, int pinCode) {
        boolean logged = false;
        dataSource.setUrl(this.url);
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                //System.out.println("Connection is valid.");
                Statement statement = connection.createStatement();
                try (ResultSet result = statement.executeQuery("select number, pin from card where number = '" + card + "'")){
                    while (result.next()) {
                        long account = result.getLong("number");
                        int pin = result.getInt("pin");
                        if (card == account && pinCode == pin) {
                            logged = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logged;
    }

    public int viewBalance(long card) {
        int balance = 0;
        dataSource.setUrl(this.url);
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                //System.out.println("Connection is valid.");
                Statement statement = connection.createStatement();
                try (ResultSet result = statement.executeQuery("select balance from card where number = '" + card + "'")){
                    result.next();
                    balance = result.getInt("balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public void addIncome(long account, int balance) {
        dataSource.setUrl(this.url);
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                //System.out.println("Connection is valid.");
                Statement statement = connection.createStatement();
                statement.executeUpdate("update card set balance = " + balance + " where number = '" + account + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean cardExist(long card) {
        boolean exist = false;
        dataSource.setUrl(this.url);
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                //System.out.println("Connection is valid.");
                Statement statement = connection.createStatement();
                try (ResultSet result = statement.executeQuery("select number, pin from card where number = '" + card + "'")){
                    while (result.next()) {
                        exist = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public void makeTransfer(long account, long toCard, int transferSum) {
        dataSource.setUrl(this.url);
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                //System.out.println("Connection is valid.");
                int balanceAccount = viewBalance(account);
                int balanceToCard = viewBalance(toCard);
                Statement statement = connection.createStatement();
                statement.executeUpdate("update card set balance = " + (balanceAccount - transferSum) + " where number = '" + account + "'");
                statement.executeUpdate("update card set balance = " + (balanceToCard + transferSum) + " where number = '" + toCard + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(long account) {
        dataSource.setUrl(this.url);
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                //System.out.println("Connection is valid.");
                Statement statement = connection.createStatement();
                statement.executeUpdate("delete from card where number = '" + account + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
