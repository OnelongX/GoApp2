package com.ways2u.android.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.annotation.SuppressLint;

/**
 * 日期工具�?
 * 
 * @author Administrator
 * 
 */
public class DateUtil {
	private static Calendar calendar;
	private static final Locale locale = Locale.getDefault();
	private static final String[] weekends = new String[] { "星期日", "星期一",
			"星期二", "星期三", "星期四", "星期五", "星期六", };

	public static Calendar getCalendarInstance() {
		if (calendar == null) {
			calendar = Calendar.getInstance(locale);
		}
		return calendar;
	}

	/**
	 * 返回日期字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            要显示的日期格式
	 * @return 解析后的字符串格式
	 */
	public static String getStringWithDate(Date date, String format) {
		if (date == null) {
			date = getCurrentDate();
		}
		if (StrUtil.nullToStr(format).equals("")) {
			format = "yyyy-MM-dd";
		}
		return new SimpleDateFormat(format, locale).format(date);
	}

	public static String getWeekendName() {
		return weekends[getWeekend()];
	}

	/**
	 * 返回星期几
	 * 
	 * @return
	 */
	public static int getWeekend() {
		return getCalendarInstance().get(Calendar.DAY_OF_WEEK);
	}

	public static String getStringWithDate(Date date) {
		if (date == null) {
			date = getCurrentDate();
		}
		return new SimpleDateFormat("yyyy-MM-dd", locale).format(date);
	}

	public static String getStringWithDateAndTime(Date date) {
		if (date == null) {
			date = getCurrentDate();
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).format(date);
	}

	/**
	 * 通过字符串解析还原日期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDateWithString(String dateStr) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd", locale).parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCurrentDate();
	}

	/**
	 * 通过字符串解析还原日期
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param format
	 *            格式
	 * @return
	 */
	public static Date parseDateWithString(String dateStr, String format) {
		format = StrUtil.nullToStr(format).equals("") ? "yyyy-MM-dd" : format;
		try {
			return new SimpleDateFormat(format, locale).parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCurrentDate();
	}

	/**
	 * 计算两个日期之间的天数
	 * 
	 * @param early
	 * @param late
	 * @return
	 */
	public static final int daysBetween(String early, String late) {
		Date earlydate = new Date();
		Date latedate = new Date();
		DateFormat df = DateFormat.getDateInstance();
		try {
			earlydate = df.parse(early);
			latedate = df.parse(late);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		java.util.Calendar calst = java.util.Calendar.getInstance();
		java.util.Calendar caled = java.util.Calendar.getInstance();
		calst.setTime(earlydate);
		caled.setTime(latedate);
		// 设置时间为0时
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calst.set(java.util.Calendar.MINUTE, 0);
		calst.set(java.util.Calendar.SECOND, 0);
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
		caled.set(java.util.Calendar.MINUTE, 0);
		caled.set(java.util.Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}

	/**
	 * 获取日历对象
	 * 
	 * @return
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 获取当前字符串格式
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDateString(String format) {
		return getStringWithDate(getCurrentDate(), format);
	}

	/**
	 * 获取当前字符串格式
	 * 
	 * @return
	 */
	public static String getCurrentDateString() {
		return getStringWithDate(getCurrentDate());
	}

	/***
	 * 获取日期时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateStrYmdHmsByDate(Date date) {
		date = (date == null ? getCurrentDate() : date);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).format(date);
	}

	/**
	 * 获取日期时间字符串
	 * 
	 * @return
	 */
	public static String getDateStrYmdHms() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)
				.format(getCurrentDate());
	}
	
	public static String getDateStringYmdHms() {
		return new SimpleDateFormat("yyyyMMddHHmmss", locale)
				.format(getCurrentDate());
	}

	/**
	 * 获取日期时间字符串
	 * 
	 * @return
	 */
	public static String getDateStrYmdHm() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm", locale)
				.format(getCurrentDate());
	}

	public static String getDateStrYmd() {
		return new SimpleDateFormat("yyyy-MM-dd", locale)
				.format(getCurrentDate());
	}

