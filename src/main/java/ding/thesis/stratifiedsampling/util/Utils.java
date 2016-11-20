package ding.thesis.stratifiedsampling.util;

import ding.thesis.stratifiedsampling.model.SampleBucket;

public class Utils {
	public static final double ERROR_BOUND = 0.2;
	public static final double CONFIDENCE = 0.85;
	
	
	public static int getNewSampleSize(int input, SampleBucket bucket) {
		if(input == bucket.getMin() || input == bucket.getMax())
			return bucket.getSampleSize();
		
		int newSmapleSize = (int) ( Math.ceil(Math.pow(Math.max(input, bucket.getMax()) - bucket.getMin(), 2) * Math.log(2/ (1 - CONFIDENCE))/ (2 * Math.pow(ERROR_BOUND, 2))));
		newSmapleSize = Math.min(newSmapleSize, bucket.getEndId() - bucket.getStartId() +2);
		return newSmapleSize;
	}
	
}
