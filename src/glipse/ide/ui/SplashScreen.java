package glipse.ide.ui;

import javax.swing.*;

public class SplashScreen {
    public static JFrame frame = new JFrame("Glipse");

    public SplashScreen() {
        frame.setUndecorated(true);
        frame.setIconImage(new ImageIcon("icons/icon.png").getImage());
        frame.setSize(434, 301);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
        JLabel splash = new JLabel(new ImageIcon("icons/splash.png"));
        frame.add(splash);
        frame.setSize(434, 300);
        frame.setSize(434, 301);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        frame.setVisible(false);
    }
}
