package glipse.ide.internal;

import java.io.*;

public class FileManager {
    public static void write(String path,String text){
    	if(!new File(path).exists())
			try {
				new File(path).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	FileWriter fw;
		try {
			fw = new FileWriter(path);
			fw.write(text);
			fw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    public static  String read(String path){
    	if(!new File(path).exists())
			try {
				new File(path).createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        String out = "";
        try {
            String temp = null;
            BufferedReader bf = new BufferedReader(new FileReader(path));
            while((temp = bf.readLine())!=null){out+=temp+'\n';}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  out;
    }
}
