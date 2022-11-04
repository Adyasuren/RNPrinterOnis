package com.onisprinter;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.print.api.PrinterHelp;
import com.print.api.PrinterManager;

import java.io.OutputStream;
import java.util.Set;

@ReactModule(name = OnisPrinterModule.NAME)
public class OnisPrinterModule extends ReactContextBaseJavaModule implements PrinterAlert, PrinterHelp.PrinterManagerListener {
    public static final String NAME = "OnisPrinter";
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


    public OnisPrinterModule(ReactApplicationContext reactContext) {
        super(reactContext);
      if (mBluetoothAdapter == null) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      }

      printBill = new PrintBill(this);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

  @ReactMethod
  public void isConnectPrinter(Promise promise) {
    promise.resolve(isPrinterConnect);
  }


  @ReactMethod
  public void initPrinter(Promise promise) {
    try {
      this.mPM = PrinterManager.getPrinterManager(getCurrentActivity(), this);
      if (this.mPM != null) {
        this.mPM.onPrinterStart();
        promise.resolve(true);
      } else {
        promise.resolve(false);
      }
    } catch (Exception ex) {
      promise.resolve( false);
    }
  }

  @ReactMethod
  public void printText(String text) {
    try {
      printBill.printText(text);
    } catch (Exception ex) {
    }
  }

  @ReactMethod
  public void initBtPrinter() {
    try {
      printBill.initPrinter();
    } catch (Exception ex) {
    }
  }

  @ReactMethod
  public void printNewLine() {
    try {
      printBill.printNewLine();
    } catch (Exception ex) {
    }
  }

  @ReactMethod
  public void setBold(boolean isBold) {
    try {
      printBill.setBold(isBold);
    } catch (Exception ex) {
    }
  }

  @ReactMethod
  public void setAlign(int align) {
    try {
      printBill.setAlign(align);
    } catch (Exception ex) {
    }
  }

  @ReactMethod
  public void setSmall(boolean isSmall) {
    try {
      printBill.setSmall(isSmall);
    } catch (Exception ex) {
    }
  }

  @ReactMethod
  public void printLine() {
    try {
      printBill.printLine();
    } catch (Exception ex) {
    }
  }

  @ReactMethod
  public void setLineSpacing(int size) {
    try {
      printBill.setLineSpacing(size);
    } catch (Exception ex) {
    }
  }

  @ReactMethod
  public void feedPaper() {
    try {
      printBill.feedPaper();
    } catch (Exception ex) {
    }
  }

  @ReactMethod
  public void getPairedDevice(final Promise promise) {
    if(checkPermission()) {
      WritableArray deviceList = Arguments.createArray();
      if (mBluetoothAdapter != null) {
        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice rawDevice : bondedDevices) {
          WritableMap device = deviceToWritableMap(rawDevice);
          deviceList.pushMap(device);
        }
      }
      promise.resolve(deviceList);
    } else {
      promise.resolve("false");
    }
  }

  @ReactMethod
  public void isBluetoothEnabled(Promise promise) {
    if (BluetoothAdapter.getDefaultAdapter() != null) {
      promise.resolve(BluetoothAdapter.getDefaultAdapter().isEnabled());
    }
    promise.resolve(false);
  }

  @ReactMethod
  public void connectBtPrinter(String address, final Promise promise) {
    if(checkPermission()) {
      if(address != "") {
        if (mBluetoothAdapter != null) {
          BluetoothDevice device = getDevice(address);
          if(device != null) {
            printBill.print(device, promise);
          } else {
            promise.resolve(false);
          }
        }
      } else {
        promise.resolve(false);
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

  public void onServiceConnected() {
    if (this.mPM != null) {
      this.mPM.printerInit();
      printBill.setPrinterManager(mPM);
      isPrinterConnect = true;
    }
  }

  public void onDestroy() {
    if (this.mPM != null) {
      this.mPM.onPrinterStop();
      isPrinterConnect = false;
    }
  }


    @ReactMethod
    public void multiply(double a, double b, Promise promise) {
        promise.resolve(a * b);
    }

}