	/**
	 * 获取明天
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getStrTomorrow() {
		Date date = new Date(getCurrentDate().getTime() + (24 * 60 * 60 * 1000));
		return getStringWithDate(
				new Date(date.getYear(), date.getMonth(), date.getDate()),
				"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取昨天
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getStrYesterday() {
		Date date = new Date(getCurrentDate().getTime() - (24 * 60 * 60 * 1000));
		return getStringWithDate(
				new Date(date.getYear(), date.getMonth(), date.getDate()),
				"yyyy-MM-dd HH:mm:ss");
	}

	@SuppressLint("SimpleDateFormat")
	public static Date getDateByString(String time) {
		Date date = null;
		if (time == null)
			return date;
		String date_format = "yyyy-MM-dd HH:mm";
		SimpleDateFormat format = new SimpleDateFormat(date_format);
		try {
			date = format.parse(time);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getShortTime(String time) {
		return getShortTime_message(time);
	}

	public static String getShortTime_message(String time) {
		String shortstring = null;
		long now = Calendar.getInstance().getTimeInMillis();
		Date date = getDateByString(time);
		if (date == null)
			return shortstring;
		long deltime = (now - date.getTime()) / 1000;
		if (deltime > 30 * 24 * 60 * 60) {
			shortstring = time;
		} else if (deltime > 7 * 24 * 60 * 60 && deltime < 30 * 24 * 60 * 60) {
			shortstring = (int) (deltime / (24 * 60 * 60)) + "天前";
		} else if (deltime > 1 * 24 * 60 * 60 && deltime < 7 * 24 * 60 * 60) {
			shortstring = "周" + getWeekDay(date) + " " + dateToStrLong(date);
		} else if (deltime > 60 * 60 && deltime < 1 * 24 * 60 * 60) {
			shortstring = (int) (deltime / (60 * 60)) + "小时前" + " "
					+ dateToStrLong(date);
		} else if (deltime > 60) {
			shortstring = (int) (deltime / (60)) + "分前";
		} else if (deltime > 1) {
			shortstring = "刚刚";
		} else {
			shortstring = "刚刚";
		}
		return shortstring;
	}

	public static int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_of_year = c.get(Calendar.DAY_OF_WEEK);
		return week_of_year - 1;
	}

	@SuppressLint("SimpleDateFormat")
	public static String dateToStrLong(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	@SuppressLint("SimpleDateFormat")
	public static String twoDateDistance(Date startDate, Date endDate) {

		if (startDate == null || endDate == null) {
			return null;
		}
		long timeLong = endDate.getTime() - startDate.getTime();
		if (timeLong < 60 * 1000)
			return timeLong / 1000 + "秒前";
		else if (timeLong < 60 * 60 * 1000) {
			timeLong = timeLong / 1000 / 60;
			return timeLong + "分钟前";
		} else if (timeLong < 60 * 60 * 24 * 1000) {
			timeLong = timeLong / 60 / 60 / 1000;
			return timeLong + "小时前";
		} else if (timeLong < 60 * 60 * 24 * 1000 * 7) {
			timeLong = timeLong / 1000 / 60 / 60 / 24;
			return timeLong + "天前";
		} else if (timeLong < 60 * 60 * 24 * 1000 * 7 * 4) {
			timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
			return timeLong + "周前";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
			return sdf.format(startDate);
		}
	}


	public static String distanceTodayOrYesterDay(Date d1, Date d2){
		Calendar c1 = getCalendar();
		Calendar c2 = getCalendar();
		c1.setTime(d1);
		c2.setTime(d2);
		int startYear = c1.get(Calendar.YEAR);
		int startMonth = c1.get(Calendar.MONTH)+1;
		int startDate = c1.get(Calendar.DATE);
		int endYear = c2.get(Calendar.YEAR);
		int endMonth = c2.get(Calendar.MONTH)+1;
		int endDate = c2.get(Calendar.DATE);
		if(startYear < endYear){
			return "昨天";
		}else {
			if(startMonth < endMonth){
				return "昨天";
			}else{
				if(startDate < endDate){
					return "昨天";
				}
			}
		}
		return "今天";
	}
	@SuppressLint("SimpleDateFormat")
	public static String twoDateDistance2(Date startDate, Date endDate) {

		if (startDate == null || endDate == null) {
			return null;
		}
		long timeLong = endDate.getTime() - startDate.getTime();
		if (timeLong < 60 * 1000) {
			return StrUtil.appendString(distanceTodayOrYesterDay(startDate,endDate),getPeriodOfTime(startDate)," ","刚刚");
		} else if (timeLong < 60 * 60 * 1000) {
			timeLong = timeLong / 1000 / 60;
			return StrUtil.appendString(distanceTodayOrYesterDay(startDate,endDate),getPeriodOfTime(startDate)," ",getHourMinuteWithDate(startDate));
		} else if (timeLong < 60 * 60 * 24 * 1000) {
			timeLong = timeLong / 60 / 60 / 1000;
			return StrUtil.appendString(distanceTodayOrYesterDay(startDate,endDate),getPeriodOfTime(startDate)," ",getHourMinuteWithDate(startDate));
		} else if (timeLong < 60 * 60 * 24 * 1000 * 7) {
			timeLong = timeLong / 1000 / 60 / 60 / 24;
			switch((int)timeLong){
			case 1:
				return StrUtil.appendString("昨天",getPeriodOfTime(startDate)," ",getHourMinuteWithDate(startDate));
			case 2:
				return StrUtil.appendString("前天",getPeriodOfTime(startDate)," ",getHourMinuteWithDate(startDate));
			default:
				return timeLong + "天前";
			}
		} else if (timeLong < 60 * 60 * 24 * 1000 * 7 * 4) {
			timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
			return timeLong + "周前";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
			return sdf.format(startDate);
		}
	}
	/**
	 * 时间轴，只显示今天，昨天，或者日日期
	 * */
	@SuppressLint("SimpleDateFormat")
	public static String twoDateDistance3(Date startDate, Date endDate) {

		if (startDate == null || endDate == null) {
			return null;
		}
		long timeLong = endDate.getTime() - startDate.getTime();
		if (timeLong < 60 * 1000) {
			return StrUtil.appendString(distanceTodayOrYesterDay(startDate,endDate));
		} else if (timeLong < 60 * 60 * 1000) {
			timeLong = timeLong / 1000 / 60;
			return StrUtil.appendString(distanceTodayOrYesterDay(startDate,endDate));
		} else if (timeLong < 60 * 60 * 24 * 1000) {
			timeLong = timeLong / 60 / 60 / 1000;
			return StrUtil.appendString(distanceTodayOrYesterDay(startDate,endDate));
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
			return sdf.format(startDate);
		}
	}

