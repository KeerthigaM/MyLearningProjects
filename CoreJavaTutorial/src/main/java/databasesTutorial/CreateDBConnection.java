package databasesTutorial;

import java.text.ParseException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDBConnection {
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException {
		File resourcesDirectory = new File("src/main/resources");
		String filePath = resourcesDirectory.getAbsolutePath();
		Connection con = null;
		con = derbyDB(con,filePath);
		//con = openOfficeDB(con,filePath);
		//con = msOfficeDB(con,filePath);
		//con = oracleDB(con);
		//con = oracleSQLServerDB(con);
		//con = mySQLDB(con);
		if (con != null) {
			System.out.println("Success");
		}
		con.close();
	}
	public static Connection derbyDB(Connection con, String filePath) throws SQLException, ClassNotFoundException {		
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		System.setProperty("derby.system.home", filePath+"/.derby");
		con = DriverManager.getConnection("jdbc:derby:kirthidb;create=true;user=kirthi;password=2492");
		return con;
	}
	public static Connection openOfficeDB(Connection con, String filePath) throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");
		String OpenOfficeDBFilePath = filePath+"/OpenOfficeDB.odb";
		con = DriverManager.getConnection("jdbc:hsqldb:file:" + OpenOfficeDBFilePath, "sa", "");
		return con;
	}
	public static Connection msOfficeDB(Connection con,String filePath) throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");
		String msAccessDBFilePath = filePath+"/MSAccessDB.mdb";
		con = DriverManager.getConnection("jdbc:ucanaccess://" + msAccessDBFilePath);
		return con;
	}
	public static Connection oracleDB(Connection con) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String HOST="", PORT="", DBName="", UN="", PASS="";
		con = DriverManager.getConnection("jdbc:oracle:thin:"+HOST+":"+PORT+":xe"+DBName,UN,PASS);
		return con;
	}
	public static Connection oracleSQLServerDB(Connection con) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String HOST="", PORT="", DBName="", UN="", PASS="";
		con = DriverManager.getConnection("jdbc:sqlserver://"+HOST+":"+PORT+";databaseName="+DBName,UN,PASS);
		return con;
	}
	public static Connection mySQLDB(Connection con,String filePath) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String DBName="", UN="", PASS="";
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBName,UN,PASS);
		return con;
	}
}
