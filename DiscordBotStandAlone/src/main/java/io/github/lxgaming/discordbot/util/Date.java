package io.github.lxgaming.discordbot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
	
	public static String getDateTime() {
		return getDate() + " " + getTime();
	}
	
	public static String getDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(cal.getTime());
	}
	
	public static String getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());
	}
}
