package br.com.tcs.calculadoraimc.utils.jdbc;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	private static final DateTimeFormatter localDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter localDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static Date stringToDate(String dateStr) {
		try {
			return dataFormat.parse(dateStr);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date stringToDateTime(String dateTimeStr) {
		try {
			return dataTimeFormat.parse(dateTimeStr);
		} catch (Exception e) {
			return null;
		}
	}

	public static LocalDate stringToLocalDate(String localDateStr) {
		try {
			return LocalDate.parse(localDateStr, localDateFormat);
		} catch (Exception e) {
			return null;
		}
	}

	public static LocalDateTime stringToLocalDateTime(String localDateTimeStr) {
		try {
			return LocalDateTime.parse(localDateTimeStr, localDateTimeFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String dateToString(Date dateStr, String pattern) {
		try {
			SimpleDateFormat dataFormat = new SimpleDateFormat(pattern);
			return dataFormat.format(dateStr);
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date dateStr) {
		return dateToString(dateStr, dataFormat.toPattern());
	}

	public static String dateTimeToString(Date dateTimeStr) {
		try {
			return dataTimeFormat.format(dateTimeStr);
		} catch (Exception e) {
			return null;
		}
	}

	public static String localDateToString(LocalDate localDateStr) {
		try {
			return localDateFormat.format(localDateStr);
		} catch (Exception e) {
			return null;
		}
	}

	public static String localDateTimeToString(LocalDateTime localDateTimeStr) {
		try {
			return localDateTimeFormat.format(localDateTimeStr);
		} catch (Exception e) {
			return null;
		}
	}

	public static String localDateTimeToString(LocalDateTime localDateTimeStr, String pattern) {
		try {
			return DateTimeFormatter.ofPattern(pattern).format(localDateTimeStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date asDate(LocalDate localDate) {
	    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	  }

	  public static Date asDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	  }

	  public static LocalDate asLocalDate(Date date) {
	    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	  }

	  public static LocalDateTime asLocalDateTime(Date date) {
	    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	  }
}
