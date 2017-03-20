package ding.thesis.stratifiedsampling.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ding.thesis.stratifiedsampling.model.Item;
import ding.thesis.stratifiedsampling.model.SampleBucket;
import ding.thesis.stratifiedsampling.model.SampleItem;

public class DatabaseUtil {
	public final static String JDBC_URL = "jdbc:mysql://localhost:3306/fang";
	public final static String USERNAME = "root";
	public final static String PASSWORD = "liaodings";
	
	public static void dropSampleBucketTableIfExist(Connection conn) {
		String sqlCreate = "DROP TABLE IF EXISTS samplebucket ";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlCreate);
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
	
	public static void createSampleBucketTableIfNotExist(Connection conn) {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS samplebucket "
        + "  (id int not null auto_increment,"
        + "   startid int,"
        + "   endid int,"
        + "   min_no int,"
        + "   max_no int,"
        + "   samplesize int,"
        + "   totalsize int,"
        + "PRIMARY KEY ( id ))";

		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sqlCreate);
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
	
	public static void dropSampleItemTableIfExist(Connection conn) {
		String sqlCreate = "DROP TABLE IF EXISTS sampleitem ";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlCreate);
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
	
	public static void createSampleItemTableIfNotExist(Connection conn) {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS sampleitem "
        + "  (id int not null auto_increment,"
        + "   original_id int,"
        + "   length int,"
        + "   line_no int,"
        + "   samplebucket_id int,"
        + "   scalefactor double, "
        + "PRIMARY KEY ( id ))";

		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sqlCreate);
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
	
	public static void insertSampleBucket(SampleBucket sb, Connection conn) {
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO samplebucket (startId, endId, sampleSize,totalSize, min_no, max_no)"
					+ " values"
					+ " (?, ?, ?, ?,?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sb.getStartId());
			ps.setInt(2, sb.getEndId());
			ps.setInt(3, sb.getSampleSize());
			ps.setInt(4, sb.getEndId() - sb.getStartId() + 1);
			ps.setInt(5, sb.getMin());
			ps.setInt(6, sb.getMax());
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
	
	public static Item getItem(int itemId, Connection conn) {
		PreparedStatement ps = null;
		try {
//			String sql = "SELECT * FROM sorted_item WHERE id>? and description_length>? limit 1";
			String sql = "SELECT * FROM sorted_item WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, itemId);
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
	
	public static SampleBucket getNextSampleBucket(int currentId, Connection conn) {
		PreparedStatement ps = null;
		try {
//			String sql = "SELECT * FROM sorted_item WHERE id>? and description_length>? limit 1";
			String sql = "SELECT * FROM samplebucket WHERE id>? limit 1";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, currentId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				SampleBucket i = new SampleBucket();
				i.setId(rs.getInt("id"));
				i.setMax(rs.getInt("max_no"));
				i.setMin(rs.getInt("min_no"));
				i.setStartId(rs.getInt("startid"));
				i.setEndId(rs.getInt("endid"));
				i.setSampleSize(rs.getInt("samplesize"));
				i.setTotalSize(rs.getInt("totalsize"));
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
	
	public static void insertSampleItem(SampleItem item, Connection conn) {
		System.out.println("insertSampleItem: " + item.toString() );
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO sampleitem (original_id, length, line_no,scalefactor, samplebucket_id)"
					+ " values"
					+ " (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, item.getOriginalId());
			ps.setInt(2, item.getLength());
			ps.setInt(3, item.getLineNo());
			ps.setDouble(4, item.getScaleFactor());
			ps.setInt(5, item.getSampleBucketId());
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
}
