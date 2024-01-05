package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.AccessMode;
import java.util.Arrays;

public class Transaction extends JFrame implements ActionListener, KeyListener {
    private final int id;
    private final JFrame main_frame;
    private final JTextField card_number_field, name_field, last_name_field, amount_field;
    private final JButton back_button, submit_button;
    Transaction(JFrame frame, int id) {
        this.id = id;
        this.main_frame = frame;

        // ---------------- frame ---------------------
        this.setLayout(null);
        this.setSize(800, 700);
        this.setResizable(false);
        this.setTitle("Unicredit");
        ImageIcon logo = new ImageIcon("logo.png");
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FrameSettings.centerFrameOnScreen(this);
        // ---------------- components ----------------
        ImagePanel hero_panel = new ImagePanel(this);
        AtmPanel main_panel = new AtmPanel(this, 0, hero_panel.getHeight());

        // -------------- Buttons/TextFields ------------
        JLabel card_no_label = new JLabel("Card number:");
        card_no_label.setFont(new Font("Swansea", Font.PLAIN, 20));
        card_no_label.setBounds(20, 10, 300, 20);
        card_no_label.setForeground(Color.white);

        card_number_field = new JTextField();
        card_number_field.setBounds(20, 35, 452, 30);
        card_number_field.setFont(new Font("Swansea", Font.PLAIN, 20));

        JLabel name_label = new JLabel("Receiver name:");
        name_label.setFont(new Font("Swansea", Font.PLAIN, 20));
        name_label.setBounds(20, 70, 200, 20);
        name_label.setForeground(Color.white);

        name_field = new JTextField();
        name_field.setBounds(20, 95, 210, 30);
        name_field.setFont(new Font("Swansea", Font.PLAIN, 20));

        JLabel last_name_label = new JLabel("Last name:");
        last_name_label.setFont(new Font("Swansea", Font.PLAIN, 20));
        last_name_label.setBounds(262, 70, 200, 20);
        last_name_label.setForeground(Color.white);

        last_name_field = new JTextField();
        last_name_field.setBounds(262, 95, 210, 30);
        last_name_field.setFont(new Font("Swansea", Font.PLAIN, 20));

        JLabel amount_label = new JLabel("Amount:");
        amount_label.setFont(new Font("Swansea", Font.PLAIN, 20));
        amount_label.setBounds(20, 130, 200, 20);
        amount_label.setForeground(Color.white);

        amount_field = new JTextField();
        amount_field.setBounds(20, 155, 452, 30);
        amount_field.setFont(new Font("Swansea", Font.PLAIN, 20));

        submit_button = new JButton("Submit");
        submit_button.setFocusable(false);
        submit_button.setBounds(20, 195, 100, 40);

        back_button = new JButton("Back");
        back_button.setFocusable(false);
        back_button.setBounds(372, 195, 100, 40);

        // ------------ Listeners ----------------------

        card_number_field.addKeyListener(this);
        name_field.addKeyListener(this);
        last_name_field.addKeyListener(this);
        amount_field.addKeyListener(this);

        back_button.addActionListener(this);
        submit_button.addActionListener(this);

        // ------------ Adding comps -------------------
        main_panel.addLabel(card_no_label);
        main_panel.addLabel(name_label);
        main_panel.addLabel(last_name_label);
        main_panel.addLabel(amount_label);

        main_panel.addTextField(card_number_field);
        main_panel.addTextField(name_field);
        main_panel.addTextField(last_name_field);
        main_panel.addTextField(amount_field);

        main_panel.addButton(submit_button);
        main_panel.addButton(back_button);

        this.add(main_panel);
        this.add(hero_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit_button) {
            if (FieldFilters.checkField(card_number_field) || FieldFilters.checkField(name_field)
                    || FieldFilters.checkField(last_name_field) || FieldFilters.checkField(amount_field)) {
                return;
            }

            card_number_field.setEditable(false);
            name_field.setEditable(false);
            last_name_field.setEditable(false);
            amount_field.setEditable(false);

            submit_button.setEnabled(false);
            back_button.setEnabled(false);

            long cardNo = Long.parseLong(card_number_field.getText());
            String name = name_field.getText();
            String last_name = last_name_field.getText();
            float amount = Float.parseFloat(amount_field.getText());

            if(Bank.transferMoney(id, cardNo, name, last_name, amount)) {
                main_frame.setVisible(true);
                this.dispose();
            } else {
                card_number_field.setEditable(true);
                name_field.setEditable(true);
                last_name_field.setEditable(true);
                amount_field.setEditable(true);

                submit_button.setEnabled(true);
                back_button.setEnabled(true);
            }
        } else if(e.getSource() == back_button) {
            main_frame.setVisible(true);
            this.dispose();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == name_field || e.getSource() == last_name_field) {
            if (!FieldFilters.checkLetter(e.getKeyChar())) {
                e.consume();
            }
        } else if(e.getSource() == amount_field || e.getSource() == card_number_field) {
            if (!FieldFilters.checkNumber(e.getKeyChar())) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
