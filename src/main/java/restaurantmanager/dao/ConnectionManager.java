package restaurantmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;


/**
 * JDBC ConnectionManager
 */
public class ConnectionManager {
	//private static final Logger logger = Logger.getLogger(ConnectionManager.class);
	private static ConnectionManager connectionManager = null;
	private Connection connection = null;
	// Constants for your favourite JDBC-Connection (default connection)
	public final static String DB_URL = "jdbc:mysql://localhost:3306";
	public final static String DATABASE = "restaurant";
	public final static String USER = "sqluser";
	public final static String PASSWORD = "12init34";
	private String connectionUrl = DB_URL + "/" + DATABASE;
	private String urlWithUser;	// = connectionUrl + "?user=" + USER + "&password=" + PASSWORD;
	private String user = USER;
	private String password = PASSWORD;

	/**
	 * Private Constructor
	 */
	private ConnectionManager() {
	}

	/**
	 * Creates an instance of ConnectionManager and loads the specified driver
	 *
	 * @param url
	 *            pass null for default url
	 *            or url like "jdbc:mysql://localhost/test"
	 *            or url like "jdbc:mysql://localhost/test?user=user&password=pw"
	 *            (driver, user and password set to null)
	 * @param user
	 *            pass null for default user or user
	 * @param password
	 *            pass null for default password or password
	 * @return Instance of ConnectionManager
	 * @throws SQLException
	 */
	public static ConnectionManager getInstance(String url, String user, String password) throws SQLException {
                //BasicConfigurator.configure();
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		if (url != null) {
			if (user == null && password == null) {
				connectionManager.urlWithUser = url;
			} else {
				connectionManager.connectionUrl = url;
				connectionManager.urlWithUser = null;
			}
		}
		if (user != null) {
			connectionManager.user = user;
		}
		if (password != null) {
			connectionManager.password = password;
		}
		return connectionManager;
	}

	/**
	 * Creates an instance of ConnectionManager and loads the specified driver
	 * Default user and password will be used.
	 *
	 * @param urlWithUser
	 *            pass null for default connection or COMPLETE url
	 *            with user and password like
	 *            "jdbc:mysql://localhost/test?user=user&password=pw"
	 * @return Instance of ConnectionManager
	 * @throws SQLException
	 */
	public static ConnectionManager getInstance(String urlWithUser) throws SQLException {
		return ConnectionManager.getInstance(urlWithUser, null, null);
	}

	/**
	 * Creates an instance of ConnectionManager and loads the specified driver
	 * Default driver, usr, user and password will be used.
	 *
	 * @return Instance of ConnectionManager
	 * @throws SQLException
	 */
	public static ConnectionManager getInstance() throws SQLException {
            if(connectionManager == null) {
                connectionManager = new ConnectionManager();
            }
            return connectionManager;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() throws SQLException {
		try {
			if (connection == null || connection.isClosed()) {
				// create a new connection if connection was closed or never used
				if (urlWithUser != null) {
					connection = DriverManager.getConnection(urlWithUser);
					//logger.debug("ConnectionUrl: " + urlWithUser);
				} else {
					connection = DriverManager.getConnection(connectionUrl, user, password);
					//logger.debug(" URL: " + connectionUrl +
					//		" User: " + user + " Password: " + password);
				}
				//logger.debug("Verbindung hergestellt!");
			}
		} catch (SQLException ex) {
			//logger.debug("ConnectionUrl: " + urlWithUser);
			//slogger.error("Verbindung konnte nicht hergestellt werden!", ex);
			throw new SQLException(ex);
		}
		return connection;
	}

	/**
	 * Close the connection but keep the ConnectionManager instance
	 */
	public void closeConnection() {
		try {
			if (connection != null)
				connection.close();
			else
				//logger.warn("Verbindung war nicht geöffnet!");
                            System.out.println("");
		} catch (SQLException ex) { 
			//logger.error("Verbindung konnte nicht geschlossen werden!", ex);
		}
	}

	public String getUrlWithUser() {
		return urlWithUser;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
