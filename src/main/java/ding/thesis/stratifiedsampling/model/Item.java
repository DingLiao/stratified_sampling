package ding.thesis.stratifiedsampling.model;

public class Item {
	private int id;
	private int length;
	private int lineNo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[id: " + id + ", length: " + length + ", lineNo: " + lineNo +" ]";
	}
	
	
}
