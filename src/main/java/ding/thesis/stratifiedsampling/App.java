package ding.thesis.stratifiedsampling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
	public final static String JDBC_URL = "jdbc:mysql://localhost:3306/fang";
	public final static String USERNAME = "root";
	public final static String PASSWORD = "liaodings";
	
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
			Connection connect = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("Success connect Mysql server!");
		    Statement stmt = connect.createStatement();
		    ResultSet rs = stmt.executeQuery("select * from item");		                                                             
		    while (rs.next()) {
		        System.out.println(rs.getString("description_length"));
		    }
		 
		} catch(Exception e) {
			System.out.println("get data error");
			e.printStackTrace();
		}
    }
}
