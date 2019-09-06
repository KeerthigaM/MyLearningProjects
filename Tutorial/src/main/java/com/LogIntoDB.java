package com;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogIntoDB {
	
	static Logger log = Logger.getLogger(LogIntoDB.class.getName());

	public static void main(String[] args) throws IOException, SQLException {
		
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		log.debug("Debug");
		log.info("Info");
	}
}
