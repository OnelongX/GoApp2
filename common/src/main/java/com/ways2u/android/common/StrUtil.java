package com.ways2u.android.common;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StrUtil {
	private static StringBuffer sb=new StringBuffer();
	/**
	 * 字符串的附加
	 * @param args
	 * @return 附加后的字符
	 */
	public static String appendString(String...args){
		if(args==null || args.length<1){
			return "";
		}
		sb.setLength(0);
		for(int i=0;i<args.length;i++){
			sb.append(args[i]);
		}
		return sb.toString();
	}
	/**
	 * 字符串的附加
	 * @param args
	 * @return 附加后的字符
	 */
	public static String appendString(Object...args){
		if(args==null || args.length<1){
			return "";
		}
		sb.setLength(0);
		for(int i=0;i<args.length;i++){
			sb.append(args[i]);
		}
		return sb.toString();
	}

	public static int stringToNum(String s){
		int num = 0;
		try {
			num = Integer.parseInt(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * 格式化数字为int
	 * 
	 * @param v
	 * @return
	 */
	public static int nullToInt(Object vStr) {
		int str = 0;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Integer.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为int
	 * 
	 * @param v
	 * @return
	 */
	public static Long nullToLong(Object vStr) {
		Long str = 0L;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Long.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为double
	 * 
	 * @param v
	 * @return
	 */
	public static Double nullToDouble(Object vStr) {
		Double str = 0.00;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Double.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为Boolean
	 * 
	 * @param v
	 * @return
	 */
	public static boolean nullToBoolean(Object vStr) {
		boolean str = false;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Boolean.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 假如obj对象 是null返回""
	 * 
	 * @param obj
	 * @return
	 */
	public static String nullToStr(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();
	}

	public static int StringToInt(String s) {
		int tmp = 0;
		if (s == null)
			return 0;
		try {
			tmp = Integer.parseInt(s);
		} catch (Exception e) {
			tmp = 0;
		}
		return tmp;
	}

	public static float StringToFloat(String s) {
		float tmp = 0;
		if (s == null)
			return 0;
		try {
			tmp = Float.parseFloat(s);
		} catch (Exception e) {
			tmp = 0;
		}
		return tmp;
	}

	/**
	 * 把字符串转换成BigDecimal,并用format进行格式化操
	 * 
	 * @param obj
	 * @param format
	 * @return
	 */
	public static BigDecimal formatBigDecimal(Number bd, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return new BigDecimal(df.format(bd));
	}

	/**
	 * 把字符串转换成BigDecimal
	 * 
	 * @param obj
	 * @param format
	 * @return
	 */
	public static BigDecimal nullToBigDecimal(Object obj) {
		if ("".equals(StrUtil.nullToStr(obj))) {
			obj = "0.00";
		}
		BigDecimal bd = new BigDecimal(obj.toString());
		return bd;
	}

	public static String encode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public static String decode(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return str;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static Date getDate(String dateStr, String format) {
		if (dateStr==null || dateStr.trim().equals("")) {
			return null;
		}
		if (format==null || format.trim().equals("")) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.getDefault());
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
