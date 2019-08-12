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

	public static ArrayList<String> readFromExcel(String filepath) throws FilloException 
	{
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(filepath);
		String strQuery="Select * from MnS";
		Recordset recordset=connection.executeQuery(strQuery);
		ArrayList<String> empID = new ArrayList<String>(); 
		while(recordset.next()){	
			empID.add(recordset.getField("Emp_ID").toString());
		}
		recordset.close();
		connection.close();
		return empID;
	}	
}