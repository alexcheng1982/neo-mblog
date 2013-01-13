package com.chengfu.neomblog.util;

import java.security.MessageDigest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	public String getCurrentUsername() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		return authentication.getName();
	}

	public String generateProfileImageUrl(String email) {
		if (email == null || email.trim().length() == 0) {
			return "http://www.gravatar.com/avatar/00000000000000000000000000000000?d=identicon";
		}
		String hash = MD5Util.md5Hex(email);
		return "http://www.gravatar.com/avatar/" + hash + "?d=identicon";
	}

	private static class MD5Util {
		public static String hex(byte[] array) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		}

		public static String md5Hex(String message) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				return hex(md.digest(message.getBytes("CP1252")));
			} catch (Exception e) {
			}
			return null;
		}
	}

}
