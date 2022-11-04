package com.xcheng.printerservice;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xcheng.printerservice.IPrinterCallback;
import java.util.List;
import java.util.Map;

public interface IPrinterService extends IInterface {
    String getBootloaderVersion() throws RemoteException;

    String getFirmwareVersion() throws RemoteException;

    void printBarCode(String str, int i, int i2, int i3, boolean z, IPrinterCallback iPrinterCallback) throws RemoteException;

    void printBitmap(Bitmap bitmap, IPrinterCallback iPrinterCallback) throws RemoteException;

    void printBitmapWithAttributes(Bitmap bitmap, Map map, IPrinterCallback iPrinterCallback) throws RemoteException;

    void printColumnsTextWithAttributes(String[] strArr, List list, IPrinterCallback iPrinterCallback) throws RemoteException;

    void printQRCode(String str, int i, int i2, IPrinterCallback iPrinterCallback) throws RemoteException;

    void printText(String str, IPrinterCallback iPrinterCallback) throws RemoteException;

    void printTextWithAttributes(String str, Map map, IPrinterCallback iPrinterCallback) throws RemoteException;

    void printWrapPaper(int i, IPrinterCallback iPrinterCallback) throws RemoteException;

    void printerInit(IPrinterCallback iPrinterCallback) throws RemoteException;

    boolean printerPaper(IPrinterCallback iPrinterCallback) throws RemoteException;

    void printerReset(IPrinterCallback iPrinterCallback) throws RemoteException;

    int printerTemperature(IPrinterCallback iPrinterCallback) throws RemoteException;

    void sendRAWData(byte[] bArr, IPrinterCallback iPrinterCallback) throws RemoteException;

    void setPrinterSpeed(int i, IPrinterCallback iPrinterCallback) throws RemoteException;

    void upgradePrinter() throws RemoteException;

    public static abstract class Stub extends Binder implements IPrinterService {
        private static final String DESCRIPTOR = "com.xcheng.printerservice.IPrinterService";
        static final int TRANSACTION_getBootloaderVersion = 3;
        static final int TRANSACTION_getFirmwareVersion = 2;
        static final int TRANSACTION_printBarCode = 12;
        static final int TRANSACTION_printBitmap = 10;
        static final int TRANSACTION_printBitmapWithAttributes = 11;
        static final int TRANSACTION_printColumnsTextWithAttributes = 9;
        static final int TRANSACTION_printQRCode = 13;
        static final int TRANSACTION_printText = 7;
        static final int TRANSACTION_printTextWithAttributes = 8;
        static final int TRANSACTION_printWrapPaper = 6;
        static final int TRANSACTION_printerInit = 4;
        static final int TRANSACTION_printerPaper = 17;
        static final int TRANSACTION_printerReset = 5;
        static final int TRANSACTION_printerTemperature = 16;
        static final int TRANSACTION_sendRAWData = 15;
        static final int TRANSACTION_setPrinterSpeed = 14;
        static final int TRANSACTION_upgradePrinter = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPrinterService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPrinterService)) {
                return new Proxy(obj);
            }
            return (IPrinterService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bitmap _arg0;
            Bitmap _arg02;
            boolean _arg4 = false;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    upgradePrinter();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _result = getFirmwareVersion();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _result2 = getBootloaderVersion();
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    printerInit(IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printerReset /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    printerReset(IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printWrapPaper /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    printWrapPaper(data.readInt(), IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printText /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    printText(data.readString(), IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printTextWithAttributes /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    printTextWithAttributes(data.readString(), data.readHashMap(getClass().getClassLoader()), IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printColumnsTextWithAttributes /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    printColumnsTextWithAttributes(data.createStringArray(), data.readArrayList(getClass().getClassLoader()), IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printBitmap /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Bitmap) Bitmap.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    printBitmap(_arg02, IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printBitmapWithAttributes /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Bitmap) Bitmap.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    printBitmapWithAttributes(_arg0, data.readHashMap(getClass().getClassLoader()), IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printBarCode /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg4 = true;
                    }
                    printBarCode(_arg03, _arg1, _arg2, _arg3, _arg4, IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printQRCode /*13*/:
                    data.enforceInterface(DESCRIPTOR);
                    printQRCode(data.readString(), data.readInt(), data.readInt(), IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setPrinterSpeed /*14*/:
                    data.enforceInterface(DESCRIPTOR);
                    setPrinterSpeed(data.readInt(), IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_sendRAWData /*15*/:
                    data.enforceInterface(DESCRIPTOR);
                    sendRAWData(data.createByteArray(), IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = printerTemperature(IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case TRANSACTION_printerPaper /*17*/:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result4 = printerPaper(IPrinterCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    if (_result4) {
                        _arg4 = true;
                    }
                    reply.writeInt(_arg4 ? 1 : 0);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IPrinterService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void upgradePrinter() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getFirmwareVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getBootloaderVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printerInit(IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printerReset(IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printerReset, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printWrapPaper(int n, IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(n);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printWrapPaper, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printText(String text, IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printText, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printTextWithAttributes(String text, Map attributes, IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeMap(attributes);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printTextWithAttributes, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printColumnsTextWithAttributes(String[] text, List attributes, IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringArray(text);
                    _data.writeList(attributes);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printColumnsTextWithAttributes, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printBitmap(Bitmap bitmap, IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bitmap != null) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printBitmap, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printBitmapWithAttributes(Bitmap bitmap, Map attributes, IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bitmap != null) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeMap(attributes);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printBitmapWithAttributes, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printBarCode(String content, int align, int width, int height, boolean showContent, IPrinterCallback callback) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(content);
                    _data.writeInt(align);
                    _data.writeInt(width);
                    _data.writeInt(height);
                    if (showContent) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printBarCode, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printQRCode(String text, int align, int size, IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeInt(align);
                    _data.writeInt(size);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printQRCode, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setPrinterSpeed(int level, IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(level);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_setPrinterSpeed, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void sendRAWData(byte[] data, IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_sendRAWData, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int printerTemperature(IPrinterCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean printerPaper(IPrinterCallback callback) throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printerPaper, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
