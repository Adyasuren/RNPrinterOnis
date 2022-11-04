package com.print.p000bt;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.print.C0054R;
import java.util.Arrays;
import java.util.HashMap;
import com.p001xy.utils.StringUtils;

/* renamed from: com.print.bt.BTPrinterActivity */
public class BTPrinterActivity extends Activity implements View.OnClickListener {
    private static final int MSG_SCAN = 1;
    private static final String TAG = "BTPrinterActivity";
    /* access modifiers changed from: private */
    public String mBTList = TAG;
    private BroadcastReceiver mBTReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.device.action.FOUND".equals(intent.getAction())) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                HashMap<String, String> deviceMsg = new HashMap<>();
                deviceMsg.put("name", device.getName());
                deviceMsg.put("address", device.getAddress());
                if (deviceMsg.size() > 0) {
                    BTPrinterActivity bTPrinterActivity = BTPrinterActivity.this;
                    bTPrinterActivity.mBTList = String.valueOf(bTPrinterActivity.mBTList) + "\nName：" + device.getName() + "\nAddress：" + device.getAddress();
                    BTPrinterActivity.this.mTextView.setText(BTPrinterActivity.this.mBTList);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public BluetoothHandler mBluetoothHandler;
    private Button mButton;
    private EditText mEditText;
    private HandlerThread mHandlerThread;
    private ImageView mImageView;
    /* access modifiers changed from: private */
    public TextView mTextView;

    /* access modifiers changed from: protected */
    public void doCustomActions() {
        BluetoothAdapter adapter = BTPrinterHelp.doGetBTAdapter();
        while (!adapter.isEnabled()) {
            BTPrinterHelp.doOpenBluetooth(adapter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case C0054R.C0055id.bt_scan:
                this.mBTList = "BT Scanning....";
                this.mTextView.setText(this.mBTList);
                if (this.mHandlerThread == null) {
                    this.mHandlerThread = new HandlerThread(TAG);
                    this.mHandlerThread.start();
                }
                if (this.mBluetoothHandler == null) {
                    this.mBluetoothHandler = new BluetoothHandler(this.mHandlerThread.getLooper());
                }
                this.mBluetoothHandler.sendEmptyMessage(1);
                return;
            case C0054R.C0055id.bt_print:
                String input = this.mEditText.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    input = "11:22:33:44:55:66\n00001101-0000-1000-8000-00805F9B34FB";
                }
                this.mTextView.setText(String.valueOf(input) + StringUtils.LINE_FEED + Arrays.toString(input.getBytes()));
                BluetoothDevice device = BTPrinterHelp.doGetPrinterDevice(BTPrinterHelp.doGetBTAdapter(), (String) null);
                if (device == null) {
                    Toast.makeText(this, "No BTPrinter!", 0).show();
                    return;
                }
                BluetoothSocket socket = BTPrinterHelp.doGetBTSocket(device, (String) null);
                if (socket == null) {
                    Toast.makeText(this, "BTSocket Connect Fail!", 0).show();
                    return;
                }
                BTPrinterHelp.doBTPrinterSendData(input.getBytes(), socket);
                BTPrinterHelp.doBTSocketClose(socket);
                return;
            default:
                return;
        }
    }

    /* renamed from: com.print.bt.BTPrinterActivity$BluetoothHandler */
    private class BluetoothHandler extends Handler {
        public BluetoothHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    BluetoothAdapter adapter = BTPrinterHelp.doGetBTAdapter();
                    if (!adapter.isEnabled()) {
                        boolean result = BTPrinterHelp.doOpenBluetooth(adapter);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (BTPrinterHelp.doStartDiscovery(adapter)) {
                        BTPrinterActivity.this.mBTList = "BoundedDevice：\n";
                        for (BluetoothDevice device : BTPrinterHelp.doGetBoundedDevices(adapter)) {
                            BTPrinterActivity bTPrinterActivity = BTPrinterActivity.this;
                            bTPrinterActivity.mBTList = String.valueOf(bTPrinterActivity.mBTList) + "Name：" + device.getName() + StringUtils.LINE_FEED + "Address：" + device.getAddress() + StringUtils.LINE_FEED;
                        }
                        BTPrinterActivity bTPrinterActivity2 = BTPrinterActivity.this;
                        bTPrinterActivity2.mBTList = String.valueOf(bTPrinterActivity2.mBTList) + "\nBTDiscovery：";
                        BTPrinterActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                BTPrinterActivity.this.mTextView.setText(BTPrinterActivity.this.mBTList);
                            }
                        });
                        return;
                    } else if (BTPrinterActivity.this.mBluetoothHandler != null) {
                        BTPrinterActivity.this.mBluetoothHandler.sendEmptyMessage(1);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doInitViews() {
        this.mEditText = (EditText) findViewById(C0054R.C0055id.f0et);
        this.mEditText.setVisibility(0);
        this.mButton = (Button) findViewById(C0054R.C0055id.bt_scan);
        this.mButton.setOnClickListener(this);
        this.mButton.setVisibility(0);
        this.mButton = (Button) findViewById(C0054R.C0055id.bt_print);
        this.mButton.setOnClickListener(this);
        this.mButton.setVisibility(0);
        this.mTextView = (TextView) findViewById(C0054R.C0055id.f2tv);
        this.mTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.mTextView.setVisibility(0);
        this.mImageView = (ImageView) findViewById(C0054R.C0055id.f1iv);
        this.mImageView.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0054R.layout.activity_bt);
        doInitViews();
        doCustomActions();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        registerReceiver(this.mBTReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        unregisterReceiver(this.mBTReceiver);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mHandlerThread != null) {
            this.mHandlerThread.quit();
            this.mHandlerThread = null;
        }
        if (this.mBluetoothHandler != null) {
            this.mBluetoothHandler = null;
        }
    }
}
