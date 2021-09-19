package glipse.ide.util;

public class StringUtil {
 public static boolean isEqual(String a,String b) {
	 if(a.length()!=b.length())return false;
	 for(int i = 0;i<a.length();i++) {
		 if(a.charAt(i)!=b.charAt(i))return false;
	 }
	 return true;
 }
 public  static boolean exists(String a,String b) {
	 int temp = 0;
	 for(int i = 0;i<a.length();i++) {
		 if(a.charAt(i)==b.charAt(temp)) {
			 temp++;
		 }
		 else temp = 0;
		 if(temp==b.length()-2)return true;
	 }
	 return false;
 }
}
