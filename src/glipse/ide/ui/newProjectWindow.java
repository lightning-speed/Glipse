package glipse.ide.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import glipse.ide.launcher;
import glipse.ide.internal.Core;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class newProjectWindow {

	private JFrame frmNewClass;
	private JTextField class_input;

	public newProjectWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewClass = new JFrame();
		frmNewClass.setVisible(true);
		frmNewClass.setTitle("New Project");
		frmNewClass.setResizable(false);
		frmNewClass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNewClass.setSize(438,141);
		frmNewClass.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmNewClass.getContentPane().add(panel, BorderLayout.CENTER);
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
				if(class_input.getText().length()>0&&!new File(System. getProperty("user.home")+"/GlipseProjects/"+class_input.getText()).exists()) {
					System.out.println(class_input.getText());
				Core.createNewProject(System.getProperty("user.home")+"/GlipseProjects/"+class_input.getText());
				Core.openProject(System. getProperty("user.home")+"/GlipseProjects/"+class_input.getText());
			    frmNewClass.setVisible(false);
				}
				else {
					if(class_input.getText().length()<=0)
					error.setText("Error: Project Name should not be empty!");
					else if(new File(System. getProperty("user.home")+"/GlipseProjects/"+class_input.getText()).exists())
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
		frmNewClass.repaint();
	}
}
