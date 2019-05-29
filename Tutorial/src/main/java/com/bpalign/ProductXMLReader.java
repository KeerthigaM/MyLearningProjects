package com.bpalign;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProductXMLReader 
{
	public static void main(String argv[]) 
	{
		try {
			String file="D:\\Users\\886758\\Downloads\\BP Files\\XML files\\I2135_BP_PRODUCT_UPDATES_20190219155236.xml";		
			File fXmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Payload");
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				System.out.println("Current Element :"+nNode.getNodeName());
				System.out.println("First Child :"+nNode.getFirstChild().getNodeName());	
				NodeList nList1 = nNode.getFirstChild().getChildNodes();
				for(int temp1 = 0; temp1 < nList1.getLength(); temp1++)
				{
					Node nNode1 = nList1.item(temp1);
					if (nNode1.getNodeType() == Node.ELEMENT_NODE) 
					{
						Element eElement1 = (Element) nNode1;
						System.out.println("Product ID : " +eElement1.getElementsByTagName("mns:ProductID").item(0).getTextContent()
								+" || Gross Weight : "+		eElement1.getElementsByTagName("grossWeight").item(0).getTextContent());						
					}						
				}
				System.out.println("Product Count : "+nList1.getLength());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}