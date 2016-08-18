package com.jgd.permissiondemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * 创建者：     金国栋      <br/><br/>
 * 创建时间:   2016/8/18 14:57   <br/><br/>
 * 描述:	      6.0运行时权限帮助类
 */
public class PermissionHelper {

    /**
     * 显示申请权限对话框，如果有申请过就不会显示
     *
     * @return true 代表已经允许过这个权限，可以直接干你想干的事了
     */
    public static boolean requestPermissionDialog(Activity context, String permissions, int requestCode) {
        if (!hasPermission(context, permissions)) {
            ActivityCompat.requestPermissions(context, new String[]{permissions}, requestCode);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检查用户是否点击了不再提醒
     *
     * @return false代表用户点击了不再提醒，这样对话框就不会再弹了，要开启权限必须用户手动到设置里面开启
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean twiceShowRequestPermission(Activity context, String permission) {
        return context.shouldShowRequestPermissionRationale(permission);
    }

    /**
     * 检查是否已经允许过这个权限
     *
     * @return true允许， false未允许
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasPermission(Context context, String permission) {
        if (canMakeSmores()) {
            return (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }

    /**
     * 检查版本是否是6.0以上
     */
    public static boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }
}
