package com.framework.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期实用类
 * 
 * @author gaof
 * 
 */
public class DateUtils {
	/**
	 * 取得当月天数
	 */
	public static int getDaysOfMonth() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 取得当前年份
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 得到指定月的天数
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 返回当前月
	 */
	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前日期的任意格式
	 * 
	 * @param pattern
	 * @return
	 */
	public static String formatDate(String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = "yyyyMMddHHmm";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(new Date());
	}

	/**
	 * 根据指定的字符串和格式，把字符串转换成特定的日期格式
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String formatDate(String pattern, String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Date d = null;
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateFormat.applyPattern(pattern);
		return dateFormat.format(d);
	}
	/**
	 * 根据指定的字符串和格式，把字符串转换成特定的日期格式
	 * <br>返回的是日期类型
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static Date toDate(String pattern, String date) {
		if(pattern == null){
			pattern = "yyyy-Mm-dd";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date d = null;
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;

	}
	/**
	 * 根据指定的字符串和格式，把字符串转换成特定的日期格式
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String formatDate(String pattern, Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		dateFormat.applyPattern(pattern);
		return dateFormat.format(date);
	}
	public static String forDateFormat(String date) {

		String rtn = "";
		if (date == null) {
			return rtn;
		}
		if (date.length() == 8) {
			String year = date.substring(0, 4);
			String month = date.substring(4, 6);
			String day = date.substring(6, 8);
			rtn = year + "-" + month + "-" + day;
		} else if (date.length() == 12) {
			String year = date.substring(0, 4);
			String month = date.substring(4, 6);
			String day = date.substring(6, 8);
			rtn = year + "-" + month + "-" + day + " " + date.substring(8, 10)
					+ ":" + date.substring(10, 12);
		}
		return rtn;
	}

	/**
	 * 返回一个格式化后的日期字符串， 例：2005-08-16
	 * 
	 * @param strDateIn
	 *            String
	 * @return strDate String
	 */
	public static String getFormatDate(String strDateIn) {
		// 定义一个字符串变量
		String strDate = "";
		// 空值返回字符串
		if (strDateIn == null || "".equals(strDateIn.trim())) {
			return "";
		}
		// 时分的情况

		if (strDateIn.trim().length() == 4) {
			strDate = strDateIn.substring(0, 2) + ":"
					+ strDateIn.substring(2, 4);
		} else if (strDateIn.trim().length() == 8) {
			// 只有年月日
			strDate = strDateIn.substring(0, 4) + "-"
					+ strDateIn.substring(4, 6) + "-"
					+ strDateIn.substring(6, 8);
		} else if (strDateIn.trim().length() == 6) {
			// 只有年月
			strDate = strDateIn.substring(0, 4) + "-"
					+ strDateIn.substring(4, 6);
		} else if (strDateIn.trim().length() == 12) {
			// 只有年月日时分
			strDate = strDateIn.substring(0, 4) + "-"
					+ strDateIn.substring(4, 6) + "-"
					+ strDateIn.substring(6, 8) + " "
					+ strDateIn.substring(8, 10) + ":"
					+ strDateIn.substring(10, 12);
		} else if (strDateIn.trim().length() == 14) {
			// 只有年月日时分秒
			strDate = strDateIn.substring(0, 4) + "-"
					+ strDateIn.substring(4, 6) + "-"
					+ strDateIn.substring(6, 8) + " "
					+ strDateIn.substring(8, 10) + ":"
					+ strDateIn.substring(10, 12) + ":"
					+ strDateIn.substring(12, 14);

		} else {
			throw new NumberFormatException(
					"日期格式错误：日期类型应为长度应为6位,8位或12位,14位的数字。"
							+ "如，2005年7月11日13点45分应为“200507111345”");
		}
		// 返回结果

		return strDate;
	}

