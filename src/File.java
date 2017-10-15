import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class File {
	public static ArrayList<Key> readKeysFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        ArrayList<Key> ret= new ArrayList<Key>();
        Key curKey=new Key();
        for(int i=0;i<content.length();++i) {
        	if(i%3==0) {
        		if(i>0) {
        			ret.add(curKey);
        		}
        		curKey= new Key();
        		curKey.setNxtChar(content.charAt(i));
        	}else if(i%3==1) {
        		curKey.setGo((int)content.charAt(i));
        	}else if(i%3==2) {
        		curKey.setLmx((int)content.charAt(i));
        	}
        }
        ret.add(curKey);
        return ret;
    }
	public static void writeToKeysFile(String fileName, ArrayList<Key> keys) throws IOException {
		String content = "";
		for (Key k : keys) {
			content += k.getNxtChar();
			content += (char) k.getGo();
			content += (char) k.getLmx();
		}
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
	}
    public static String readTextFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }
    public static void writeToTextFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
    }

}
