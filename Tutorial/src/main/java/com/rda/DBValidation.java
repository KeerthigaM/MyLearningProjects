package com.rda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DBValidation {

	public static void main(String[] args) throws SQLException {		
		//getCategoryVendorID();
		getCategoryVendorData();
	}
	public static void printMapData(Map<String, ArrayList<String>> mapData) {
		  Iterator it = mapData.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + "|" + pair.getValue().toString());
		        //it.remove(); // avoids a ConcurrentModificationException
		    }
		
	}
	private static Map<String, ArrayList<String>> getMapData(Statement st, ArrayList<String> users,String data) throws SQLException {
		Map<String,ArrayList<String>> mapData = new HashMap<String,ArrayList<String>>(); 
		for(String user : users) {
			switch(data) {
			case "category": ArrayList<String> categories = getCategory(st,user); 
					 mapData = setMapData(mapData,user,categories);
					 break;
			case "vendor": ArrayList<String> vendors = getVendor(st,user); 
					 mapData = setMapData(mapData,user,vendors);
					 break;
				}
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
				+ "where AES_DECRYPT(U.mns_user_id,\"insertpasswordhere\")=\""+UN+"\";";
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
				+ "where AES_DECRYPT(U.mns_user_id,\"insertpasswordhere\")=\""+UN+"\";";
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
		String query="SELECT CAST(AES_DECRYPT(mns_user_id, \"insertpasswordhere\") as CHAR(50)) from users;";
		ResultSet rs = st.executeQuery(query);
		ArrayList<String> userList = new ArrayList<String>();
		while(rs.next())
		{
			//System.out.println(rs.getString(1));
			userList.add(rs.getString(1));
		}
		return userList;
	}
	public static String getUsersId(Statement st,String mnsUserID) throws SQLException 
	{
		String query="SELECT user_id from users where CAST(AES_DECRYPT(mns_user_id, \"insertpasswordhere\") as CHAR(50))='"+mnsUserID+"';";
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			query = rs.getString(1);
		}
		return query;
	}
	public static String getVendorId(Statement st, String vendorCode) throws SQLException {
		String query="SELECT vendor_id from vendor where vendor_code='"+vendorCode+"';";
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			query = rs.getString(1);
		}
		return query;
	}
	public static String getCategoryId(Statement st, String cat) throws SQLException {
		String query="SELECT category_id from category where category_code='"+cat+"';";
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			query = rs.getString(1);
		}
		return query;
	}
	public static void getCategoryVendorID() throws SQLException {
		Connection con = null;
		try {
			con  = DBtoXLS.getConnection();
			if(con !=null) {
				Statement st = con.createStatement();
				String data="T00B0144-;T00S0054-F01825;T00B0143-;T00S0055-F01826;T00B0142-;T00S0052-F01815;T00B0141-;T00S0053-F01823;T00S0050-F01788;T00B0140-;T00S0051-F01799;T0020157-;T0020156-;T0020159-;T0020158-;T0020164-;T0020163-;T0020166-F00518;T0020165-;T4567890-;T0020160-;T0020162-;T0020161-;T00S0049-F01752;T00B0149-;T00S0047-F01733;T00B0148-;T00S0048-F01749;T00B0147-;T00S0045-F01718;T00B0146-;T00B0145-;T00S0046-F01721;T00S0065-F01903;T00B0133-;T00S0066-F01906;T00B0132-;T00B0131-;T00S0063-F01887;T00S0064-F01901;T00B0130-;T00S0061-F01866;T00S0062-F01873;T00S0060-F01860;T0020167-;T00DG001-;T0020175-;T0020174-;T0020171-;T0020173-;T0020172-;T00B0139-;T00B0138-;T00S0058-F01853;T00B0137-;T00S0059-F01857;T00B0136-;T00S0056-F01844;T00B0135-;T00S0057-F01845;T00B0134-;T00S0076-F01953;T00S0077-F01960;T00S0110-F02279;T00S0074-F01946;T00S0075-F01950;T00S0072-F01933;T00S0073-F01945;T00S0070-F01921;T00S0071-F01922;T00S0108-F02240;T00S0109-F02248;T00S0106-F02224;T00S0107-F02234;T00S0104-F02173;T00S0105-F02196;T00S0069-F01918;T00S0102-F02128;T00S0103-F02163;T00S0067-F01908;T00S0100-F02106;T00S0101-F02124;T00S0068-F01917;T00S0087-F02020;T00S0120-F01504;T00S0088-F02021;T00S0085-F02009;T00S0086-F02014;T00S0083-F02004;T00B0150-;T00S0084-F02007;T00S0081-F01983;T00S0082-F02000;T00S0080-F01974;T0020153-;T0020152-;T0020155-;T0020154-;T0020151-;T00S0119-F01502;T00S0117-F00518;T00S0118-F01501;T00S0115-F02374;T00S0116-F02422;T00S0113-F02343;T00S0114-F02356;T00S0078-F01967;T00S0111-F02285;T00S0112-F02332;T00S0079-F01972;T00S0010-F01515;T00S0098-F02103;T00S0099-F02104;T00S0011-F01516;T00S0096-F02058;T00S0097-F02063;T00S0094-F02050;T00S0095-F02055;T00S0092-F02045;T00S0093-F02046;T00S0090-F02033;T00S0091-F02036;T00S0009-F01514;T00S0007-F01511;T00S0008-F01512;T00S0005-F01505;T00S0006-F01507;T00S0003-F01502;T00S0004-F01504;T00S0001-F00518;T00S0089-F02029;T00S0002-F01501;T00S0021-F01528;T00S0022-F01529;T00S0020-F01526;T0020006-;T0020003-;T0020002-F01611;T0020005-;T0020004-;T00S0018-F01524;T00S0019-F01525;T00S0016-F01522;T00S0017-F01523;T00S0014-F01520;T00S0015-F01521;T00S0012-F01518;T00S0013-F01519;T00B0122-;T00B0001-;T00S0032-F01586;T00B0121-;T00S0033-F01594;T00S0030-F01562;T00S0031-F01584;T00S0029-F01558;T00B0129-;T00S0027-F01546;T00B0128-;T00S0028-F01552;T00B0127-;T00B0126-;T00S0025-F01532;T00B0125-;T00S0026-F01534;T00S0023-F01530;T00B0124-;T00S0024-F01531;T00B0123-;T00S0043-F01706;T00S0044-F01714;T00S0041-F01686;T00S0042-F01702;T00S0040-F01671;T00S0038-F01634;T00S0039-F01643;T00S0036-F01611;T00S0037-F01623;T00S0034-F01597;T00S0035-F01606";
				//String data="T00B0144-F60;T00S0054-F15,F33;T00B0143-F23;T00S0055-F07,F15,F16,F33,F44,F61;T00B0142-F74;T00S0052-F07,F12,F13,F61;T00B0141-F27;T00S0053-F15;T00S0050-F01,F02,F03,F04,F38,F62,F63;T00B0140-F34;T00S0051-F02,F03,F04,F07,F08,F10,F19,F63,F75,F76,F79;T0020157-;T0020156-;T0020159-;T0020158-;T0020164-;T0020163-;T0020166-F07,F19,F21,F22,F35;T0020165-;T4567890-;T0020160-;T0020162-;T0020161-;T00S0049-F03,F04,F07,F15,F16,F60,F75,F76;T00B0149-F09;T00S0047-F03,F06,F10,F33,F60,F76,F90;T00B0148-F19;T00S0048-F03,F04,F07,F16,F35,F60,F62,F63,F64,F75,F76,F90;T00B0147-F13;T00S0045-F13,F33;T00B0146-F25;T00B0145-F77;T00S0046-F02,F03,F17,F38,F62,F63;T00S0065-F20,F23;T00B0133-F18,F90;T00S0066-F02,F03,F04,F17,F63,F64,F65,F73,F75,F76;T00B0132-F16,F30;T00B0131-F15,F75;T00S0063-F20,F21,F23;T00S0064-F03,F04,F07,F15,F16,F38,F60,F61,F62,F63,F64,F75,F76,F90;T00B0130-F05,F38;T00S0061-F07,F09;T00S0062-F18,F19,F33,F60,F75,F76,F90;T00S0060-F04,F07,F08,F09,F15,F16,F60,F64,F75,F76,F90;T0020167-F07,F19,F21,F22,F35;T00DG001-;T0020175-F07;T0020174-F21;T0020171-F20;T0020173-F19;T0020172-F33;T00B0139-F39;T00B0138-F33;T00S0058-F13,F33,F75;T00B0137-F44;T00S0059-F03,F04,F06,F07,F17,F20,F64,F75,F76;T00B0136-F62;T00S0056-F03,F04,F07,F11,F15,F16,F17,F38,F60,F61,F62,F63,F64,F75,F76,F90;T00B0135-F11,F65;T00S0057-F20,F23;T00B0134-F17,F63;T00S0076-F06,F07,F22,F30,F64,F75,F76;T00S0077-F20,F23;T00S0110-F20,F23;T00S0074-F07,F09,F10,F13,F18,F19,F25,F33,F75,F76;T00S0075-F07,F22,F30,F61,F75;T00S0072-F06,F61,F64;T00S0073-F06,F15,F16,F63;T00S0070-F01,F02,F03,F04,F07,F38,F60,F62,F63,F64,F73,F76,F90;T00S0071-F02,F15,F16,F60,F62;T00S0108-F35;T00S0109-F20;T00S0106-F20;T00S0107-F06;T00S0104-F09;T00S0105-F20;T00S0069-F20,F21,F23;T00S0102-F20;T00S0103-F07,F20,F21,F22,F23,F76;T00S0067-F07,F10,F18,F33;T00S0100-F10,F12;T00S0101-F02;T00S0068-F01,F02,F03,F04,F05,F07,F11,F16,F17,F38,F62,F63,F64,F75,F76;T00S0087-F20;T00S0120-F25,F33;T00S0088-F20;T00S0085-F02,F03,F04,F62,F63,F64,F75,F76;T00S0086-F01,F11;T00S0083-F02;T00B0150-F02;T00S0084-F02,F03,F04,F11,F17,F62,F63,F64,F65,F73,F76,F77;T00S0081-F09,F27,F75,F76,F79;T00S0082-F02,F04,F11,F17,F62,F63,F75,F77;T00S0080-F03,F04,F07,F09,F27,F33,F38,F60,F64,F75,F76,F79,F90;T0020153-F11,F17,F18,F33,F44;T0020152-F03,F04,F05,F06,F15;T0020155-F02,F09,F13,F19,F25,F77;T0020154-F23,F27,F34,F39,F60,F74;T0020151-F01,F07,F08,F10,F12,F19,F21,F22,F35;T00S0119-F11,F17,F75;T00S0117-F06,F10,F12,F13;T00S0118-F07,F18,F19,F60,F76,F77,F79;T00S0115-F06;T00S0116-F38;T00S0113-F20,F21;T00S0114-F09;T00S0078-F02,F06,F76;T00S0111-F11,F17,F38;T00S0112-F20,F21;T00S0079-F03,F07,F09,F27,F33,F63,F76,F79;T00S0010-F12;T00S0098-F11,F17,F77;T00S0099-F20;T00S0011-F02,F17,F38,F63;T00S0096-F01,F03,F04,F07,F15,F16,F38,F62,F63,F75,F76,F90;T00S0097-F15,F16;T00S0094-F07,F12,F75;T00S0095-F07,F12;T00S0092-F03,F15;T00S0093-F06,F17,F76;T00S0090-F76;T00S0091-F02,F07,F61,F76;T00S0009-F09,F13,F33;T00S0007-F08;T00S0008-F19;T00S0005-F19,F33;T00S0006-F20,F23;T00S0003-F07,F12,F33;T00S0004-F10,F13,F76;T00S0001-F07,F19,F21,F22,F35;T00S0089-F07,F11,F15,F16,F17,F60,F61,F76;T00S0002-F02,F03,F06,F08,F09,F13,F33,F61,F73,F75;T00S0021-F22;T00S0022-F20;T00S0020-F07,F10,F12,F18,F33,F75,F76;T0020006-;T0020003-F38;T0020002-F38;T0020005-;T0020004-;T00S0018-F01,F02,F03,F04,F07,F15,F38,F60,F62,F63,F64,F65,F73,F75,F76,F90;T00S0019-F04,F16,F38,F62,F63,F76,F77;T00S0016-F07,F12;T00S0017-F22;T00S0014-F23;T00S0015-F20,F23;T00S0012-F20,F23;T00S0013-F19;T00B0122-F12,F20;T00B0001-;T00S0032-F07,F09,F10,F12,F18,F19,F33,F61;T00B0121-F07,F79;T00S0033-F01,F02,F38,F63,F76,F77;T00S0030-F04,F19,F33,F60,F62,F75,F77;T00S0031-F01,F02,F03,F04,F07,F38,F60,F63,F64,F75,F76;T00S0029-F07,F12,F33,F61,F75;T00B0129-F06,F73;T00S0027-F02,F04,F07,F08,F11,F17,F63,F65,F75,F76,F90;T00B0128-F03,F76;T00S0028-F07,F10,F18,F19,F33,F39,F75,F76;T00B0127-F04,F61;T00B0126-F01,F64;T00S0025-F20,F23;T00B0125-F08,F22;T00S0026-F06;T00S0023-F01,F02,F03,F04,F07,F08,F11,F15,F16,F38,F60,F62,F63,F64,F65,F73,F75,F76,F90;T00B0124-F14,F21;T00S0024-F06;T00B0123-F10,F35;T00S0043-F02,F60,F63,F64,F75;T00S0044-F03,F06,F07,F60,F75,F76;T00S0041-F19,F33,F35;T00S0042-F14,F33;T00S0040-F09,F33,F75;T00S0038-F04,F06,F07,F08,F63,F64,F75;T00S0039-F10,F12,F18,F33,F76;T00S0036-F02,F03,F04,F05,F11,F16,F17,F38,F60,F63,F73,F76;T00S0037-F02,F04,F06,F07,F08,F16,F30,F75,F76,F90;T00S0034-F02,F04,F06,F07,F08,F09,F27,F33,F61,F73,F74,F75,F76,F77,F79;T00S0035-F01,F02,F11,F17,F34,F38,F63,F64,F76";
				String[] dataArr = data.split(";");
				ArrayList<String> dataList = new ArrayList<String>(Arrays.asList(dataArr));
				int count=0;
				for(String dat : dataList) {
					String[] datArr = dat.split("-");
					ArrayList<String> datList = new ArrayList<String>(Arrays.asList(datArr));
					for(int i=0;i<datList.size();i=i+2) {
						if(datList.size()!=1 && datList.get(i)!="") {
							System.out.println("("+(count=count+1)+","+DBValidation.getUsersId(st,datList.get(0))+","+DBValidation.getVendorId(st,datList.get(1))+"),");
							String[] daArr = datList.get(1).split(",");
							ArrayList<String> daList = new ArrayList<String>(Arrays.asList(daArr));
							for(String cat:daList) {
							//	System.out.println("("+(count=count+1)+","+DBValidation.getUsersId(st,datList.get(0))+","+DBValidation.getCategoryId(st,cat)+"),");	
							}
							}
						}
					}
				}
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally {
			if(con!=null) con.close();
			System.out.println("COMPLETED!!!!!");
		}
	}
	public static void getCategoryVendorData() throws SQLException {
		Connection con = null;
		try {
			con  = DBtoXLS.getConnection();
			if(con !=null) {
				Statement st = con.createStatement();
				ArrayList<String> users = getUsers(st);
				Map<String,ArrayList<String>> mapData = getMapData(st,users,"category");
				//Map<String,ArrayList<String>> mapData = getMapData(st,users,"vendor");
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
}