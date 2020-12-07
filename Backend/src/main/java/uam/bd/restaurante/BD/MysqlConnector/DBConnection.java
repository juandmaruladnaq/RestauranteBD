package uam.bd.restaurante.BD.MysqlConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;;

public class DBConnection 
{
	private static Connection conn = null;

	public static Connection getConnection() 
	{
		if (conn == null) 
		{
			String url = "jdbc:mysql://localhost/restaurante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";	        
	        Properties properties = new Properties();
	        properties.put("user", "root");
	        properties.put("password", "8774677jD1");
			try 
			{				
				conn = DriverManager.getConnection(url, properties);
				System.out.println("Connnn");
			} 
			catch (SQLException e)
			{
				Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return conn;
	}
	
	public static void disableAutoCommit()
	{
		try 
		{
			conn.setAutoCommit(false);
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
	}
	
	public static void commit()
	{
		try 
		{
			conn.commit();
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
	}
	
	public static void rollback()
	{
		try 
		{
			conn.rollback();
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
	}

	public static void closeConnection() 
	{
		if (conn != null) 
		{
			try 
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}	

	public static void closeStatement(Statement st) 
	{
		if (st != null) 
		{
			try 
			{
				st.close();
			} 
			catch (SQLException e) 
			{
				Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	public static void closeResultSet(ResultSet rs) 
	{
		if (rs != null)
		{
			try 
			{
				rs.close();
			} 
			catch (SQLException e) 
			{
				Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
}