	/**
	 * 返回当前月的开始日期 201001
	 */
	public static String getStartDate(String yearMM) {
		String strStartDate = "";
		if (yearMM == null) {
			return "";
		}
		if (yearMM.length() != 6) {
			throw new NumberFormatException("日期格式错误：(" + yearMM
					+ ") 日期类型应为长度应为6位的数字。" + "如，200507");
		}
		// 本月第一天
		strStartDate = yearMM + "01";
		return strStartDate;
	}

	/**
	 * 返回当前月的结束日期
	 * 
	 * @param yearMM
	 *            String 年月 例：20050831。
	 * @return strStartDate
	 */
	public static String getEndDate(String yearMM) {

		if (yearMM == null || "".equals(yearMM.trim())) {
			return "";
		}
		if (yearMM.length() != 6) {
			throw new NumberFormatException("日期格式错误：(" + yearMM
					+ ") 日期类型应为长度应为6位的数字。" + "如，200507”");
		}
		// 格式化为整形
		int time = Integer.parseInt(yearMM);
		// 日历控件
		Calendar cal = Calendar.getInstance();
		// 设置年，月，日
		cal.set((time / 100), (time % 100 - 1), 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		// 返回当前月的最后一天
		return yearMM + String.valueOf(cal.get(Calendar.DATE));
	}

	/**
	 * 返回一个8位的当前日期字符串，例：20050816。
	 * 
	 * @return
	 */
	public static String getDate8() {
		String pattern = "yyyyMMdd";
		String today = formatDate(pattern);
		// 返回结果
		return today;
	}

	/**
	 * 返回一个12位的当前日期字符串， 例：200508161127。
	 * 
	 * @return today
	 */
	public static String getDate12() {
		String pattern = "yyyyMMddHHmm";
		String today = formatDate(pattern);
		// 返回结果
		return today;
	}

	/**
	 * 返回一个14位的当前日期字符串， 例：200508161127112。
	 * 
	 * @return today
	 */
	public static String getDate14() {
		String pattern = "yyyyMMddHHmmSSS";
		String today = formatDate(pattern);
		// 返回结果
		return today;
	}

	/**
	 * 返回一个6位的当前年月字符串， 例：200508。
	 * 
	 * @return return getDate8().substring(0,6)
	 */
	public static String getYYYYMM() {
		return getDate8().substring(0, 6);
	}

	/**
	 * 返回一个4位的当前年字符串， 例：2005。
	 * 
	 * @return getDate8().substring(0,4)
	 */
	public static String getYYYY() {
		return getDate8().substring(0, 4);
	}

	/**
	 * 用户输入一个8位或12位数字的日期字符串，返回该日期所在的周的周一和周日的8位日期
	 * 
	 * @param date
	 * @return
	 */
	public static String[] dayInWeek(String day) throws NumberFormatException {
		// 未输入日起时，日起默认为"00000000"。
		if (day == null || "".equals(day.trim())) {
			day = "00000000";
		}
		// 去空格
		day = day.trim();

		// 判断日期长度是否为8位或12位，否则抛出异常
		if (!(day.length() == 8 || day.length() == 12))
			throw new NumberFormatException(
					"日期长度错误：日期类型应为长度应为8位或12位的数字。如，2005年7月11日13点45分应为“200507111345”");

		// 判断所输入的日益是否恩能转换成数字，否则抛出异常
		try {
			Long.parseLong(day);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new NumberFormatException(
					"日期格式错误：日期类型应为长度应为8位或12位的数字。如，2005年7月11日13点45分应为“200507111345”");
		}
		SimpleDateFormat format = null;

		if (day.length() == 8) {
			format = new SimpleDateFormat("yyyyMMdd");
		} else {
			format = new SimpleDateFormat("yyyyMMddHHmm");
		}

		Date date = null;
		try {
			date = format.parse(day);
		} catch (ParseException ex) {
			ex.printStackTrace(System.err);
			throw new NumberFormatException(
					"日期格式错误：日期类型应为长度应为8位或12位的数字。如，2005年7月11日13点45分应为“200507111345”");
		}
		if (date == null)
			throw new NumberFormatException(
					"日期格式错误：日期类型应为长度应为8位或12位的数字。如，2005年7月11日13点45分应为“200507111345”");
		// 星期几
		int weekDay = date.getDay();
		if (weekDay == 0)
			weekDay = 7;

		String[] towDay = new String[2];
		// 星期一
		towDay[0] = addDate(day, 1 - weekDay).substring(0, 8);
		// 星期日
		towDay[1] = addDate(day, 7 - weekDay).substring(0, 8);
		return towDay;
	}
	/**
	 * 返回当前天的开始日期和结束日期
	 * 
	 * @param date
	 * @return
	 */
	public static String[] dayInWeek() throws NumberFormatException {

		SimpleDateFormat format =  new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();	
		// 星期几
		int weekDay = date.getDay();
		if (weekDay == 0)
			weekDay = 7;
        String day = format.format(date);
		String[] towDay = new String[2];
		// 星期一
		towDay[0] = addDate(day, 1 - weekDay).substring(0, 8);
		// 星期日
		towDay[1] = addDate(day, 7 - weekDay).substring(0, 8);
		
		return towDay;
	}
	/**
	 * 日期加减（按天数）
	 * 
	 * @param day
	 * @param x
	 * @return
	 */
	public static String addDate(String day, int x)
			throws NumberFormatException {
		// 未输入日起时，日起默认为"00000000"。
		if (day == null || "".equals(day.trim())) {
			day = "00000000";
		}
		// 去空格
		day = day.trim();

		// 判断日期长度是否为8位或12位，否则抛出异常
		if (!(day.length() == 8 || day.length() == 12))
			throw new NumberFormatException(
					"日期长度错误：日期类型应为长度应为8位或12位的数字。如，2005年7月11日13点45分应为“200507111345”");

		// 判断所输入的日益是否恩能转换成数字，否则抛出异常
		try {
			Long.parseLong(day);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new NumberFormatException(
					"日期格式错误：日期类型应为长度应为8位或12位的数字。如，2005年7月11日13点45分应为“200507111345”");
		}
		SimpleDateFormat format = null;

		if (day.length() == 8) {
			format = new SimpleDateFormat("yyyyMMdd");
		} else {
			format = new SimpleDateFormat("yyyyMMddHHmm");
		}

		Date date = null;
		try {
			date = format.parse(day);
		} catch (ParseException ex) {
			ex.printStackTrace(System.err);
			throw new NumberFormatException(
					"日期格式错误：日期类型应为长度应为8位或12位的数字。如，2005年7月11日13点45分应为“200507111345”");
		}
		if (date == null)
			throw new NumberFormatException(
					"日期格式错误：日期类型应为长度应为8位或12位的数字。如，2005年7月11日13点45分应为“200507111345”");

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, x);
		date = cal.getTime();
		cal = null;
		return format.format(date);
	}

	/**
	 * 返回一个14位的当前日期字符串， 例：2005年08月16
	 * 
	 * @return today
	 */
	public static String getToday() {
		String pattern = "yyyy年MM月dd日";
		String today = formatDate(pattern);
		// 返回结果
		return today;
	}

	/**
	 * 获得之间天数
	 * 
	 * @param beginDay
	 * @param endDay
	 * @return
	 */
	public static long betweenDays(String beginDay, String endDay) {
		SimpleDateFormat format = null;

		if (beginDay.length() == 8) {
			format = new SimpleDateFormat("yyyyMMdd");
		} else if (beginDay.length() == 12) {
			format = new SimpleDateFormat("yyyyMMddHHmm");
		}

		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate = format.parse(beginDay);
			endDate = format.parse(endDay);
		} catch (ParseException ex) {
			ex.printStackTrace(System.err);
			throw new NumberFormatException("日期格式：日期类型应为长度应为8位或12位."
					+ "如，2005年7月11日13时45分应为“200507111345”");
		}
		long beginTime = beginDate.getTime();
		long endTime = endDate.getTime();
		long betweenDays = (long) ((endTime - beginTime)
				/ (1000 * 60 * 60 * 24) + 0.5);
		return betweenDays;
	}


}
