package com.ways2u.android.common.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * md5签名
 * 
 * @author 刘哈哈
 * 
 */
public class MD5 {

	/**
	 * 默认密钥为空
	 */
	private static String DEFAULT_KEY = "你能破解么,我就不信了,哈哈,傻了吧 .就是要让你傻.";
	/**
	 * 默认字符串为utf-8
	 */
	private static String DEFAULT_CODING = "utf-8";

	public static byte[] md5(byte[] buffer, byte[] key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(buffer);
			return md5.digest(key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String md5(byte[] buffer) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(buffer);
			byte[] temp = md5.digest(DEFAULT_KEY.getBytes(DEFAULT_CODING));
			String result = "";
			for (int i = 0; i < temp.length; i++) {
				result += Integer.toHexString(
						(0x000000ff & temp[i]) | 0xffffff00).substring(6);
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 采用默认key和默认字符编码
	 * 
	 * @Title: md5
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param strSrc
	 * @return
	 * @author 刘哈哈
	 * @date Jan 20, 2012
	 */
	public static String md5(String strSrc) {
		return md5(strSrc, DEFAULT_KEY, DEFAULT_CODING);
	}

	/**
	 * 
	 * @Title: md5
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param strSrc
	 * @param key
	 * @return
	 * @author 刘哈哈
	 * @date Jan 20, 2012
	 */
	public static String md5(String strSrc, String key) {
		return md5(strSrc, key, DEFAULT_CODING);
	}

	/**
	 * 签名
	 * 
	 * @Title: md5
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param strSrc
	 * @param key
	 * @param encoding
	 * @return
	 * @author 刘哈哈
	 * @date Jan 20, 2012
	 */
	public static String md5(String strSrc, String key, String encoding) {
		try {
			return md5(strSrc.getBytes(encoding), key, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param data
	 * @param key
	 * @param encoding
	 * @return
	 */
	private static String md5(byte[] data, String key, String encoding) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(data);

			String result = "";
			byte[] temp = md5.digest(key.getBytes(encoding));
			for (int i = 0; i < temp.length; i++) {
				result += Integer.toHexString(
						(0x000000ff & temp[i]) | 0xffffff00).substring(6);
			}
			return result;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
