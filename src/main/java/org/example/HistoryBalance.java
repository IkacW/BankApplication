package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HistoryBalance extends JFrame implements ActionListener, KeyListener {
    private final int id;
    private int page = 0;

    private final JFrame main_frame;

    private final JPanel history_panel;

    private final JButton previous_page_button, next_page_button, back_button;
    HistoryBalance(JFrame frame, int id) {
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

        // -------------- Buttons/Labels ------------
        previous_page_button = new JButton("<");
        previous_page_button.setFocusable(false);
        previous_page_button.setBounds(20, (main_panel.getOptionPanelHeight() - 30) / 2, 50, 30);
        previous_page_button.setEnabled(false);

        next_page_button = new JButton(">");
        next_page_button.setFocusable(false);
        next_page_button.setBounds(main_panel.getOptionPanelWidth() - 70, (main_panel.getOptionPanelHeight() - 30) / 2, 50, 30);

        back_button = new JButton("Back");
        back_button.setFocusable(false);
        back_button.setBounds(372, 215, 100, 20);

        history_panel = new JPanel();
        history_panel.setLayout(null);
        history_panel.setBounds(90, 29, 312, 175);
        history_panel.setBackground(Color.white);

        JLabel from_card_l = new JLabel("from card");
        from_card_l.setFont(new Font("Swansea", Font.PLAIN, 12));
        from_card_l.setBounds(6, 10, 85, 12);
        from_card_l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JLabel to_card_l = new JLabel("to card");
        to_card_l.setFont(new Font("Swansea", Font.PLAIN, 12));
        to_card_l.setBounds(97, 10, 85, 12);
        to_card_l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JLabel amount_l = new JLabel("amount");
        amount_l.setFont(new Font("Swansea", Font.PLAIN, 12));
        amount_l.setBounds(188, 10, 50, 12);
        amount_l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        JLabel date_time_l = new JLabel("Date/Time");
        date_time_l.setFont(new Font("Swansea", Font.PLAIN, 12));
        date_time_l.setBounds(244, 10, 60, 12);
        date_time_l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        if (!Bank.showHistory(this.id, page, history_panel)) {
            next_page_button.setEnabled(false);
        }
        // ------------ Listeners ----------------------
        previous_page_button.addActionListener(this);
        next_page_button.addActionListener(this);
        back_button.addActionListener(this);

        // ------------ Adding comps -------------------
        history_panel.add(from_card_l);
        history_panel.add(to_card_l);
        history_panel.add(amount_l);
        history_panel.add(date_time_l);
        main_panel.addButton(previous_page_button);
        main_panel.addButton(next_page_button);
        main_panel.addButton(back_button);
        main_panel.addPanel(history_panel);

        this.add(hero_panel);
        this.add(main_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == previous_page_button) {
            page--;
            Bank.showHistory(id, page, history_panel);
            if(page == 0) {
                previous_page_button.setEnabled(false);
            }
            next_page_button.setEnabled(true);
        } else if(e.getSource() == next_page_button) {
            page++;
            if(Bank.showHistory(id, page, history_panel)) {
                next_page_button.setEnabled(true);
            } else {
                next_page_button.setEnabled(false);
            }
            previous_page_button.setEnabled(true);
        } else if(e.getSource() == back_button) {
            main_frame.setVisible(true);
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
