package ding.thesis.stratifiedsampling.model;

public class SampleItem {
	private int id;
	private int originalId;
	private int length;
	private int lineNo;
	private double scaleFactor = 1.0;
	private int sampleBucketId;
	
	public SampleItem(int originalId, int length, int lineNo, double scaleFactor, int sampleBucketId) {
		super();
		this.originalId = originalId;
		this.length = length;
		this.lineNo = lineNo;
		this.scaleFactor = scaleFactor;
		this.sampleBucketId = sampleBucketId;
	}
	
	public SampleItem() {
		super();
	}
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
	
	public int getOriginalId() {
		return originalId;
	}
	public void setOriginalId(int originalId) {
		this.originalId = originalId;
	}
	public double getScaleFactor() {
		return scaleFactor;
	}
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	public int getSampleBucketId() {
		return sampleBucketId;
	}
	public void setSampleBucketId(int sampleBucketId) {
		this.sampleBucketId = sampleBucketId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[id: " + id + ", length: " + length + ", lineNo: " + lineNo +", scaleFactor: " + scaleFactor + " ]";
	}
}
