package glipse.ide.internal;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import glipse.ide.launcher;

public class FC {
 public static String chooseFolder() {
	 JFileChooser fileChooser = new JFileChooser(System. getProperty("user.home")+"/GlipseProjects/");
     fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     int option = fileChooser.showOpenDialog(launcher.win);
     if(option == JFileChooser.APPROVE_OPTION){
        File file = fileChooser.getSelectedFile();
        if(!new File(file.getAbsolutePath()+"/proj").exists()) {
        	JOptionPane.showMessageDialog(launcher.win, "The Project is invalid",
                    "Error", JOptionPane.ERROR_MESSAGE);
        	return "";
        }
        return file.getAbsolutePath();
     }else{
       return "";
     }
 }
}
