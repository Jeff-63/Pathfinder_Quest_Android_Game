package com.chevy.tp_android.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.chevy.tp_android.R;

import com.chevy.tp_android.entities.Item;

import static com.chevy.tp_android.R.drawable.frame_chain;

//but: conteneur d'item;
//cette classe doit contenir:
//une bordure (image);
//un ImageView et une instance d'item disponible pour insérer l'image de l'item;
//onTouch transmettant l'id de l'item

public class ItemView extends View {

    private Bitmap border;
    private Bitmap itemImage;
    private Item item;
    private Rect frame;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, int itemId) {
        super(context);
        border = BitmapFactory.decodeResource(getResources(), frame_chain);

        item = new Item(context, itemId);

        switch (itemId) {
            case 1:
                itemImage = BitmapFactory.decodeResource(getResources(), R.drawable.item1);
                break;
            case 2:
                itemImage = BitmapFactory.decodeResource(getResources(), R.drawable.item2);
                break;
            case 3:
                itemImage = BitmapFactory.decodeResource(getResources(), R.drawable.item3);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(border, 0, 0, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (item.getEffect()) {
            case SHIELD:
                //shieldEffect();
                break;
            case HPGAIN:
                //hpgainEffect();
                break;
            case ATKPLUS:
                //atkplusEffect();
                break;
            case EXPLOSION:
                //explosionEffect();
                break;
            default:
                break;
        }
        return true;
    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(50, 50);
//    }

    public Bitmap getBorder() {
        return border;
    }

    public void setBorder(Bitmap border) {
        this.border = border;
    }

    public Bitmap getItemImage() {
        return itemImage;
    }

    public void setItemImage(Bitmap itemImage) {
        this.itemImage = itemImage;
    }
}
