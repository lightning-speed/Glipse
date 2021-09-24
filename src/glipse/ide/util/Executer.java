package glipse.ide.util;

import java.io.IOException;

import glipse.ide.internal.FileManager;

public class Executer {
 public static void execute(String commandLine) {
	 FileManager.write("temp\\build.bat", "@echo off\n" + commandLine);
	 try {
		Runtime.getRuntime().exec("cmd.exe /c start temp\\build.bat");
	} catch (IOException e) {
		e.printStackTrace();
	}
 }
}
