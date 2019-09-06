package com.rda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DBValidation {

	public static void main(String[] args) throws SQLException {
		
		Connection con = null;
		try {
			con  = DBtoXLS.getConnection();
			if(con !=null) {
				Statement st = con.createStatement();
				ArrayList<String> users = getUsers(st);
				Map<String,ArrayList<String>> mapData = getMapData(st,users);
				printMapData(mapData);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally {
			if(con!=null) con.close();
			System.out.println("COMPLETED!!!!!");
		}

	}
	private static void printMapData(Map<String, ArrayList<String>> mapData) {
		  Iterator it = mapData.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + "|" + pair.getValue().toString());
		        //it.remove(); // avoids a ConcurrentModificationException
		    }
		
	}
	private static Map<String, ArrayList<String>> getMapData(Statement st, ArrayList<String> users) throws SQLException {
		Map<String,ArrayList<String>> mapData = new HashMap<String,ArrayList<String>>(); 
		for(String user : users) {
			ArrayList<String> categories = getCategory(st,user);
			mapData = setMapData(mapData,user,categories);
		}	
		return mapData;
	}
	private static Map<String, ArrayList<String>> setMapData(Map<String, ArrayList<String>> mapData, String user, ArrayList<String> categories) {
		if(!mapData.containsKey(user)) {
			mapData.put(user,categories);
		}		
		return mapData;
	}
	private static ArrayList<String> getCategory(Statement st, String UN) throws SQLException 
	{
		String query="SELECT category_code "
				+ "FROM users as U "
				+ "inner join user_category as UC on UC.user_id = U.user_id "
				+ "inner join category as C on C.category_id =UC.category_id "
				+ "where AES_DECRYPT(U.mns_user_id,\"abcd\")=\""+UN+"\";";
		//System.out.println(UN);
		ResultSet rs = st.executeQuery(query);
		ArrayList<String> categoryList = new ArrayList<String>();
		while(rs.next())
		{
			//System.out.println(rs.getString(1));
			categoryList.add(rs.getString(1));
		}
		return categoryList;
	}	
	private static ArrayList<String> getVendor(Statement st, String UN) throws SQLException 
	{
		String query="SELECT vendor_code "
				+ "FROM users as U "
				+ "inner join user_vendor as UV on UV.user_id = U.user_id "
				+ "inner join Vendor as V on V.Vendor_id = UV.vendor_id "
				+ "where AES_DECRYPT(U.mns_user_id,\"abcd\")=\""+UN+"\";";
		//System.out.println(UN);
		ResultSet rs = st.executeQuery(query);
		ArrayList<String> vendorList = new ArrayList<String>();
		while(rs.next())
		{
			//System.out.println(rs.getString(1));
			vendorList.add(rs.getString(1));
		}
		return vendorList;
	}
	private static ArrayList<String> getUsers(Statement st) throws SQLException 
	{
		String query="SELECT CAST(AES_DECRYPT(mns_user_id, \"abcd\") as CHAR(50)) from users;";
		ResultSet rs = st.executeQuery(query);
		ArrayList<String> userList = new ArrayList<String>();
		while(rs.next())
		{
			//System.out.println(rs.getString(1));
			userList.add(rs.getString(1));
		}
		return userList;
	}
}