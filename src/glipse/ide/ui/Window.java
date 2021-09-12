package glipse.ide.ui;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import glipse.ide.launcher;
import glipse.ide.internal.Core;
import glipse.ide.internal.FC;
import glipse.ide.internal.FileManager;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame{
 public MainView main_view = new MainView(); 
 public Window() {
	 this.setIconImage(new ImageIcon("icons/icon.png").getImage());
	 this.setTitle("Glipse - ["+ new File(launcher.dirPath).getName()+"]");
	this.setVisible(true);
	this.setSize(800,600);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	JMenuBar menuBar = new JMenuBar();
	menuBar.setBackground(new Color(235,235,240));
	setJMenuBar(menuBar);
	
	JMenu file_menu = new JMenu("File");
	menuBar.add(file_menu);
	
	JMenuItem mntmNewProject = new JMenuItem("New Project");
	mntmNewProject.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new newProjectWindow();
		}
	});
	file_menu.add(mntmNewProject);
	
	JMenuItem mntmOpenProject = new JMenuItem("Open Project");
	mntmOpenProject.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String path = new FC().chooseFolder();
			if(path!="")
			Core.openProject(path);
		}
	});
	file_menu.add(mntmOpenProject);
	
	JMenuItem mntmNewClass = new JMenuItem("New Class");
	mntmNewClass.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new newClassWindow();
		}
	});
	file_menu.add(mntmNewClass);
	
	JMenu mnHelp = new JMenu("Help");
	menuBar.add(mnHelp);
	
	JMenuItem mntmAbout = new JMenuItem("About");
	mntmAbout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				Runtime.getRuntime().exec("launcher.exe About.html");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	mnHelp.add(mntmAbout);
	this.setContentPane(main_view);
	repaint();
	setSize(800,601);
 }
}
