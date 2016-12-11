package com.ways2u.android.common;

public class MathUtil {
	/**
	 * 取小数点后两位
	 * @param value
	 * @return
	 */
	public static double convert(double value) {
		long l1 = Math.round(value * 100); // 四舍五入
		double ret = l1 / 100.0; // 注意:使用 100.0 而不是 100
		return ret;
	}
}
