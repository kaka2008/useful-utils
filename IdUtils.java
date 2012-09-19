import java.util.Random;
import java.util.UUID;

/**
 * ����Ψһ��Id
 * 
 * @author kaka2008
 * 
 */
public class IdUtils {

	private static Random rd = null;

	/**
	 * ����һ��Ψһ��id(32λ)
	 * 
	 * @return
	 */
	public static String getUUID() {
		if (rd == null)
			rd = new Random(System.currentTimeMillis());
		long l1 = java.lang.Math.abs(rd.nextLong());
		long l2 = java.lang.Math.abs(rd.nextLong());
		long l3 = java.lang.Math.abs(rd.nextLong());
		long l4 = java.lang.Math.abs(rd.nextLong());
		long l5 = java.lang.Math.abs(rd.nextLong());
		long l6 = java.lang.Math.abs(rd.nextLong());
		String result = (Long.toHexString(l1 * l2) + Long.toHexString(l3 * l4) + Long
				.toHexString(l5 * l6)).substring(0, 32);
		return result;
	}

	/**
	 * ʹ��Java��������������һ��Ψһ��id(32λ)
	 * 
	 * @return
	 */
	public static String getUUID2() {
		UUID uuid = UUID.randomUUID();
		String[] con = uuid.toString().split("-");
		StringBuilder sb = new StringBuilder();
		for (String string : con) {
			sb.append(string);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println("uuid 1 = " + getUUID());
		System.out.println("uuid 2 = " + getUUID2());
	}

}
