package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu extends JFrame implements ActionListener {
    ImageIcon logo = new ImageIcon("logo.png");
    JPanel main_panel;
    int id;
    JButton balance_button, transaction_button, withdraw_button, deposit_button,
            history_button, exit_button;
    public Menu(int id) {
        this.id = id;
        // ---------------- frame ---------------------
        this.setLayout(null);
        this.setSize(800, 700);
        this.setResizable(false);
        this.setTitle("Unicredit");
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FrameSettings.centerFrameOnScreen(this);

        // ---------------- components ----------------
        ImagePanel hero_panel = new ImagePanel(this);
        AtmPanel main_panel = new AtmPanel(this, 0, hero_panel.getHeight());

        // Buttons
        balance_button = new JButton("Account balance");
        balance_button.setFocusable(false);
        balance_button.setBounds(50, 25, 150, 40);

        history_button = new JButton("Transaction history");
        history_button.setFocusable(false);
        history_button.setBounds(50, 75, 150, 40);

        transaction_button = new JButton("Transaction");
        transaction_button.setFocusable(false);
        transaction_button.setBounds(50, 125, 150, 40);

        withdraw_button = new JButton("Withdraw");
        withdraw_button.setFocusable(false);
        withdraw_button.setBounds(290, 25, 150, 40);

        deposit_button = new JButton("Deposit");
        deposit_button.setFocusable(false);
        deposit_button.setBounds(290, 75, 150, 40);

        exit_button = new JButton("Exit");
        exit_button.setFocusable(false);
        exit_button.setBounds(290, 185, 150, 40);

        // ------------- Button Listeners -----------------
        balance_button.addActionListener(this);
        transaction_button.addActionListener(this);
        history_button.addActionListener(this);
        withdraw_button.addActionListener(this);
        deposit_button.addActionListener(this);
        exit_button.addActionListener(this);


        // --------------- Adding comps -----------------
        this.add(hero_panel);
        this.add(main_panel);

        main_panel.addButton(balance_button);
        main_panel.addButton(history_button);
        main_panel.addButton(transaction_button);
        main_panel.addButton(withdraw_button);
        main_panel.addButton(deposit_button);
        main_panel.addButton(exit_button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == balance_button) {
            Conn connection = new Conn();
            String q = "SELECT * FROM `user` WHERE id = " + id;

            ResultSet rs;
            String name = "";
            float balance = Float.MIN_VALUE;
            try {
                rs = connection.statement.executeQuery(q);
                if(rs.next()) {
                    name = rs.getString("firstName");
                    balance = rs.getFloat("balance");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if(name == "" || balance == Float.MIN_VALUE) {
                JOptionPane.showMessageDialog(null, "Error occurred.");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Dear " + name + ", your balance is: " + balance);
            }
        } else if (e.getSource() == history_button) {
            new HistoryBalance(this, this.id).setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == transaction_button) {
            new Transaction(this, this.id).setVisible(true);
            this.setVisible(false);
        } else if(e.getSource() == withdraw_button) {
            new Withdraw(this, id).setVisible(true);
            this.setVisible(false);
        } else if(e.getSource() == deposit_button) {
            new Deposit(this, id).setVisible(true);
            this.setVisible(false);
        } else if(e.getSource() == exit_button) {
            this.dispose();
        }
    }
}
