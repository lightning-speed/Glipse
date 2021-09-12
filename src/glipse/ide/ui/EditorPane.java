package glipse.ide.ui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import glipse.ide.launcher;
import glipse.ide.internal.Core;
import glipse.ide.internal.FileManager;
import glipse.ide.util.StringUtil;

import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class EditorPane {
 public JScrollPane Tab;
 public JTextArea pane;
 public String fileName = "";
 public String texts[] = new String[10000];
 int index = 0;
 public EditorPane(String fn) {
	this.fileName = fn;
	pane = new JTextArea(FileManager.read(fileName));
	texts[0]=pane.getText();
	index++;
	pane.setFont(new FontStyle().getFont("fonts/UbuntuMono-Bold.ttf",17f));
	Tab  = new JScrollPane(pane);
	Tab.setBorder(null);
	pane.setBorder(null);
	
	JToolBar toolBar = new JToolBar();
	toolBar.setFloatable(false);
	Tab.setColumnHeaderView(toolBar);
	
	JButton run_btn = new JButton("Run");
	run_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Core.run(fileName);
		}
	});
	toolBar.add(run_btn);
	
	JButton compile_btn = new JButton("Compile");
	compile_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Core.compile(fileName);
		}
	});
	toolBar.add(compile_btn);
	pane.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent arg0) {
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			texts[index]=pane.getText();
			index++;
			
		}});
	JButton undo_btn = new JButton("Undo");
	undo_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			undo();
			undo();
		}
	});
	toolBar.add(undo_btn);
	
	JButton redo_btn = new JButton("Redo");
	redo_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			redo();
			redo();
		}
	});
	toolBar.add(redo_btn);
	
	JButton del_btn = new JButton("Delete");
	del_btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			 int result = JOptionPane.showConfirmDialog(launcher.win,"Are you sure you want to delete this Class?", "Delete",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
		            if(result == JOptionPane.YES_OPTION){
		               new File(fileName).delete();
		               if(StringUtil.exists(FileManager.read(launcher.dirPath+"/proj"),";"+fileName))
		               FileManager.write(launcher.dirPath+"/proj",FileManager.read(launcher.dirPath+"/proj").replace(";"+fileName+"\n", ""));
		               else 
		            	   FileManager.write(launcher.dirPath+"/proj",FileManager.read(launcher.dirPath+"/proj").replace(fileName+"\n", ""));
		               MainView.tabbedPane.remove(getTab());
		            }
		}
	});
	toolBar.add(del_btn);
	new Thread(new Runnable() {

		@Override
		public void run() {
			for(;;) {
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			save();
			String art = FileManager.read(fileName).replace("  ", " ");
			for(int i = 0;i<5;i++)
				art = art.replace("  ", " ");
		     if(StringUtil.exists(art,"public static void main(String args[])")) {
		    	 run_btn.setEnabled(true);
		     }
		     else run_btn.setEnabled(false);
			}
			
		}}).start();

 }
 public JScrollPane getTab() {
	 return Tab;
 }
 
 public void save() {
	 FileManager.write(fileName,pane.getText());
 }
 public void undo() {
	 if(index>0) {
	pane.setText(texts[index-1]);
	index--;
	 }
 }
 public void redo() {
	 if (texts[index]!=null) {
			pane.setText(texts[index]);
			index++;
	 }
 }
}
