package com.chevy.tp_android.entities;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.chevy.tp_android.R;

public class Personnage {

    private Bitmap img;
    private Rect mRectangle = null;
    private int mX;
    private int mY;
    private int size_width;
    private int size_height;
    private int vitesse = 100;

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public Personnage(Context ctx) {
        mRectangle = new Rect();

        Bitmap tileset = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.tileset);
        size_width = tileset.getWidth()/6;
        size_height = tileset.getHeight();

        int tile_x = 0;
        int tile_y = 0;

        img = Bitmap.createBitmap(tileset, tile_x*size_width, tile_y, size_width, size_height);

    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Rect getmRectangle() {
        return mRectangle;
    }

    public void setmRectangle(Rect mRectangle) {
        this.mRectangle = mRectangle;
    }

    public int getmX() {
        return mX;
    }

    public void setmX(int mX) {
        this.mX = mX;
    }

    public int getmY() {
        return mY;
    }

    public void setmY(int mY) {
        this.mY = mY;
    }

    public int getSize_width() {
        return size_width;
    }

    public void setSize_width(int size_width) {
        this.size_width = size_width;
    }

    public int getSize_height() {
        return size_height;
    }

    public void setSize_height(int size_height) {
        this.size_height = size_height;
    }

    // Mettre à jour les coordonnées du perso
    public Rect putXAndY(int pX, int pY) {

        setmX(pX);
        setmY(pY);

        mRectangle.set(mX, mY, mX + size_width, mY + size_height);

        return mRectangle;
    }


    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(getImg(), null, getmRectangle(), null);
    }

}