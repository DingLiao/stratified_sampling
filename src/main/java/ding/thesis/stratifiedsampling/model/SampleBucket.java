package ding.thesis.stratifiedsampling.model;

public class SampleBucket {
	private int startId;
	
	private int endId;
	
	private int sampleSize;
	
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[startid: " + startId + ", endId: " + endId + ", sampleSize: " + sampleSize + ", min: " + min + ", max: " + max + "]";
	}
	
	
}
