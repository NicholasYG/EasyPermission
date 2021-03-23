package com.gbs.easypermission.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gbs.easypermission.DialogClickListener;
import com.gbs.easypermission.DialogUtils;
import com.gbs.easypermission.R;
import com.gbs.easypermission.Utils;

/**
 *AndroidX库中提供的请求运行时权限的Api
 */
public class SecondActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> requestPermissionLauncher;
    private Button btn;
    private TextView text;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        text = findViewById(R.id.explain_text);
        text.setText(R.string.Android_x_text);
        btn = findViewById(R.id.btn);
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                Utils.callPhone(this);
            } else {
                Toast.makeText(this,"电话权限被拒绝",Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Utils.callPhone(this);
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                DialogUtils.showDialog(this, () -> requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE));

            } else {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
            }

        });
    }


}