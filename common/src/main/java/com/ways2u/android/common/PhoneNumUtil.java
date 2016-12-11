package com.ways2u.android.common;
/**
 * 移动：134、135、136、137、138、139、147、150、151、152、157、158、159、182、183、187、188。
 * 联通：130、131、132、155、156、185、186、145。
 * 电信：133、153、180、189。
 **/
public class PhoneNumUtil {


	public static boolean isGMCCMobile(String mobile) {
		if (mobile == null || mobile.length() != 11) {
			return false;
		}
		if (!(mobile.startsWith("134") || mobile.startsWith("135")
				|| mobile.startsWith("136") || mobile.startsWith("137")
				|| mobile.startsWith("138") || mobile.startsWith("139")
				|| mobile.startsWith("150") || mobile.startsWith("151")
				|| mobile.startsWith("152") || mobile.startsWith("158")
				|| mobile.startsWith("159") || mobile.startsWith("187") || mobile
					.startsWith("188"))) {
			return false;
		}
		return true;
	}

	public static boolean isChinaUnicom(String mobile) {
		if (mobile == null || mobile.length() != 11) {
			return false;
		}
		if (!(mobile.startsWith("130") || mobile.startsWith("131")
				|| mobile.startsWith("132") || mobile.startsWith("155")
				|| mobile.startsWith("156") || mobile.startsWith("185")
				|| mobile.startsWith("186") || mobile.startsWith("145"))) {
			return false;
		}
		return true;
	}

	//

}
