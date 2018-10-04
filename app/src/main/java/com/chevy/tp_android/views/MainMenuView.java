package com.chevy.tp_android.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chevy.tp_android.activities.MainActivity;
import com.chevy.tp_android.R;

public class MainMenuView extends RelativeLayout {

    public MainMenuView(final Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View fullView = inflater.inflate(R.layout.mainmenu_layout, this);
        Button btn_startgame = fullView.findViewById(R.id.btn_startgame);
        Button btn_stageselect = fullView.findViewById(R.id.btn_stageselect);

        btn_startgame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                getContext().startActivity(intent);
            }
        });

        btn_stageselect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
