package glipse.ide.internal;

import glipse.ide.util.StringUtil;

public class ConfigParser {
  public String fc = "";
  public String lines[];
  public ConfigParser(String fn) {
	  fc = FileManager.read(fn);
	  lines = fc.split("\n");
  }
  public String getContentOf(String of) {
	  for(int i = 0;i<lines.length;i++) {
	  String name = lines[i].split("=")[0];
	  if(StringUtil.isEqual(of,name)) {
		  return lines[i].split("=")[1];
	  }
	  }
	  return "";
  }
}
