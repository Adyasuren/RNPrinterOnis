package com.print.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import com.p001xy.utils.StringUtils;

public class PrinterHelp {
    public static final String PRINTER_CHARSET_DEFAULT = "GB2312";
    public static final int PRINTER_PAPER_WIDTH = 384;
    public static final String PRINTER_TEXTSIZE_DEFAULT = "24";
    public static final String PRINTER_TYPEFACE_DEFAULT = "GH.ttf";

    public interface PrinterManagerListener {
        void onServiceConnected();
    }

    public static Bitmap doCreateBitmap(Context context, String charset) {
        String text = String.valueOf(charset) + ":\n";
        byte[] bytes = new byte[256];
        for (int i = 0; i < 256; i++) {
            bytes[i] = (byte) i;
        }
        try {
            text = String.valueOf(text) + new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Typeface tf = Typeface.createFromAsset(context.getAssets(), PRINTER_TYPEFACE_DEFAULT);
        TextPaint tp = new TextPaint();
        tp.setColor(-16777216);
        tp.setTypeface(tf);
        tp.setTextSize((float) Integer.parseInt(PRINTER_TEXTSIZE_DEFAULT));
        tp.setAntiAlias(true);
        tp.setLetterSpacing(0.0f);
        return doCreateBitmap(text, tp);
    }

    public static Bitmap doCreateBitmap(Typeface tf, int size) {
        TextPaint tp = new TextPaint();
        tp.setColor(-16777216);
        tp.setTextSize((float) size);
        tp.setTypeface(tf);
        tp.setAntiAlias(true);
        tp.setLetterSpacing(0.0f);
        return doCreateBitmap("12345678901234567890123456789012{[(:+;)*,-.]/|\\%!=^?}~'\"_<>`@$#&AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFabcdefghijklmnopqrstuvwxyzfgjpqyZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ\n", tp);
    }

    public static Bitmap doCreateBitmap(String text, TextPaint tp) {
        StaticLayout layout = new StaticLayout(text, tp, PRINTER_PAPER_WIDTH, Layout.Alignment.ALIGN_NORMAL, 1.0f, 4.0f, false);
        Bitmap bitmap = Bitmap.createBitmap(layout.getWidth(), layout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(-1);
        canvas.save();
        layout.draw(canvas);
        canvas.restore();
        return bitmap;
    }

    public static byte[] doPrintFile(String filename) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
        StringBuilder sb = new StringBuilder();
        byte[] bytes = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                String str = br.readLine();
                if (str == null) {
                    return sb.toString().getBytes("CP860");
                }
                if (str.substring(0, 1).equals("X")) {
                    sb.append(new String(StringUtils.hexStringToBytes(str.substring(1)), "CP860"));
                } else {
                    sb.append(str.substring(1));
                }
                sb.append("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return bytes;
        }
    }
}
