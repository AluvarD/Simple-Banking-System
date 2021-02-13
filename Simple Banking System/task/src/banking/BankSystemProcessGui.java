package banking;

import javax.swing.*;
import java.awt.*;

public class BankSystemProcessGui extends JFrame {

    public BankSystemProcessGui() throws HeadlessException {
        super("Bank System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        //button menu
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton button1 = new JButton("Create an account");
        JButton button2 = new JButton("Log into the account");
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
