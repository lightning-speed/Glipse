package glipse.ide.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import glipse.ide.launcher;
import glipse.ide.internal.Core;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class newClassWindow {

	private JFrame frmNewClass;
	private JTextField class_input;

	public newClassWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewClass = new JFrame();
		frmNewClass.setIconImage(new ImageIcon("icons/icon.png").getImage());
		frmNewClass.setVisible(true);
		frmNewClass.setTitle("New Class");
		frmNewClass.setResizable(false);
		frmNewClass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNewClass.setSize(457,123);
		frmNewClass.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmNewClass.getContentPane().add(panel, BorderLayout.CENTER);
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
				if(class_input.getText().length()>0&&!new File(launcher.dirPath+"/"+class_input.getText()+".java").exists()) {
				Core.createNewClass(class_input.getText());
				MainView.tabbedPane.addTab(new File(launcher.dirPath+"/"+class_input.getText()+".java").getName(),new EditorPane(launcher.dirPath+"/"+class_input.getText()+".java").getTab());
			    frmNewClass.setVisible(false);
				}
			    else {
			    	if(class_input.getText().length()<=0)
					error.setText("Error: Class name should not be empty");
			    	else if(new File(launcher.dirPath+"/"+class_input.getText()+".java").exists())
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
		frmNewClass.setSize(457,124);
		frmNewClass.setSize(457,123);
	}
}
