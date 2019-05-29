package com.bpalign;

public class BPRanged 
{
	String productID;
	String UPC;
	String prodDesc;
	String drainedWeight;
	String unitOfWeigh;
	public BPRanged(String productID, String uPC, String prodDesc, String drainedWeight, String unitOfWeigh) {
		super();
		this.productID = productID;
		UPC = uPC;
		this.prodDesc = prodDesc;
		this.drainedWeight = drainedWeight;
		this.unitOfWeigh = unitOfWeigh;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getUPC() {
		return UPC;
	}
	public void setUPC(String uPC) {
		UPC = uPC;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getDrainedWeight() {
		return drainedWeight;
	}
	public void setDrainedWeight(String drainedWeight) {
		this.drainedWeight = drainedWeight;
	}
	public String getUnitOfWeigh() {
		return unitOfWeigh;
	}
	public void setUnitOfWeigh(String unitOfWeigh) {
		this.unitOfWeigh = unitOfWeigh;
	}
	
	
}
