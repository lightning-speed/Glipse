package glipse.ide.ui;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import glipse.ide.launcher;
import glipse.ide.internal.Core;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

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
		frame.setSize(499,126);
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
		frame.repaint();
	}
}
