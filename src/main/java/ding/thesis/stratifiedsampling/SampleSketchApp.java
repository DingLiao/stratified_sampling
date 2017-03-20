package ding.thesis.stratifiedsampling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ding.thesis.stratifiedsampling.model.Item;
import ding.thesis.stratifiedsampling.model.SampleBucket;
import ding.thesis.stratifiedsampling.model.SampleItem;
import ding.thesis.stratifiedsampling.util.DatabaseUtil;
import ding.thesis.stratifiedsampling.util.Utils;

public class SampleSketchApp {
	public static void main(String[] args ){
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
			DatabaseUtil.dropSampleItemTableIfExist(connect);
			DatabaseUtil.createSampleItemTableIfNotExist(connect);
			
			SampleBucket sb = DatabaseUtil.getNextSampleBucket(0, connect);
			while(sb!=null){
				for(int itemId : Utils.getSampleIdsFromSampleBucket(sb)) {
					Item i = DatabaseUtil.getItem(itemId, connect);
					SampleItem si = new SampleItem(itemId, i.getLength(),i.getLineNo(), sb.getScaleFactor(),sb.getId());
					DatabaseUtil.insertSampleItem(si, connect);
				}
				sb = DatabaseUtil.getNextSampleBucket(sb.getId(), connect);
			}
			connect.close();
			long endTime = System.currentTimeMillis();
		    System.out.println("Processing time: " + (endTime - startTime)/1000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
