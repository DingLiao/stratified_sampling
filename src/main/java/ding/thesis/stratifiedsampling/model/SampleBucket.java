package ding.thesis.stratifiedsampling.model;

public class SampleBucket {
	private int id;
	
	private int startId;
	
	private int endId;
	
	private int sampleSize;
	
	private int totalSize;
	
	private int min;
	
	private int max;

	public int getStartId() {
		return startId;
	}

	public void setStartId(int startId) {
		this.startId = startId;
	}

	public int getEndId() {
		return endId;
	}

	public void setEndId(int endId) {
		this.endId = endId;
	}

	public int getSampleSize() {
		return sampleSize;
	}

	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getScaleFactor() {
		return this.totalSize/(double)this.sampleSize;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[startid: " + startId + ", endId: " + endId + ", sampleSize: " + sampleSize + ", totalSize: " + totalSize + ", min: " + min + ", max: " + max + "]";
	}
	
	
}
