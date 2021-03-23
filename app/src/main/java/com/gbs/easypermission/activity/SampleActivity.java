package com.gbs.easypermission.activity;
import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.gbs.easypermission.ActivityCollector;
import com.gbs.easypermission.PermissionListener;
import com.gbs.easypermission.R;
import com.gbs.easypermission.Utils;
import java.util.List;


public class SampleActivity extends BaseActivity {

    private Button btn;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        text = findViewById(R.id.explain_text);
        text.setText(R.string.sample_text);
        btn = findViewById(R.id.btn);
        ActivityCollector.addActivity(this);
        btn.setOnClickListener(view -> requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE}, new PermissionListener() {
            @Override
            public void onGranted() {
                Utils.callPhone(SampleActivity.this);
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for (String permission : deniedPermission) {
                Toast.makeText(SampleActivity.this,permission + "权限被拒绝了", Toast.LENGTH_SHORT).show();
            }
            }
        }));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}