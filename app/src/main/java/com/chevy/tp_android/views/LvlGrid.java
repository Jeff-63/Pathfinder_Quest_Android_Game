package com.chevy.tp_android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

public class LvlGrid extends GridLayout {

    private int[][] map;

    public LvlGrid(Context context) {
        super(context);
        setRowCount(7);
        setColumnCount(9);
        setRowOrderPreserved(true);
        setColumnOrderPreserved(true);

        map = new int[][]{
                {5, 5, 5, 5, 5, 5, 5, 5, 5}
                , {5, 4, 4, 4, 4, 5, 3, 4, 5}
                , {5, 4, 4, 4, 4, 5, 4, 2, 5}
                , {5, 3, 4, 4, 4, 4, 4, 4, 5}
                , {5, 4, 5, 4, 5, 5, 4, 4, 5}
                , {5, 0, 5, 4, 4, 4, 4, 4, 5}
                , {5, 5, 5, 5, 5, 5, 5, 5, 5}};

        for(int i = 0; i<map.length;i++)
        {
            for(int j = 0; j<map[i].length; j++)
            {
                int val = map[i][j];
                addView(new LvlTile(val == 8?true:false,val,context));
            }
        }
    }

    public LvlGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
