/**
 * 根据文件后缀来得到mime type
 * 
 * @author kaka2008
 * 
 */
public class MimeUtils {

	/**
	 * 
	 */
	public MimeUtils() {
		super();
	}

	public static String getMIMETypeByExtName(String filePro) {
		String mimeType = "text/plain";

		if (filePro == null)
			return mimeType;
		if (filePro.toLowerCase().equals("doc")) {
			mimeType = "application/msword";
		} else if (filePro.toLowerCase().equals("ppt")) {
			mimeType = "application/mspowerpoint";
		} else if (filePro.toLowerCase().equals("xls")) {
			mimeType = "application/msexcel";
		} else if (filePro.toLowerCase().equals("vsd")) {
			mimeType = "application/msvisio";
		} else if (filePro.toLowerCase().equals("pdf")) {
			mimeType = "application/pdf";
		} else if (filePro.toLowerCase().equals("zip")) {
			mimeType = "application/zip";
		} else if ((filePro.toLowerCase().equals("jpg"))
				| (filePro.toLowerCase().equals("jpeg"))) {
			mimeType = "image/jpeg";
		} else if (filePro.toLowerCase().equals("gif")) {
			mimeType = "image/gif";
		} else if (filePro.toLowerCase().equals("tif")) {
			mimeType = "image/tiff";
		} else if (filePro.toLowerCase().equals("png")) {
			mimeType = "image/png";
		} else if (filePro.toLowerCase().equals("txt")) {
			mimeType = "text/plain";
		} else if (filePro.toLowerCase().equals("rtf")) {
			mimeType = "text/rtf";
		} else if (filePro.toLowerCase().equals("html")) {
			mimeType = "text/html";
		} else if (filePro.toLowerCase().equals("xml")) {
			mimeType = "text/xml";
		} else if (filePro.toLowerCase().equals("swf")) {
			mimeType = "application/x-shockwave-flash";
		} else {
			mimeType = "text/plain";
		}
		return mimeType;
	}

}
