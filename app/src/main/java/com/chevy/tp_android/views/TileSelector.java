package com.chevy.tp_android.views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;

public class TileSelector extends ViewGroup {

    private HorizontalScrollView scrollSelector;
    private ArrayList<LvlTile> tiles;
    int rowCount, columnCount, tilesetWidth, tilesetHeight; //infos du tileset (ex: floor6x2.png veut dire tileset de plancher 6x2)


    public TileSelector(Context context) {
        super(context);

        tiles = new ArrayList<>();
        //On va chercher les tiles a partir du tileset
        for(int row = 0; row < rowCount; row++){
            for(int column = 0; column < columnCount; column++){
                LvlTile t = new LvlTile(true, column * tilesetWidth, context);
                tiles.add(t);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
