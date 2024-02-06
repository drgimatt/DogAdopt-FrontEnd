package com.doggo.dogadopt;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;

import com.doggo.dogadopt.R;

public class AnimationUtility {
    private static AnimationUtility s_instance;
    private AnimationUtility() {
    }
    public static AnimationUtility getInstance() {
        if (s_instance == null)
            s_instance = new AnimationUtility();
        return s_instance;
    }


    private Context context;
    private AlertDialog loadingAnimDialog;

    public void initialize(Context ctx, LayoutInflater layoutInflater) {
        // Initialize AlertDialog
        this.context = ctx;
        // Loading Animation
        AlertDialog.Builder alert = new AlertDialog.Builder(this.context);
        //alert.setView(layoutInflater.inflate(R.layout.app_loading_animation, null));
        loadingAnimDialog = alert.create();
        loadingAnimDialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
        loadingAnimDialog.setCancelable(false);
    }

    public void startLoading() {
        loadingAnimDialog.show();
    }

    public void endLoading() {
        loadingAnimDialog.dismiss();
    }
}
