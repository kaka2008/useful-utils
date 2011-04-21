

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * @author kaka2008
 *
 */
public class DateUtils {

	/**
	 * 取当前起某段时间间隔后的时间
	 * 
	 * @param interval
	 * @return
	 */
	public static final Calendar getCalendarAfterInterval(long interval) {
		Calendar now = Calendar.getInstance();
		long time = now.getTimeInMillis() + interval;
		now.setTimeInMillis(time);
		return now;
	}

	/**
	 * 取当前年
	 * 
	 * @return
	 */
	public static String getCurrentYear() {
		Calendar now = Calendar.getInstance();
		return new Integer(now.get(Calendar.YEAR)).toString();
	}

	/**
	 * 取当前月
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		Calendar now = Calendar.getInstance();
		return new Integer(now.get(Calendar.MONTH) + 1).toString();
	}

	/**
	 * 取当前天的字符串
	 * 
	 * @return
	 */
	public static String getCurrentDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	/**
	 * 根据某个Date得到字符串，格式为yyyy-MM-dd
	 * 
	 * @param date
	 * @return @
	 */
	public static String getString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * 根据某个Date得到格式化后的字符串
	 * 
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String getString(Date date, String formatString) {
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		return format.format(date);
	}

	/**
	 * 得到某个Date的日期时间字符串，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 得到某个日期的时间部门
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeStringOnly(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 根据字符串得到某个日期，格式为yyyy-MM-dd
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date getDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据字符串得到某个日期，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date getDateFromTimeString(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取某个日期的年份
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	/**
	 * 取某个日期的月份
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 取某个日期的天份
	 * 
	 * @param date
	 * @return
	 */
	public static String getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.DATE));
	}

	/**
	 * 取某一年的第一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getFirstDayOfYear(String year) {
		return getDate(year + "-1-1");
	}

	/**
	 * 取某一年的最后一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getLastDayOfYear(String year) {
		return getDate(year + "-12-31");
	}

	/**
	 * 取某年某月的第一天
	 * 
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(String year, String month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 取某年某月的最后一天
	 * 
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(String year, String month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 取某年某月的第一天
	 * 
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(Date date) {

		try {
			Calendar calendar = getCalendarFromDate(date);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			String result = year + "-" + month + "-1";
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 取某年某月的最后一天
	 * 
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(Date date) {

		try {
			Calendar calendar = getCalendarFromDate(date);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int lastDayOfMonth = calendar
					.getActualMaximum(Calendar.DAY_OF_MONTH);
			String result = year + "-" + month + "-" + lastDayOfMonth;
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// calendar.set(Calendar.DAY_OF_MONTH, calendar
		// .getActualMaximum(Calendar.DAY_OF_MONTH));
		return "";
	}

	/**
	 * 取得某天对应的本周第一天
	 * 
	 * @param month
	 * @return
	 */
	public static String getFirstDateOfWeek(Calendar calendar) {

		Calendar result = (Calendar) calendar.clone();
		result.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return getStringFromCalendar(result);
	}

	/**
	 * 取得某天对应的本周最后一天
	 * 
	 * @param month
	 * @return
	 */
	static public String getFinalDateOfWeek(Calendar calendar) {

		Calendar result = (Calendar) calendar.clone();
		result.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		result.add(Calendar.DATE, 8 - result.get(Calendar.DAY_OF_WEEK));
		return getStringFromCalendar(result);

	}

	/**
	 * 取得某天对应的本周第一天
	 * 
	 * @param month
	 * @return
	 */
	public static String getFirstDateOfWeek(Date date) {
		return getFirstDateOfWeek(getCalendarFromDate(date));

	}

	/**
	 * 取得某天对应的本周最后一天
	 * 
	 * @param month
	 * @return
	 */
	static public String getFinalDateOfWeek(Date date) {
		return getFinalDateOfWeek(getCalendarFromDate(date));

	}

	/**
	 * 根据年和第几周 取得周的第一天
	 * 
	 * @param month
	 * @return
	 */
	public static String getFirstDateOfWeek(int year, int week) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		return getFirstDateOfWeek(calendar);

	}

	/**
	 * 根据年和第几周 取得周的最后一天
	 * 
	 * @param month
	 * @return
	 */
	public static String getFinalDateOfWeek(int year, int week) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		return getFinalDateOfWeek(calendar);

	}

	/**
	 * 根据日历得到日期字符串
	 * 
	 * @param calendar
	 * @return
	 */
	public static String getStringFromCalendar(Calendar calendar) {
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		int currentDay = calendar.get(Calendar.DATE);

		return String.valueOf(currentYear) + "-" + String.valueOf(currentMonth)
				+ "-" + String.valueOf(currentDay);
	}

	/**
	 * 根据Date 得到对应的Calendar
	 * 
	 * @param date
	 * @return @
	 */
	public static Calendar getCalendarFromDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 判断两个日期是否是同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
	}

	/**
	 * 得到下一年
	 * 
	 * @param year
	 * @return
	 */
	public static String getNextYear(String year) {
		if (year == null || "".equals(year))
			return "";
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.add(Calendar.YEAR, 1);
		int nextYear = c.get(Calendar.YEAR);
		return String.valueOf(nextYear);
	}

	/**
	 * 得到上一年
	 * 
	 * @param year
	 * @return
	 */
	public static String getPrevYear(String year) {
		if (year == null || "".equals(year))
			return "";
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.add(Calendar.YEAR, -1);
		int nextYear = c.get(Calendar.YEAR);
		return String.valueOf(nextYear);
	}

	/**
	 * 取前或者后多少年
	 * 
	 * @param year
	 * @param num
	 * @return
	 */
	public static String getManyBackYear(String year, Integer num) {
		if (year == null || "".equals(year))
			return "";
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.add(Calendar.YEAR, num);
		int nextYear = c.get(Calendar.YEAR);
		return String.valueOf(nextYear);
	}

	/**
	 * 得到某一天的开始时间：即yyyy-mm-dd 00:00:00 000
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.AM_PM, Calendar.AM);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 000);
		return c.getTime();
	}

	/**
	 * 得到某一天的结束时间：即yyyy-mm-dd 11:59:59 999
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.AM_PM, Calendar.PM);
		c.set(Calendar.HOUR, 11);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 得到某天的下一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

	// 测试方法
	public static void main(String[] args) {
		Date d = new Date();
		// @author Administrator
		System.out.println("当前年：" + getCurrentYear());
		System.out.println("当前月：" + getCurrentMonth());
		System.out.println("当前天：" + getCurrentDay());
		System.out.println(getString(d));
		System.out.println(getTimeString(d));
		System.out.println(getTimeStringOnly(d));
		System.out.println(getDate("2008-2-25"));
		System.out.println(getDateFromTimeString("2009-12-25 12:25:30"));
		System.out.println(getFirstDayOfMonth("2009", "1"));
		System.out.println(getLastDayOfMonth("2009", "2"));
		System.out.println("日期年：" + getYear(d));
		System.out.println("日期月：" + getMonth(d));
		System.out.println("日期天：" + getDay(d));
		System.out.println("start:" + getStartOfDay(d));
		System.out.println("end:" + getEndOfDay(d));
		System.out.println("getDateStringByFormatString："
				+ getDateStringByFormatString(new Date(), "yyyy年MM月dd日"));
		System.out.println("两天的间隔数+："
				+ getIntervalDate(new Date(), new Date(0)));
		System.out.println("两天的间隔数-："
				+ getIntervalDate(new Date(), new Date(System
						.currentTimeMillis() + 10000000000L)));
		try {
			System.out.println("月：：：：： " + getLastDayOfMonth(d));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据字符串格式 得到指定格式的字符串
	 * 
	 * @author tantao
	 * @param date
	 * @param formatString
	 *            指定格式的字符串例如[yyyy-MM-dd]
	 * @return
	 */
	public static String getDateStringByFormatString(Date date,
			String formatString) {
		try {
			if (formatString == null)
				formatString = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(formatString);
			return sdf.format(date);
		} catch (Exception e) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_STRING_COMMON_CN);
			return sdf.format(new Date());
		}
	}
	
	public static String DATE_FORMAT_STRING_COMMON="yyyy-MM-dd";
	public static String DATE_FORMAT_STRING_DEFAULT="yyyy-MM-dd HH:mm:ss";
	public static String DATE_FORMAT_STRING_DEFAULT_CN="yyyy年MM月dd日 HH时mm分ss秒";
	public static String DATE_FORMAT_STRING_COMMON_CN="yyyy年MM月dd日";

	/**
	 * 两天的间隔数
	 * 
	 * @author tantao
	 * @param begin
	 *            Date 开始日期
	 * @param end
	 *            Date 结束日期
	 * 
	 * @return 两天的间隔数
	 */
	public static Long getIntervalDate(Date begin, Date end) {
		if (begin == null || end == null)
			return 0L;
		Long num = begin.getTime() - end.getTime();
		num = num / 1000 / 60 / 60 / 24;
		return num;
	}
}
