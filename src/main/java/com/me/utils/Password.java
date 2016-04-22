package com.me.utils;

public class Password {
	public static final String __OBFUSCATE = "OBF:";

	public static String deobfuscate(String s) {
		if (s.startsWith(__OBFUSCATE))
			s = s.substring(4);

		byte[] b = new byte[s.length() / 2];
		int l = 0;
		for (int i = 0; i < s.length(); i += 4) {
			String x = s.substring(i, i + 4);
			int i0 = Integer.parseInt(x, 36);
			int i1 = (i0 / 256);
			int i2 = (i0 % 256);
			b[l++] = (byte) ((i1 + i2 - 254) / 2);
		}

		return new String(b, 0, l);
	}
}
