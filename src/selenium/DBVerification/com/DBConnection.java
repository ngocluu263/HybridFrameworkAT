package selenium.DBVerification.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import selenium.Conf.com.ConfigurationInterface;
import selenium.Conf.com.ReadTestEnvConfiguration;
import selenium.XLparser.com.ReadXL;

/*
 * This class will return DB connection.
 * Return DB connection for MySQL or MS SQL Based on configuration values
 */
public class DBConnection {

	//Logging details in log.property file 
		static Logger log = Logger.getLogger(DBConnection.class.getName());
		static final String LOG_PROPERTIES_FILE = "Configuration/log.properties";
	
		public Connection DBconnectionStr(String DBName) throws ClassNotFoundException, SQLException {
			
			//Reading configuration file to get Test Scenarios from Input XL file
			log.info("Step: Reading DB Connection details");
			log.info("Call to Read configuration file...");
			ConfigurationInterface conf = new ReadTestEnvConfiguration();
			String DBConnection = conf.getDBConnectionString();
			String DBUser = conf.getDBUserID();
			String DBPassword = conf.getPass();
			String connection = ("jdbc:sqlserver://" + DBConnection +";user=" + DBUser + ";password=" + DBPassword + ";database=" + DBName);
			Connection conn = DriverManager.getConnection(connection);	
			return conn;
		}
	
}
