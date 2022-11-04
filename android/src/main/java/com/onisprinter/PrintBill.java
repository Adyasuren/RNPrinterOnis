package com.onisprinter;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.os.Handler;

import com.facebook.react.bridge.Promise;
import com.print.api.PrinterManager;
import java.util.ArrayList;

public class PrintBill {
    private BluetoothPrinter bluetoothPrinter;
    private PrinterManager posPrinterManager = null;
    PrinterAlert printerAlert;

    boolean isReprint;

    public PrintBill(PrinterAlert printerAlert) {
        this.printerAlert = printerAlert;
    }

    public PrintBill(PrinterAlert printerAlert, boolean isReprint) {
        this.printerAlert = printerAlert;
        this.isReprint = isReprint;
    }

    public void setPrinterManager(PrinterManager pManager) {
        this.posPrinterManager = pManager;
    }

    private void connect(BluetoothDevice macDevice, Promise promise) {
        printerAlert.startConnect();
        if (isBluetoothEnabled()) {
            ConnectPrinter(macDevice, promise);
        } else {
            this.printerAlert.ConnectionCheck();
            promise.resolve(false);
        }
    }

    private void ConnectPrinter(final BluetoothDevice dev, Promise promise)
    {
        try
        {
            if(bluetoothPrinter != null)
            {
                bluetoothPrinter.finish();
                bluetoothPrinter = null;
            }
            BluetoothPrinter.connectionState = 0;
            bluetoothPrinter = new BluetoothPrinter(dev, this, posPrinterManager);
            if (getConnectionState() == BluetoothPrinter.CONNECTING || BluetoothPrinter.connectionState != BluetoothPrinter.CONNECTED) {
                bluetoothPrinter.connectPrinter(new BluetoothPrinter.PrinterConnectListener() {
                    @Override
                    public void onConnected() {
                        printerAlert.finishConnect();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // print request
                            }
                        }, 500);
                        printerAlert.ConnectionSuccess();
                      promise.resolve(true);
                    }

                    @Override
                    public void onFailed() {
                      promise.resolve(false);
                        // failed
                    }
                });
            } else {
                BluetoothPrinter.connectionState = 0;
                ConnectPrinter(dev, promise);
            }
        }
        catch (Exception ex)
        {
            connectionFailed();
            promise.resolve(false);
        }
    }

    public void printText(String text) {
        if(text != "") {
            bluetoothPrinter.printUnicode(text);
        }
    }

    public void printNewLine() {
        bluetoothPrinter.addNewLine();
    }

    public void setBold(boolean isBold) {
        bluetoothPrinter.setBold(isBold);
    }

    public void setAlign(int align) {
        bluetoothPrinter.setAlign(align);
    }

    public void setSmall(boolean isSmall) {
        bluetoothPrinter.setSmall(isSmall);
    }

    public void printLine() {
        bluetoothPrinter.printLine2();
    }

    public void setLineSpacing(int size) {
        bluetoothPrinter.setLineSpacing(size);
    }

    public void feedPaper() {
    bluetoothPrinter.feedPaper();
  }

    public void initPrinter() {
      bluetoothPrinter.printText(BluetoothPrinter.InitializePrinter);
    }

    public void print(BluetoothDevice macDevice, Promise promise) {
        if(this.posPrinterManager != null) {
            BluetoothPrinter.connectionState = 0;
            bluetoothPrinter = new BluetoothPrinter(null, this, posPrinterManager);
            promise.resolve(true);
            // send request

        } else if (isBluetoothEnabled()) {
            connect(macDevice, promise);
        } else {
            this.printerAlert.ConnectionCheck();
        }
    }



    private boolean isPos() {
        if(this.posPrinterManager != null) return true;
        return false;
    }



    private void printBitmap(Bitmap bitmap) {
        if (bluetoothPrinter == null)
            return;

        bluetoothPrinter.printText(BluetoothPrinter.InitializePrinter);
        bluetoothPrinter.printImage(bitmap);
    }


    public void connectionLost() {
        printerAlert.ConnectionLost();
    }

    private void connectionFailed() {
        printerAlert.finishConnect();
        printerAlert.ConnectionFailed();
    }

    public boolean isConnected() {
        return bluetoothPrinter != null && bluetoothPrinter.isConnected();
    }

    private boolean isBluetoothEnabled() {
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            return BluetoothAdapter.getDefaultAdapter().isEnabled();
        }
        return false;
    }

    private int getConnectionState() {
        return BluetoothPrinter.connectionState;
    }
}
