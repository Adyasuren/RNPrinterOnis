package com.woyou.aidlservice.jiuiv5;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import com.print.api.PrinterHelp;
import java.util.List;
import java.util.Map;
import com.p001xy.utils.LogUtils;
import com.woyou.aidlservice.jiuiv5.ICallback;
import com.woyou.aidlservice.jiuiv5.IWoyouService;

public class PrinterManager extends com.print.api.PrinterManager {
    public static final String PRINTER_PACKAGE = "woyou.aidlservice.jiuiv5";
    private static final String TAG = "Woyou.PrinterManager";
    private long currentTotalLength = 0;
    private long endPrintTime = 0;
    private Activity mActivity;
    private ICallback mCallback = new ICallback.Stub() {
        public void onRunResult(boolean isSuccess) throws RemoteException {
            LogUtils.m1i(PrinterManager.TAG, "onRunResult isSuccess = " + isSuccess);
        }

        public void onReturnString(String result) throws RemoteException {
            LogUtils.m1i(PrinterManager.TAG, "onReturnString result = " + result);
        }

        public void onRaiseException(int code, String msg) throws RemoteException {
            LogUtils.m1i(PrinterManager.TAG, "onRaiseException code = " + code + " msg = " + msg);
        }

        public void onPrintResult(int code, String msg) throws RemoteException {
            LogUtils.m1i(PrinterManager.TAG, "onPrintResult code = " + code + " msg = " + msg);
        }
    };
    private ServiceConnection mConnectionService = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            PrinterManager.this.mPrinterService = IWoyouService.Stub.asInterface(service);
            PrinterManager.this.mListener.onServiceConnected();
        }

        public void onServiceDisconnected(ComponentName name) {
            PrinterManager.this.mPrinterService = null;
        }
    };
    /* access modifiers changed from: private */
    public PrinterHelp.PrinterManagerListener mListener;
    /* access modifiers changed from: private */
    public IWoyouService mPrinterService;
    private long startPrintTime = 0;
    private boolean startRecordTime = false;
    private long totalPrintLength = 0;

    public PrinterManager(Activity activity, PrinterHelp.PrinterManagerListener listener) {
        this.mActivity = activity;
        this.mListener = listener;
    }

    public void onPrinterStart() {
        if (this.mPrinterService == null) {
            Intent intent = new Intent();
            intent.setPackage(PRINTER_PACKAGE);
            intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");
            this.mActivity.startService(intent);
            this.mActivity.bindService(intent, this.mConnectionService, 0);
        }
    }

    public void onPrinterStop() {
        try {
            this.mActivity.unbindService(this.mConnectionService);
        } catch (Exception e) {
        } finally {
            this.mActivity.finish();
        }
    }

    public void printerInit() {
        try {
            this.mPrinterService.printerInit(this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRAWData(byte[] data) {
        try {
            this.mPrinterService.sendRAWData(data, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printText(String text) {
        try {
            this.mPrinterService.printText(text, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printBarCode(String content, int symbology, int width, int height, int textposition) {
        try {
            this.mPrinterService.printBarCode(content, symbology, width, height, textposition, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printQRCode(String text, int align, int size) {
        try {
            this.mPrinterService.printerInit(this.mCallback);
            this.mPrinterService.printQRCode(text, align, size, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printBitmap(Bitmap bitmap) {
        try {
            this.mPrinterService.printBitmap(bitmap, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printWrapPaper(int n) {
        try {
            this.mPrinterService.lineWrap(n, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upgradePrinter() {
        try {
            this.mPrinterService.updatePrinterState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrinterAttributes(Map<String, Integer> attributes) {
        try {
            this.mPrinterService.setFontSize((float) attributes.get(com.print.api.PrinterManager.KEY_TEXTSIZE).intValue(), this.mCallback);
            this.mPrinterService.setAlignment(attributes.get(com.print.api.PrinterManager.KEY_ALIGN).intValue(), this.mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printTextWithAttributes(String text, Map<String, Integer> attributes) {
        setPrinterAttributes(attributes);
        try {
            this.mPrinterService.printText(text, this.mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printColumnsTextWithAttributes(String[] text, List<Map<String, Integer>> attributes) {
        setPrinterAttributes(attributes.get(0));
        int[] colsWidthArr = new int[text.length];
        int[] colsAlign = new int[text.length];
        for (int i = 0; i < text.length; i++) {
            colsWidthArr[i] = ((Integer) attributes.get(i).get(com.print.api.PrinterManager.KEY_WEIGHT)).intValue();
            colsAlign[i] = ((Integer) attributes.get(i).get(com.print.api.PrinterManager.KEY_ALIGN)).intValue();
        }
        try {
            this.mPrinterService.printColumnsString(text, colsWidthArr, colsAlign, this.mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printBarCode(String content, int align, int width, int height, boolean showContent) {
        printBarCode(content, 8, width, height, 2);
    }

    public void printBitmap(Bitmap bitmap, Map<String, Integer> attributes) {
        setPrinterAttributes(attributes);
        try {
            this.mPrinterService.printBitmap(bitmap, this.mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setPrinterSpeed(int level) {
    }

    public String getFirmwareVersion() {
        try {
            return this.mPrinterService.getPrinterVersion();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getBootloaderVersion() {
        try {
            return this.mPrinterService.getServiceVersion();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printerReset() {
    }

    public int printerTemperature(Activity activity) {
        return 1;
    }

    public boolean printerPaper() {
        return true;
    }

    public void setStartRecordFlag() {
        this.startRecordTime = true;
    }

    public long getCurrentStartPrintTime() {
        return this.startPrintTime;
    }

    public void setCurrentStartPrintTime() {
        this.startPrintTime = System.currentTimeMillis();
    }

    public long getCurrentEndPrintTime() {
        return this.endPrintTime;
    }

    public void setCurrentEndPrintTime(long current, long total) {
        if (this.startRecordTime) {
            if (current == total) {
                this.currentTotalLength = 0;
            }
            this.endPrintTime = System.currentTimeMillis();
            this.totalPrintLength = total - this.currentTotalLength;
            this.currentTotalLength = total;
            this.startRecordTime = false;
        }
    }

    public long getCurrentTotalLength() {
        return this.totalPrintLength;
    }
}
