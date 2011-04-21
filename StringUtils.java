
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 处理字符串
 * 
 * @author kaka2008
 * 
 */
public class StringUtils {

	private static final String INTEGER_NUMBER = "^[-+]?\\d+$";
	private static final String FLOAT_NUMBER = "^[-+]?[0-9]*\\.?[0-9]+(?:[eE][-+]?[0-9]+)?$";
	
	/**
	 * 将oldStr中的#{}包含的内容换成bean的属性或paraMap的内容，bean的属性优先
	 * 
	 * @param oldStr
	 * @param bean
	 * @param paraMap
	 * @return
	 */
	public static final String computeReplace(String oldStr, Object bean,
			Map<String, Object> paraMap) {
		// #{}包含的内容
		String field = "";
		// #{}前面的内容
		String fieldStart = "";
		String fieldEnd = "";
		// 若有多个
		List fields = new ArrayList();
		// 用于存储field与change后值的Map
		Map changeValues = new HashMap();

		String[] ss = oldStr.split("#");

		for (int i = 0; i < ss.length; i++) {
			if (ss[i] != null && ss[i].length() > 0) {
				String forValue = "#" + ss[i];
				int k = forValue.indexOf("#{");
				int m = forValue.indexOf("}");
				if (k >= 0 && m >= 0) {
					field = forValue.substring(k + 2, m);
					fields.add(field);
				}
			}
		}
		// field非空的情况下，将#{}包含的内容field替换成bean的属性或paraMap的内容，bean的属性优先
		if (fields != null && fields.size() > 0) {
			for (int n = 0; n < fields.size(); n++) {
				// 替换后值
				String changeValue = "";
				field = (String) fields.get(n);
				if (field != null && !"".equals(field)) {
					if (bean != null) {
						changeValue = (String) ReflectionUtils.getFieldValue(
								bean, field);
					}
					if (changeValue == null || "".equals(changeValue)) {
						if (paraMap != null) {
							changeValue = (String) paraMap.get(field);
							// changeValues.put(field, changeValue);
						}
					}
					changeValues.put(field, changeValue);
				}
			}
		}
		// 返回处理
		if (fields != null && fields.size() > 0) {
			for (int g = 0; g < fields.size(); g++) {
				String chanfid = (String) fields.get(g);
				String forCh = "#{" + chanfid + "}";
				String changeDd = (String) changeValues.get(chanfid);
				if (forCh != null && !"".equals(forCh) && changeDd != null
						& !"".equals(changeDd)) {
					oldStr = oldStr.replace(forCh, changeDd);
				}
			}
		}

		return oldStr;
	}

	public static List split(String str, String delim) {
		List splitList = new ArrayList();
		StringTokenizer st = null;

		if (str == null)
			return splitList;

		if (delim != null)
			st = new StringTokenizer(str, delim);
		else
			st = new StringTokenizer(str);

		if (st != null && st.hasMoreTokens()) {
			while (st.hasMoreTokens())
				splitList.add(st.nextToken());
		}
		return splitList;
	}
	
	/**
	 * 传入一个经过format的number类型的数值，去掉其中的逗号
	 * @param value
	 * @return
	 */
	public static String removeComma(String value){
		if(value == null)
			return value;
		value = value.replace(",", "");
		value = value.replace("，", "");
		return value;
	}
	
	/**
	 * 验证传进入的值是否是整数
	 * @param number
	 * @return
	 */
	public static boolean isInteger(String number) {
		if (number == null)
			return false;
		boolean flag = true;
		flag = number.matches(INTEGER_NUMBER);
		return flag;
	}
	
	/**
	 * 验证传入的值是否是浮点数
	 * @param number
	 * @return
	 */
	public static boolean isFloat(String number){
		if (number == null)
			return false;
		boolean flag = true;
		flag = number.matches(FLOAT_NUMBER);
		return flag;
	}
}
