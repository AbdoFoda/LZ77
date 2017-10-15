import java.util.ArrayList;

public class Decryption {
	public static String decrypt(ArrayList<Key>keys) {
		String ret="";
		int curOffset=0;
		for(Key k: keys) {
			char nxt=k.getNxtChar();
			int go=curOffset-k.getGo();
			int L=k.getLmx();
			while(L-->0) {
				ret+=ret.charAt(go);
				go++;
				curOffset++;
			}
			ret+=nxt;
			curOffset++;
		}
		return ret;
	}
	
}
