package com.chevy.tp_android.layouts;

import android.content.Context;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.chevy.tp_android.views.LayerBg;
import com.chevy.tp_android.views.LayerCharacters;
import com.chevy.tp_android.views.LayerPath;
import com.chevy.tp_android.views.Wrapper;

public class MainLayout extends RelativeLayout {

    LayerBg bg;
    LayerCharacters lc;
    LayerPath lp;

    public MainLayout(Context context, int[][]  map, Handler handler, Wrapper wrapperLevel) {
        super(context);

        bg = new LayerBg(context,map);
        lc = new LayerCharacters(context,map, handler, wrapperLevel);
        lp = new LayerPath(context,map, lc);
        addView(bg);
        addView(lc);
        addView(lp);
    }
}