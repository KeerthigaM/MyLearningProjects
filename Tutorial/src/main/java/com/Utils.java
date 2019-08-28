package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.util.ResourceUtils;

public class Utils {
	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		File file = ResourceUtils.getFile("classpath:chromeDriver/chromedriver.exe");
		File file1 = new File("chromeDriver/chromedriver.exe");
		System.out.println(file.getAbsolutePath());
		System.out.println(file1.getAbsolutePath());
		System.out.println("======================================================");
		File[] files = new File(
				"D:\\Users\\886758\\Documents\\Keerthiga_886758\\Keerthiga_Workspace\\GIT\\MyLearningProjects\\Tutorial\\src\\main\\resources\\RDA")
						.listFiles();
		for (File file2 : files) {
			if (file2.isFile()) {
				System.out.println(file2.getName());
			}
		}
		System.out.println("======================================================");
		timeConvertor("2019-08-14 15:10","Asia/Kolkata","Europe/London");
		System.out.println("======================================================");
		String[] a = new String[2];
		splitFiles(a);
		System.out.println("======================================================");
		String data="60074265;21096893;20060080;20060096;20119345";
		String [] dataArray = data.split(";");
		ArrayList<String> dataList = new ArrayList<String> (Arrays.asList(dataArray));	
	}
	private static void splitFiles(String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid Input!");
		}
		// first argument is the file path
		File file = new File(args[0]);
		// second argument is the number of lines per chunk
		// In particular the smaller files will have numLinesPerChunk lines
		int numLinesPerChunk = Integer.parseInt(args[1]);
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		long start = System.currentTimeMillis();
		try {
			line = reader.readLine();
			for (int i = 1; line != null; i++) {
				writer = new PrintWriter(new FileWriter(args[0] + "_part" + i + ".txt"));
				for (int j = 0; j < numLinesPerChunk && line != null; j++) {
					writer.println(line);
					line = reader.readLine();
				}
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.close();
		long end = System.currentTimeMillis();
		System.out.println("Taken time[sec]:");
		System.out.println((end - start) / 1000);
	}
	private static void timeConvertor(String date, String fromZone, String toZone) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime gmt = LocalDateTime.parse(date, formatter);
		ZonedDateTime instant = ZonedDateTime.of(gmt, ZoneId.of(fromZone));
		LocalDateTime to = instant.withZoneSameInstant(ZoneId.of(toZone)).toLocalDateTime();
		System.out.println(to.format(formatter));
	}
}