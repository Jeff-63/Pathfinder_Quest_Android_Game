package com.chevy.tp_android.views;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.chevy.tp_android.R;

import com.chevy.tp_android.gameflow.MapUtils;
import com.chevy.tp_android.layouts.MainLayout;

public class Wrapper extends LinearLayout {


    private LinearLayout ll, ll2;
    private MainLayout wrapperLevel;
    private ItemsAndOptions itemsAndOptions;
    private Context ctx;

    public Wrapper(Context context, int levelSelected, Handler handler) {
        super(context);
        ctx = context;
        setOrientation(HORIZONTAL);
        LayoutInflater inflater = LayoutInflater.from(context);
        View fullView = inflater.inflate(R.layout.wrapper_layout, this);

        ll = fullView.findViewById(R.id.layout_grid);
        ll2 = fullView.findViewById(R.id.layout_itemsandoptions);

        int[][]  map = MapUtils.getMapByFile("niveau"+ levelSelected +".txt",context);

        switch(levelSelected){ //On dessine la bonne carte du niveau passée en param du constructeur;
            default:
                wrapperLevel = new MainLayout(context, map, handler,this);
                break;
        }

        itemsAndOptions = new ItemsAndOptions(context);

        ll.addView(wrapperLevel);
        ll2.addView(itemsAndOptions);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
