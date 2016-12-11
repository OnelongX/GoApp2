package com.ways2u.android.common;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("DefaultLocale")
public class HTTPMimeUtil
{
	private static final Map<String, String> ANDROID_MIME = new HashMap<String, String>();
	static
	{
		ANDROID_MIME.put(".css", "text/css");
		ANDROID_MIME.put(".xml", "text/xml");
		ANDROID_MIME.put(".mp1", "audio/cedara");
		ANDROID_MIME.put(".mp2", "audio/cedara");
		ANDROID_MIME.put(".ra", "audio/cedara");
		ANDROID_MIME.put(".m4r", "audio/cedara");
		ANDROID_MIME.put(".omg", "audio/cedara");
		ANDROID_MIME.put(".dts", "audio/cedara");
		ANDROID_MIME.put(".ac3", "audio/cedara");
		ANDROID_MIME.put(".flac", "audio/cedara");
		ANDROID_MIME.put(".fl", "application/android-drm-fl");
		ANDROID_MIME.put(".flv", "video/cedarx");
		ANDROID_MIME.put(".f4v", "video/cedarx");
		ANDROID_MIME.put(".vob", "video/cedarx");
		ANDROID_MIME.put(".pmp", "video/cedarx");
		ANDROID_MIME.put(".3dm", "video/cedarx");
		ANDROID_MIME.put(".3dv", "video/cedarx");
		ANDROID_MIME.put(".3gp", "video/3gpp");
		ANDROID_MIME.put(".3gpp", "video/3gpp");
		ANDROID_MIME.put(".3g2", "video/3gpp2");
		ANDROID_MIME.put(".3gpp2", "video/3gpp2");
		ANDROID_MIME.put(".ape", "audio/cedara");
		ANDROID_MIME.put(".apk", "application/vnd.android.package-archive");
		ANDROID_MIME.put(".asf", "video/ms-asf");
		ANDROID_MIME.put(".aac", "audio/aac");
		ANDROID_MIME.put(".asf", "video/ms-asf");
		ANDROID_MIME.put(".aac", "audio/aac-adts");
		ANDROID_MIME.put(".amr", "audio/amr");
		ANDROID_MIME.put(".awb", "audio/amr-wb");
		ANDROID_MIME.put(".avi", "video/msvideo");
		ANDROID_MIME.put(".bin", "application/octet-stream");
		ANDROID_MIME.put(".bmp", "image/bmp");
		ANDROID_MIME.put(".c", "text/plain");
        ANDROID_MIME.put(".csv", "text/csv" );
		ANDROID_MIME.put(".class", "application/octet-stream");
		ANDROID_MIME.put(".conf", "text/plain");
		ANDROID_MIME.put(".cpp", "text/plain");
		ANDROID_MIME.put(".doc", "application/msword");
		ANDROID_MIME.put(".docx", "application/msword");
		ANDROID_MIME.put(".xls", "application/msexcel");
		ANDROID_MIME.put(".xlsx", "application/msexcel");
		ANDROID_MIME.put(".exe", "application/octet-stream");
        ANDROID_MIME.put(".epub","application/epub" );
        ANDROID_MIME.put(".fb2", "application/fb2" );
		ANDROID_MIME.put(".gif", "image/gif");
		ANDROID_MIME.put(".gtar", "application/gtar");
		ANDROID_MIME.put(".gz", "application/gzip");
		ANDROID_MIME.put(".h", "text/plain");
		ANDROID_MIME.put(".htm", "text/html");
		ANDROID_MIME.put(".html", "text/html");
		ANDROID_MIME.put(".jar", "application/java-archive");
		ANDROID_MIME.put(".java", "text/plain");
		ANDROID_MIME.put(".jpeg", "image/jpeg");
		ANDROID_MIME.put(".png", "image/png");
		ANDROID_MIME.put(".jpg", "image/jpeg");
		ANDROID_MIME.put(".js", "application/javascript");
		ANDROID_MIME.put(".log", "text/plain");
		ANDROID_MIME.put(".m3u", "audio/mpegurl");
		ANDROID_MIME.put(".m4a", "audio/mp4a-latm");
		ANDROID_MIME.put(".m4b", "audio/mp4a-latm");
		ANDROID_MIME.put(".m4p", "audio/mp4a-latm");
		ANDROID_MIME.put(".m4u", "video/vnd.mpegurl");
		ANDROID_MIME.put(".m4v", "video/m4v");
		ANDROID_MIME.put(".mov", "video/quicktime");
		//ANDROID_MIME.put(".mp2", "audio/mpeg");
		ANDROID_MIME.put(".mp3", "audio/mpeg");
		ANDROID_MIME.put(".mp4", "video/mp4");
		ANDROID_MIME.put(".mpc", "application/vnd.mpohun.certificate");
		ANDROID_MIME.put(".mpe", "video/mpeg");
		ANDROID_MIME.put(".mpeg", "video/mpeg");
		ANDROID_MIME.put(".mpg", "video/mpeg");
		ANDROID_MIME.put(".mpg4", "video/mp4");
		ANDROID_MIME.put(".mpga", "audio/mpeg");
		ANDROID_MIME.put(".msg", "application/vnd.ms-outlook");
		ANDROID_MIME.put(".mka", "audio/matroska");
		ANDROID_MIME.put(".mid", "audio/midi");
		ANDROID_MIME.put(".midi", "audio/midi");
		ANDROID_MIME.put(".m3u", "audio/mpegurl");
		ANDROID_MIME.put(".pls", "audio/scpls");
		ANDROID_MIME.put(".m3u8", "audio/mpegurl");
		ANDROID_MIME.put(".xmf", "audio/midi");
		ANDROID_MIME.put(".rtttl", "audio/midi");
		ANDROID_MIME.put(".smf", "audio/sp-midi");
		ANDROID_MIME.put(".imy", "audio/imelody");
		ANDROID_MIME.put(".rtx", "audio/midi");
		ANDROID_MIME.put(".ota", "audio/midi");
		ANDROID_MIME.put(".mxmf", "audio/midi");
		ANDROID_MIME.put(".ogg", "audio/ogg");
		ANDROID_MIME.put(".oga", "application/ogg");
        ANDROID_MIME.put(".ogv", "video/ogg" );   // RFC 5334 
		ANDROID_MIME.put(".pdf", "application/pdf");
		ANDROID_MIME.put(".pps", "application/vnd.ms-powerpoint");
		ANDROID_MIME.put(".ppt", "application/vnd.ms-powerpoint");
		ANDROID_MIME.put(".pptx", "application/vnd.ms-powerpoint");
		ANDROID_MIME.put(".epub", "text/plain");
		ANDROID_MIME.put(".chm", "text/plain");
		ANDROID_MIME.put(".umd", "text/plain");
		ANDROID_MIME.put(".prop", "text/plain");
        ANDROID_MIME.put(".ram", "audio/pn-realaudio" );
		ANDROID_MIME.put(".rar", "application/rar-compressed");
		ANDROID_MIME.put(".rc", "text/plain");
		ANDROID_MIME.put(".rmvb", "video/vnd.rn-realvideo");
		ANDROID_MIME.put(".rm", "video/pn-realvideo");
		ANDROID_MIME.put(".rtf", "application/rtf");
		ANDROID_MIME.put(".sh", "text/plain");
		ANDROID_MIME.put(".tar", "application/tar");
		ANDROID_MIME.put(".tgz", "application/compressed");
		ANDROID_MIME.put(".txt", "text/plain");
		ANDROID_MIME.put(".ts", "video/mp2ts");
		ANDROID_MIME.put(".tp", "video/mp2ts");
		ANDROID_MIME.put(".m2ts", "video/mp2ts");
		ANDROID_MIME.put(".wav", "audio/wav");
		ANDROID_MIME.put(".wma", "audio/ms-wma");
		ANDROID_MIME.put(".webm", "video/webm");
		ANDROID_MIME.put(".wmv", "video/ms-wmv");
		ANDROID_MIME.put(".wbmp", "image/vnd.wap.wbmp");
		ANDROID_MIME.put(".webp", "image/webp");
		ANDROID_MIME.put(".wps", "application/vnd.ms-works");
		ANDROID_MIME.put(".wpl", "application/vnd.ms-wpl");
		ANDROID_MIME.put(".z", "application/compress");
		ANDROID_MIME.put(".zip", "application/zip");
		ANDROID_MIME.put("", "application/octet-stream");
	}

	public static String getMimeFromPath(String path)
	{
		String mime = null;
		if (path.lastIndexOf(".") > -1)
		{
			String end = path.toLowerCase().substring(path.lastIndexOf("."));
			mime = ANDROID_MIME.get(end);
		}
		if (mime == null)
		{
			mime = "application/octet-stream";
		}
		return mime;
	}

}
