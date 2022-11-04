package com.print.p000bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/* renamed from: com.print.bt.BTPrinterHelp */
public class BTPrinterHelp {
    public static final String BT_PRINTER_DEFAULT_ADDRESS = "11:22:33:44:55:66";
    public static final String BT_PRINTER_DEFAULT_UUID = "00001101-0000-1000-8000-00805F9B34FB";

    public static BluetoothAdapter doGetBTAdapter() {
        return BluetoothAdapter.getDefaultAdapter();
    }

    public static boolean doOpenBluetooth(BluetoothAdapter adapter) {
        if (!adapter.isEnabled()) {
            return adapter.enable();
        }
        return true;
    }

    public static boolean doCloseBluetooth(BluetoothAdapter adapter) {
        doStopDiscovery(adapter);
        if (adapter.isEnabled()) {
            return adapter.disable();
        }
        return true;
    }

    public static boolean doStartDiscovery(BluetoothAdapter adapter) {
        doStopDiscovery(adapter);
        return adapter.startDiscovery();
    }

    public static boolean doStopDiscovery(BluetoothAdapter adapter) {
        if (adapter.isDiscovering()) {
            return adapter.cancelDiscovery();
        }
        return true;
    }

    public static boolean doIsBTEnabled(BluetoothAdapter adapter) {
        return adapter.isEnabled();
    }

    public static Set<BluetoothDevice> doGetBoundedDevices(BluetoothAdapter adapter) {
        return adapter.getBondedDevices();
    }

    public static BluetoothDevice doGetPrinterDevice(BluetoothAdapter adapter, String address) {
        if (address == null) {
            address = BT_PRINTER_DEFAULT_ADDRESS;
        }
        for (BluetoothDevice device : adapter.getBondedDevices()) {
            if (device.getAddress().equals(address)) {
                return device;
            }
        }
        return null;
    }

    public static BluetoothSocket doGetBTSocket(BluetoothDevice device, String uuid) {
        BluetoothSocket socket = null;
        if (uuid == null) {
            uuid = BT_PRINTER_DEFAULT_UUID;
        }
        try {
            socket = device.createRfcommSocketToServiceRecord(UUID.fromString(uuid));
            socket.connect();
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
            return socket;
        }
    }

    public static void doBTPrinterSendData(byte[] bytes, BluetoothSocket socket) {
        try {
            OutputStream out = socket.getOutputStream();
            out.write(bytes, 0, bytes.length);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doBTSocketClose(BluetoothSocket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
