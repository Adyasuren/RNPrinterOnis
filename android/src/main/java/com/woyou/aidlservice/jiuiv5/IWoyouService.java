package com.woyou.aidlservice.jiuiv5;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.woyou.aidlservice.jiuiv5.ICallback;

public interface IWoyouService extends IInterface {
    void clearBuffer() throws RemoteException;

    void commitPrinterBuffer() throws RemoteException;

    void commitPrinterBufferWithCallback(ICallback iCallback) throws RemoteException;

    void enterPrinterBuffer(boolean z) throws RemoteException;

    void exitPrinterBuffer(boolean z) throws RemoteException;

    void exitPrinterBufferWithCallback(boolean z, ICallback iCallback) throws RemoteException;

    int getFirmwareStatus() throws RemoteException;

    void getPrintedLength(ICallback iCallback) throws RemoteException;

    void getPrinterFactory(ICallback iCallback) throws RemoteException;

    String getPrinterModal() throws RemoteException;

    String getPrinterSerialNo() throws RemoteException;

    String getPrinterVersion() throws RemoteException;

    String getServiceVersion() throws RemoteException;

    void lineWrap(int i, ICallback iCallback) throws RemoteException;

    void printBarCode(String str, int i, int i2, int i3, int i4, ICallback iCallback) throws RemoteException;

    void printBitmap(Bitmap bitmap, ICallback iCallback) throws RemoteException;

    void printBitmapCustom(Bitmap bitmap, int i, ICallback iCallback) throws RemoteException;

    void printColumnsString(String[] strArr, int[] iArr, int[] iArr2, ICallback iCallback) throws RemoteException;

    void printColumnsText(String[] strArr, int[] iArr, int[] iArr2, ICallback iCallback) throws RemoteException;

    void printOriginalText(String str, ICallback iCallback) throws RemoteException;

    void printQRCode(String str, int i, int i2, ICallback iCallback) throws RemoteException;

    void printText(String str, ICallback iCallback) throws RemoteException;

    void printTextWithFont(String str, String str2, float f, ICallback iCallback) throws RemoteException;

    void printerInit(ICallback iCallback) throws RemoteException;

    void printerSelfChecking(ICallback iCallback) throws RemoteException;

    void sendRAWData(byte[] bArr, ICallback iCallback) throws RemoteException;

    void setAlignment(int i, ICallback iCallback) throws RemoteException;

    void setFontName(String str, ICallback iCallback) throws RemoteException;

    void setFontSize(float f, ICallback iCallback) throws RemoteException;

    void updateFirmware() throws RemoteException;

    int updatePrinterState() throws RemoteException;

