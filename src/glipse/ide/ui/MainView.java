package glipse.ide.ui;
import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import glipse.ide.launcher;
import glipse.ide.internal.FileManager;
public class MainView extends JPanel{
 public static JTabbedPane tabbedPane;
 public static JLabel label;
 public MainView() {
	this.setBackground(new Color(240,240,240));
    tabbedPane = new JTabbedPane();
	GroupLayout groupLayout = new GroupLayout(this);
	try {
	for(int i = 0;i<launcher.files.length;i++) {
		if(launcher.files[i].length()>0)
		tabbedPane.addTab(new File(launcher.files[i]).getName(),new EditorPane(launcher.files[i].replace("\n", "")).getTab());
	}
	if(launcher.files[0]==""||launcher.files[0]==null) {
	label = new JLabel("No Class to diplay, Create a new Class from the File Menu");
	label.setHorizontalAlignment(SwingConstants.CENTER);
	tabbedPane.addTab("Welcome",label);
	}
	}
	catch(Exception e) {}
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
