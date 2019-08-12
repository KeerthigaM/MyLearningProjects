package com.rda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Fillo;

public class DBtoXLS
{
	public static String filloquery = "INSERT INTO pt_rda(A,B,C,D,E,F) VALUES(";
	public static void main(String args[]) throws SQLException 
	{	
		Connection con = null;
		try {
			con = getConnection("rda_pt");
			if(con !=null) {
				Statement st = con.createStatement();
				//tblToExcel(st);
				readtable(st);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally {
			if(con!=null) con.close();
			System.out.println("COMPLETED!!!!!");
		}
	}	

	private static void readtable(Statement st) throws SQLException {
		String query = "SELECT AES_DECRYPT(mns_user_id, \"insertpasswordhere\"), category_code, vendor_code FROM users as U inner join user_vendor as UV on UV.user_id = U.user_id inner join Vendor as V on V.Vendor_id = UV.vendor_id inner join user_category as UC on UC.user_id = U.user_id inner join category as C on C.category_id =UC.category_id where AES_DECRYPT(U.mns_user_id, \"insertpasswordhere\") = \"T00S0001\";";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) { 
		System.out.println((rs.getString(2))+" || "+(rs.getString(3)));	
		}
	}

	private static void tblToExcel(Statement st) throws SQLException, FilloException {
		rolesTblToXl(st);
		categortTblToXl(st);
		usersTblToXl(st);
		vendorTblToXl(st);
		userCategoryTblToXl(st);
		userVendorTblToXl(st);		
	}

	private static void userVendorTblToXl(Statement st)  throws SQLException, FilloException {
		String append = "'user_vendor_id','user_id','vendor_id','','','')";
		importToExcel(filloquery+append);
		ResultSet rs = readUserVendorTbl(st);
		while(rs.next()) { 		
			append = "'"+rs.getInt(1)+"','"
					   +nullCheck(rs.getString(2))+"','"
					   +nullCheck(rs.getString(3))+"','','','')";
			importToExcel(filloquery+append);		
		}	
		importToExcel(filloquery+"'','','','','','')");
	}

	private static void userCategoryTblToXl(Statement st) throws SQLException, FilloException {
		String append = "'user_category_id','user_id','category_id','','','')";
		importToExcel(filloquery+append);
		ResultSet rs = readUserCategoryTbl(st);
		while(rs.next()) { 		
			append = "'"+rs.getInt(1)+"','"
					   +nullCheck(rs.getString(2))+"','"
					   +nullCheck(rs.getString(3))+"','','','')";
			importToExcel(filloquery+append);	
		}
		importToExcel(filloquery+"'','','','','','')");
	}

	private static void vendorTblToXl(Statement st) throws SQLException, FilloException {
		String append = "'vendor_id','vendor_code','vendor_name','','','')";
		importToExcel(filloquery+append);
		ResultSet rs = readVendorTbl(st);
		while(rs.next()) { 		
			append = "'"+rs.getInt(1)+"','"
					   +nullCheck(rs.getString(2))+"','"
					   +nullCheck(rs.getString(3)).replace("'", "\"")+"','','','')";
			importToExcel(filloquery+append);		
		}	
		importToExcel(filloquery+"'','','','','','')");
	}

	private static void rolesTblToXl(Statement st) throws SQLException, FilloException {
		String append = "'role_id','role_code','role_name','banner','','')";
		importToExcel(filloquery+append);
		ResultSet rs = readRolesTbl(st); 
		while(rs.next()) {
		append = "'"+rs.getInt(1)+"','"
					+nullCheck(rs.getString(2))+"','"
					+nullCheck(rs.getString(3))+"','"
					+nullCheck(rs.getString(4))+"','','')";
		importToExcel(filloquery+append);
		}	
		importToExcel(filloquery+"'','','','','','')");
	}

	private static void usersTblToXl(Statement st) throws SQLException, FilloException {
		String append = "'user_id','mns_user_id','first_name','last_name','email','role_id')";
		importToExcel(filloquery+append);
		ResultSet rs = readUsersTbl(st);
		while(rs.next()) { 		
			append = "'"+rs.getInt(1)+"','"
					   	+nullCheck(rs.getString(2))+"','"
					   	+nullCheck(rs.getString(3)).replace("'", "\"")+"','"
					   	+nullCheck(rs.getString(4)).replace("'", "\"")+"','"
					   	+nullCheck(rs.getString(5))+"','"
						+nullCheck(rs.getString(6))+"')";
			importToExcel(filloquery+append);			
		}
		importToExcel(filloquery+"'','','','','','')");		
	}

	private static void categortTblToXl(Statement st) throws SQLException, FilloException {
		String append = "'category_id','category_code','category_name','','','')";
		importToExcel(filloquery+append);
		ResultSet rs = readCategoryTbl(st);
		while(rs.next()) { 		
			append = "'"+rs.getInt(1)+"','"
					   +nullCheck(rs.getString(2))+"','"
					   +nullCheck(rs.getString(3))+"','','','')";
			importToExcel(filloquery+append);		
		}
		importToExcel(filloquery+"'','','','','','')");		
	}

	private static void importToExcel(String query) throws FilloException {
		String FILEPATH="D:\\Users\\886758\\Documents\\Keerthiga_886758\\M&S\\RDA\\RDA_AWS_DB.xlsx";
		Fillo fillo=new Fillo();
		com.codoid.products.fillo.Connection connection=fillo.getConnection(FILEPATH);
		connection.executeUpdate(query);
		connection.close();	
	}
	
	private static String nullCheck(String data) {
		if (data != null) return data;
		else return "";
	}

	private static ResultSet readUsersTbl(Statement st) throws SQLException {
		String query = "SELECT user_id, AES_DECRYPT(mns_user_id, \"insertpasswordhere\"),AES_DECRYPT(first_name, \"insertpasswordhere\"), AES_DECRYPT(last_name, \"insertpasswordhere\"), AES_DECRYPT(email, \"insertpasswordhere\"), role_id from users;";
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	private static ResultSet readVendorTbl(Statement st)throws SQLException {
		String query = "SELECT vendor.vendor_id, vendor.vendor_code, AES_DECRYPT(vendor_name, \"insertpasswordhere\") as vendor_name FROM vendor;";
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	private static ResultSet readUserVendorTbl(Statement st) throws SQLException {
		String query = "Select * from user_vendor;";
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	private static ResultSet readUserCategoryTbl(Statement st) throws SQLException {
		String query = "Select * from user_category;";
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	private static ResultSet readCategoryTbl(Statement st) throws SQLException {
		String query = "Select * from category;";
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	private static ResultSet readRolesTbl(Statement st) throws SQLException {
		String query = "Select * from roles;";
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	private static Connection getConnection(String DBNAME) throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/"+DBNAME;
        String userName = "root";String password = "root";  
        //url="jdbc:mysql://fodensidrdamql01.mysql.database.azure.com:3306/rda?verifyServerCertificate=TRUE&useSSL=TRUE&requireSSL=TRUE&serverTimezone=Europe/London";
    	//userName="rdauser@fodensidrdamql01"; password="Rdausr@123";
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