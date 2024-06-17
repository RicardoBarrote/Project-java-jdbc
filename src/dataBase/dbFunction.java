package dataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dbFunction {

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			try {
				conn = DriverManager.getConnection(url, props);
			} 
			catch (SQLException e) {
				throw new dbException(e.getMessage());
			}

		}
		return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new dbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fis = new FileInputStream("dataBase.properties")) {
			Properties props = new Properties();
			props.load(fis);
			return props;
		} 
		catch (IOException e) {
			throw new dbException(e.getMessage());
		}
	}
}
