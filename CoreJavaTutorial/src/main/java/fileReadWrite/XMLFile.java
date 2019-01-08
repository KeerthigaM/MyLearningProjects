package fileReadWrite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFile {

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, TransformerException 
	{
		File resourcesDirectory = new File("src/main/resources");
		String filePath = resourcesDirectory.getAbsolutePath() + "/XMLfile.xml";
		System.out.println("********** WRITE ***********");
		writeXML(filePath);
		System.out.println("********** READ ***********");
		readXML(filePath);
	}
// Write XML
	private static void writeXML(String filePath) throws ParserConfigurationException, TransformerException 
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		
		// add elements to Document
		Element rootElement = doc.createElementNS("http://www.journaldev.com/employee", "Employees");
		// append root element to document
		doc.appendChild(rootElement);
		// append first child element to root element
		rootElement.appendChild(getEmployee(doc, "1", "Pankaj", "29", "Java Developer", "Male"));
		// append second child
		rootElement.appendChild(getEmployee(doc, "2", "Lisa", "35", "Manager", "Female"));
		// for output to file, console
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		// for pretty print
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);

		// write to console or file
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(new File(filePath));
		// write data
		transformer.transform(source, console);
		transformer.transform(source, file);
		System.out.println("*********DONE*********");
	}
	private static Node getEmployee(Document doc, String id, String name, String age, String role, String gender) 
	{
		Element employee = doc.createElement("Employee");
		// set id attribute
		employee.setAttribute("id", id);
		// create name element
		employee.appendChild(getEmployeeElements(doc, employee, "name", name));
		// create age element
		employee.appendChild(getEmployeeElements(doc, employee, "age", age));
		// create role element
		employee.appendChild(getEmployeeElements(doc, employee, "role", role));
		// create gender element
		employee.appendChild(getEmployeeElements(doc, employee, "gender", gender));
		return employee;
	}	
	private static Node getEmployeeElements(Document doc, Element element, String name, String value) 
	{// utility method to create text node
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
// Read XML
	private static void readXML(String filePath) throws ParserConfigurationException, SAXException, IOException 
	{
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nodeList = doc.getElementsByTagName("Employee");
		// now XML is loaded as Document in memory, lets convert it to Object List
		List<Employee> empList = new ArrayList<Employee>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			empList.add(getEmployee(nodeList.item(i)));
		}
		// lets print Employee list information
		for (Employee emp : empList) {
			System.out.println(emp.toString());
		}
	}
	private static Employee getEmployee(Node node) 
	{
		// XMLReaderDOM domReader = new XMLReaderDOM();
		Employee emp = new Employee();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			emp.setName(getTagValue("name", element));
			emp.setAge(Integer.parseInt(getTagValue("age", element)));
			emp.setGender(getTagValue("gender", element));
			emp.setRole(getTagValue("role", element));
		}
		return emp;
	}
	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
}
