package ding.thesis.stratifiedsampling.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ding.thesis.stratifiedsampling.model.Item;
import ding.thesis.stratifiedsampling.model.SampleBucket;

public class DatabaseUtil {
	public final static String JDBC_URL = "jdbc:mysql://localhost:3306/fang";
	public final static String USERNAME = "root";
	public final static String PASSWORD = "liaodings";
	
	public static void insertSampleBucket(SampleBucket sb, Connection conn) {
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO samplebucket (startId, endId, sampleSize, min_no, max_no)"
					+ " values"
					+ " (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sb.getStartId());
			ps.setInt(2, sb.getEndId());
			ps.setInt(3, sb.getSampleSize());
			ps.setInt(4, sb.getMin());
			ps.setInt(5, sb.getMax());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Item getNextValidItem(Item currentItem, Connection conn) {
		PreparedStatement ps = null;
		try {
//			String sql = "SELECT * FROM sorted_item WHERE id>? and description_length>? limit 1";
			String sql = "SELECT * FROM sorted_item WHERE id>? limit 1";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currentItem!=null ? currentItem.getId() : 0);
//			ps.setInt(2, currentItem!=null ? currentItem.getLength() : 0);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setLength(rs.getInt("description_length"));
				i.setLineNo(rs.getInt("line_no"));
				return i;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
