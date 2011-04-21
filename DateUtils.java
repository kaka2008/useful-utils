

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
	 * ȡ��ǰ��ĳ��ʱ�������ʱ��
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
	 * ȡ��ǰ��
	 * 
	 * @return
	 */
	public static String getCurrentYear() {
		Calendar now = Calendar.getInstance();
		return new Integer(now.get(Calendar.YEAR)).toString();
	}

	/**
	 * ȡ��ǰ��
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		Calendar now = Calendar.getInstance();
		return new Integer(now.get(Calendar.MONTH) + 1).toString();
	}

	/**
	 * ȡ��ǰ����ַ���
	 * 
	 * @return
	 */
	public static String getCurrentDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	/**
	 * ����ĳ��Date�õ��ַ�������ʽΪyyyy-MM-dd
	 * 
	 * @param date
	 * @return @
	 */
	public static String getString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * ����ĳ��Date�õ���ʽ������ַ���
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
	 * �õ�ĳ��Date������ʱ���ַ�������ʽΪyyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * �õ�ĳ�����ڵ�ʱ�䲿��
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeStringOnly(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date);
	}

	/**
	 * �����ַ����õ�ĳ�����ڣ���ʽΪyyyy-MM-dd
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
	 * �����ַ����õ�ĳ�����ڣ���ʽΪyyyy-MM-dd HH:mm:ss
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
	 * ȡĳ�����ڵ����
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
	 * ȡĳ�����ڵ��·�
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
	 * ȡĳ�����ڵ����
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
	 * ȡĳһ��ĵ�һ��
	 * 
	 * @param year
	 * @return
	 */
	public static Date getFirstDayOfYear(String year) {
		return getDate(year + "-1-1");
	}

	/**
	 * ȡĳһ������һ��
	 * 
	 * @param year
	 * @return
	 */
	public static Date getLastDayOfYear(String year) {
		return getDate(year + "-12-31");
	}

	/**
	 * ȡĳ��ĳ�µĵ�һ��
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
	 * ȡĳ��ĳ�µ����һ��
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
	 * ȡĳ��ĳ�µĵ�һ��
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
	 * ȡĳ��ĳ�µ����һ��
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
	 * ȡ��ĳ���Ӧ�ı��ܵ�һ��
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
	 * ȡ��ĳ���Ӧ�ı������һ��
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
	 * ȡ��ĳ���Ӧ�ı��ܵ�һ��
	 * 
	 * @param month
	 * @return
	 */
	public static String getFirstDateOfWeek(Date date) {
		return getFirstDateOfWeek(getCalendarFromDate(date));

	}

	/**
	 * ȡ��ĳ���Ӧ�ı������һ��
	 * 
	 * @param month
	 * @return
	 */
	static public String getFinalDateOfWeek(Date date) {
		return getFinalDateOfWeek(getCalendarFromDate(date));

	}

	/**
	 * ������͵ڼ��� ȡ���ܵĵ�һ��
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
	 * ������͵ڼ��� ȡ���ܵ����һ��
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
	 * ���������õ������ַ���
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
	 * ����Date �õ���Ӧ��Calendar
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
	 * �ж����������Ƿ���ͬһ��
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
	}

	/**
	 * �õ���һ��
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
	 * �õ���һ��
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
	 * ȡǰ���ߺ������
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
	 * �õ�ĳһ��Ŀ�ʼʱ�䣺��yyyy-mm-dd 00:00:00 000
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
	 * �õ�ĳһ��Ľ���ʱ�䣺��yyyy-mm-dd 11:59:59 999
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
	 * �õ�ĳ�����һ��
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

	// ���Է���
	public static void main(String[] args) {
		Date d = new Date();
		// @author Administrator
		System.out.println("��ǰ�꣺" + getCurrentYear());
		System.out.println("��ǰ�£�" + getCurrentMonth());
		System.out.println("��ǰ�죺" + getCurrentDay());
		System.out.println(getString(d));
		System.out.println(getTimeString(d));
		System.out.println(getTimeStringOnly(d));
		System.out.println(getDate("2008-2-25"));
		System.out.println(getDateFromTimeString("2009-12-25 12:25:30"));
		System.out.println(getFirstDayOfMonth("2009", "1"));
		System.out.println(getLastDayOfMonth("2009", "2"));
		System.out.println("�����꣺" + getYear(d));
		System.out.println("�����£�" + getMonth(d));
		System.out.println("�����죺" + getDay(d));
		System.out.println("start:" + getStartOfDay(d));
		System.out.println("end:" + getEndOfDay(d));
		System.out.println("getDateStringByFormatString��"
				+ getDateStringByFormatString(new Date(), "yyyy��MM��dd��"));
		System.out.println("����ļ����+��"
				+ getIntervalDate(new Date(), new Date(0)));
		System.out.println("����ļ����-��"
				+ getIntervalDate(new Date(), new Date(System
						.currentTimeMillis() + 10000000000L)));
		try {
			System.out.println("�£��������� " + getLastDayOfMonth(d));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �����ַ�����ʽ �õ�ָ����ʽ���ַ���
	 * 
	 * @author tantao
	 * @param date
	 * @param formatString
	 *            ָ����ʽ���ַ�������[yyyy-MM-dd]
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
	public static String DATE_FORMAT_STRING_DEFAULT_CN="yyyy��MM��dd�� HHʱmm��ss��";
	public static String DATE_FORMAT_STRING_COMMON_CN="yyyy��MM��dd��";

	/**
	 * ����ļ����
	 * 
	 * @author tantao
	 * @param begin
	 *            Date ��ʼ����
	 * @param end
	 *            Date ��������
	 * 
	 * @return ����ļ����
	 */
	public static Long getIntervalDate(Date begin, Date end) {
		if (begin == null || end == null)
			return 0L;
		Long num = begin.getTime() - end.getTime();
		num = num / 1000 / 60 / 60 / 24;
		return num;
	}
}
