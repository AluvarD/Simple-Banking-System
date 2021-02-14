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
                System.out.println("Connection is valid.");
                Statement statement = connection.createStatement();
                statement.executeUpdate("insert into card (number, pin, balance) values (" + account + ", " + pin + ", " + balance + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
