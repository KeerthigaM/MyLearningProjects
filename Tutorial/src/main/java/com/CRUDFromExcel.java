package com;

import java.io.IOException;
import java.util.ArrayList;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class CRUDFromExcel 
{
	public static void main(String[] args) throws InterruptedException, IOException, FilloException
	{
		String FILEPATH="D:\\Users\\886758\\Downloads\\RDA_AWS_DB.xlsx";
		excel2Console(FILEPATH);
	}
	
	public static boolean updateExcel(String filepath, String id, String mail) throws FilloException {
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(filepath);
		String strQuery="Update MnS Set Emp_MailID='"+mail+"' where Emp_ID='"+id+"'";
		 
		connection.executeUpdate(strQuery);
		 
		connection.close();
		return false;
	}
	
	public static boolean insertExcel(String filepath, ArrayList<String> data) throws FilloException {
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(filepath);
		String strQuery="INSERT INTO dev(Name,Country) VALUES('Peter','UK')";		 
		connection.executeUpdate(strQuery);		 
		connection.close();
		return false;
	}
	
	private static void excel2Console(String FILEPATH) throws FilloException {
		Recordset recordset = readFromExcel(FILEPATH,"sheetname");
		ArrayList<String> header = getHeader(recordset);
		readRS(recordset,header);		
	}
	
	private static void readRS(Recordset recSet, ArrayList<String> header) throws FilloException {
		while(recSet.next()) 
		{
			for(String head:header) {
			System.out.println(head+" : "+recSet.getField(head).toString());
			}
			System.out.println("=========================X=========================");
		}
	}
	
	private static ArrayList<String> getHeader(Recordset rs) throws FilloException 
	{	
		ArrayList<String> header= rs.getFieldNames();
		return header;
	}

	public static Recordset readFromExcel(String filepath,String sheetName) throws FilloException 
	{
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(filepath);
		String strQuery="Select * from "+sheetName;
		Recordset recordset=connection.executeQuery(strQuery);
		return recordset;
	}
}