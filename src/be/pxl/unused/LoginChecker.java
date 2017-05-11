package be.pxl.unused;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.pxl.crypto.MD5;
import be.pxl.crypto.RandomStringGenerator;

public abstract class LoginChecker {
	private static abstract class ConnectionFactory {
		private static Connection connection;

		private static Connection getConnection() throws SQLException {
			if (connection == null || connection.isClosed())
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
				String hash = MD5.getHash(pass + salt);
				ResultSet loginresult = statement
						.executeQuery("SELECT Password FROM Users WHERE Username ='" + username + "';");
				if (loginresult.next()) {
					String wachtwoord = loginresult.getString(1);
					loginresult.close();
					return wachtwoord.equals(hash);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void createUser(String username, String pass) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement statement = connection.createStatement();
			String salt = RandomStringGenerator.generateRandomString(30);
			String hash = MD5.getHash(pass + salt);
			statement.executeUpdate("INSERT INTO Users(Username,Password,Salt) VALUES('" + username + "','" + hash
					+ "','" + salt + "');");
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
