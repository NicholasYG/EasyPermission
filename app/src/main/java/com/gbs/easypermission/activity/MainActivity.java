package com.gbs.easypermission.activity;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.gbs.easypermission.ActivityCollector;
import com.gbs.easypermission.DialogClickListener;
import com.gbs.easypermission.DialogUtils;
import com.gbs.easypermission.R;
import com.gbs.easypermission.Utils;

import static com.gbs.easypermission.activity.BaseActivity.CALL_PHONE_REQUEST_CODE;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void callPhone(View view) {
        //简单封装
//        List<String> permissionList = new ArrayList<>();
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.CALL_PHONE);
//        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//        if (!permissionList.isEmpty()) {
//            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]),CALL_PHONE_REQUEST_CODE);
//        }else {
//            call();
//        }
        //请求权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)) {
                    DialogUtils.showDialog(this, new DialogClickListener() {
                        @Override
                        public void ok() {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},CALL_PHONE_REQUEST_CODE);
                        }
                    });
            }else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},CALL_PHONE_REQUEST_CODE);
            }
        }else {
            Utils.callPhone(this);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CALL_PHONE_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Utils.callPhone(this);
                }else {
                    Toast.makeText(MainActivity.this,"电话权限被拒绝",Toast.LENGTH_SHORT).show();
                }
//                if (grantResults.length > 0) {
//
//                    for (int i = 0; i < grantResults.length; i++) {
//                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
//                            Toast.makeText(MainActivity.this,permissions[i] + "权限被拒绝了---",Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    }
//                    call();
//                }
                break;
            default:
                break;
        }
    }

    public void androidXActivity(View view) {
        startActivity(new Intent(this,SecondActivity.class));
    }

    public void androidXKotlinActivity(View view) {
        startActivity(new Intent(this,AndroidXPermissionActivity.class));
    }

    public void sampleActivity(View view) {
        startActivity(new Intent(this,SampleActivity.class));
    }
}