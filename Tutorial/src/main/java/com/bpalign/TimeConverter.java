package com.bpalign;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;

public class TimeConverter 
{
	public static void main(String[] args) 
	{
		System.out.println("please enter time with date in yyyy-MM-dd HH:mm format");
		Scanner sc=new Scanner(System.in);
		String date=sc.nextLine();
		System.out.println("Enter FROM Zone");
		sc=new Scanner(System.in);
		String fromZone=sc.nextLine();
		System.out.println("Enter TO Zone");
		sc=new Scanner(System.in);
		String toZone=sc.nextLine();
		convertor(date,fromZone,toZone);	
	}

	private static void convertor(String date, String fromZone, String toZone) 
	{
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime gmt = LocalDateTime.parse(date, formatter);
		ZonedDateTime instant = ZonedDateTime.of(gmt, ZoneId.of(getZoneId(fromZone).toUpperCase()));
		LocalDateTime bst = instant.withZoneSameInstant(ZoneId.of(getZoneId(toZone))).toLocalDateTime();
		System.out.println(bst.format(formatter));		
	}

	private static String getZoneId(String toZone) 
	{
		switch(toZone)
		{		
		case"IST":return "Asia/Kolkata";
		case"BST":return "Europe/London";
		default: return "GMT";
		}		
	}
}
