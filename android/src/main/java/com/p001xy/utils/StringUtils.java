package com.p001xy.utils;

import java.util.Arrays;

/* renamed from: xy.utils.StringUtils */
public class StringUtils {
    public static final String BLANK_SPACE = " ";
    public static final String LINE_FEED = "\n";
    public static final String TAG = "StringUtils";

    public static void doPrintBytes(byte[] bytes) {
        LogUtils.m0e(TAG, Arrays.toString(bytes));
    }

    public static byte[] doBytesMerge(byte[] byte1, byte[] byte2) {
        byte[] out = new byte[(byte1.length + byte2.length)];
        System.arraycopy(byte1, 0, out, 0, byte1.length);
        System.arraycopy(byte2, 0, out, byte1.length, byte2.length);
        return out;
    }

    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }
        String str2 = str.replaceAll(BLANK_SPACE, "");
        byte[] bytes = new byte[(str2.length() / 2)];
        for (int i = 0; i < str2.length() / 2; i++) {
            bytes[i] = (byte) Integer.parseInt(str2.substring(i * 2, (i * 2) + 2), 16);
        }
        return bytes;
    }

    public static String doRepeatString(String str, int repeat) {
        StringBuffer buf = new StringBuffer(str.length() * repeat);
        for (int i = 0; i < repeat; i++) {
            buf.append(str);
        }
        return buf.toString();
    }

    public static String doAutoLineFeed(String str, int len) {
        String result = "";
        if (!str.contains(BLANK_SPACE)) {
            return String.valueOf(result) + str;
        }
        int size = str.length();
        int i = 0;
        while (i < size - len) {
            String tmp = str.substring(i, i + len);
            if (tmp.contains(BLANK_SPACE)) {
                tmp = String.valueOf(tmp) + LINE_FEED;
            }
            result = String.valueOf(result) + tmp;
            i += len;
        }
        return String.valueOf(result) + str.substring(i, size);
    }
}
