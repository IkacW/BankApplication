package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AtmPanel extends JPanel {
    BufferedImage atm_image = null;
    BufferedImage bg_image = null;
    JLabel label = null;
    JPanel option_panel;
    public AtmPanel(JFrame frame, int x, int y) {
        this.setBounds(x, y, frame.getWidth(), frame.getHeight() - y);
        this.setLayout(null);

        try {
            atm_image = ImageIO.read(new File("atmsq.jpg"));
            bg_image = ImageIO.read(new File("red_bg.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image atm_dimg = atm_image.getScaledInstance(this.getWidth(), frame.getHeight() - y, Image.SCALE_FAST);
        ImageIcon atm_icon = new ImageIcon(atm_dimg);

        Image bg_dimg = atm_image.getScaledInstance(this.getWidth(), frame.getHeight() - y, Image.SCALE_FAST);
        ImageIcon bg_icon = new ImageIcon(bg_dimg);

        option_panel = new JPanel();
        option_panel.setBackground(new Color(0, 0, 0, 100));
        option_panel.setLayout(null);
        option_panel.setBounds(153, 112, 492, 243);
        option_panel.setOpaque(true);

        this.add(option_panel);

        JLabel label1 = new JLabel();

        label = new JLabel();
        label.setIcon(atm_icon);
        label.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.add(label);
    }

    public int getOptionPanelHeight() {
        return option_panel.getHeight();
    }

    public int getOptionPanelWidth() {
        return option_panel.getWidth();
    }

    public void addButton(JButton button) {
        option_panel.add(button);
        this.setVisible(true);
    }

    public void addLabel(JLabel label) {
        option_panel.add(label);
        this.setVisible(true);
    }

    public void addTextField(JTextField textField) {
        option_panel.add(textField);
        this.setVisible(true);
    }

    public void addPanel(JPanel panel) {
        option_panel.add(panel);
        this.setVisible(true);
    }
}
