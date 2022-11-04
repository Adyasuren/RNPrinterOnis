package com.xcheng.printerservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import com.print.api.PrinterHelp;
import com.xcheng.printerservice.IPrinterCallback;
import com.xcheng.printerservice.IPrinterService;
import java.util.List;
import java.util.Map;
import com.p001xy.utils.LogUtils;

public class PrinterManager extends com.print.api.PrinterManager {
    public static final String PRINTER_PACKAGE = "com.xcheng.printerservice";
    private static final String TAG = "XCheng.PrinterManager";
    private long currentTotalLength = 0;
    private long endPrintTime = 0;
    private Activity mActivity;
    private IPrinterCallback mCallback = new IPrinterCallback.Stub() {
        public void onException(int code, String msg) throws RemoteException {
            LogUtils.m1i(PrinterManager.TAG, "onException code = " + code + " msg = " + msg);
        }

        public void onLength(long current, long total) throws RemoteException {
            LogUtils.m1i(PrinterManager.TAG, "onLength current = " + current + " total = " + total);
            PrinterManager.this.setCurrentEndPrintTime(current, total);
        }

        public void onComplete() throws RemoteException {
            LogUtils.m1i(PrinterManager.TAG, "onComplete");
        }
    };
    private ServiceConnection mConnectionService = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            PrinterManager.this.mPrinterService = IPrinterService.Stub.asInterface(service);
            PrinterManager.this.mListener.onServiceConnected();
        }

        public void onServiceDisconnected(ComponentName name) {
            PrinterManager.this.mPrinterService = null;
        }
    };
    /* access modifiers changed from: private */
    public PrinterHelp.PrinterManagerListener mListener;
    /* access modifiers changed from: private */
    public IPrinterService mPrinterService;
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
            intent.setAction("com.xcheng.printerservice.IPrinterService");
            this.mActivity.startService(intent);
            this.mActivity.bindService(intent, this.mConnectionService, 1);
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

    public void printTextWithAttributes(String text, Map<String, Integer> attributes) {
        try {
            this.mPrinterService.printTextWithAttributes(text, attributes, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printColumnsTextWithAttributes(String[] text, List<Map<String, Integer>> attributes) {
        try {
            this.mPrinterService.printColumnsTextWithAttributes(text, attributes, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printBarCode(String content, int align, int width, int height, boolean showContent) {
        try {
            this.mPrinterService.printBarCode(content, align, width, height, showContent, this.mCallback);
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

    public void printBitmap(Bitmap bitmap, Map<String, Integer> attributes) {
        try {
            this.mPrinterService.printBitmapWithAttributes(bitmap, attributes, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printWrapPaper(int n) {
        try {
            this.mPrinterService.printWrapPaper(n, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrinterSpeed(int level) {
        try {
            this.mPrinterService.setPrinterSpeed(level, this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upgradePrinter() {
        try {
            this.mPrinterService.upgradePrinter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFirmwareVersion() {
        try {
            return this.mPrinterService.getFirmwareVersion();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getBootloaderVersion() {
        try {
            return this.mPrinterService.getBootloaderVersion();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void printerReset() {
        try {
            this.mPrinterService.printerReset(this.mCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int printerTemperature(Activity activity) {
        try {
            return this.mPrinterService.printerTemperature(this.mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean printerPaper() {
        try {
            return this.mPrinterService.printerPaper(this.mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setStartRecordFlag() {
        this.startRecordTime = true;
    }

    public long getCurrentStartPrintTime() {
        return this.startPrintTime;
    }

    public void setCurrentStartPrintTime() {
        if (this.startRecordTime) {
            this.startPrintTime = System.currentTimeMillis();
        }
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
