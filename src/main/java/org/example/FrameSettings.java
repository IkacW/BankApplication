package org.example;

import javax.swing.*;
import java.awt.*;

public class FrameSettings {

    public static void centerFrameOnScreen(JFrame frame) {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screen_size.width - frame.getWidth()) / 2;
        int y = (screen_size.height - frame.getHeight()) / 2;

        frame.setLocation(x, y);
    }
}

