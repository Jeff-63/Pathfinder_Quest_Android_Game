package com.chevy.tp_android.views;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

//classe doit contenir:
//trois boutons : RESUME/RESTART/MAIN MENU

public class OptionsView extends LinearLayout {

    private Button btn_resume, btn_restart, btn_mainmenu;

    public OptionsView(Context context) {
        super(context);
        btn_resume = new Button(context);
        btn_restart = new Button(context);
        btn_mainmenu = new Button(context);

        addView(btn_resume);
        addView(btn_restart);
        addView(btn_mainmenu);
    }
}
