package org.example;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Login frame = new Login();
/*
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("hero_image.png");
        JPanel panel = new JPanel();
        JLabel label = new JLabel();

        // -------------- scaled image --------------------
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("hero_image.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image dimg = image.getScaledInstance(455, 69, Image.SCALE_SMOOTH);

        // ---------------- frame ---------------------
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,700);
        frame.setResizable(false);
        frame.setTitle("Unicredit");
        frame.setIconImage(icon.getImage());

        // --------------- panels/lables -----------------------

        ImageIcon hero_image = new ImageIcon();
        hero_image.setImage(dimg);

        label.setIcon(hero_image);

        panel.setBounds(0, 0, frame.getWidth(), 75);
        panel.setBackground(Color.red);

        // --------------- adding ----------------------
        panel.add(label);
        frame.add(panel);

        frame.setVisible(true);
*/
    }
}