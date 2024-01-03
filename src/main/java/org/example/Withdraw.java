package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Withdraw extends JFrame implements ActionListener {
    ImageIcon logo = new ImageIcon("logo.png");
    JPanel main_panel;
    int id;

    public Withdraw(JFrame frame, int id) {
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

        this.add(hero_panel);
        this.add(main_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
