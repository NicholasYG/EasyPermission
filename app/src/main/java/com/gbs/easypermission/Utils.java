package com.gbs.easypermission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.gbs.easypermission.activity.BaseActivity;

import java.util.List;

/**
 * @author gdy
 * @date 3/16/21
 */
public class Utils {
//    public void test(Context context){
//        BaseActivity.requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION}, new PermissionListener() {
//            @Override
//            public void onGranted() {
//                Toast.makeText(context,"权限全部通过",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDenied(List<String> deniedPermission) {
//                for (String permission : deniedPermission) {
//                    Toast.makeText(context,permission + "权限被拒绝了",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    public static void callPhone(Context context){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://12345678"));
        context.startActivity(intent);
    }


}
