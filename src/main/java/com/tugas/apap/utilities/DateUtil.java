package com.tugas.apap.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {
	public static String convertDate(String dateStr) {
		String dateResult = "";
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			DateFormat formatterDateIndo = new SimpleDateFormat("dd-mm-yyyy");
			
			dateResult = formatterDateIndo.format(formatter.parse(dateStr));
		} catch (Exception e) {
			log.error("Error : " + e + "\nconvertDate " + dateStr);
		}
		return dateResult;
		
	}

}
