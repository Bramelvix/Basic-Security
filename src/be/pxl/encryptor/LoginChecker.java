package be.pxl.encryptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginChecker {
	private static class ConnectionFactory {
		private static Connection connection;

		public static Connection getConnection() throws SQLException {
			if (connection == null)
				connection = DriverManager.getConnection("jdbc:mysql://213.136.26.180:3306/bramlvx_basicSecurity",
						"bramlvx_user", "userpass");
			return connection;
		}
	}

	public static boolean isLoginValid(String username, String pass) {
		try (Connection connection = ConnectionFactory.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT Salt FROM Users WHERE Username ='" + username + "';")) {

			if (resultSet.next()) {
				String salt = resultSet.getString(1);
				String hash = MD5.getHash(pass+salt);
				System.out.println(hash);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}
