package fileReadWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFile {
	public static void main(String[] args) throws IOException, ParseException {
		File resourcesDirectory = new File("src/main/resources");
		String filePath = resourcesDirectory.getAbsolutePath() + "/JSONfile.json";
		System.out.println("********** WRITE ***********");
		writeJSON(filePath);
		System.out.println("********** READ ***********");
		readJSON(filePath);
	}

	private static void readJSON(String filePath) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String nameOfCountry = (String) jsonObject.get("Name");
		System.out.println("Name Of Country: " + nameOfCountry);
		long population = (Long) jsonObject.get("Population");
		System.out.println("Population: " + population);
		System.out.println("States are :");
		JSONArray listOfStates = (JSONArray) jsonObject.get("States");
		Iterator iterator = listOfStates.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	private static void writeJSON(String filePath) throws IOException {
		JSONObject countryObj = new JSONObject();
		countryObj.put("Name", "India");
		countryObj.put("Population", new Integer(1000000));
		JSONArray listOfStates = new JSONArray();
		listOfStates.add("Tamil Nadu");
		listOfStates.add("Karnataka");
		listOfStates.add("Kerala");
		countryObj.put("States", listOfStates);
		File file = new File(filePath);
		FileWriter fileWriter = new FileWriter(file);		
		System.out.print(countryObj);
		fileWriter.write(countryObj.toJSONString());
		fileWriter.flush();
		fileWriter.close();
	}
}
