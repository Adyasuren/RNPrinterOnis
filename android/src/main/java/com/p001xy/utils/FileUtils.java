package com.p001xy.utils;

import android.content.Context;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: xy.utils.FileUtils */
public class FileUtils {
    public static void doWriteByteToFile(byte[] data, String path, boolean append) {
        File file = new File(path);
        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file, append);
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doWriteStringToFile(String toWrite, String path, boolean append) {
        try {
            FileWriter fileWriter = new FileWriter(path, append);
            fileWriter.flush();
            fileWriter.write(toWrite);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] doReadByteFromFile(String path) {
        File file = new File(path);
        try {
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = new byte[((int) file.length())];
            in.read(bytes);
            in.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void doSaveBitmap(Bitmap bitmap, String path) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        doWriteByteToFile(baos.toByteArray(), path, false);
    }

    public static byte[] doReadAssertFile(Context context, String fileName) {
        byte[] buf = new byte[1024];
        byte[] byteData = null;
        try {
            ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
            InputStream is = context.getAssets().open(fileName);
            while (true) {
                int ch = is.read(buf);
                if (ch == -1) {
                    byte[] byteData2 = byteArrayOS.toByteArray();
                    byteArrayOS.close();
                    return byteData2;
                }
                byteArrayOS.write(buf, 0, ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return byteData;
        }
    }
}
