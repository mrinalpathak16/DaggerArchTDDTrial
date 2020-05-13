package com.example.daggerarchtddtrial.activities.common;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;

import com.example.daggerarchtddtrial.R;

import javax.inject.Inject;

public class UtilWorks {
    private Context context;
    private AlertDialog progressDialog;

    @Inject
    public UtilWorks(Context context) {
        this.context = context;
    }

    @UiThread
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new AlertDialog.Builder(context)
                    .setView(R.layout.layout_progress_dialog)
                    .setCancelable(false)
                    .create();
        }
        if (!isProgressDialogShowing()){
            progressDialog.show();
        }
    }

    @UiThread
    public void dismissProgressDialog() {
        if (progressDialog != null && isProgressDialogShowing()){
            progressDialog.dismiss();
        }
    }

    private boolean isProgressDialogShowing() {
        return progressDialog.isShowing();
    }

}
