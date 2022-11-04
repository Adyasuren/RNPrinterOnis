package com.print.api;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import com.print.api.PrinterHelp;
import java.util.List;
import java.util.Map;
import com.p001xy.utils.DeviceUtils;

public abstract class PrinterManager {
    public static final int DEF_DEFAULT_ALIGN = 1;
    public static final int DEF_DEFAULT_LINESPACE = 4;
    public static final int DEF_DEFAULT_MARGINBOTOM = 0;
    public static final int DEF_DEFAULT_MARGINLEFT = 0;
    public static final int DEF_DEFAULT_MARGINRIGHT = 0;
    public static final int DEF_DEFAULT_MARGINTOP = 0;
    public static final int DEF_DEFAULT_TEXTSIZE = 24;
    public static final int DEF_DEFAULT_TYPEFACE = 0;
    public static final int DEF_DEFAULT_WEIGHT = 1;
    public static final String KEY_ALIGN = "key_attributes_align";
    public static final String KEY_LINESPACE = "key_attributes_linespace";
    public static final String KEY_MARGINBOTTOM = "key_attributes_marginbottom";
    public static final String KEY_MARGINLEFT = "key_attributes_marginleft";
    public static final String KEY_MARGINRIGHT = "key_attributes_marginright";
    public static final String KEY_MARGINTOP = "key_attributes_margintop";
    public static final String KEY_TEXTSIZE = "key_attributes_textsize";
    public static final String KEY_TYPEFACE = "key_attributes_typeface";
    public static final String KEY_WEIGHT = "key_attributes_weight";
    private static PrinterManager mPrinterManager;

    public abstract String getBootloaderVersion();

    public abstract long getCurrentEndPrintTime();

    public abstract long getCurrentStartPrintTime();

    public abstract long getCurrentTotalLength();

    public abstract String getFirmwareVersion();

    public abstract void onPrinterStart();

    public abstract void onPrinterStop();

    public abstract void printBarCode(String str, int i, int i2, int i3, boolean z);

    public abstract void printBitmap(Bitmap bitmap);

    public abstract void printBitmap(Bitmap bitmap, Map<String, Integer> map);

    public abstract void printColumnsTextWithAttributes(String[] strArr, List<Map<String, Integer>> list);

    public abstract void printQRCode(String str, int i, int i2);

    public abstract void printText(String str);

    public abstract void printTextWithAttributes(String str, Map<String, Integer> map);

    public abstract void printWrapPaper(int i);

    public abstract void printerInit();

    public abstract boolean printerPaper();

    public abstract void printerReset();

    public abstract int printerTemperature(Activity activity);

    public abstract void sendRAWData(byte[] bArr);

    public abstract void setCurrentEndPrintTime(long j, long j2);

    public abstract void setCurrentStartPrintTime();

    public abstract void setPrinterSpeed(int i);

    public abstract void setStartRecordFlag();

    public abstract void upgradePrinter();

    public static PrinterManager getPrinterManager(Activity activity, PrinterHelp.PrinterManagerListener listener) {
        if (DeviceUtils.doCheckPackageInstalled(activity, com.prints.printerservice.PrinterManager.PRINTER_PACKAGE)) {
            mPrinterManager = new com.prints.printerservice.PrinterManager(activity, listener);
        }
        if (DeviceUtils.doCheckPackageInstalled(activity, com.xcheng.printerservice.PrinterManager.PRINTER_PACKAGE)) {
            mPrinterManager = new com.xcheng.printerservice.PrinterManager(activity, listener);
        }
        if (DeviceUtils.doCheckPackageInstalled(activity, com.woyou.aidlservice.jiuiv5.PrinterManager.PRINTER_PACKAGE)) {
            mPrinterManager = new com.woyou.aidlservice.jiuiv5.PrinterManager(activity, listener);
        }
        return mPrinterManager;
    }

    public void doPrinterCutPaper(Context context) {
        //if (SettingsActivity.doCheckIsCutPaperSupport(context)) {
        //    sendRAWData(ESCHelper.FeedPaperCutPartial());
        //}
    }
}
