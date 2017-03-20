package ding.thesis.stratifiedsampling.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ding.thesis.stratifiedsampling.model.SampleBucket;

public class Utils {
	public static final double ERROR_BOUND = 60; // 1%
	//public static final double ERROR_BOUND = 180; // 3%
	//public static final double ERROR_BOUND = 300; // 5%
	//public static final double ERROR_BOUND = 600; // 10%
	//public static final double ERROR_BOUND = 900; // 15%
//	public static final double ERROR_BOUND = 200; //
	public static final double CONFIDENCE = 0.95;
	
	
	public static int getNewSampleSize(int input, SampleBucket bucket) {
		if(input == bucket.getMin() || input == bucket.getMax())
			return bucket.getSampleSize();
		
		int newSmapleSize = (int) ( Math.ceil(Math.pow(Math.max(input, bucket.getMax()) - bucket.getMin(), 2) * Math.log(2/ (1 - CONFIDENCE))/ (2 * Math.pow(ERROR_BOUND, 2))));
		newSmapleSize = Math.min(newSmapleSize, bucket.getEndId() - bucket.getStartId() +2);
		return newSmapleSize;
	}
	
	public static Set<Integer> getSampleIdsFromSampleBucket(SampleBucket bucket) {
		Random rng = new Random(); // Ideally just create one instance globally
		// Note: use LinkedHashSet to maintain insertion order
		Set<Integer> generated = new LinkedHashSet<Integer>();
		while (generated.size() < bucket.getSampleSize())
		{
		    Integer next = rng.nextInt((bucket.getEndId() - bucket.getStartId()) + 1) + bucket.getStartId();
		    // As we're adding to a set, this will automatically do a containment check
		    generated.add(next);
		}
		
		return generated;
	}
	
}
