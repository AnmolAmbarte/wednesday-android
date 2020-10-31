package com.sample.wednesday.ui;

import android.app.ProgressDialog;
import android.content.Context;

class ShowHideProgress {

    private ProgressDialog pDialog;


    public ShowHideProgress(Context context) {
        pDialog = new ProgressDialog(context);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
    }

    public void showDialog(String data) {
        if (!pDialog.isShowing()) {
            pDialog.setMessage(data);
            pDialog.show();
        }
    }

    public void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }

    }


}
