package com.chevy.tp_android.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import com.chevy.tp_android.R;

public class LayerBg extends View {

    private Bitmap img, tileset;
    int img_width, img_height;
    private int[][] map;

    public LayerBg(Context context, int[][] map) {
        super(context);

        tileset = BitmapFactory.decodeResource(context.getResources(), R.drawable.tileset);
        img_width = tileset.getWidth()/6;
        img_height = tileset.getHeight();
        this.map = map;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int displayW = (int) (getWidth());
        int displayH = getHeight();

        int totalW = img_width*9;
        int totalH = img_height*7;

        double coeffW = (double)displayW/totalW;
        double coeffH = (double)displayH/totalH;


        for(int i = 0; i<map.length;i++)
        {
            for(int j = 0; j<map[i].length; j++)
            {
                int val = map[i][j];

                if(val == 8 || val == 9){val = 4;}
                img = Bitmap.createBitmap(tileset, ((val==0?4:val==3?4:val)*img_width), 0, img_width, img_height);

                Rect position = new Rect((int) ((j*img_width)*coeffW), (int) ((i*img_height)*coeffH), (int) (((j*img_width)+img_width)*coeffW), (int) (((i*img_height)+img_height)*coeffH));
                canvas.drawBitmap(img,null,position,null);

            }
        }
    }
}