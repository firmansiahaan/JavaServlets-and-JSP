package murach.data.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import murach.business.User;
import murach.sql.DBUtil;

public class UserDB {
	
	public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInsance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        
        String query =
        		"INSERT INTO User (Email, FirstName, LastName)"
        		+ "VALUES (?, ?, ?)";
        try {
        	preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setString(1, user.getEmail());
        	preparedStatement.setString(2, user.getFirstName());
        	preparedStatement.setString(3, user.getLastName());
        	return preparedStatement.executeUpdate();
        } catch(SQLException sqle) {
        	System.out.println(sqle);
        	return 0;
        } finally {
        	DBUtil.closePreparedStatement(preparedStatement);
        	pool.freeConnection	(connection);
        }
    }

	public static int update(User user) {
		ConnectionPool pool = ConnectionPool.getInsance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        
        String query = "UPDATE User SET " +
        		"firstName = ?, " +
        		"lastName = ?, " +
        		"email = ?";
        
        try {
        	preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setString(1, user.getFirstName());
        	preparedStatement.setString(2, user.getLastName());
        	preparedStatement.setString(3, user.getEmail());
        	
        	return preparedStatement.executeUpdate()	;
        } catch(SQLException e) {
        	System.out.println(e);
        	return(0);
        } finally {
        	DBUtil.closePreparedStatement(preparedStatement);
        	pool.freeConnection(connection);
        }
	}
	
	public static int delete(User user) {
		ConnectionPool pool = ConnectionPool.getInsance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        
        String query =
        		"DELETE FROM User WHERE Email = ?";
        try {
        	preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setString(1, user.getEmail());
        	
        	return preparedStatement.executeUpdate();
        } catch(SQLException e) {
        	System.out.println(e);
        	return 0;
        } finally {
        	DBUtil.closePreparedStatement(preparedStatement);
        	pool.freeConnection(connection);
        }
	}
	
	public static boolean emailExists(String email) {
		ConnectionPool pool = ConnectionPool.getInsance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        String query =
        		"SELECT Email From User "
        		+ "WHERE Email = ?";
        try {
        	preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setString(1, email);
        	rs = preparedStatement.executeQuery();
        	return rs.next();
        } catch(SQLException sqle) {
        	System.out.println(sqle);
        	return false	;
        } finally {
        	DBUtil.closeResultSet(rs);
        	DBUtil.closePreparedStatement(preparedStatement);
        	pool.freeConnection(connection);
        }
	}
	
	public static User SelectUser(String email) {
		ConnectionPool pool = ConnectionPool.getInsance();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        String query =
        		"SELECT * From User "
        		+ "WHERE Email = ?";
        try {
        	preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setString(1, email);
        	rs = preparedStatement.executeQuery();
        	User user = null;
        	if (rs.next()) {
        		user = new User();
        		user.setFirstName(rs.getString("firstName"));
        		user.setLastName(rs.getString("lastName"));
        		user.setEmail(rs.getString("email"));
        	}
        	
        	return user;
        	
        } catch(SQLException sqle) {
        	System.out.println(sqle);
        	return null;
        } finally {
        	DBUtil.closeResultSet(rs);
        	DBUtil.closePreparedStatement(preparedStatement);
        	pool.freeConnection(connection);
        }
	}
}
