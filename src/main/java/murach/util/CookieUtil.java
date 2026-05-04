package murach.util;

import javax.servlet.http.*;

public class CookieUtil {
	
	/**
	 * sample:
	 * 	Cookie[] cookies = request.getCookies();
	 * 	String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
	 * 
	 * @param cookies
	 * @param cookiesName
	 * @return cookieValue
	 */
	public static String getCookieValue(
			Cookie[] cookies, String cookiesName) {
		
		String cookieValue = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookiesName)) {
					cookieValue = cookie.getValue();
				}
			}
		}
		
		return cookieValue;
	}
}