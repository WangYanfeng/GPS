package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/**
 * WangYanfeng 作者 E-mail:wangyanfeng_2012@163.com
 * 创建时间：2015年2月3日 下午8:09:24
 * 
 * 数据库操作
 */
public class DBExecutor {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/gps";
	private static String user = "root";
	private static String password = "123456";
	
	public CachedRowSet query(String sql) throws Exception
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			// if (!conn.isClosed())
			// System.out.println("Succeeded connecting to the Database!");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (ClassNotFoundException e)
		{
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e)
		{
			System.out.println("Sorry!Can't connect to the Databases!");
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRs = factory.createCachedRowSet();
		cachedRs.populate(rs);

		rs.close();
		stmt.close();
		conn.close();
		return cachedRs;
	}
	
	public int update(String sql) throws Exception
	{
		Connection conn = null;
		Statement stmt = null;
		// ResultSet rs=null;
		int i = 0;

		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			// if (!conn.isClosed())
			// System.out.println("Succeeded connecting to the Database!");
			stmt = conn.createStatement();
			i = stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e)
		{
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e)
		{
			System.out.println("Sorry!Can't connect to the Databases!");
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		stmt.close();
		conn.close();
		return i;
	}
}
