package com.sample.wednesday.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.sample.wednesday.R;

/**
 * Created by ANMOL$ on 9/6/2019$.
 */
public class Task {

    public static void showWarning(final Context context, String title) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.warning_box);
        dialog.setCancelable(false);
        TextView tv_title = dialog.findViewById(R.id.textTitle);
        Button task_yes = dialog.findViewById(R.id.btn_yes);
        task_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_title.setText(title);
        dialog.show();
    }
}
