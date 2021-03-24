package me.javacourse.Types;

public class DiscNode {

	private String name;
	private int kbytes;
	
	public DiscNode(String name, int kbytes) {
		this.setName(name);
		this.setKbytes(kbytes);
	}
	
	public String getName() {
		return name;
	}

	public int getKbytes() {
		return kbytes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKbytes(int kbytes) {
		this.kbytes = kbytes;
	}
	
}
