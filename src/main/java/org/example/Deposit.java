package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Deposit extends JFrame implements ActionListener, KeyListener {
    private ImageIcon logo = new ImageIcon("logo.png");
    private JPanel main_panel;
    private int id;
    private JLabel amount_label;
    private JTextField amount_field;
    private JButton back_button, deposit_button;
    private JFrame main_frame;
    public Deposit(JFrame frame, int id) {
        this.id = id;
        this.main_frame = frame;
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


        // -------------- Buttons/TextFields ------------
        amount_label = new JLabel("Deposit amount:");
        amount_label.setFont(new Font("Swansea", Font.PLAIN, 20));
        amount_label.setBounds(17, 20, 300, 20);
        amount_label.setForeground(Color.white);

        amount_field = new JTextField();
        amount_field.setBounds(17, 50, 340, 30);
        amount_field.setFont(new Font("Swansea", Font.PLAIN, 20));

        deposit_button = new JButton("Deposit");
        deposit_button.setBounds(374, 50, 100, 30);

        back_button = new JButton("Back");
        back_button.setFocusable(false);
        back_button.setBounds(374, 185, 100, 40);

        // ------------- Listeners ---------------------
        deposit_button.addActionListener(this);
        back_button.addActionListener(this);

        amount_field.addKeyListener(this);

        // ------------ Adding comps -------------------
        this.add(main_panel);
        this.add(hero_panel);

        main_panel.addLabel(amount_label);
        main_panel.addTextField(amount_field);
        main_panel.addButton(deposit_button);
        main_panel.addButton(back_button);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deposit_button) {
            if(amount_field.getText().equals("")){
                return;
            }

            amount_field.setEditable(false);
            deposit_button.setEnabled(false);
            back_button.setEnabled(false);

            float amount = Float.parseFloat(amount_field.getText());

            if (Bank.depositMoney(id, amount)) {
                main_frame.setVisible(true);
                this.dispose();
            } else {
                amount_field.setEditable(true);
                deposit_button.setEnabled(true);
                back_button.setEnabled(true);
                amount_field.setText("");
            }
        } else if(e.getSource() == back_button) {
            main_frame.setVisible(true);
            this.dispose();
        }
    }

    private boolean findChar(char[] chars, char c) {
        for(char character : chars) {
            if (c == character) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.'};

        if(!findChar(numbers, e.getKeyChar())) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10) {
            deposit_button.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}