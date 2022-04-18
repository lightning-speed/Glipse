package glipse.ide.ui;

import glipse.ide.internal.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class newProjectWindow {

    private JFrame frame;
    private JTextField class_input;

    public newProjectWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setTitle("New Project");
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("icons/icon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(469, 141);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        class_input = new JTextField("");
        class_input.setBounds(90, 25, 332, 25);
        panel.add(class_input);
        class_input.setColumns(10);

        JLabel class_name = new JLabel("Project Name:");
        class_name.setBounds(10, 30, 83, 14);
        panel.add(class_name);

        JButton btnNewButton = new JButton("Create");
        JLabel error = new JLabel("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (class_input.getText().length() > 0 && !new File(System.getProperty("user.home") + "/GlipseProjects/" + class_input.getText()).exists()) {
                    System.out.println(class_input.getText());
                    Core.createNewProject(System.getProperty("user.home") + "/GlipseProjects/" + class_input.getText());
                    Core.openProject(System.getProperty("user.home") + "/GlipseProjects/" + class_input.getText());
                    frame.setVisible(false);
                } else {
                    if (class_input.getText().length() <= 0)
                        error.setText("Error: Project Name should not be empty!");
                    else if (new File(System.getProperty("user.home") + "/GlipseProjects/" + class_input.getText()).exists())
                        error.setText("Error: Project already exists");
                }
            }
        });
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setBounds(10, 68, 89, 23);
        panel.add(btnNewButton);

        error.setForeground(Color.RED);
        error.setBounds(126, 68, 250, 23);
        panel.add(error);
        frame.setSize(469, 142);
        frame.setSize(469, 141);

    }
}
