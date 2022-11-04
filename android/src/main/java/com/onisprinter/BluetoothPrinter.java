package com.onisprinter;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;

import com.print.api.PrinterManager;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BluetoothPrinter {
    PrintBill printBill;
    public static final String BIT_IMAGE_MODE = "*";
    public static final int ALIGN_CENTER = 100;
    public static final int ALIGN_RIGHT = 101;
    public static final int ALIGN_LEFT = 102;
    private static final byte[] NEW_LINE = {10};
    private static final byte[] ESC_ALIGN_CENTER = new byte[]{0x1b, 'a', 0x01};
    private static final byte[] ESC_ALIGN_RIGHT = new byte[]{0x1b, 'a', 0x02};
    private static final byte[] ESC_ALIGN_LEFT = new byte[]{0x1b, 'a', 0x00};


    private static final int P_PRINTER_ALIGN_CENTER = 0;
    private static final int P_PRINTER_ALIGN_LEFT = 1;
    private static final int P_PRINTER_ALIGN_RIGHT = 2;
    private static final int P_PRINTER_TEXT_BIG = 24;
    private static final int P_PRINTER_TEXT_SMALL = 19;
    private static final int P_PRINTER_TEXT_FONT = 8;

    private PrinterManager posPrinterManager = null;
    Map<String, Integer> P_PRINTER_TEXT_STYLE = new HashMap<>();

    private BluetoothDevice printer;
    private BluetoothSocket btSocket = null;
    private OutputStream btOutputStream = null;

    public static int connectionState = 0; /*1-startConnect, 2-finishConnect, -1-FAILED*/

    public static int CONNECTING = 1;
    public static int CONNECTED = 2;
    public static int FAILED = -1;


    public static String ESC = "\u001B";
    public static String InitializePrinter = ESC + "@";
    String BoldOn = ESC + "E" + "\u0001";
    String BoldOff = ESC + "E" + "\0";

    byte[] fontSmall = new byte[]{27, 77, 1}; //small
    byte[] fontBig = new byte[]{27, 77, 0}; //big

    public BluetoothPrinter(BluetoothDevice printer) {
        this.printer = printer;
    }

    public BluetoothPrinter(BluetoothDevice printer, PrintBill printBill, PrinterManager pManager) {
        this.printer = printer;
        this.printBill = printBill;
        this.posPrinterManager = pManager;
        if(this.posPrinterManager != null) {
            P_PRINTER_TEXT_STYLE.put(PrinterManager.KEY_TEXTSIZE, P_PRINTER_TEXT_BIG);
            P_PRINTER_TEXT_STYLE.put(PrinterManager.KEY_TYPEFACE, P_PRINTER_TEXT_FONT);
            P_PRINTER_TEXT_STYLE.put(PrinterManager.KEY_ALIGN, P_PRINTER_ALIGN_LEFT);
        }
    }

    public void connectPrinter(final PrinterConnectListener listener) {
        ConnectTask connectTask = new ConnectTask(new ConnectTask.BtConnectListener() {
            @Override
            public void onConnected(BluetoothSocket socket) {
                btSocket = socket;
                try {
                    btOutputStream = socket.getOutputStream();
                    listener.onConnected();
                } catch (IOException e) {
                    listener.onFailed();
                }
            }

            @Override
            public void onFailed() {
                listener.onFailed();
            }
        });

        connectTask.execute(printer);
    }


    public boolean isConnected() {
        if (btSocket != null && btSocket.isConnected())
            return true;
        else
            return false;
    }

    public void finish() {
        if (btSocket != null) {
            try {
                btOutputStream.close();
                btSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            btSocket = null;
        }
    }

    public boolean printText(String text) {
        try {
            if(this.posPrinterManager != null) {
                this.posPrinterManager.sendRAWData(encodeNonAscii(text).getBytes());
                // TODO
            } else {
                btOutputStream.write(encodeNonAscii(text).getBytes());
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean printUnicode(String text) {
        try {
            if(this.posPrinterManager != null) {
                this.posPrinterManager.printTextWithAttributes(text, P_PRINTER_TEXT_STYLE);
            } else {
                text = text.replaceAll("Ө", "@");
                text = text.replaceAll("ө", "#");
                text = text.replaceAll("Ү", "V");
                text = text.replaceAll("ү", "v");

                byte[] byte_ = text.getBytes("CP866");

                //byte[] byte_ = text.getBytes("gbk"); //GB2312
                //byte[] byte_ = text.getBytes();

                for (int i = 0; i < byte_.length; i++) {
                    if (byte_[i] == 64) {
                        byte_[i] = (byte) 242;
                    } else if (byte_[i] == 35)
                        byte_[i] = (byte) 243;
                }
                if (btOutputStream != null) {
                    if (byte_ != null)
                        btOutputStream.write(byte_);
                    else
                        return false;
                } else
                    return false;


            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Exception", "Broken pipe");
            if (printBill != null)
                printBill.connectionLost();
            else
                Log.v("printBill", "null");
            return false;
        }
    }


    public boolean printUnicode(byte[] data) {
        try {
            if (btOutputStream != null) {
                if (data != null)
                    btOutputStream.write(data);
                else
                    return false;
            } else
                return false;

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Exception", "Broken pipe");
            if (printBill != null)
                printBill.connectionLost();
            else
                Log.v("printBill", "null");

            return false;
        }
    }

    public boolean printLine() {
        return printText("________________________________");
    }

    public boolean printLine2() {
        String sepLine = "--------------------------------";
        if(this.posPrinterManager != null) {
            this.posPrinterManager.printText(sepLine);
            return true;
        } else {
            return printText(sepLine);
        }

    }

    public boolean addNewLine() {
        if(this.posPrinterManager != null) {
            //this.posPrinterManager.printWrapPaper(1);
            return true;
        } else {
            return printUnicode(NEW_LINE);
        }
    }

    public int addNewLines(int count) {
        int success = 0;
        for (int i = 0; i < count; i++) {
            if (addNewLine()) success++;
        }

        return success;
    }

    public boolean printImage(Bitmap bitmap) {
        if(this.posPrinterManager != null) {
            Map<String, Integer> P_PRINTER_QR_STYLE = new HashMap<>();
            P_PRINTER_QR_STYLE.put(PrinterManager.KEY_ALIGN, P_PRINTER_ALIGN_CENTER);
            this.posPrinterManager.printBitmap(bitmap, P_PRINTER_QR_STYLE);
            return true;
        } else {
            byte[] command = decodeBitmap(bitmap);
            if (command.length > 0)
                return printUnicode(command);
            else
                return false;
        }
    }

    public void setAlign(int alignType) {
        if(this.posPrinterManager != null) {
            switch (alignType) {
                case ALIGN_CENTER:
                    P_PRINTER_TEXT_STYLE.put(PrinterManager.KEY_ALIGN, P_PRINTER_ALIGN_CENTER);
                    break;
                case ALIGN_LEFT:
                    P_PRINTER_TEXT_STYLE.put(PrinterManager.KEY_ALIGN, P_PRINTER_ALIGN_LEFT);
                    break;
                case ALIGN_RIGHT:
                    P_PRINTER_TEXT_STYLE.put(PrinterManager.KEY_ALIGN, P_PRINTER_ALIGN_RIGHT);
                    break;
                default:
                    P_PRINTER_TEXT_STYLE.put(PrinterManager.KEY_ALIGN, P_PRINTER_ALIGN_LEFT);
                    break;
            }
        } else {
            byte[] d;
            switch (alignType) {
                case ALIGN_CENTER:
                    d = ESC_ALIGN_CENTER;
                    break;
                case ALIGN_LEFT:
                    d = ESC_ALIGN_LEFT;
                    break;
                case ALIGN_RIGHT:
                    d = ESC_ALIGN_RIGHT;
                    break;
                default:
                    d = ESC_ALIGN_LEFT;
                    break;
            }
            try {
                btOutputStream.write(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLineSpacing(int lineSpacing) {
        byte[] cmd = new byte[]{0x1B, 0x33, (byte) lineSpacing};
        if(this.posPrinterManager != null) {
            // TODO
            // this.posPrinterManager.sendRAWData(cmd);
        } else {
            printUnicode(cmd);
        }

    }

    public void setBold(boolean bold) {
        /*byte[] cmd = new byte[]{0x1B, 0x45, bold ? (byte) 1 : 0};
        printUnicode(cmd);*/
        // TODO
        if(this.posPrinterManager != null) {

        } else {
            if (bold)
                printText(this.BoldOn);
            else
                printText(this.BoldOff);
        }
    }

    public void setSmall(boolean small) {
        /*byte[] cmd = new byte[]{0x1B, 0x45, small ? (byte) 30 : 0};
        printUnicode(cmd);*/
        if(this.posPrinterManager != null) {
            if(small) {
                P_PRINTER_TEXT_STYLE.put(PrinterManager.KEY_TEXTSIZE, P_PRINTER_TEXT_SMALL);
            } else {
                P_PRINTER_TEXT_STYLE.put(PrinterManager.KEY_TEXTSIZE, P_PRINTER_TEXT_BIG);
            }
        } else {
            if (small) {
                printUnicode(fontSmall);
            } else {
                printUnicode(fontBig);
            }
        }

    }

    public void feedPaper() {
        addNewLine();
        addNewLine();
        addNewLine();
    }

    private static class ConnectTask extends AsyncTask<BluetoothDevice, Void, BluetoothSocket> {
        private BtConnectListener listener;

        private ConnectTask connectTask;
        CountDownTimer countDownTimer;

        private ConnectTask(BtConnectListener listener) {
            this.listener = listener;
        }

        @Override
        protected BluetoothSocket doInBackground(BluetoothDevice... bluetoothDevices) {
            BluetoothDevice device = bluetoothDevices[0];
            boolean connected = true;
            BluetoothSocket socket = null;

            if (device != null) {
                UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                try {
                    socket = device.createRfcommSocketToServiceRecord(uuid);
                } catch (IOException e) {
                }
                try {
                    socket.connect();
                } catch (IOException e) {
                    try {
                        socket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[]{int.class})
                                .invoke(device, 1);
                        socket.connect();
                    } catch (Exception e2) {
                        connected = false;
                    }
                }
            } else {
                Log.v("deviceDoInBackground", "null");
            }
            return connected ? socket : null;
        }

        @Override
        protected void onPreExecute() {
            if (listener != null) {
                BluetoothPrinter.connectionState = 1;
            }

            Log.v("onPreExecute", "startConnect : " + BluetoothPrinter.connectionState);
            connectTask = this;

        }

        @Override
        protected void onPostExecute(BluetoothSocket bluetoothSocket) {
            if (countDownTimer != null)
                countDownTimer.cancel();

            if (listener != null) {
                if (bluetoothSocket != null) {
                    BluetoothPrinter.connectionState = 2;
                    listener.onConnected(bluetoothSocket);
                } else {
                    BluetoothPrinter.connectionState = -1;
                    listener.onFailed();
                }
            }

            Log.v("onPostExecute", "" + BluetoothPrinter.connectionState);
        }

        private interface BtConnectListener {
            void onConnected(BluetoothSocket socket);

            void onFailed();
        }
    }

    public interface PrinterConnectListener {
        void onConnected();

        void onFailed();
    }

    private static String encodeNonAscii(String text) {
        return text.replace('á', 'a')
                .replace('č', 'c')
                .replace('ď', 'd')
                .replace('é', 'e')
                .replace('ě', 'e')
                .replace('í', 'i')
                .replace('ň', 'n')
                .replace('ó', 'o')
                .replace('ř', 'r')
                .replace('š', 's')
                .replace('ť', 't')
                .replace('ú', 'u')
                .replace('ů', 'u')
                .replace('ý', 'y')
                .replace('ž', 'z')
                .replace('Á', 'A')
                .replace('Č', 'C')
                .replace('Ď', 'D')
                .replace('É', 'E')
                .replace('Ě', 'E')
                .replace('Í', 'I')
                .replace('Ň', 'N')
                .replace('Ó', 'O')
                .replace('Ř', 'R')
                .replace('Š', 'S')
                .replace('Ť', 'T')
                .replace('Ú', 'U')
                .replace('Ů', 'U')
                .replace('Ý', 'Y')
                .replace('Ž', 'Z');
    }

    public static byte[] decodeBitmap(Bitmap bmp) {
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();

        List<String> list = new ArrayList<>();
        StringBuffer sb;
        int zeroCount = bmpWidth % 8;
        String zeroStr = "";
        if (zeroCount > 0) {
            for (int i = 0; i < (8 - zeroCount); i++) zeroStr = zeroStr + "0";
        }

        for (int i = 0; i < bmpHeight; i++) {
            sb = new StringBuffer();
            for (int j = 0; j < bmpWidth; j++) {
                int color = bmp.getPixel(j, i);
                int r = (color >> 16) & 0xff;
                int g = (color >> 8) & 0xff;
                int b = color & 0xff;
                if (r > 160 && g > 160 && b > 160) sb.append("0");
                else sb.append("1");
            }

            if (zeroCount > 0) sb.append(zeroStr);
            list.add(sb.toString());
        }

        List<String> bmpHexList = binaryListToHexStringList(list);
        String commandHexString = "1D763000";
        String widthHexString = Integer
                .toHexString(bmpWidth % 8 == 0 ? bmpWidth / 8 : (bmpWidth / 8 + 1));
        if (widthHexString.length() > 2) {
            return null;
        } else if (widthHexString.length() == 1) {
            widthHexString = "0" + widthHexString;
        }
        widthHexString = widthHexString + "00";

        String heightHexString = Integer.toHexString(bmpHeight);
        if (heightHexString.length() > 2) {
            return null;
        } else if (heightHexString.length() == 1) {
            heightHexString = "0" + heightHexString;
        }
        heightHexString = heightHexString + "00";

        List<String> commandList = new ArrayList<>();
        commandList.add(commandHexString + widthHexString + heightHexString);
        commandList.addAll(bmpHexList);

        return hexList2Byte(commandList);
    }

    private static List<String> binaryListToHexStringList(List<String> list) {
        List<String> hexList = new ArrayList<>();
        for (String binaryStr : list) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < binaryStr.length(); i += 8) {
                String str = binaryStr.substring(i, i + 8);
                String hexString = strToHexString(str);
                sb.append(hexString);
            }
            hexList.add(sb.toString());
        }
        return hexList;
    }

    private static String hexStr = "0123456789ABCDEF";
    private static String[] binaryArray = {"0000", "0001", "0010", "0011",
            "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011",
            "1100", "1101", "1110", "1111"};

    /*private static String strToHexString(String binaryStr) {
        String hex = "";
        String f4 = binaryStr.substring(0, 2);
        String b4 = binaryStr.substring(2, 6);
        for (int i = 0; i < binaryArray.length; i++) {
            if (f4.equals(binaryArray[i]))
                hex += hexStr.substring(i, i + 1);
        }
        for (int i = 0; i < binaryArray.length; i++) {
            if (b4.equals(binaryArray[i]))
                hex += hexStr.substring(i, i + 1);
        }

        return hex;
    }*/

    private static String strToHexString(String binaryStr) {
        String hex = "";
        String f4 = binaryStr.substring(0, 4);
        String b4 = binaryStr.substring(4, 8);
        for (int i = 0; i < binaryArray.length; i++) {
            if (f4.equals(binaryArray[i]))
                hex += hexStr.substring(i, i + 1);
        }
        for (int i = 0; i < binaryArray.length; i++) {
            if (b4.equals(binaryArray[i]))
                hex += hexStr.substring(i, i + 1);
        }

        return hex;
    }

    private static byte[] hexList2Byte(List<String> list) {
        List<byte[]> commandList = new ArrayList<>();
        for (String hexStr : list) commandList.add(hexStringToBytes(hexStr));
        return sysCopy(commandList);
    }

    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) return null;
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte[] sysCopy(List<byte[]> srcArrays) {
        int len = 0;
        for (byte[] srcArray : srcArrays) {
            len += srcArray.length;
        }
        byte[] destArray = new byte[len];
        int destLen = 0;
        for (byte[] srcArray : srcArrays) {
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
            destLen += srcArray.length;
        }

        return destArray;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public BluetoothSocket getSocket() {
        return btSocket;
    }

    public BluetoothDevice getDevice() {
        return printer;
    }
}
