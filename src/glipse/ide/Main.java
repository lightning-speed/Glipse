package glipse.ide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import glipse.ide.ui.SplashScreen;
import glipse.ide.util.Executer;

public class Main {
 public static SplashScreen sc;
 public static void main(String args[]) {
	 sc = new SplashScreen();
	 try {
	 launcher.launch();
	 }
	 catch(Exception e) {
		 e.printStackTrace();
	 }
 }
}
