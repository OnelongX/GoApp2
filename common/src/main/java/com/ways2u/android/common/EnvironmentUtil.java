package com.ways2u.android.common;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

public class EnvironmentUtil {

	private EnvironmentUtil() {
	}

	public static boolean isHasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isHasLocal() {
		String path = "/storage";
		boolean hasLocal = false;
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			if (file.canWrite()) {
				// SDCARD_ROOT_PATH = path;
				hasLocal = true;
			} else {
				if (file.list() != null)
					for (File f : file.listFiles()) {
						if (f.canWrite()) {
							// SDCARD_ROOT_PATH = f.getAbsolutePath();
							hasLocal = true;
							break;
						}
					}
			}
		}
		return hasLocal;
	}

	public static File getLocal() {
		String path = "/storage";
		boolean hasLocal = false;
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			if (file.canWrite()) {
				// SDCARD_ROOT_PATH = path;
				hasLocal = true;
			} else {
				if (file.list() != null)
					for (File f : file.listFiles()) {
						if (f.canWrite()) {
							path = f.getAbsolutePath();
							hasLocal = true;
							break;
						}
					}
			}
		}
		if (hasLocal)
			return new File(path);
		return null;
	}

	/**
	 * 获取SD卡可用空间大小
	 * 
	 * @return
	 */
	public static long getAvailaleSize() {
		File path = null;
		if (isHasSdcard())
			path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
		else if (isHasLocal())
			path = getLocal();
		if (path == null) {
			return 0;
		}
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
		// (availableBlocks * blockSize)/1024 KIB 单位
		// (availableBlocks * blockSize)/1024 /1024 MIB单位
	}

	/**
	 * 获取SD卡空间大小
	 * 
	 * @return
	 */
	public static long getAllSize() {
		File path = null;
		if (isHasSdcard())
			path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
		else if (isHasLocal())
			path = getLocal();
		if (path == null) {
			return 0;
		}
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getBlockCount();
		return availableBlocks * blockSize;
	}

}
