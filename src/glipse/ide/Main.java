package glipse.ide;

import glipse.ide.ui.SplashScreen;

public class Main {
    public static SplashScreen sc;

    public static void main(String[] args) {
        sc = new SplashScreen();
        try {
            launcher.launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
