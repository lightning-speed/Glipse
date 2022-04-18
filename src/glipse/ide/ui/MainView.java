package glipse.ide.ui;

import glipse.ide.launcher;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.io.File;

public class MainView extends JPanel {
    public static JTabbedPane tabbedPane;
    public static JLabel label;

    public MainView() {
        this.setBackground(new Color(240, 240, 240));
        tabbedPane = new JTabbedPane();
        GroupLayout groupLayout = new GroupLayout(this);
        try {
            for (int i = 0; i < launcher.files.length; i++) {
                if (launcher.files[i].length() > 0) {
                    EditorPane pane = new EditorPane(launcher.files[i].replace("\n", ""));
                    tabbedPane.addTab(new File(launcher.files[i]).getName(), pane.getTab());
                    pane.update();
                }

            }
            if (launcher.files[0] == "" || launcher.files[0] == null) {
                label = new JLabel("No Class to diplay, Create a new Class from the File Menu");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                tabbedPane.addTab("Welcome", label);
            }
        } catch (Exception e) {
        }
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        setLayout(groupLayout);
        repaint();
    }
}
