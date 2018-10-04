package com.chevy.tp_android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.LinearLayout;
import android.widget.TextView;

//classe doit contenir les infos de HP du joueur

public class LifeView extends LinearLayout {

    private int hitPointsCurrent, hitPointsMax;
    private TextView tvHitPoints;

    public LifeView(Context context) {
        super(context);

        tvHitPoints = new TextView(context);
        addView(tvHitPoints);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        tvHitPoints.setText("HP: " + hitPointsCurrent + "/" + hitPointsMax);
    }

    public void hpGain(int amount) {
        hitPointsCurrent += amount;

        if (hitPointsCurrent > hitPointsMax)
            hitPointsCurrent = hitPointsMax;

        postInvalidate();
    }

    public void hpLoss(int amount) {
        hitPointsCurrent -= amount;
        postInvalidate();
        //checkLoseConditionHP();
    }

    public int getHitPointsCurrent() {
        return hitPointsCurrent;
    }

    public void setHitPointsCurrent(int hitPointsCurrent) {
        this.hitPointsCurrent = hitPointsCurrent;
    }

    public int getHitPointsMax() {
        return hitPointsMax;
    }

    public void setHitPointsMax(int hitPointsMax) {
        this.hitPointsMax = hitPointsMax;
    }

    public TextView getTvHitPoints() {
        return tvHitPoints;
    }

    public void setTvHitPoints(TextView tvHitPoints) {
        this.tvHitPoints = tvHitPoints;
    }
}
