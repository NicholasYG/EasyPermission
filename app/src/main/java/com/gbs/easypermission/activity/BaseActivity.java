package com.gbs.easypermission.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import com.gbs.easypermission.ActivityCollector;
import com.gbs.easypermission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @author gdy
 * @date 3/15/21
 * 集成BaseActivity可以简化申请动态权限的步骤，详细用法可以参考
 */
public class BaseActivity extends AppCompatActivity {
    public static final int CALL_PHONE_REQUEST_CODE = 1;
    private static PermissionListener mListener;
    public static void requestRuntimePermission(String[] permissions, PermissionListener listener){
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null) {
            return;
        }
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {

                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(topActivity,permissionList.toArray(new String[permissionList.size()]),CALL_PHONE_REQUEST_CODE);
        }else {
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CALL_PHONE_REQUEST_CODE:
                if (grantResults.length > 0) {
                    List<String> deniedList = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            deniedList.add(permissions[i]);
                        }
                    }
                    if (deniedList.isEmpty()) {
                        mListener.onGranted();
                    }else {
                        mListener.onDenied(deniedList);
                    }
                }
                break;
            default:
                break;
        }
    }
}
