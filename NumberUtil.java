import java.text.NumberFormat;
import java.util.Calendar;

/**
 * 跟数字有关的Util
 * 
 * @author kaka2008
 * 
 */
public class NumberUtil {
	private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";
	private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
	/*
	 * 最大值
	 */
	private static final double MAX_VALUE = Double.MAX_VALUE;
	/*
	 * 浮点数的正则表达式
	 */
	private static final String FLOAT_NUMBER = "^[-+]?[0-9]*\\.?[0-9]+(?:[eE][-+]?[0-9]+)?$";
	/*
	 * 整数的正则表达式
	 */
	private static final String INTEGER_NUMBER = "^[-+]?\\d+$";

	/**
	 * 格式化数字
	 * 
	 * @param number
	 * @return
	 */
	public static String formatDouble(double number) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(number);
	}

	/**
	 * 得到中文大写的金额
	 * 
	 * @param money
	 * @return
	 */
	public static String getChineseMoney(double money) {
		if (money < 0 || money > MAX_VALUE)
			return "参数非法!";
		long lo = Math.round(money * 100);
		if (lo == 0)
			return "零元整";
		String strValue = lo + "";
		// i用来控制数
		int i = 0;
		// j用来控制单位
		int j = UNIT.length() - strValue.length();
		String rs = "";
		boolean isZero = false;
		for (; i < strValue.length(); i++, j++) {
			char ch = strValue.charAt(i);
			if (ch == '0') {
				isZero = true;
				if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万'
						|| UNIT.charAt(j) == '元') {
					rs = rs + UNIT.charAt(j);
					isZero = false;
				}
			} else {
				if (isZero) {
					rs = rs + "零";
					isZero = false;
				}
				rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
			}
		}
		if (!rs.endsWith("分")) {
			rs = rs + "正";
		}
		rs = rs.replaceAll("亿万", "亿");
		return rs;
	}

	/**
	 * 去掉传入参数中的逗号
	 * 
	 * @param value
	 * @return
	 */
	public static String removeComma(String value) {
		if (value == null)
			return value;
		value = value.replace(",", "");
		value = value.replace("，", "");
		return value;
	}

	/**
	 * 判断传入的内容是否是整数
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isInteger(String number) {
		if (number == null)
			return false;
		boolean flag = true;
		flag = number.matches(NumberUtil.INTEGER_NUMBER);
		return flag;
	}

	/**
	 * 判断传入的内容是否是小数<br>
	 * 整数也可以
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isFloat(String number) {
		if (number == null)
			return false;
		boolean flag = true;
		flag = number.matches(NumberUtil.FLOAT_NUMBER);
		return flag;
	}

	/**
	 * 得到传入内容的int类型的值
	 * 
	 * @param content
	 * @return
	 */
	public static int getIntValue(String content) {
		int number = 0;
		if (isInteger(content))
			return Integer.parseInt(content);
		return number;
	}

	/**
	 * 得到传入内容的double类型的值
	 * 
	 * @param content
	 * @return
	 */
	public static double getDoubleValue(String content) {
		double number = 0.0;
		if (isFloat(content))
			return Double.parseDouble(content);
		return number;
	}

	/**
	 * 得到当前年的汉字写法
	 * 
	 * @return
	 */
	public static String getChineseYearForShow() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String[] str = new String[] { "〇", "一", "二", "三", "四", "五", "六", "七",
				"八", "九" };
		String yearStr = String.valueOf(year);
		char[] ch = yearStr.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : ch) {
			Integer in = Integer.parseInt(String.valueOf(c));
			sb.append(str[in]);
		}
		return sb.append("年").toString();
	}
}
