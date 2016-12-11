package com.ways2u.android.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

import com.ways2u.android.common.security.MD5;


public class SignaturesUtil {

	private SignaturesUtil() {
	}

	/**
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static Signature getPackageSignature(Context context,
			String packageName) {
		PackageManager pm = context.getPackageManager();
		List<PackageInfo> apps = pm
				.getInstalledPackages(PackageManager.GET_SIGNATURES);
		Iterator<PackageInfo> it = apps.iterator();
		while (it.hasNext()) {
			PackageInfo info = it.next();
			if (info.packageName.equals(packageName)) {
				return info.signatures[0];
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static PackageInfo getPackageArchiveInfo(String archiveFilePath,
			int flags) {
		// Workaround for
		// https://code.google.com/p/android/issues/detail?id=9151#c8
		try {
			Class<?> packageParserClass = Class
					.forName("android.content.pm.PackageParser");
			Class<?>[] innerClasses = packageParserClass.getDeclaredClasses();
			Class<?> packageParserPackageClass = null;
			for (Class<?> innerClass : innerClasses) {
				if (0 == innerClass.getName().compareTo(
						"android.content.pm.PackageParser$Package")) {
					packageParserPackageClass = innerClass;
					break;
				}
			}
			Constructor<?> packageParserConstructor = packageParserClass
					.getConstructor(String.class);
			Method parsePackageMethod = packageParserClass.getDeclaredMethod(
					"parsePackage", File.class, String.class,
					DisplayMetrics.class, int.class);
			Method collectCertificatesMethod = packageParserClass
					.getDeclaredMethod("collectCertificates",
							packageParserPackageClass, int.class);
			Method generatePackageInfoMethod = packageParserClass
					.getDeclaredMethod("generatePackageInfo",
							packageParserPackageClass, int[].class, int.class,
							long.class, long.class);
			packageParserConstructor.setAccessible(true);
			parsePackageMethod.setAccessible(true);
			collectCertificatesMethod.setAccessible(true);
			generatePackageInfoMethod.setAccessible(true);

			Object packageParser = packageParserConstructor
					.newInstance(archiveFilePath);

			DisplayMetrics metrics = new DisplayMetrics();
			metrics.setToDefaults();

			final File sourceFile = new File(archiveFilePath);

			Object pkg = parsePackageMethod.invoke(packageParser, sourceFile,
					archiveFilePath, metrics, 0);
			if (pkg == null) {
				return null;
			}

			if ((flags & PackageManager.GET_SIGNATURES) != 0) {
				collectCertificatesMethod.invoke(packageParser, pkg, 0);
			}

			return (PackageInfo) generatePackageInfoMethod.invoke(null, pkg,
					null, flags, 0, 0);
		} catch (Exception e) {
			Log.e("Signature Monitor",
					"android.content.pm.PackageParser reflection failed: "
							+ e.toString());
		}

		return null;
	}

	/**
	 * 
	 * @param context
	 * @param apkFile
	 *            文件的全路径信息（包括apk文件的名称），如果是无效的apk文件，返回值为null
	 * @return
	 */
	public static Signature getApkSignatureByFilePath(Context context,
			String apkFile) {
		PackageInfo newInfo = getPackageArchiveInfo(apkFile,
				PackageManager.GET_ACTIVITIES | PackageManager.GET_SIGNATURES);
		if (newInfo != null) {
			if (newInfo.signatures != null && newInfo.signatures.length > 0) {
				return newInfo.signatures[0];
			}
		}
		return null;
	}

