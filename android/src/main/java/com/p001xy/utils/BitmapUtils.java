package com.p001xy.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* renamed from: xy.utils.BitmapUtils */
public class BitmapUtils {
    public static int RGB2Gray(int r, int g, int b) {
        return (int) ((0.299d * ((double) r)) + (0.587d * ((double) g)) + (0.114d * ((double) b)));
    }

    public static byte px2Byte(int pixel) {
        if (RGB2Gray((16711680 & pixel) >> 16, (65280 & pixel) >> 8, pixel & 255) < 127) {
            return 0;
        }
        return 1;
    }

    public static Bitmap doResizeBitmap(Bitmap old, int w, int h) {
        int width = old.getWidth();
        int height = old.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) w) / ((float) width), ((float) h) / ((float) height));
        return Bitmap.createBitmap(old, 0, 0, width, height, matrix, true);
    }
}
