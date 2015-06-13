package selenium.DBVerification.com;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DBExecuteQuery {
	//Logging details in log.property file 
	static Logger log = Logger.getLogger(DBExecuteQuery.class.getName());
	static final String LOG_PROPERTIES_FILE = "Configuration/log.properties";
	
	public ResultSet sqlQuery (Connection conn, String query) throws SQLException {
		Statement sta = conn.createStatement();
		String Sql = query;
		ResultSet rs = sta.executeQuery(Sql);
		return rs;
	}
}
