package murach.data.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

	private static ConnectionPool pool = null;
	private static DataSource dataSource = null;
	
	private ConnectionPool() {
		try {
			InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/murach");
		} catch (NamingException ne) {
			System.out.println(ne);
		}
	}
	
	public static synchronized ConnectionPool getInsance() {
		if (pool == null) {
			pool = new ConnectionPool();
		}
		return pool;
	}
	
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch(SQLException sqle) {
			System.out.println(sqle);
			return null;
		}
	}
	
	public void freeConnection(Connection c) {
		try {
			c.close();
		} catch(SQLException sqle) {
			System.out.println(sqle);
		}
	}
}
