import java.util.ArrayList;

public class Encryption {
	private static int match(int i, int j,String s) {
		int k = 0;
		while (i + k < (int) s.length() - 1 && s.charAt(i + k) == s.charAt(j + k) && k < Main.windowSize) {
			k++;
		}
		return k;
	}
	public static ArrayList<Key> encrypt(String s) {
		ArrayList<Key> ret = new ArrayList<Key>();
		for (int i = 0; i < (int) s.length() - 1; ++i) {
			int go = 0, Lmx = 0;
			for (int j = i - 1; j >= 0 && j >= i - Main.windowSize; --j) {
				int Li = match(i, j,s);
				if (Li > Lmx) {
					Lmx = Li;
					go = i - j;
				}

			}
			ret.add(new Key(s.charAt(i + Lmx), go, Lmx));
			i+=Lmx;
		}
		return ret;
	}
}
