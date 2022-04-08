package br.com.tcs.calculadoraimc.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import br.com.tcs.calculadoraimc.utils.jdbc.DateUtils;

public class QueryBuilder {

	public static String getQuery(String query, Object[] parametros) {
		for (Object param : parametros) {
			String paramStr = "";
			if (param instanceof String) {
				paramStr = "'" + param.toString() + "'";
			} else if (param instanceof Date) {
				paramStr = "'" +DateUtils.dateToString((Date) param)+ "'";
			} else if (param instanceof LocalDate) {
				paramStr = "'" +DateUtils.localDateToString((LocalDate) param)+ "'";
			} else if (param instanceof LocalDateTime) {
				paramStr = "'" +DateUtils.localDateTimeToString((LocalDateTime) param) + "'";
			}else {
				paramStr = param+"";
			}
			query = query.replaceFirst("[?]", paramStr);
		}
		return query;
	}
}
