package com.p001xy.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.ArrayList;
import java.util.List;

/* renamed from: xy.utils.DeviceUtils */
public class DeviceUtils {
    public static int doGetVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String doGetVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean doCheckPackageInstalled(Context context, String packageName) {
        if (doGetInstalledPackages(context).contains(packageName)) {
            return true;
        }
        return false;
    }

    public static List<String> doGetInstalledPackages(Context context) {
        List<String> packagesName = new ArrayList<>();
        packagesName.clear();
        for (PackageInfo pi : context.getPackageManager().getInstalledPackages(0)) {
            packagesName.add(pi.packageName);
        }
        return packagesName;
    }
}
