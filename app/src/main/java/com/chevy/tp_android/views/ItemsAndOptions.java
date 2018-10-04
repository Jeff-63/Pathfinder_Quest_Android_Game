package com.chevy.tp_android.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.chevy.tp_android.R;

import java.util.ArrayList;

//cette classe doit contenir:
//conteneur de vues allant à droite de la LvlGrid;
//contient inventaire d'items, info vie du joueur et bouton menu/options
//transmet le onTouch info

public class ItemsAndOptions extends LinearLayout {


    LinearLayout ll = null, ll2 = null, ll3 = null;
    ImageView v1, v2, v3, imgLife;
    private LifeView lifeView;
    private OptionsButton optionsButton;
    private ArrayList<ItemView> itemList;
    private Rect frames;
    Context ctx;

    public ItemsAndOptions(Context context) {
        super(context);
        ctx = context;
        setBackgroundColor(Color.GRAY);

        LayoutInflater inflater = LayoutInflater.from(context);
        View fullView = inflater.inflate(R.layout.itemsandoptions_layout, this);

        ll = fullView.findViewById(R.id.ll_itemsandoptions);
        imgLife = fullView.findViewById(R.id.ll_lifeview);
        ll3 = fullView.findViewById(R.id.ll_optionsbutton);

        v1 = fullView.findViewById(R.id.view1);
        v2 = fullView.findViewById(R.id.view2);
        v3 = fullView.findViewById(R.id.view3);
        v1.setImageResource(R.drawable.frame_chain);
        v2.setImageResource(R.drawable.frame_chain);
        v3.setImageResource(R.drawable.frame_chain);
        imgLife.setImageResource(R.drawable.halfheart);

        itemList = new ArrayList<>();
        itemList.add(null);
        itemList.add(null);
        itemList.add(null);
        lifeView = new LifeView(context);
        optionsButton = new OptionsButton(context);

        updateInventorySlot(0, true, new ItemView(ctx, 2));
        updateInventorySlot(1, true, new ItemView(ctx, 3));
        updateInventorySlot(2, true, new ItemView(ctx, 1));
        v1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemList.get(0) != null){
                    updateInventorySlot(0, false, itemList.get(0));
                }
            }
        });
        v2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemList.get(1) != null){
                    updateInventorySlot(1, false, itemList.get(1));
                }
            }
        });
        v3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemList.get(2) != null){
                    updateInventorySlot(2, false, itemList.get(2));
                }
            }
        });

        ll3.addView(optionsButton);
    }

    public void updateInventorySlot(int position, boolean isItemAdded,ItemView item) {
        switch (position) {
            case 0:
                if (isItemAdded) {
                    addToItemList(position, item);
                    v1.setImageResource(R.drawable.item1);
                } else {
                    //activateeffect(item.getEffect());
                    removeFromItemList(position);
                    v1.setImageResource(R.drawable.frame_chain);
                }
                break;
            case 1:
                if (isItemAdded) {
                    addToItemList(position, item);
                    v2.setImageResource(R.drawable.item2);
                } else {
                    //activateeffect(item.getEffect());
                    removeFromItemList(position);
                    v2.setImageResource(R.drawable.frame_chain);
                }
                break;
            case 2:
                if (isItemAdded) {
                    addToItemList(position, item);
                    v3.setImageResource(R.drawable.item3);
                } else {
                    //activateeffect(item.getEffect());
                    removeFromItemList(position);
                    v3.setImageResource(R.drawable.frame_chain);
                }
                break;
            default:
                break;
        }
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void addToItemList(int index, ItemView item) {
        itemList.set(index, item);
    }

    public void removeFromItemList(int index) {
        itemList.set(index, null);
    }
}
