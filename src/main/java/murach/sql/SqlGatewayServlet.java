package murach.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SqlGatewayServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		String sqlStatement = request.getParameter("sqlStatement");
		String sqlResult = "";
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// get a connection
			String dbURL = "jdbc:mysql://localhost:3306/murach_test";
			String dbUser = "root";
			String dbPassword = "password";
			Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
			
			// create a statement
			Statement statement = connection.createStatement();
			
			// parse the SQL String
			sqlStatement = sqlStatement.trim();
			if (sqlStatement.length() >= 6) {
				String sqlType = sqlStatement.substring(0, 6);
				if (sqlType.equalsIgnoreCase("SELECT")) {
					// create a HTML for the result set
					ResultSet resultSet = statement.executeQuery(sqlStatement);
					sqlResult = SQLUtil.getHtmlTable(resultSet);
					resultSet.close();
				}
				else {
					int i = statement.executeUpdate(sqlStatement);
					if (i == 0) {
						sqlResult = "<p>The statement executed successfully.</p>";
					}
					else {
						sqlResult = "<p>The statement executed successfully.<br>" 
								+ i + " row(s) affected.<p>";
					}
				}
			}
			statement.close();
			connection.close();
		} catch (ClassNotFoundException cnfe) {
			sqlResult = "<p>Error loading the database driver: <br>"
					+ cnfe.getMessage() + "</p>";
		} catch (SQLException sqle) {
			sqlResult = "<p>Error executing SQL statement: <br>"
					+ sqle.getMessage() + "</p>";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("sqlResult", sqlResult);
		session.setAttribute("sqlStatement", sqlStatement);
		
		String url = "/ch12.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}
