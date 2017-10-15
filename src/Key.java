
public class Key {
	private char nxtChar;
	private int go,Lmx;
	public Key() {
		
	}
	public Key(char nxtChar,int go,int Lmx) {
		this.setNxtChar(nxtChar);
		this.setGo(go);
		this.setLmx(Lmx);
	}
	public char getNxtChar() {
		return nxtChar;
	}
	public void setNxtChar(char nxtChar) {
		this.nxtChar = nxtChar;
	}
	public int getLmx() {
		return Lmx;
	}
	public void setLmx(int lmx) {
		Lmx = lmx;
	}
	public int getGo() {
		return go;
	}
	public void setGo(int go) {
		this.go = go;
	}
}
