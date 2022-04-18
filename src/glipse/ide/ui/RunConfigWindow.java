package glipse.ide.ui;

import glipse.ide.internal.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunConfigWindow {

    private JFrame frame;
    private JTextField arguments;

    /**
     * @wbp.parser.entryPoint
     */
    public RunConfigWindow() {
        initialize();
    }

    /**
     * @wbp.parser.entryPoint
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("icons/icon.png").getImage());
        frame.setTitle("Run Configration");
        frame.setVisible(true);
        frame.setSize(499, 126);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(null);

        JLabel lblProgramArguments = new JLabel("Program arguments:");
        lblProgramArguments.setBounds(10, 22, 134, 20);
        panel.add(lblProgramArguments);

        arguments = new JTextField("");
        arguments.setText(Core.getProgramArguments());
        arguments.setBounds(143, 22, 329, 23);
        panel.add(arguments);
        JButton btnNewButton = new JButton("Done");
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Core.setProgramArguments(arguments.getText());
                frame.setVisible(false);
            }
        });
        btnNewButton.setBounds(383, 53, 89, 23);
        panel.add(btnNewButton);
        frame.setSize(499, 127);
        frame.setSize(499, 126);
    }
}
