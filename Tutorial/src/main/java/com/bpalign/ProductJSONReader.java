package com.bpalign;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.codoid.products.exception.FilloException;

public class ProductJSONReader {

	private static final String filepath="D:\\Users\\886758\\Downloads\\BP Files\\JSON files\\Split\\";	
	public static void main(String[] args) throws IOException, FilloException, InterruptedException {			
		//esJSONToXLSX();
		System.out.println("DONE");
		compareFinalList();
	}
	private static void compareFinalList() throws IOException {
		ArrayList<String> bprangedarticleList=getBPRangedArticleList(filepath+"BPRangedArticles.txt");
		ArrayList<String> outputList=getBPRangedArticleList(filepath+"BPList.txt");
		for(String outbp: outputList)
		{			
			System.out.println(outbp+" "+bprangedarticleList.contains(removeZero(outbp)));
		}		
	}
	private static void esJSONToXLSX() throws IOException, FilloException, InterruptedException {
		String files="xaa,xab,xac,xad,xae,xaf,xag,xah,xai,xaj,xak,xal,xam,xan";
		String[] filesarr=files.split(",");
		String BPRangedArticle=filepath+"BPRangedArticles.txt";
		ArrayList<String> bprangedarticleList=getBPRangedArticleList(BPRangedArticle);
		ArrayList<BPRanged> bplist=new ArrayList<BPRanged>();
		for(int i=0;i<filesarr.length;i++)
		{			
			String file=filepath+filesarr[i]+".json";			
			bplist = ProductJsonReader(file,bprangedarticleList,bplist);
		}		
		writeToExcel(bplist);		
	}
	public static ArrayList<String> getBPRangedArticleList(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		ArrayList<String> bprangedarticleList = new ArrayList<String>();
		String line;
		while((line = in.readLine()) != null)
		{
		    bprangedarticleList.add(line);
		}
		in.close();
		return bprangedarticleList;
	}
	private static ArrayList<BPRanged> ProductJsonReader(String filename ,ArrayList<String> bprangedarticleList ,ArrayList<BPRanged> bplist) {
		JSONParser parser = new JSONParser();		 
        try 
        { 
            Object object= parser.parse(new BufferedReader(new FileReader(filename)));            
            JSONArray jsonArray = (JSONArray) object;             
            for(int i = 0; i < jsonArray.size(); i++)
            {
            	JSONObject jsonObject = (JSONObject) jsonArray.get(i);            	
            	boolean bpRanged=checkForBPRange(removeZero(jsonObject.get("productId").toString()),bprangedarticleList);
            	if(bpRanged)
            	{            		
            		String productID = "",UPC = "",prodDesc = "",drainedWeight = "",unitOfWeight = "";
            		JSONObject jsonObject1  = (JSONObject) jsonObject.get("barcodes");
            		productID = jsonObject.get("productId").toString();
	        		UPC = jsonObject1.get("UPC").toString();
	        		JSONArray jsonArray1  = (JSONArray) jsonObject.get("productDescriptions");                
	        		for(int j = 0; j < jsonArray1.size(); j++)
	        		{
	        			JSONObject jsonObject2 = (JSONObject) jsonArray1.get(j);
	        			prodDesc = jsonObject2.get("textDesc").toString(); 
	        		}
	                JSONArray jsonArray2  = (JSONArray) jsonObject.get("version");  
	                		for(int j = 0; j < jsonArray2.size(); j++)
	                		{
	                			JSONObject jsonObject3 = (JSONObject) jsonArray2.get(j);
	                			drainedWeight = jsonObject3.get("drainedWeight").toString();
	                			unitOfWeight = jsonObject3.get("unitsOfWeight").toString();		                		 
	                		}
	               // System.out.println(productID+" || "+UPC+" || "+prodDesc+" || "+drainedWeight+" || "+unitOfWeight);
	                BPRanged bp=new BPRanged(productID,UPC,prodDesc,drainedWeight,unitOfWeight);
	                bplist.add(bp);	              
            	}
            }             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bplist;
	}
	private static int writeToExcel(ArrayList<BPRanged> bpList) 
	{	
		Workbook workbook = new XSSFWorkbook();		 
        Sheet productSheet = workbook.createSheet("product");
        int rowIndex=0;
        for(BPRanged bp : bpList){
            Row row = productSheet.createRow(rowIndex++);
            int cellIndex = 0;            
            row.createCell(cellIndex++).setCellValue(bp.getProductID());
            row.createCell(cellIndex++).setCellValue(bp.getUPC());
            row.createCell(cellIndex++).setCellValue(bp.getProdDesc());
            row.createCell(cellIndex++).setCellValue(bp.getDrainedWeight());
            row.createCell(cellIndex++).setCellValue(bp.getUnitOfWeigh());
        }
        try {
            FileOutputStream fos = new FileOutputStream(filepath+"Product02052019.xlsx");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowIndex;
	}
	public static boolean checkForBPRange(String prodId, ArrayList<String> bprangedarticleList) 
	{
	 for(String bp:bprangedarticleList)
	 {
		 if(bp.equalsIgnoreCase(prodId))
			 return true;
	 }
		return false;
	}
	public static String removeZero(String str) 
    {  
        int i = 0; 
        while (str.charAt(i) == '0') 
            i++;
        StringBuffer sb = new StringBuffer(str); 
        sb.replace(0, i, "");   
        return sb.toString();  
    } 

}
