package com.onisprinter;

import android.bluetooth.BluetoothDevice;

public interface PrinterAlert {
    void ConnectionFailed();

    void ConnectionLost();

    void ConnectionCheck();

    void ConnectionSuccess();

    void FindDevice();

    void startConnect();

    void finishConnect();

    void bluetoothPairing(BluetoothDevice selectedDevice);
}
