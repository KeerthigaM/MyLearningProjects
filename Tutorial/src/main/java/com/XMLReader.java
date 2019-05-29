package com;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader 
{
	public static void main(String argv[]) 
	{
		try {
			String file="D:\\Users\\886758\\Downloads\\BP Files\\XML files\\I2134_BP_WHOLESALE_PRICE_UPDATES_20190204154927.xml";
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
						System.out.println(eElement1.getElementsByTagName("mns:ProductID").item(0).getTextContent()+"-"+nList2.getLength());
						for(int temp2 = 0; temp2 < nList2.getLength(); temp2++)
						{
							Node nNode2 = nList2.item(temp2);							
							if (nNode2.getNodeType() == Node.ELEMENT_NODE) 
							{
								Element eElement2 = (Element) nNode2;
								System.out.println(eElement2.getElementsByTagName("validFrom").item(0).getTextContent()
								+" || "+ eElement2.getElementsByTagName("validTo").item(0).getTextContent()		
								+" || "+ eElement2.getElementsByTagName("amount").item(0).getTextContent()
								+" || "+ eElement2.getElementsByTagName("conditionType").item(0).getTextContent()
										);
							}
						}	
					}						
				}
				System.out.println("Product Count : "+nList1.getLength());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}