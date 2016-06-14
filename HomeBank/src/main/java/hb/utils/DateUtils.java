package hb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static String getHumanRedableDate(Date date){
		return sdf.format(date);
	}
	
	public static Date getDateFromString(String date){
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
