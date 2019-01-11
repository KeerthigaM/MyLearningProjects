package com;

public class TransportRegister {
	private int S_NO;
	private String INVOICE_DATE,INVOICE_NUMBER,CONSINOR,LR_NUMBER,DELIVERY_DATE,POD_STATUS,VEHICLE_NUMBER,DRIVER_NAME,REMARKS;
	
	public int getS_NO() {
		return S_NO;
	}
	public void setS_NO(int s_NO) {
		S_NO = s_NO;
	}
	public String getINVOICE_DATE() {
		return INVOICE_DATE;
	}
	public void setINVOICE_DATE(String iNVOICE_DATE) {
		INVOICE_DATE = iNVOICE_DATE;
	}
	public String getINVOICE_NUMBER() {
		return INVOICE_NUMBER;
	}
	public void setINVOICE_NUMBER(String iNVOICE_NUMBER) {
		INVOICE_NUMBER = iNVOICE_NUMBER;
	}
	public String getCONSINOR() {
		return CONSINOR;
	}
	public void setCONSINOR(String cONSINOR) {
		CONSINOR = cONSINOR;
	}
	public String getLR_NUMBER() {
		return LR_NUMBER;
	}
	public void setLR_NUMBER(String lR_NUMBER) {
		LR_NUMBER = lR_NUMBER;
	}
	public String getDELIVERY_DATE() {
		return DELIVERY_DATE;
	}
	public void setDELIVERY_DATE(String dELIVERY_DATE) {
		DELIVERY_DATE = dELIVERY_DATE;
	}
	public String getPOD_STATUS() {
		return POD_STATUS;
	}
	public void setPOD_STATUS(String pOD_STATUS) {
		POD_STATUS = pOD_STATUS;
	}
	public String getVEHICLE_NUMBER() {
		return VEHICLE_NUMBER;
	}
	public void setVEHICLE_NUMBER(String vEHICLE_NUMBER) {
		VEHICLE_NUMBER = vEHICLE_NUMBER;
	}
	public String getDRIVER_NAME() {
		return DRIVER_NAME;
	}
	public void setDRIVER_NAME(String dRIVER_NAME) {
		DRIVER_NAME = dRIVER_NAME;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}	
	
	@Override
	public String toString() {
		return "'"+INVOICE_DATE+"','"+INVOICE_NUMBER+"','"+CONSINOR+"','"+LR_NUMBER+"','"+DELIVERY_DATE+"','"+POD_STATUS+"','"+VEHICLE_NUMBER+"','"+DRIVER_NAME+"','"+REMARKS+"'";
		 
	}
}
