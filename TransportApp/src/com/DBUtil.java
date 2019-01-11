package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class DBUtil 
{
	public static void main(String[] args) throws ParseException ,ClassNotFoundException, SQLException	
	{
		Statement s=createStatement();
		//createTable(s);
    	//dropTable(s);
    	//insertData(s);
    	//readTable(s);
		s.close();
	}
	public static Statement createStatement() throws ClassNotFoundException, SQLException
	{
		String currentDir = System.getProperty("user.dir");
		currentDir="C:/Users/886758/Documents/Keerthiga Stuffs/Java Learning/Learning Workspace/TransportApp";
        String msAccessDBName =currentDir.replace("\\", "/") + "/DB/KirthiMSAccessDB.mdb";
        Connection connection = null;
        Statement statement = null;
        //Class.forName("org.hsqldb.jdbcDriver");
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        connection=DriverManager.getConnection("jdbc:ucanaccess://"+msAccessDBName);		
		if(connection!=null)
	    {
	    	System.out.println("Success");
	    	statement=connection.createStatement();
	    } 
		return statement;
	}
	private static void insertData(Statement s) throws SQLException 
	{    
       String insertData = "INSERT INTO TransportTable (INVOICEDATE,INVOICENUMBER,CONSINOR,LRNUMBER,DELIVERYDATE,PODSTATUS,VEHICLENUMBER,DRIVERNAME,REMARKS) values ('1','kirthi','kirthi64','kirthi@gmail.com','india','1','1','1','1')";
       boolean status=s.execute(insertData);
       System.out.println("Insert Data Status "+status);
	}
	private static void readTable(Statement s) throws SQLException 
	{    
		   String readTable = "SELECT * FROM TransportTable";		   
		   ResultSet rs = s.executeQuery(readTable);
			while (rs.next()) 
			{
				System.out.println(rs.getInt(1)+" == "+rs.getString(2)+" == "+rs.getString(3)+" == "+rs.getString(4)+" == "+rs.getString(5));
			}
	}
	private static void dropTable(Statement s)  throws SQLException 
	{    
	   String deleteTable = "DROP TABLE TransportTable";
	   boolean status=s.execute(deleteTable);
	   System.out.println("Delete Status "+status);
	}
	private static void createTable(Statement s) throws SQLException 
	{    
	   String createTable = "CREATE TABLE TransportTable (INVOICEDATE Text(32), INVOICENUMBER Text(32), CONSINOR Text(32), LRNUMBER Text(32), DELIVERYDATE Text(32), PODSTATUS Text(32), VEHICLENUMBER Text(32), DRIVERNAME Text(32), REMARKS Text(32))";
       boolean status=s.execute(createTable);
       System.out.println("Create Status "+status);
	}
}
