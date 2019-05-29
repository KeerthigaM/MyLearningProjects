package com.rda;

import java.sql.*;

public class DB
{
	public static void main(String args[]) throws SQLException 
	{
		Connection con = connectDB();
	}	

	private static Connection connectDB() {		 
         Connection conn = null;
         String url = "jdbc:mysql://rdamysqldev.mysql.database.azure.com:3306/rda?verifyServerCertificate=true&useSSL=true&requireSSL=true&serverTimezone=Europe/London";
         url="jdbc:mysql://rda-pt.cvmqfdjj1q2s.eu-west-1.rds.amazonaws.com:3306/rda_pt?verifyServerCertificate=true&useSSL=true&requireSSL=true&serverTimezone=Europe/London";
         String driver = "com.mysql.jdbc.Driver";
         String userName = "rdauser@rdamysqldev"; 
         userName="root";
         String password = "Rdausr@123"; try {
        	 password ="root";
         Class.forName(driver);
         System.out.println("Connecting to "+url);
         conn = DriverManager.getConnection(url,userName,password);
         if(conn!=null)System.out.println("Connected to the database");
         else System.out.println("Unable to connect to database");
         //conn.close();              
         } catch (Exception e) {
              e.printStackTrace();
         }		
         return conn;
	}
}