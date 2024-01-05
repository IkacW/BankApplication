package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.PasswordAuthentication;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener, KeyListener {
    JLabel username_label, password_label;
    JPanel main_panel = new JPanel();
    ImageIcon logo = new ImageIcon("logo.png");
    JTextField username_field = new JTextField();
    JPasswordField password_field = new JPasswordField();
    JButton submit_button = new JButton("Submit");
    Login() {
        // ---------------- frame ---------------------
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,700);
        this.setResizable(false);
        this.setTitle("Unicredit");
        this.setIconImage(logo.getImage());

        FrameSettings.centerFrameOnScreen(this);

        // ---------------- components ----------------
        ImagePanel hero_panel = new ImagePanel(this);

        main_panel.setBounds(0, hero_panel.getHeight(), hero_panel.getWidth(), this.getHeight() - hero_panel.getHeight());
        main_panel.setBackground(Color.red);

        // ---------------- center fields/buttons ------------
        int x = (main_panel.getWidth() - 200)/2;

        main_panel.setLayout(null);

        // TextFields
        username_field.setBounds(x, main_panel.getHeight()/3 - 100, 200,50);
        username_field.setFont(new Font("Swansea", Font.PLAIN, 20));
        username_field.setForeground(Color.BLACK);

        password_field.setBounds(x, username_field.getY() + 100, 200, 50);
        password_field.setFont(new Font("Swansea", Font.PLAIN, 20));
        password_field.setForeground(Color.BLACK);

        // Labels
        username_label = new JLabel("Username");
        username_label.setFont(new Font("Swansea", Font.PLAIN, 20));
        username_label.setBounds(username_field.getX(), username_field.getY() - 40, 100, 50);

        password_label = new JLabel("Password/Pin");
        password_label.setFont(new Font("Swansea", Font.PLAIN, 20));
        password_label.setBounds(password_field.getX(), password_field.getY() - 40, 200, 50);

        // Buttons
        submit_button.setBounds((main_panel.getWidth() - 100) / 2, password_field.getY() + 75, 100, 50);
        submit_button.setFocusable(false);

        // ----------------- Listeners ----------------
        submit_button.addActionListener(this);

        username_field.addKeyListener(this);
        password_field.addKeyListener(this);

        // ----------------- Adding Components ---------
        main_panel.add(username_label);
        main_panel.add(username_field);

        main_panel.add(password_label);
        main_panel.add(password_field);

        main_panel.add(submit_button);

        this.add(hero_panel);
        this.add(main_panel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit_button) {
            if(FieldFilters.checkField(username_field) || FieldFilters.checkField(password_field)) {
                return;
            }
            username_field.setEditable(false);
            password_field.setEditable(false);
            submit_button.setEnabled(false);

            Conn conn = new Conn();

            String username = username_field.getText();
            String password = password_field.getText();

            String q = "SELECT * FROM `user_credentials` WHERE `username` = '" + username + "' and " +
                    "`password` = '" + password + "'";

            System.out.println(username + " " + password);

            ResultSet rs;
            try {
                rs = conn.statement.executeQuery(q);
                if(rs.next()){
                    this.setVisible(false);
                    new Menu(rs.getInt("user_id")).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect credentials.");
                    username_field.setEditable(true);
                    password_field.setEditable(true);
                    submit_button.setEnabled(true);

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10) {
            submit_button.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
