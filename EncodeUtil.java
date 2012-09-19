import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;

/**
 * web中使用，对内容转换编码
 * 
 * @author kaka2008
 * 
 */
public class EncodeUtil {

	static BitSet dontNeedEncoding;
	static {
		dontNeedEncoding = new BitSet(256);
		for (int i = 32; i <= 126; i++)
			dontNeedEncoding.set(i);
	}

	public static void main(String[] args) {
		try {
			System.out.println(encode("世界杯"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encode(String s) throws UnsupportedEncodingException {
		return encode(s, "utf-8");
	}

	public static String encode(String s, String s1)
			throws UnsupportedEncodingException {
		if (s == null)
			return "";
		boolean flag = false;
		boolean flag1 = false;
		byte byte0 = 10;
		StringBuffer stringbuffer = new StringBuffer(s.length());
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(
				byte0);
		OutputStreamWriter outputstreamwriter = new OutputStreamWriter(
				bytearrayoutputstream, s1);
		for (int i = 0; i < s.length(); i++) {
			int j = s.charAt(i);
			if (dontNeedEncoding.get(j)) {
				if (j == 32) {
					j = 43;
					flag = true;
				}
				stringbuffer.append((char) j);
				flag1 = true;
				continue;
			}
			try {
				if (flag1) {
					outputstreamwriter = new OutputStreamWriter(
							bytearrayoutputstream, s1);
					flag1 = false;
				}
				outputstreamwriter.write(j);
				if (j >= 55296 && j <= 56319 && i + 1 < s.length()) {
					char c = s.charAt(i + 1);
					if (c >= '\uDC00' && c <= '\uDFFF') {
						outputstreamwriter.write(c);
						i++;
					}
				}
				outputstreamwriter.flush();
			} catch (IOException ioexception) {
				bytearrayoutputstream.reset();
				continue;
			}
			byte abyte0[] = bytearrayoutputstream.toByteArray();
			for (int k = 0; k < abyte0.length; k++) {
				stringbuffer.append('%');
				char c1 = Character.forDigit(abyte0[k] >> 4 & 0xf, 16);
				if (Character.isLetter(c1))
					c1 -= ' ';
				stringbuffer.append(c1);
				c1 = Character.forDigit(abyte0[k] & 0xf, 16);
				if (Character.isLetter(c1))
					c1 -= ' ';
				stringbuffer.append(c1);
			}

			bytearrayoutputstream.reset();
			flag = true;
		}

		return flag ? stringbuffer.toString() : s;
	}

}
