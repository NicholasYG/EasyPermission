package com.gbs.easypermission.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gbs.easypermission.DialogClickListener
import com.gbs.easypermission.DialogUtils
import com.gbs.easypermission.R
import com.gbs.easypermission.Utils

/**
 * 使用AndroidX包，申请运行时权限 kotlin版
 */

@RequiresApi(Build.VERSION_CODES.M)
class AndroidXPermissionActivity : AppCompatActivity() {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var btn: Button
    private lateinit var text: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_x_permission)
        btn = findViewById(R.id.btn)
        text = findViewById(R.id.explain_text)
        text.setText(R.string.android_x_kotlin_text)
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                Utils.callPhone(this)
            } else {
                Toast.makeText(this, "权限被拒了", Toast.LENGTH_SHORT).show()
            }
        }
        btn.setOnClickListener {
            requestStoragePermission()
        }

    }

    private fun requestStoragePermission() {
        when {
            ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED -> {
                Utils.callPhone(this)
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) -> {
                DialogUtils.showDialog(this) {
                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                }
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        }
    }
}