    public static abstract class Stub extends Binder implements IWoyouService {
        private static final String DESCRIPTOR = "woyou.aidlservice.jiuiv5.IWoyouService";
        static final int TRANSACTION_clearBuffer = 26;
        static final int TRANSACTION_commitPrinterBuffer = 22;
        static final int TRANSACTION_commitPrinterBufferWithCallback = 27;
        static final int TRANSACTION_enterPrinterBuffer = 23;
        static final int TRANSACTION_exitPrinterBuffer = 24;
        static final int TRANSACTION_exitPrinterBufferWithCallback = 28;
        static final int TRANSACTION_getFirmwareStatus = 2;
        static final int TRANSACTION_getPrintedLength = 9;
        static final int TRANSACTION_getPrinterFactory = 25;
        static final int TRANSACTION_getPrinterModal = 8;
        static final int TRANSACTION_getPrinterSerialNo = 6;
        static final int TRANSACTION_getPrinterVersion = 7;
        static final int TRANSACTION_getServiceVersion = 3;
        static final int TRANSACTION_lineWrap = 10;
        static final int TRANSACTION_printBarCode = 19;
        static final int TRANSACTION_printBitmap = 18;
        static final int TRANSACTION_printBitmapCustom = 31;
        static final int TRANSACTION_printColumnsString = 29;
        static final int TRANSACTION_printColumnsText = 17;
        static final int TRANSACTION_printOriginalText = 21;
        static final int TRANSACTION_printQRCode = 20;
        static final int TRANSACTION_printText = 15;
        static final int TRANSACTION_printTextWithFont = 16;
        static final int TRANSACTION_printerInit = 4;
        static final int TRANSACTION_printerSelfChecking = 5;
        static final int TRANSACTION_sendRAWData = 11;
        static final int TRANSACTION_setAlignment = 12;
        static final int TRANSACTION_setFontName = 13;
        static final int TRANSACTION_setFontSize = 14;
        static final int TRANSACTION_updateFirmware = 1;
        static final int TRANSACTION_updatePrinterState = 30;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWoyouService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IWoyouService)) {
                return new Proxy(obj);
            }
            return (IWoyouService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bitmap _arg0;
            Bitmap _arg02;
            boolean _arg03 = false;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    updateFirmware();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getFirmwareStatus();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _result2 = getServiceVersion();
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    printerInit(ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printerSelfChecking /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    printerSelfChecking(ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getPrinterSerialNo /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    String _result3 = getPrinterSerialNo();
                    reply.writeNoException();
                    reply.writeString(_result3);
                    return true;
                case TRANSACTION_getPrinterVersion /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    String _result4 = getPrinterVersion();
                    reply.writeNoException();
                    reply.writeString(_result4);
                    return true;
                case TRANSACTION_getPrinterModal /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    String _result5 = getPrinterModal();
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case TRANSACTION_getPrintedLength /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    getPrintedLength(ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_lineWrap /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    lineWrap(data.readInt(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_sendRAWData /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    sendRAWData(data.createByteArray(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setAlignment /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    setAlignment(data.readInt(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setFontName /*13*/:
                    data.enforceInterface(DESCRIPTOR);
                    setFontName(data.readString(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setFontSize /*14*/:
                    data.enforceInterface(DESCRIPTOR);
                    setFontSize(data.readFloat(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printText /*15*/:
                    data.enforceInterface(DESCRIPTOR);
                    printText(data.readString(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    printTextWithFont(data.readString(), data.readString(), data.readFloat(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printColumnsText /*17*/:
                    data.enforceInterface(DESCRIPTOR);
                    printColumnsText(data.createStringArray(), data.createIntArray(), data.createIntArray(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printBitmap /*18*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Bitmap) Bitmap.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    printBitmap(_arg02, ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printBarCode /*19*/:
                    data.enforceInterface(DESCRIPTOR);
                    printBarCode(data.readString(), data.readInt(), data.readInt(), data.readInt(), data.readInt(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printQRCode /*20*/:
                    data.enforceInterface(DESCRIPTOR);
                    printQRCode(data.readString(), data.readInt(), data.readInt(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printOriginalText /*21*/:
                    data.enforceInterface(DESCRIPTOR);
                    printOriginalText(data.readString(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_commitPrinterBuffer /*22*/:
                    data.enforceInterface(DESCRIPTOR);
                    commitPrinterBuffer();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_enterPrinterBuffer /*23*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = true;
                    }
                    enterPrinterBuffer(_arg03);
                    reply.writeNoException();
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = true;
                    }
                    exitPrinterBuffer(_arg03);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getPrinterFactory /*25*/:
                    data.enforceInterface(DESCRIPTOR);
                    getPrinterFactory(ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_clearBuffer /*26*/:
                    data.enforceInterface(DESCRIPTOR);
                    clearBuffer();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_commitPrinterBufferWithCallback /*27*/:
                    data.enforceInterface(DESCRIPTOR);
                    commitPrinterBufferWithCallback(ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_exitPrinterBufferWithCallback /*28*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = true;
                    }
                    exitPrinterBufferWithCallback(_arg03, ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_printColumnsString /*29*/:
                    data.enforceInterface(DESCRIPTOR);
                    printColumnsString(data.createStringArray(), data.createIntArray(), data.createIntArray(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_updatePrinterState /*30*/:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = updatePrinterState();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case TRANSACTION_printBitmapCustom /*31*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Bitmap) Bitmap.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    printBitmapCustom(_arg0, data.readInt(), ICallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IWoyouService {
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

            public void updateFirmware() throws RemoteException {
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

            public int getFirmwareStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getServiceVersion() throws RemoteException {
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

            public void printerInit(ICallback callback) throws RemoteException {
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

            public void printerSelfChecking(ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printerSelfChecking, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getPrinterSerialNo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getPrinterSerialNo, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getPrinterVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getPrinterVersion, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getPrinterModal() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getPrinterModal, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getPrintedLength(ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_getPrintedLength, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void lineWrap(int n, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(n);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_lineWrap, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void sendRAWData(byte[] data, ICallback callback) throws RemoteException {
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

            public void setAlignment(int alignment, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(alignment);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_setAlignment, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setFontName(String typeface, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(typeface);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_setFontName, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setFontSize(float fontsize, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(fontsize);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_setFontSize, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printText(String text, ICallback callback) throws RemoteException {
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

            public void printTextWithFont(String text, String typeface, float fontsize, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeString(typeface);
                    _data.writeFloat(fontsize);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringArray(colsTextArr);
                    _data.writeIntArray(colsWidthArr);
                    _data.writeIntArray(colsAlign);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printColumnsText, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printBitmap(Bitmap bitmap, ICallback callback) throws RemoteException {
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

            public void printBarCode(String data, int symbology, int height, int width, int textposition, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(data);
                    _data.writeInt(symbology);
                    _data.writeInt(height);
                    _data.writeInt(width);
                    _data.writeInt(textposition);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printBarCode, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printQRCode(String data, int modulesize, int errorlevel, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(data);
                    _data.writeInt(modulesize);
                    _data.writeInt(errorlevel);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printQRCode, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printOriginalText(String text, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printOriginalText, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void commitPrinterBuffer() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_commitPrinterBuffer, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void enterPrinterBuffer(boolean clean) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (clean) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_enterPrinterBuffer, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void exitPrinterBuffer(boolean commit) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (commit) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getPrinterFactory(ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_getPrinterFactory, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void clearBuffer() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_clearBuffer, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void commitPrinterBufferWithCallback(ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_commitPrinterBufferWithCallback, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void exitPrinterBufferWithCallback(boolean commit, ICallback callback) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (commit) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_exitPrinterBufferWithCallback, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printColumnsString(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringArray(colsTextArr);
                    _data.writeIntArray(colsWidthArr);
                    _data.writeIntArray(colsAlign);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printColumnsString, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int updatePrinterState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_updatePrinterState, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void printBitmapCustom(Bitmap bitmap, int type, ICallback callback) throws RemoteException {
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
                    _data.writeInt(type);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_printBitmapCustom, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
