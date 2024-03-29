package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Withdraw extends JFrame implements ActionListener, KeyListener {
    private final int id;
    private final JTextField amount_field;
    private final JButton back_button;
    private final JButton withdraw_button;
    private final JFrame main_frame;
    public Withdraw(JFrame frame, int id) {
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
        JLabel amount_label = new JLabel("Withdraw amount:");
        amount_label.setFont(new Font("Swansea", Font.PLAIN, 20));
        amount_label.setBounds(17, 20, 300, 20);
        amount_label.setForeground(Color.white);

        amount_field = new JTextField();
        amount_field.setBounds(17, 50, 340, 30);
        amount_field.setFont(new Font("Swansea", Font.PLAIN, 20));

        withdraw_button = new JButton("Withdraw");
        withdraw_button.setBounds(374, 50, 100, 30);

        back_button = new JButton("Back");
        back_button.setFocusable(false);
        back_button.setBounds(374, 185, 100, 40);

        // ------------- Listeners ---------------------
        withdraw_button.addActionListener(this);
        back_button.addActionListener(this);

        amount_field.addKeyListener(this);

        // ------------ Adding comps -------------------
        this.add(main_panel);
        this.add(hero_panel);

        main_panel.addLabel(amount_label);
        main_panel.addTextField(amount_field);
        main_panel.addButton(withdraw_button);
        main_panel.addButton(back_button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdraw_button) {
            if(amount_field.getText().equals("")){
                return;
            }

            amount_field.setEditable(false);
            withdraw_button.setEnabled(false);
            back_button.setEnabled(false);

            float amount = Float.parseFloat(amount_field.getText());

            if (Bank.withdrawMoney(id, amount)) {
                main_frame.setVisible(true);
                this.dispose();
            } else {

                amount_field.setEditable(true);
                withdraw_button.setEnabled(true);
                back_button.setEnabled(true);
            }
        } else if(e.getSource() == back_button) {
            main_frame.setVisible(true);
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
          if(!FieldFilters.checkNumber(e.getKeyChar())) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10) {
            withdraw_button.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
