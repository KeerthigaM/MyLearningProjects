package com;

import java.sql.SQLException;

public class TransportDAO 
{	
	public static boolean save(TransportRegister trans)
	{			
		String insertData = "INSERT INTO TransportTable (INVOICEDATE,INVOICENUMBER,CONSINOR,LRNUMBER,DELIVERYDATE,PODSTATUS,VEHICLENUMBER,DRIVERNAME,REMARKS) values ("+trans+")";
		boolean status = true;
		try {
			status = DBUtil.createStatement().execute(insertData);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return status;		
	}

}
