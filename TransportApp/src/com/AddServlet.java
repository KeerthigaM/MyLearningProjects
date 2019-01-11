package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public static String message="";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{			
		TransportRegister trans = new TransportRegister();
		trans.setINVOICE_DATE(request.getParameter("INVOICE_DATE"));
		trans.setINVOICE_NUMBER(request.getParameter("INVOICE_NUMBER"));
		trans.setCONSINOR(request.getParameter("CONSINOR"));
		trans.setLR_NUMBER(request.getParameter("LR_NUMBER"));
		trans.setDELIVERY_DATE(request.getParameter("DELIVERY_DATE"));
		trans.setPOD_STATUS(request.getParameter("POD_STATUS"));
		trans.setVEHICLE_NUMBER(request.getParameter("VEHICLE_NUMBER"));
		trans.setDRIVER_NAME(request.getParameter("DRIVER_NAME"));
		trans.setREMARKS(request.getParameter("REMARKS"));
		boolean status = TransportDAO.save(trans);
		if (!status)
			message="Record saved successfully!";
		else	
			message="Unable to save record. Please try again!";
		System.out.println(message);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/AddData.html").include(request, response);
	}
}
