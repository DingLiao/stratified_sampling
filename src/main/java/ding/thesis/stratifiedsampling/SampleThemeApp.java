package ding.thesis.stratifiedsampling;

import java.sql.Connection;
import java.sql.DriverManager;
import ding.thesis.stratifiedsampling.model.Item;
import ding.thesis.stratifiedsampling.model.SampleBucket;
import ding.thesis.stratifiedsampling.util.DatabaseUtil;
import ding.thesis.stratifiedsampling.util.Utils;


public class App 
{
	public static final int TOTAL = 1000000;
	
    public static void main( String[] args )
    {
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
			
			Item currentItem = DatabaseUtil.getNextValidItem(null, connect);
			SampleBucket sb = new SampleBucket();
			sb.setMax(currentItem.getLength());
			sb.setMin(currentItem.getLength());
			sb.setStartId(currentItem.getId());
			sb.setEndId(currentItem.getId());
			sb.setSampleSize(1);
			
			int i = 1;
			while(i< TOTAL) {
				currentItem = DatabaseUtil.getNextValidItem(currentItem, connect);
				int newSize = Utils.getNewSampleSize(currentItem.getLength(), sb);
				if(sb.getSampleSize() + 1 >= newSize) {
					sb.setSampleSize(newSize);
					sb.setMax(Math.max(currentItem.getLength(), sb.getMax()));
					sb.setEndId(currentItem.getId());
					System.out.println("Extend current sb: " + currentItem.toString() );
				} else {
					DatabaseUtil.insertSampleBucket(sb, connect);
					System.out.println("Insert current sb: " + sb.toString() );
					sb.setMax(currentItem.getLength());
					sb.setMin(currentItem.getLength());
					sb.setStartId(currentItem.getId());
					sb.setEndId(currentItem.getId());
					sb.setSampleSize(1);
					System.out.println("Create new sb with currentItem: " + currentItem.toString() );
				}
				i = currentItem.getId();
			}
			DatabaseUtil.insertSampleBucket(sb, connect);
			System.out.println("Insert current sb: " + currentItem.toString() );
		    connect.close();
		} catch(Exception e) {
			System.out.println("get data error");
			e.printStackTrace();
		}
    }
}
