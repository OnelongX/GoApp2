package com.ways2u.android.common.security;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public abstract class DESCoder {

	public static final String KEY_ALGORITHM = "DES";

	public static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

	private static final byte[] ENCIV = { (byte) 0xEF, (byte) 0xAB,
			(byte) 0x56, (byte) 0x78, (byte) 0x90, (byte) 0x34, (byte) 0xCD,
			(byte) 0x12 };

	private static Key toKey(byte[] key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance(KEY_ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(dks);
		return secretKey;
	}

	public static byte[] encrypt(byte[] data, byte[] key) {
		try {
			Key k = toKey(key);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			AlgorithmParameterSpec iv = new IvParameterSpec(ENCIV);
			cipher.init(Cipher.ENCRYPT_MODE, k, iv);
			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decrypt(byte[] data, byte[] key) {
		try {
			Key k = toKey(key);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			AlgorithmParameterSpec iv = new IvParameterSpec(ENCIV);
			cipher.init(Cipher.DECRYPT_MODE, k, iv);
			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(String dataStr, String keyStr) {
		byte[] dateBytes = DESCoder.encrypt(dataStr.getBytes(),
				keyStr.getBytes());
		if (dateBytes != null)
			return Base64.encode(dateBytes);
		return "";
	}

	public static String decrypt(String dataStr, String keyStr) {
		try {
			byte[] dataBytes = Base64.decode(dataStr);
			dataBytes = DESCoder.decrypt(dataBytes, keyStr.getBytes());
			if (dataBytes != null)
				return new String(dataBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
