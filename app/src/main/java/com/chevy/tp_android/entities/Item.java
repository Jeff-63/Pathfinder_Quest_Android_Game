package com.chevy.tp_android.entities;

import android.content.Context;
import android.widget.LinearLayout;

import com.chevy.tp_android.gameflow.Constants;

public class Item extends LinearLayout {

    private int id;
    private Constants.ItemEffect effect;
    private String name;

    public Item(Context context) {
        super(context);
    }

    public Item(Context context, int id) {
        super(context);
        this.id = id;

        switch (this.id){
            case 1:
                effect = Constants.ItemEffect.JUMP;
                setName("Feather");
                break;
            case 2:
                effect = Constants.ItemEffect.HPGAIN;
                setName("HP Potion");
                break;
            case 3:
                effect = Constants.ItemEffect.SHIELD;
                setName("Shield");
                break;
            case 4:
                break;
            default:
                break;

        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Constants.ItemEffect getEffect() {
        return effect;
    }

    public void setEffect(Constants.ItemEffect effect) {
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
