package com.jgd.permissiondemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CALL_PHONE_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void call(View v) {
        if (PermissionHelper.requestPermissionDialog(this, Manifest.permission.CALL_PHONE, CALL_PHONE_REQUEST_CODE)) {
            callPhone();
        }
    }

    /**
     * 申请权限对话框回调
     */
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CALL_PHONE_REQUEST_CODE:
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted) {   //授权成功

                    callPhone();

                } else {        //授权失败

                    //false代表用户点击了不再提醒
                    boolean flag = PermissionHelper.twiceShowRequestPermission(this, Manifest.permission.CALL_PHONE);

                    if (flag)

                        toast("权限申请失败，部分功能无法使用！");

                    else

                        toast(String.format("权限申请失败，请到设置->应用->%s 进行手动更改", getPackageManager().getApplicationLabel(getApplicationInfo())));
                }
                break;
        }
    }

    /**
     * 拨打电话
     */
    public void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + 888888));
        startActivity(intent);
    }

    private void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
