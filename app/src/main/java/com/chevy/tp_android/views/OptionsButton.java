package com.chevy.tp_android.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.chevy.tp_android.activities.MainActivity;
import com.chevy.tp_android.activities.MainMenu_activity;
import com.chevy.tp_android.R;

public class OptionsButton extends Button {

    Context ctx;

    public OptionsButton(Context context) {
        super(context);
        ctx = context;
        setText("MENU");
        setTextSize(15);
        Log.d("debug", "added optionsbutton");
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsClick();
            }
        });
    }

    public void onOptionsClick(){
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View optionsLayout = inflater.inflate(R.layout.alertdiag_options, null);
        Button btn_resume = optionsLayout.findViewById(R.id.btn_resume);
        Button btn_restart = optionsLayout.findViewById(R.id.btn_restart);
        Button btn_toMainMenu = optionsLayout.findViewById(R.id.btn_tomainmenu);

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setView(optionsLayout);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();

        btn_resume.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_restart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_toMainMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, MainMenu_activity.class);
                getContext().startActivity(intent);
                ((MainActivity)ctx).finish();
            }
        });

        dialog.show();

    }
}
