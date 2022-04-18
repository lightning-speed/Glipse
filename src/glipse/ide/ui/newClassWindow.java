package glipse.ide.ui;

import glipse.ide.internal.Core;
import glipse.ide.launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class newClassWindow {

    private JFrame frame;
    private JTextField class_input;

    public newClassWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setIconImage(new ImageIcon("icons/icon.png").getImage());
        frame.setVisible(true);
        frame.setTitle("New Class");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(457, 123);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        class_input = new JTextField();
        class_input.setBounds(82, 25, 340, 25);
        panel.add(class_input);
        class_input.setColumns(10);

        JLabel class_name = new JLabel("Class Name:");
        class_name.setBounds(10, 30, 83, 14);
        panel.add(class_name);

        JButton btnNewButton = new JButton("Create");
        JLabel error = new JLabel("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (class_input.getText().length() > 0 && !new File(launcher.dirPath + "/" + class_input.getText() + ".java").exists()) {
                    Core.createNewClass(class_input.getText());
                    MainView.tabbedPane.addTab(new File(launcher.dirPath + "/" + class_input.getText() + ".java").getName(), new EditorPane(launcher.dirPath + "/" + class_input.getText() + ".java").getTab());
                    frame.setVisible(false);
                } else {
                    if (class_input.getText().length() <= 0)
                        error.setText("Error: Class name should not be empty");
                    else if (new File(launcher.dirPath + "/" + class_input.getText() + ".java").exists())
                        error.setText("Error: Class already exists");
                }
            }

        });
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setBounds(10, 61, 89, 23);
        panel.add(btnNewButton);
        error.setForeground(Color.RED);
        error.setBounds(126, 59, 250, 23);
        panel.add(error);
        frame.setSize(457, 124);
        frame.setSize(457, 123);
    }
}
