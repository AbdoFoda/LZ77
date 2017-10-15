import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
//import javax.swing.JFrame;
import java.io.IOException;

//import javax.swing.JFrame;

public class Main{
	public static final int windowSize=(1<<31)-1;
	public static Boolean validFileAndDirectory(String file, String Dir) {
		File f = new File(file);
		if (Files.isDirectory(Paths.get(Dir)) && f.exists()) {
			return true;
		}
		return false;
	}
	public static Boolean newFile(String file) throws IOException {
		File f= new File(file);
		return f.createNewFile();
	}
	public static void main(String []args)  {
		Window w = new Window();
		
		
		
//		String s=File.readTextFile("/home/abdo/Desktop/stringFile.txt");
//		Encryption e= new Encryption(s);
//		ArrayList<Key> ans= e.encrypt();
//		for(Key k:ans) {
//			System.out.println(k.getNxtChar()+"("+k.getGo()+","+k.getLmx()+")");
//		}
//		File.writeToKeysFile("/home/abdo/Desktop/keysFile.txt",ans);
//		ans= File.readKeysFile("/home/abdo/Desktop/keysFile.txt");
//		for(Key k:ans) {
//			System.out.println(k.getNxtChar()+"("+k.getGo()+","+k.getLmx()+")");
//		}
//		Decryption d= new Decryption(ans);
//		String s2=d.decrypt();
//		System.out.println(s2);
//		File.writeToTextFile("/home/abdo/Desktop/String2.txt", s2);
	}
}
