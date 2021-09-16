package glipse.ide.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import glipse.ide.launcher;
import glipse.ide.internal.Core;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class ExportWindow {

	private JFrame frmExport;
	private JTextField textField;
	private JButton btnExport;
	private JLabel error;

	public ExportWindow() {
		initialize();
	}

	
	private void initialize() {
		frmExport = new JFrame();
		frmExport.setIconImage(Toolkit.getDefaultToolkit().getImage("icons\\icon.png"));
		frmExport.setResizable(false);
		frmExport.setTitle("Export");
		frmExport.setSize(450, 131);
		frmExport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmExport.setVisible(true);
		frmExport.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frmExport.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblMainClass = new JLabel("Main Class:");
		lblMainClass.setBounds(10, 33, 80, 20);
		panel.add(lblMainClass);
		
		textField = new JTextField();
		textField.setBounds(104, 33, 330, 23);
		panel.add(textField);
		textField.setColumns(10);
		
		btnExport = new JButton("Export\r\n");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()<=0) {
					error.setText("The name of the class should not be empty");
				}
				else if(!new File(launcher.dirPath+"\\"+textField.getText()+".java").exists()) {
					error.setText("The class doesn't exists");
				}
				else {
				 Core.export(textField.getText());
				 frmExport.setVisible(false);
				}	
			}
		});
		btnExport.setForeground(Color.BLACK);
		btnExport.setBackground(Color.LIGHT_GRAY);
		btnExport.setBounds(10, 66, 89, 23);
		panel.add(btnExport);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(128, 67, 250, 23);
		panel.add(error);
	}
}
