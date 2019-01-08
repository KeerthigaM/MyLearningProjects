package databasesTutorial;
import java.sql.*;
import java.util.Scanner;
public class CreateDB 
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException 
	  {   		  
		Connection connection = connectSQLServer();
		connection.close();
	  }  
    public static Connection connectSQLServer() throws ClassNotFoundException, SQLException 
	  {
		  	Scanner scnr = new Scanner(System.in);
		    System.out.println("Please provide below details to connect Oracle Database");
		    System.out.println("Select Database Type");
		    System.out.println("1:OpenOffice Base");
		    System.out.println("2:MS Office Access");
		    System.out.println("3:Oracle DB");
		    System.out.println("4:Oracle SQL server DB");
		    System.out.println("5:MYSQL DB");
		    String DBType = scnr.next();		    
		    String driver=getDriverFromDBType(DBType);
		    Class.forName(driver);
		    Connection connection=getConnectionFromDBType(DBType);		    
		    if (connection != null) 
		    	{
		    	System.out.println("Successfullly connected to DB");
		    	}
		    else
		    	{
		    	System.err.println("Failed to connect to DB");
		    	}
		    scnr.close();
		   return connection;
	  }
	private static Connection getConnectionFromDBType(String DBType) throws SQLException {
		Connection connection=null;
		Scanner scanner = new Scanner(System.in);
		String UN="",PASS="",HOST="@localhost",PORT="1521",DBName="";
		if(DBType.equals("1")|| DBType.equals("2")) 
	    {
	    	System.out.println("Enter File Path with File Name and extension");
	    	DBName=scanner.nextLine();
	    }
		else 
	    {
	    	System.out.println("Enter Database Name");
	    	DBName=scanner.next();
	    	System.out.println("Enter UserName");
			UN = scanner.next();
			System.out.println("Enter Password");
			PASS = scanner.next();
			if(DBType.equals("3")|| DBType.equals("4"))
			{
				System.out.println("Enter Host Name");
				HOST = scanner.next();
				System.out.println("Enter PORT Number");
				PORT = scanner.next();
			}	
	    }	
		System.out.println("Connecting...");
		switch(DBType)
		{
		case "1":
				connection = DriverManager.getConnection("jdbc:hsqldb:"+DBName,"sa","");break;//OpenOffice Base
		case "2":
				connection = DriverManager.getConnection("jdbc:ucanaccess://"+DBName);break;//MS Office Access
		case "3":
				connection = DriverManager.getConnection("jdbc:oracle:thin:"+HOST+":"+PORT+":xe"+DBName,UN,PASS);break;//Oracle DB
		case "4":
				connection = DriverManager.getConnection("jdbc:sqlserver://"+HOST+":"+PORT+";databaseName="+DBName,UN,PASS);break;//Oracle SQL server DB		
		case "5":
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBName,UN,PASS);break;//MYSQL DB
		}
		scanner.close();
		return connection;
	}
	private static String getDriverFromDBType(String dBType) 
	{
		String driver="";
		switch(dBType)
		{
		case "1":driver="org.hsqldb.jdbcDriver";break;//OpenOffice Base
		case "2":driver="org.hsqldb.jdbcDriver";break;//MS Office Access
		case "3":driver="oracle.jdbc.driver.OracleDriver";break;//Oracle DB
		case "4":driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";break;//Oracle SQL server DB	
		case "5":driver="com.mysql.jdbc.Driver";break;//MySQL server DB
		default: driver="oracle.jdbc.driver.OracleDriver";break;//Oracle DB
		}
		return driver;
	}
}