	public static String showUninstallAPKSignatures(String apkPath) {
		String PATH_PackageParser = "android.content.pm.PackageParser";
		try {
			// apk包的文件路径
			// 这是一个Package 解释器, 是隐藏的
			// 构造函数的参数只有一个, apk文件的路径
			// PackageParser packageParser = new PackageParser(apkPath);
			Class<?> pkgParserCls = Class.forName(PATH_PackageParser);
			Class<?>[] typeArgs = new Class[1];
			typeArgs[0] = String.class;
			Constructor<?> pkgParserCt = pkgParserCls.getConstructor(typeArgs);
			Object[] valueArgs = new Object[1];
			valueArgs[0] = apkPath;
			Object pkgParser = pkgParserCt.newInstance(valueArgs);
			// MediaApplication.logD(DownloadApk.class, "pkgParser:" +
			// pkgParser.toString());
			// 这个是与显示有关的, 里面涉及到一些像素显示等等, 我们使用默认的情况
			DisplayMetrics metrics = new DisplayMetrics();
			metrics.setToDefaults();
			// PackageParser.Package mPkgInfo = packageParser.parsePackage(new
			// File(apkPath), apkPath,
			// metrics, 0);
			typeArgs = new Class[4];
			typeArgs[0] = File.class;
			typeArgs[1] = String.class;
			typeArgs[2] = DisplayMetrics.class;
			typeArgs[3] = Integer.TYPE;
			Method pkgParser_parsePackageMtd = pkgParserCls.getDeclaredMethod(
					"parsePackage", typeArgs);
			valueArgs = new Object[4];
			valueArgs[0] = new File(apkPath);
			valueArgs[1] = apkPath;
			valueArgs[2] = metrics;
			valueArgs[3] = PackageManager.GET_SIGNATURES;
			Object pkgParserPkg = pkgParser_parsePackageMtd.invoke(pkgParser,
					valueArgs);

			typeArgs = new Class[2];
			typeArgs[0] = pkgParserPkg.getClass();
			typeArgs[1] = Integer.TYPE;
			Method pkgParser_collectCertificatesMtd = pkgParserCls
					.getDeclaredMethod("collectCertificates", typeArgs);
			valueArgs = new Object[2];
			valueArgs[0] = pkgParserPkg;
			valueArgs[1] = PackageManager.GET_SIGNATURES;
			pkgParser_collectCertificatesMtd.invoke(pkgParser, valueArgs);
			// 应用程序信息包, 这个公开的, 不过有些函数, 变量没公开
			Field packageInfoFld = pkgParserPkg.getClass().getDeclaredField(
					"mSignatures");
			Signature[] info = (Signature[]) packageInfoFld.get(pkgParserPkg);
			return info[0].toCharsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSigns(Context context, String signaturesPackageName) {
		PackageManager pm = context.getPackageManager();
		List<PackageInfo> apps = pm
				.getInstalledPackages(PackageManager.GET_SIGNATURES);
		Iterator<PackageInfo> iter = apps.iterator();
		while (iter.hasNext()) {
			PackageInfo packageinfo = iter.next();
			String packageName = packageinfo.packageName;
			if (packageName.equals(signaturesPackageName)) {
				return packageinfo.signatures[0].toCharsString();
			}
		}
		return null;
	}

	public static String getFingerprint(Context context,
			String signaturesPackageName, boolean isSHA) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(signaturesPackageName/* "com.sina,weibo" */,
							PackageManager.GET_SIGNATURES);
			Signature[] signs = packageInfo.signatures;
			String t = "MD5";
			if(isSHA){
				t = "SHA-1";
			}
			MessageDigest md5 = MessageDigest.getInstance(t);
			for (Signature sign : signs) {
				md5.update(sign.toByteArray());
			}
			byte[] md = md5.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0, len = md.length; i < len; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16)
					sb.append("0");
				sb.append(Integer.toHexString(val));
				sb.append(":");
			}
			if (sb.length() > 0)
				sb.deleteCharAt(sb.length() - 1);
			return sb.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSignMD5(Context context,
			String signaturesPackageName) {
		try {
			PackageInfo packageInfo = context.getApplicationContext().getPackageManager()
					.getPackageInfo(signaturesPackageName/* "com.sina,weibo" */,
							PackageManager.GET_SIGNATURES);
			Signature[] signs = packageInfo.signatures;
			Signature sign = signs[0];
			byte[] data = sign.toByteArray();
			String md5 = MD5.md5(data);
			return md5;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSignMD5WithTelephonyInfo(Context context,
			String signaturesPackageName) {
		try {
			PackageInfo packageInfo = context.getApplicationContext().getPackageManager()
					.getPackageInfo(signaturesPackageName/* "com.sina,weibo" */,
							PackageManager.GET_SIGNATURES);
			Signature[] signs = packageInfo.signatures;
			Signature sign = signs[0];
			// int versionCode = packageInfo.versionCode;
			byte[] data = sign.toByteArray();
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = telephonyManager.getDeviceId();
			String imsi = telephonyManager.getSubscriberId();
			String md5 = MD5.md5(data);
			md5 = md5 + imei + imsi;
			md5 = MD5.md5(md5.getBytes());
			return md5;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void parseSignature(byte[] signature) {
		try {
			CertificateFactory certFactory = CertificateFactory
					.getInstance("X.509");
			X509Certificate cert = (X509Certificate) certFactory
					.generateCertificate(new ByteArrayInputStream(signature));
			String pubKey = cert.getPublicKey().toString();
			String signNumber = cert.getSerialNumber().toString();
			System.out.println("signName:" + cert.getSigAlgName());
			System.out.println("pubKey:" + pubKey);
			System.out.println("signNumber:" + signNumber);
			System.out.println("subjectDN:" + cert.getSubjectDN().toString());
		} catch (CertificateException e) {
			e.printStackTrace();
		}
	}

}
