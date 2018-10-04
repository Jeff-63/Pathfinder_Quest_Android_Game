package com.chevy.tp_android.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.chevy.tp_android.services.PlayTheme;
import com.chevy.tp_android.views.Wrapper;

public class MainActivity extends Activity {

    private Wrapper wrapper;
    private Handler handler = new Handler();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        wrapper = new Wrapper(this, (int) ( Math.random() * 4 + 1),handler);
        setContentView(wrapper);

        intent = new Intent(this, PlayTheme.class);
    }

    @Override
    protected void onResume() {
        startService(intent);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        stopService(intent);
        super.onDestroy();
    }

    /*public void stageTransition(int nextLevel){
        final Wrapper newStage = new Wrapper(this, nextLevel,handler);

        setContentView(newStage);

    }*/

}
