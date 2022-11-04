package com.print.api;

import com.p001xy.utils.StringUtils;

public class PrinterData {
    public static final int DATA_TYPE_BlockBlack = 2;
    public static final int DATA_TYPE_Horizontal = 1;
    public static final int DATA_TYPE_ObliqueLine = 3;
    public static final int DATA_TYPE_Veritical = 0;
    private static final String HEXBLOCKBLACK = "ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff";
    private static final String HEXHORIZONTAL = "ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ";
    private static final String HEXOBLIQUELINE = "f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f000 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f 00 00 00 0f";
    private static final String HEXVERITICAL = "f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00 f0 00 00 00";

    public static byte[] TEST_STRESS_DATA(int type) {
        String hexHeadString;
        String hexBodyString;
        String hexString = HEXVERITICAL;
        switch (type) {
            case 0:
                hexString = HEXVERITICAL;
                break;
            case 1:
                hexString = HEXHORIZONTAL;
                break;
            case DATA_TYPE_BlockBlack /*2*/:
                hexString = HEXBLOCKBLACK;
                break;
            case DATA_TYPE_ObliqueLine /*3*/:
                hexString = HEXOBLIQUELINE;
                break;
        }
        if (type == -1) {
            hexHeadString = "1d 76 30 03 30 00 00 20";
            hexBodyString = StringUtils.doRepeatString(hexString, 1024);
        } else {
            hexHeadString = "1d 76 30 03 30 00 00 08";
            hexBodyString = StringUtils.doRepeatString(hexString, 256);
        }
        return StringUtils.hexStringToBytes(hexHeadString.concat(hexBodyString));
    }

    public static byte[] TEST_BLOCK_BLACK(int h, int w) {
        int hh = h;
        int ww = ((w - 1) / 8) + 1;
        byte[] data = new byte[((hh * ww) + 10)];
        data[0] = ESCHelper.f6GS;
        data[1] = 118;
        data[2] = 48;
        data[3] = 0;
        data[4] = (byte) ww;
        data[5] = (byte) (ww >> 8);
        data[6] = (byte) h;
        data[7] = (byte) (h >> 8);
        int k = 8;
        int i = 0;
        while (i < hh) {
            int j = 0;
            int k2 = k;
            while (j < ww) {
                data[k2] = -1;
                j++;
                k2++;
            }
            i++;
            k = k2;
        }
        data[k] = 0;
        data[k + 1] = 0;
        return data;
    }

    public static byte[] TEST_GERTEC_BARCODE_UPCE() {
        return new byte[]{ESCHelper.f6GS, 104, 85, ESCHelper.f6GS, 72, 1, ESCHelper.f6GS, 107, 66, 8, 48, 50, 51, 52, 53, 54, 55, 51, 10, 10};
    }

    public static byte[] TEST_GERTEC_BARCODE_CODE93() {
        return new byte[]{ESCHelper.f6GS, 104, 85, ESCHelper.f6GS, 72, 1, ESCHelper.f6GS, 107, 72, 1, 48, 10, ESCHelper.f6GS, 107, 72, 2, 97, 98, 10, ESCHelper.f6GS, 107, 72, 3, 115, 116, 117, 10, ESCHelper.f6GS, 107, 72, 6, 122, 123, 124, 125, 126, Byte.MAX_VALUE, 10, ESCHelper.f6GS, 107, 72, 7, 33, 34, 40, 42, 42, ESCHelper.f9SP, 41, 10, ESCHelper.f6GS, 107, 72, 8, 85, 86, 87, 88, 89, 96, 97, 98, 10, 10};
    }

    public static byte[] TEST_GERTEC_BARCODE_ITF() {
        return new byte[]{ESCHelper.f6GS, 104, 85, ESCHelper.f6GS, 72, 1, ESCHelper.f6GS, 107, 70, 8, 48, 50, 51, 52, 53, 54, 55, 56, 10, ESCHelper.f6GS, 107, 70, 10, 48, 50, 51, 52, 53, 54, 55, 56, 57, 48, 10, ESCHelper.f6GS, 107, 70, ESCHelper.f4FF, 48, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 10, 10};
    }

    public static byte[] TEST_GERTEC_BARCODE_CODABAR() {
        return new byte[]{ESCHelper.f6GS, 104, 85, ESCHelper.f6GS, 72, 1, ESCHelper.f6GS, 107, 71, 7, 98, 50, 51, 52, 53, 54, 98, 10, ESCHelper.f6GS, 107, 71, 8, 99, 50, 51, 52, 53, 54, 55, 65, 10, ESCHelper.f6GS, 107, 71, 9, 100, 50, 51, 52, 53, 54, 55, 56, 100, 10, 10};
    }

    public static byte[] TEST_GERTEC_BARCODE_CODE128() {
        return new byte[]{ESCHelper.f6GS, 104, 85, ESCHelper.f6GS, 72, 1, ESCHelper.f6GS, 107, 73, 4, 123, 66, 33, 34, 10, ESCHelper.f6GS, 107, 73, 5, 123, 65, 35, ESCHelper.f9SP, 36, 10, ESCHelper.f6GS, 107, 73, 6, 123, 66, 37, 38, 39, 40, 10, ESCHelper.f6GS, 107, 73, 7, 123, 67, 48, 69, 89, 86, 42, 10, 10};
    }

    public static byte[] TEST_GERTEC_BARCODE_CODE39() {
        return new byte[]{ESCHelper.f6GS, 104, 85, ESCHelper.f6GS, 72, 1, ESCHelper.f6GS, 107, 69, 4, 48, 50, 51, 52, 10, ESCHelper.f6GS, 107, 69, 5, 48, 50, 51, 52, 53, 10, ESCHelper.f6GS, 107, 69, 6, 48, 50, 51, 52, 53, 54, 10, ESCHelper.f6GS, 107, 69, 7, 48, 50, 51, 52, 53, 54, 55, 10, ESCHelper.f6GS, 107, 69, 8, 48, 50, 51, 52, 53, 54, 55, 56, 10, 10};
    }
}
