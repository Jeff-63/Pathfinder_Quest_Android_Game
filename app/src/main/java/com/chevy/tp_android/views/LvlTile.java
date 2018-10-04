package com.chevy.tp_android.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.os.Build;
import android.widget.ImageView;

import com.chevy.tp_android.R;

//Classe doit contenir:
//image d'une tuile;
//touchlistener renvoyant son id de tuile correspondant à sa place dans la LvlGrid (à condition que le TileType le permette);
//

public class LvlTile extends ImageView {

    private boolean isWall;
    private int x, y, tilePositionX, tilePositionY, img_height, img_width;
    private Bitmap img, tileset;
    private RectF bounds;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LvlTile(boolean isWall, int tilePositionX, Context ctx) {
        super(ctx);
        this.isWall = isWall;
        this.tilePositionX = tilePositionX;
        this.tilePositionY = 0;

        tileset  = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.tileset);
        img_width = tileset.getWidth()/6;
        img_height = tileset.getHeight();

        this.x = this.tilePositionX*img_width;
        this.y = this.tilePositionY*img_height;

        img = Bitmap.createBitmap(tileset, x, y, img_width, img_height);
        //bounds = new RectF(x,y,x+img_width, y+img_height);

        setImageBitmap(img);
    }

    public boolean isWall() {
        return isWall;
    }
}
