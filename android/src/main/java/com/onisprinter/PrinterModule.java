package com.onisprinter;

import com.onisprinter.entity.PairedDevice;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.print.api.PrinterManager;
import com.print.api.PrinterHelp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;

public class PrinterModule extends ReactContextBaseJavaModule implements PrinterAlert, PrinterHelp.PrinterManagerListener {
    private BluetoothSocket btSocket = null;
    private OutputStream btOutputStream = null;
    private PrinterManager mPM = null;
    private BluetoothAdapter mBluetoothAdapter;
    public boolean isPrinterConnect = false;
    PrintBill printBill;
    private static String[] PERMISSIONS = {
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_CONNECT
    };


    PrinterModule(ReactApplicationContext context) {
        super(context);
        if (mBluetoothAdapter == null) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }

        printBill = new PrintBill(this);
    }

    @ReactMethod
    public void isConnectPrinter(Callback cb) {
        cb.invoke(null, isPrinterConnect);
    }

    @ReactMethod
    public void initPrinter(Callback cb) {
        try {
            this.mPM = PrinterManager.getPrinterManager(getCurrentActivity(), this);
            if (this.mPM != null) {
                this.mPM.onPrinterStart();
                cb.invoke(null, true);
            } else {
                cb.invoke(null, false);
            }
        } catch (Exception ex) {
            cb.invoke(ex.getMessage(), false);
        }
    }

    @ReactMethod
    public void printText(String text, Callback cb) {
        try {
            printBill.printText(text);
        } catch (Exception ex) {
            cb.invoke(ex.getMessage(), false);
        }
    }

    @ReactMethod
    public void getPairedDevice(final Promise cb) {
        if(checkPermission()) {
            WritableArray deviceList = Arguments.createArray();
            if (mBluetoothAdapter != null) {
                Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
                for (BluetoothDevice rawDevice : bondedDevices) {
                    WritableMap device = deviceToWritableMap(rawDevice);
                    deviceList.pushMap(device);
                }
            }
            cb.resolve(deviceList);
        } else {
            cb.reject("false");
        }
    }

    @ReactMethod
    public void connectBtPrinter(String address, final Promise cb) {
        if(checkPermission()) {
            if(address != "") {
                if (mBluetoothAdapter != null) {
                    BluetoothDevice device = getDevice(address);

                    if(device != null) {
                        printBill.print(device);
                        cb.resolve("success");
                        return;
                    } else {
                        cb.resolve("No printer");
                        return;
                    }
                }
            } else {
                cb.resolve("No address");
                return;
            }
        }
    }

    private BluetoothDevice getDevice(String address) {
        if (mBluetoothAdapter != null) {
            return mBluetoothAdapter.getRemoteDevice(address);
        }
        return null;
    }

    private boolean checkPermission() {
        return true;
        /*if (ContextCompat.checkSelfPermission(this.getCurrentActivity().getApplicationContext(),
                        PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getCurrentActivity().getApplicationContext(),
                        PERMISSIONS[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getCurrentActivity().getApplicationContext(),
                        PERMISSIONS[2]) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }*/
    }

    void permissionRequest() {
        ActivityCompat.requestPermissions(
                getCurrentActivity(),
                PERMISSIONS,
                1
        );
    }


    /* access modifiers changed from: protected */
   /* public void onDestroy() {
        super.
        if (this.mPM != null) {
            this.mPM.onPrinterStop();
        }
    }*/

    public void onServiceConnected() {
        if (this.mPM != null) {
            this.mPM.printerInit();
            printBill.setPrinterManager(mPM);
        }
    }

    @Override
    public String getName() {
        return "PrinterModule";
    }

    private WritableMap deviceToWritableMap(BluetoothDevice device) {
        WritableMap params = Arguments.createMap();
        params.putString("name", device.getName());
        params.putString("address", device.getAddress());
        params.putString("id", device.getAddress());
        if (device.getBluetoothClass() != null) {
            params.putInt("class", device.getBluetoothClass().getDeviceClass());
        }

        return params;
    }

    @Override
    public void ConnectionFailed() {
        Log.e("adya", "error connection");
    }

    @Override
    public void ConnectionLost() {
        Log.e("adya", "error connection");
    }

    @Override
    public void ConnectionCheck() {
        Log.e("adya", "connection check");
    }

    @Override
    public void ConnectionSuccess() {
        Log.e("adya", "connection success");
    }

    @Override
    public void FindDevice() {
        Log.e("adya", "find device");
    }

    @Override
    public void startConnect() {
        Log.e("adya", "start connection");
    }

    @Override
    public void finishConnect() {
        Log.e("adya", "finish connection");
    }

    @Override
    public void bluetoothPairing(BluetoothDevice selectedDevice) {

    }
}
