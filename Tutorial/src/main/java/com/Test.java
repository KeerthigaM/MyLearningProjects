package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.apache.derby.impl.sql.catalog.SYSROUTINEPERMSRowFactory;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream o = new PrintStream(new File("D:\\Users\\886758\\Desktop\\Dummy.txt"));
		String data="553186,553223,553230,553247,553254,553261,553322,553452,553544,553568,553605,553612,553650,553667,553735,553759,553834,553896,556453,556514,545143,553018,553148,553162,553407,553438,553025,553445,553469,553483";
		getData(data,o);
	}

	private static void getData(String data,PrintStream o) throws FileNotFoundException {
		String[] catArr=data.split(",");
			for(int i=0;i<catArr.length;i++){
				System.setErr(o);
				System.out.println("{\"categories\":\"F09\",\"articleNo\":\"000000000020059481\",\"articleDescription\":\"FTO DP WHITE MADEIRA\",\"unitDescription\":\"FTO DP WHITE MADEIRA\",\"upc\":\""+catArr[i]+"\",\"multiSupply\":\"N\",\"vendorName\":\"Avana Bakeries\",\"ragStatus\":\"G\",\"vendorNo\":\"F01501\",\"currency\":\"GBP\",\"workflowStatus\":\"N\",\"requestor\":\"T00S0002\",\"creationDate\":\"2019/09/16\",\"creationTime\":\"21:35:33\",\"workflowId\":\"000017139103\",\"workflowTitle\":\"Equipment Mode Update\",\"reasonCode\":\"UPD\",\"role\":\"BYR\",\"multistage\":\"1\",\"deliveryMethod\":\"R\",\"costPrice\":[{\"fromDate\":\"2019/09/30\",\"endDate\":\"9999/12/31\",\"newCostPrice\":\"10.160\",\"currentCostPrice\":\"0.000\",\"upt\":\"13\",\"orderUnit\":\"O03\"}],\"supplyChain\":[{\"depots\":[{\"siteName\":\"BARNSLEY GIST DEPOT\",\"storeCode\":\"6567\"},{\"siteName\":\"HEMEL GIST DEPOT\",\"storeCode\":\"5966\"},{\"siteName\":\"THATCHAM GIST DEPOT\",\"storeCode\":\"5908\"},{\"siteName\":\"EIRE GIST DEPOT\",\"storeCode\":\"5898\"},{\"siteName\":\"FAVERSHAM GIST DEPOT\",\"storeCode\":\"5652\"},{\"siteName\":\"BRISTOL GIST DEPOT\",\"storeCode\":\"5380\"},{\"siteName\":\"CREWE GIST DEPOT\",\"storeCode\":\"5212\"},{\"siteName\":\"CUMBERNAULD GIST DEPOT\",\"storeCode\":\"5173\"}],\"currentOrderUnit\":\"O02\",\"newOrderUnit\":\"O03\",\"currentUPT\":\"12\",\"newUPT\":\"13\",\"fromDate\":\"2019/09/30\",\"endDate\":\"9999/12/31\",\"siteGroup\":\"ZRDARDC\"}],\"equipment\":[{\"orderUnit\":\"O03\",\"upt\":\"13\",\"fromDate\":\"2019/09/30\",\"endDate\":\"9999/12/31\",\"newEquipmentType\":\"HALF TRAY\",\"newEquipmentSetting\":\"LOW\",\"currentEquipmentType\":\"\",\"currentEquipmentSetting\":\"\"}],\"unitWeight\":[],\"trayWeight\":[]},");
			}
	}	
}