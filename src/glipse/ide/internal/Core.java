package glipse.ide.internal;

import java.io.File;
import java.io.IOException;

import glipse.ide.launcher;
import glipse.ide.ui.EditorPane;
import glipse.ide.ui.MainView;
import glipse.ide.util.Executer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Core {
 public static EditorPane currentTab;
 public static void core_init() {
	
 }
 public static void compile(String file) {
	 Executer.execute("title Compile\njava\\bin\\javac -classpath "+new File(file).getParent()+"/. "+file+"\n echo done\npause\nexit");
 }
 public static void run(String file) {
	 String in = FileManager.read(file);
	 for(int i = 0;i<20;i++)
	 in = in.replace("  ", " ");
	 Executer.execute("title Run\n"+"java\\bin\\javac -classpath "+new File(file).getParent()+"/. "+file+"\njava\\bin\\java -classpath "+new File(file).getParent()+" "+new File(file).getName().replace(".java","")+" "+Core.getProgramArguments()+"\npau.exe process-ended\nexit");
 }
 public static void createNewClass(String className) {
	 MainView.tabbedPane.remove(MainView.label);
	 String ap = ";";
	 if(launcher.files[0]==""||launcher.files[0]==null)ap="";
	 FileManager.write(launcher.dirPath+"/proj",FileManager.read(launcher.dirPath+"/proj")+ap+launcher.dirPath+"/"+className+".java");
	 FileManager.write(launcher.dirPath+"/"+className+".java","public class "+className+"{\n\n}");
 }
 public static void createNewProject(String dir) {
	 new File(dir).mkdirs();
	 try {
		new File(dir+"/proj").createNewFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 public static void  openProject(String path) {
	 FileManager.write("user_data", "dirPath="+path);
	 launcher.win.setVisible(false);
	 launcher.launch();
 }
 public static void export(String mainClass) {
	new File(System.getProperty("user.home")+"\\GlipseBuilds").mkdir();
	FileManager.write("manifest.mf","Main-Class: "+mainClass+"\n");
	Executer.execute("java\\bin\\jar.exe -cvfm "+System.getProperty("user.home")+"\\GlipseBuilds\\"+new File(launcher.dirPath).getName()+".jar manifest.mf -C "+launcher.dirPath+"\\ ."+"\nexit");
 }
 public static void setProgramArguments(String arguments) {
	 FileManager.write(launcher.dirPath+"\\.aruments",arguments);
 }
 public static String getProgramArguments() {
	 return FileManager.read(launcher.dirPath+"\\.aruments");
 }
}
