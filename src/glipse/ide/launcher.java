package glipse.ide;

import glipse.ide.internal.ConfigParser;
import glipse.ide.internal.FileManager;
import glipse.ide.ui.Window;
import glipse.ide.ui.newProjectWindow;

import javax.swing.*;
import java.io.File;

public class launcher {
    public static Window win;
    public static String dirPath = "";
    public static String[] files;

    public static void launch() {
        dirPath = new ConfigParser("user_data").getContentOf("dirPath");
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dirPath);
        openDir();
        win = new Window();
        if (dirPath != "" && dirPath != null && new File(dirPath).exists()) {

        } else {
            win.setVisible(false);
            new newProjectWindow();
        }
    }

    public static void openDir() {
        files = FileManager.read(dirPath + "/proj").split(";");
    }
}
