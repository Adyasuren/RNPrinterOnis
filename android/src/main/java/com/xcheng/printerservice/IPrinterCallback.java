package com.xcheng.printerservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPrinterCallback extends IInterface {
    void onComplete() throws RemoteException;

    void onException(int i, String str) throws RemoteException;

    void onLength(long j, long j2) throws RemoteException;

    public static abstract class Stub extends Binder implements IPrinterCallback {
        private static final String DESCRIPTOR = "com.xcheng.printerservice.IPrinterCallback";
        static final int TRANSACTION_onComplete = 3;
        static final int TRANSACTION_onException = 1;
        static final int TRANSACTION_onLength = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPrinterCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPrinterCallback)) {
                return new Proxy(obj);
            }
            return (IPrinterCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onException(data.readInt(), data.readString());
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onLength(data.readLong(), data.readLong());
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onComplete();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IPrinterCallback {
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

            public void onException(int code, String msg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(code);
                    _data.writeString(msg);
                    this.mRemote.transact(1, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onLength(long current, long total) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(current);
                    _data.writeLong(total);
                    this.mRemote.transact(2, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onComplete() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