	public static String getPeriodOfTime(Date date) {
		if (date != null) {
			Calendar c = getCalendar();
			c.setTime(date);
			int hour = c.get(Calendar.HOUR_OF_DAY);
			if (hour < 6 ) {
				return "凌晨";
			}else if (hour >= 6 && hour < 8) {
				return "早上";
			} else if (hour >= 8 && hour < 11) {
				return "上午";
			} else if (hour >= 11 && hour < 13) {
				return "中午";
			} else if (hour >= 13 && hour < 18) {
				return "下午";
			} else {
				return "晚上";
			}
		}
		return "";
	}
	public static String getHourMinuteWithDate(Date date) {
		if (date != null) {
			Calendar c = getCalendar();
			c.setTime(date);
			return  StrUtil.appendString(
					c.get(Calendar.HOUR_OF_DAY)<10?"0"+c.get(Calendar.HOUR_OF_DAY):c.get(Calendar.HOUR_OF_DAY),
					":",c.get(Calendar.MINUTE)<10?"0"+c.get(Calendar.MINUTE):c.get(Calendar.MINUTE));
		}
		return "";
	}
	
	/**
	 * 算出两个点的差数 差数由minutes决定
	 * @param startDate
	 * @param endDate
	 * @param minute
	 * @return
	 */
	public static boolean isTwoDateDistanceForTime(Date startDate, Date endDate,int minute) {

		if (startDate == null || endDate == null) {
			return false;
		}
		long timeLong = endDate.getTime() - startDate.getTime();
		if (timeLong >= (minute * 60 * 1000)) {
			return true;
		} 
		return false;
	}
}
