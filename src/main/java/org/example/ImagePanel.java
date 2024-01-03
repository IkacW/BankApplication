package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(JFrame frame) {
        this.setBounds(0, 0, frame.getWidth(), 75);
        this.setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel();

        try {
            image = ImageIO.read(new File("hero_image.png"));
        } catch (IOException e) {
            System.out.println("Image not found");
        }

        Image dimg = image.getScaledInstance(455, 69, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(dimg);
        label.setIcon(icon);

        this.add(label);
    }
}
