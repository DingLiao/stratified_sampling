package ding.thesis.stratifiedsampling;

import java.sql.Connection;
import java.sql.DriverManager;
import ding.thesis.stratifiedsampling.model.Item;
import ding.thesis.stratifiedsampling.model.SampleBucket;
import ding.thesis.stratifiedsampling.util.DatabaseUtil;
import ding.thesis.stratifiedsampling.util.Utils;


public class SampleThemeApp 
{
	public static final int TOTAL = 1000000;
	
    public static void main( String[] args )
    {
    	long startTime =System.currentTimeMillis();
    	try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!");
		}catch(Exception e) {
			System.out.println("Error loading Mysql Driver");
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(DatabaseUtil.JDBC_URL,DatabaseUtil.USERNAME,DatabaseUtil.PASSWORD);
			System.out.println("Success connect Mysql server!");
			DatabaseUtil.dropSampleBucketTableIfExist(connect);
			DatabaseUtil.createSampleBucketTableIfNotExist(connect);
			
			Item currentItem = DatabaseUtil.getNextValidItem(null, connect);
			SampleBucket currentSb = new SampleBucket();
			currentSb.setMax(currentItem.getLength());
			currentSb.setMin(currentItem.getLength());
			currentSb.setStartId(currentItem.getId());
			currentSb.setEndId(currentItem.getId());
			currentSb.setSampleSize(1);
			
			int i = 1;
			while(i< TOTAL) {
				currentItem = DatabaseUtil.getNextValidItem(currentItem, connect);
				int newSize = Utils.getNewSampleSize(currentItem.getLength(), currentSb);
				if(currentSb.getSampleSize() + 1 >= newSize) {
					currentSb.setSampleSize(newSize);
					currentSb.setMax(Math.max(currentItem.getLength(), currentSb.getMax()));
					currentSb.setEndId(currentItem.getId());
					System.out.println("Extend current sb: " + currentItem.toString() );
				} else {
					DatabaseUtil.insertSampleBucket(currentSb, connect);
					System.out.println("Insert current sb: " + currentSb.toString() );
					currentSb.setMax(currentItem.getLength());
					currentSb.setMin(currentItem.getLength());
					currentSb.setStartId(currentItem.getId());
					currentSb.setEndId(currentItem.getId());
					currentSb.setSampleSize(1);
					System.out.println("Create new sb with currentItem: " + currentItem.toString() );
				}
				i = currentItem.getId();
			}
			DatabaseUtil.insertSampleBucket(currentSb, connect);
			System.out.println("Insert current sb: " + currentItem.toString() );
		    connect.close();
		    long endTime = System.currentTimeMillis();
		    System.out.println("Processing time: " + (endTime - startTime)/1000);
		} catch(Exception e) {
			System.out.println("get data error");
			e.printStackTrace();
		}
    }
}
