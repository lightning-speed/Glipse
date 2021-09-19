package glipse.ide.util;

import java.io.IOException;

import glipse.ide.internal.FileManager;

public class Executer {
 public static void execute(String commandLine) {
	 FileManager.write("build.bat", "@echo off\n" + commandLine);
	 try {
		Runtime.getRuntime().exec("cmd.exe /c start build.bat");
	} catch (IOException e) {
		e.printStackTrace();
	}
 }
}
