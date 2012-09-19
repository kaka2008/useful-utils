import java.text.NumberFormat;
import java.util.Calendar;

/**
 * �������йص�Util
 * 
 * @author kaka2008
 * 
 */
public class NumberUtil {
	private static final String UNIT = "��ǧ��ʰ��ǧ��ʰ��ǧ��ʰԪ�Ƿ�";
	private static final String DIGIT = "��Ҽ��������½��ƾ�";
	/*
	 * ���ֵ
	 */
	private static final double MAX_VALUE = Double.MAX_VALUE;
	/*
	 * ��������������ʽ
	 */
	private static final String FLOAT_NUMBER = "^[-+]?[0-9]*\\.?[0-9]+(?:[eE][-+]?[0-9]+)?$";
	/*
	 * ������������ʽ
	 */
	private static final String INTEGER_NUMBER = "^[-+]?\\d+$";

	/**
	 * ��ʽ������
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
	 * �õ����Ĵ�д�Ľ��
	 * 
	 * @param money
	 * @return
	 */
	public static String getChineseMoney(double money) {
		if (money < 0 || money > MAX_VALUE)
			return "�����Ƿ�!";
		long lo = Math.round(money * 100);
		if (lo == 0)
			return "��Ԫ��";
		String strValue = lo + "";
		// i����������
		int i = 0;
		// j�������Ƶ�λ
		int j = UNIT.length() - strValue.length();
		String rs = "";
		boolean isZero = false;
		for (; i < strValue.length(); i++, j++) {
			char ch = strValue.charAt(i);
			if (ch == '0') {
				isZero = true;
				if (UNIT.charAt(j) == '��' || UNIT.charAt(j) == '��'
						|| UNIT.charAt(j) == 'Ԫ') {
					rs = rs + UNIT.charAt(j);
					isZero = false;
				}
			} else {
				if (isZero) {
					rs = rs + "��";
					isZero = false;
				}
				rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
			}
		}
		if (!rs.endsWith("��")) {
			rs = rs + "��";
		}
		rs = rs.replaceAll("����", "��");
		return rs;
	}

	/**
	 * ȥ����������еĶ���
	 * 
	 * @param value
	 * @return
	 */
	public static String removeComma(String value) {
		if (value == null)
			return value;
		value = value.replace(",", "");
		value = value.replace("��", "");
		return value;
	}

	/**
	 * �жϴ���������Ƿ�������
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
	 * �жϴ���������Ƿ���С��<br>
	 * ����Ҳ����
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
	 * �õ��������ݵ�int���͵�ֵ
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
	 * �õ��������ݵ�double���͵�ֵ
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
	 * �õ���ǰ��ĺ���д��
	 * 
	 * @return
	 */
	public static String getChineseYearForShow() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String[] str = new String[] { "��", "һ", "��", "��", "��", "��", "��", "��",
				"��", "��" };
		String yearStr = String.valueOf(year);
		char[] ch = yearStr.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : ch) {
			Integer in = Integer.parseInt(String.valueOf(c));
			sb.append(str[in]);
		}
		return sb.append("��").toString();
	}
}
