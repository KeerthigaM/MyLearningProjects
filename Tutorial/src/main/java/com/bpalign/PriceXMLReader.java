package com.bpalign;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PriceXMLReader 
{
	public static void main(String argv[]) 
	{
		String UPC="00011679,00011686,00011693,00011709,00011723,00011730,00011747,00011754,00011778,00020108,00023115,00028325,00053525,00060479,00071161,00071604,00072526,00117746,00161534,00161558,00161688,00166782,00176439,00186803,00221573,00221597,00222303,00222310,00230889,00231626,00231664,00231671,00233040,00255493,00257091,00257336,00288460,00288675,00288729,00290364,00290708,00294539,00320870,00321266,00338165,00356398,00361576,00361651,00361682,00361729,00378819,00404068,00461689,00468626,00498692,00538169,00538565,00542739,00585477,00664424,00670227,00670272,00670289,00670319,00671613,00671620,00671729,00675864,00692137,00700788,00722803,00722810,00722834,00722841,00727662,00744706,00752640,00752749,00752770,00779739,00816717,00841122,00865203,00906012,00942638,00969512,00988315,00998840";
		ArrayList<String> UPCList = StringToArrayList(UPC);	
		/*String wh="664424,722803,722810,675864,166782,321266,28325,988315,11693,117746,538565,11778,670272,186803,11679,11730,670227,11747,233040,221573,969512,221597,222310,222303,865203,816717,671613,60479,71604,671729,257336,671620,692137,744706,670319,670289,11709,11686,176439,11754,23115,338165,11723,255493,538169,161688,356398,942638,722834,378819,231664,722841,727662,231671,320870,161558,231626";
		ArrayList<String> WHList = StringToArrayList(wh);
		String rp="222303,671729,969512,221597,671620,221573,60479,692137,186803,161688,744706,11679,461689,942638,28325,378819,722841,722810,321266,722803,11709,11693,117746,538565,161558,11686,675864,320870,538169,231626,727662,11723,161534,231671,356398,722834,11778,11754,231664,338165,11730,255493,664424,11747,166782,23115,988315,176439,670289,233040,670272,670319,670227,257336,222310,71604,671613,865203,816717\r\n";
		ArrayList<String> RPList = StringToArrayList(rp);
		for(String frd: UPCList)
		{
			if(!RPList.contains(frd))
			{
				System.out.println("RP"+frd);
			}
			if(!WHList.contains(frd))
			{
				System.out.println("WH"+frd);
			}
		}*/
		getPriceData(UPCList);
	}

	private static ArrayList<String> StringToArrayList(String str) {
		String[] Arr=str.split(",");
		ArrayList<String> ArrList = new ArrayList<String>(Arrays.asList(Arr));	
		return ArrList;
	}

	private static void getPriceData(ArrayList<String> UPCList) {
		try {
			String file="D:\\Users\\886758\\Downloads\\BP Files\\discrepancy\\I2133_BP_RETAIL_PRICE_UPDATES_20190218154950.xml";
			File fXmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Payload");
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				//System.out.println("Current Element :"+nNode.getNodeName());
				//System.out.println("First Child :"+nNode.getFirstChild().getNodeName());	
				NodeList nList1 = nNode.getFirstChild().getChildNodes();
				for(int temp1 = 0; temp1 < nList1.getLength(); temp1++)
				{
					Node nNode1 = nList1.item(temp1);
					if (nNode1.getNodeType() == Node.ELEMENT_NODE) 
					{
						Element eElement1 = (Element) nNode1;						
						NodeList nList2 = eElement1.getElementsByTagName("PriceByCurrency");
						//System.out.println(eElement1.getElementsByTagName("mns:ProductID").item(0).getTextContent()+"-"+nList2.getLength());
						String UPC = eElement1.getElementsByTagName("UPC").item(0).getTextContent().toString();
						//if(UPCList.contains(UPC))
						{
							for(int temp2 = 0; temp2 < nList2.getLength(); temp2++)
							{
								Node nNode2 = nList2.item(temp2);							
								if (nNode2.getNodeType() == Node.ELEMENT_NODE) 
								{
									Element eElement2 = (Element) nNode2;									
									if(eElement2.getElementsByTagName("validFrom").item(0).getTextContent().toString().equalsIgnoreCase("2019-02-19"))
									{
										System.out.println(UPC 
										+","+ eElement2.getElementsByTagName("validFrom").item(0).getTextContent()
										+","+ eElement2.getElementsByTagName("validTo").item(0).getTextContent()		
										+","+ eElement2.getElementsByTagName("amount").item(0).getTextContent()
									//	+" || "+ eElement2.getElementsByTagName("conditionType").item(0).getTextContent()
												);
									}
								}
							}							
						}
					}						
				}
				//System.out.println("Product Count : "+nList1.getLength());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}