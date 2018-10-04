package com.chevy.tp_android.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.chevy.tp_android.R;
import com.chevy.tp_android.services.PlayTheme;

public class MainMenu_activity extends Activity {

    Context ctx;
    Button btn_startgame;
    Button btn_stageselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mainmenu_layout);

        ctx = this;
        btn_startgame = findViewById(R.id.btn_startgame);
        btn_stageselect = findViewById(R.id.btn_stageselect);

        btn_startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ctx, MainActivity.class));
                finish();
            }
        });
    }
}
