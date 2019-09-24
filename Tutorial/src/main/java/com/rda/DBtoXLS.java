package com.rda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Fillo;

public class DBtoXLS
{
	//Please change data encryption password and DB details
	public static String filloquery = "INSERT INTO pt_rda(A,B,C,D,E,F) VALUES(";
	public static String DBNAME = "rda";
	public static String FILEPATH="D:/Users/886758/Documents/Keerthiga_886758/M&S/RDA/RDA_AWS_DB.xlsx";
	public static int maxHeader=6;//not less than 3
	public static boolean consoleFlag=true;
	public static void main(String args[]) throws SQLException 
	{	
		Connection con = null;
		try {
			con = getConnection();
			if(con !=null) {
				Statement st = con.createStatement();
				readtablesData(st);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally {
			if(con!=null) con.close();
			System.out.println("COMPLETED!!!!!");
		}
	}
	public static void readtablesData(Statement st) throws SQLException, FilloException {
		appendValue(st,"USERS");
		appendValue(st,"CATEGORY");
		appendValue(st,"VENDOR");
		appendValue(st,"ROLES");
		appendValue(st,"USER_CATEGORY");
		appendValue(st,"USER_VENDOR");
	}
	public static void appendValue(Statement st,String dBNAME) throws SQLException, FilloException {
		ArrayList<String> header = getHeader(dBNAME,st);
		ResultSet rs = readTable(st,dBNAME);
		//For table header
		String append="'";
		for(String head:header) {
			append=append+head+"','";
		}
		writeData(append,header.size());	
		//For table data
		while(rs.next()) { 	
			append="'";
			for(int i=1;i<=header.size();i++){
				String data = nullCheck(rs.getString(i).replace("'", "\""));
				append = append + data +"','";
			}
			writeData(append,header.size());
		}		
		writeData("'','",1);
	}
	public static void writeData(String append, int headerSize) throws FilloException {
		append=append.substring(0, append.length()-3);
		if(headerSize<=maxHeader){
			for(int i=headerSize;i<=maxHeader;i++) {
				if(i!=maxHeader)
					append=append.concat("','");
					else
					append=append.concat("')");	
			}
		}
		if(consoleFlag)
			System.out.println(append);
		else
			importToExcel(filloquery+append);
	}
	private static ResultSet readTable(Statement st, String dbname) throws SQLException {
		String query="";
		switch(dbname){
		case "USERS": query = "SELECT user_id, AES_DECRYPT(mns_user_id, \"abcd\"),AES_DECRYPT(first_name, \"abcd\"), AES_DECRYPT(last_name, \"abcd\"), AES_DECRYPT(email, \"abcd\"), role_id from users;";break;
		case "VENDOR": query = "SELECT vendor.vendor_id, vendor.vendor_code, AES_DECRYPT(vendor_name, \"abcd\") as vendor_name FROM vendor;";break;
		default: query="SELECT * FROM "+dbname+";";break;
		}
		ResultSet rs = st.executeQuery(query);
		return rs;
	}	
	public static String nullCheck(String data) {
		if (data != null) return data;
		else return "";
	}
	public static ArrayList<String> getHeader(String TableName,Statement st) throws SQLException {
		String query = "SHOW COLUMNS FROM "+TableName;
		ResultSet rs = st.executeQuery(query);
		ArrayList<String> header = new ArrayList<String>();
		while(rs.next())		{
		header.add(rs.getString(1));		
		}
		return header;
	}
	public static void importToExcel(String query) throws FilloException {
		Fillo fillo=new Fillo();
		com.codoid.products.fillo.Connection connection=fillo.getConnection(FILEPATH);
		connection.executeUpdate(query);
		connection.close();	
	}
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/"+DBNAME;
        String userName = "root";String password = "root";
    	Class.forName(driver);
        System.out.println("Connecting to "+url);
        Connection con = DriverManager.getConnection(url,userName,password);   
		if(con!=null)
			{
			System.out.println("Connected to the database");
			}
   	 	else System.out.println("Unable to connect to database");
		return con;
	}	
}