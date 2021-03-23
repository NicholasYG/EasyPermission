package com.gbs.easypermission;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author gdy
 * @date 3/18/21
 */
public class DialogUtils {


    public static void showDialog(Context context,DialogClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_ok, (dialog, id) -> {
                    listener.ok();
                });
        builder.create().show();
    }
}
