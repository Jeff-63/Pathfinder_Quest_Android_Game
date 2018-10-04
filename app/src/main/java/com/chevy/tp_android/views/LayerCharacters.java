package com.chevy.tp_android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.chevy.tp_android.activities.MainActivity;

import java.util.ArrayList;

import com.chevy.tp_android.entities.Ennemie;
import com.chevy.tp_android.entities.Personnage;
import com.chevy.tp_android.gameflow.MapUtils;

public class LayerCharacters extends View implements Runnable {

    private Personnage p;
    private ArrayList<Ennemie> ennemies;
    private ArrayList<Rect> murs, items;
    private ArrayList<Point> path;
    private int[][] map;
    private boolean start = false;
    private Context ctx;

    private int displayW;
    private int displayH;

    private int totalW;
    private int totalH;

    private double coeffW;
    private double coeffH;

    private Wrapper wrapperLevel;

    int iteration = 0;

    @Override
    public Handler getHandler() {
        return handler;
    }

    private Handler handler;

    public LayerCharacters(Context context, int[][] map, Handler handler, Wrapper wrapperLevel) {
        super(context);
        ctx = context;

        p = new Personnage(ctx);
        ennemies = new ArrayList<>();
        murs = new ArrayList<>();
        path = new ArrayList<>();

        this.map = map;
        this.handler = handler;
        this.wrapperLevel = wrapperLevel;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        displayW = getWidth();
        displayH = getHeight();

        totalW = MapUtils.getImg_width(ctx) * 9;
        totalH = MapUtils.getImg_height(ctx) * 7;

        coeffW = (double) displayW / totalW;
        coeffH = (double) displayH / totalH;


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int val = map[i][j];
                Rect position;

                if (val == 0) {
                    position = new Rect((int) ((j * MapUtils.getImg_width(ctx)) * coeffW), (int) ((i * MapUtils.getImg_height(ctx)) * coeffH), (int) (((j * MapUtils.getImg_width(ctx)) + MapUtils.getImg_width(ctx)) * coeffW), (int) (((i * MapUtils.getImg_height(ctx)) + MapUtils.getImg_height(ctx)) * coeffH));
                    p.setmRectangle(position);

                    p.setSize_width((int)((MapUtils.getImg_width(ctx) * coeffW)*0.8));
                    p.setSize_height((int) ((MapUtils.getImg_height(ctx) * coeffH)*0.8));
                } else if (val == 3) {

                    Ennemie e = new Ennemie(ctx);
                    position = new Rect((int) ((j * MapUtils.getImg_width(ctx)) * coeffW), (int) ((i * MapUtils.getImg_height(ctx)) * coeffH), (int) (((j * MapUtils.getImg_width(ctx)) + MapUtils.getImg_width(ctx)) * coeffW), (int) (((i * MapUtils.getImg_height(ctx)) + MapUtils.getImg_height(ctx)) * coeffH));

                    e.setmRectangle(position);
                    e.setSize_width((int)((MapUtils.getImg_width(ctx) * coeffW)*0.8));
                    e.setSize_height((int) ((MapUtils.getImg_height(ctx) * coeffH)*0.8));

                    Rect depart = new Rect((int) ((j * MapUtils.getImg_width(ctx)) * coeffW), (int) ((i * MapUtils.getImg_height(ctx)) * coeffH), (int) (((j * MapUtils.getImg_width(ctx)) + MapUtils.getImg_width(ctx)) * coeffW), (int) (((i * MapUtils.getImg_height(ctx)) + MapUtils.getImg_height(ctx)) * coeffH));
                    e.setmDepart(depart);
                    ennemies.add(e);
                    ennemies.trimToSize();

                } else if (val == 5) {
                    position = new Rect((int) ((j * MapUtils.getImg_width(ctx)) * coeffW), (int) ((i * MapUtils.getImg_height(ctx)) * coeffH), (int) (((j * MapUtils.getImg_width(ctx)) + MapUtils.getImg_width(ctx)) * coeffW), (int) (((i * MapUtils.getImg_height(ctx)) + MapUtils.getImg_height(ctx)) * coeffH));

                    murs.add(position);
                } else if (val == 8) {
                    ennemies.get(0).setmDestination(new Rect((int) ((j * MapUtils.getImg_width(ctx)) * coeffW), (int) ((i * MapUtils.getImg_height(ctx)) * coeffH), (int) (((j * MapUtils.getImg_width(ctx)) + MapUtils.getImg_width(ctx)) * coeffW), (int) (((i * MapUtils.getImg_height(ctx)) + MapUtils.getImg_height(ctx)) * coeffH)));
                    ennemies.get(0).setMovesOnX(true);
                } else if (val == 9) {
                    ennemies.get(1).setmDestination(new Rect((int) ((j * MapUtils.getImg_width(ctx))*coeffW), (int) ((i * MapUtils.getImg_height(ctx))*coeffH), (int) (((j * MapUtils.getImg_width(ctx)) + MapUtils.getImg_width(ctx))*coeffW), (int) (((i *MapUtils.getImg_height(ctx)) + MapUtils.getImg_height(ctx))*coeffH)));
                    ennemies.get(1).setMovesOnX(false);
                }

            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        p.draw(canvas);
        for (Ennemie e : ennemies) {
            e.draw(canvas);
        }
    }

    public Personnage getPersonnage() {
        return p;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    @Override
    public void run() {

        for(Ennemie e : ennemies){
            if(e.isMovesOnX()) {
                e.putXAndY(((float)e.getmRectangle().left) + (10*e.getSens()),e.getmRectangle().top);
                if (e.getmRectangle().left > e.getmDestination().left || e.getmRectangle().left < e.getmDepart().left ) {
                    e.switchSens();
                }
            }
            else{
                e.putXAndY(e.getmRectangle().left,e.getmRectangle().top+ (10*e.getSens()));
                if (e.getmRectangle().top > e.getmDestination().top || e.getmRectangle().top < e.getmDepart().top) {
                    e.switchSens();
                }
            }
            checkMonsterCollision(e);
        }
        p.putXAndY((int) (path.get(iteration).x - ((MapUtils.getImg_width(ctx) * coeffW) / 3)), (int) (path.get(iteration).y - ((MapUtils.getImg_height(ctx) * coeffH) / 2)));
        invalidate();
        iteration++;
        if (iteration < path.size()) {
            handler.postDelayed(this, p.getVitesse());
        } else {
            handler.removeCallbacks(this);
            Toast.makeText(ctx, "Arrivé", Toast.LENGTH_SHORT).show();
            ((MainActivity) ctx).setContentView(new Wrapper(ctx, (int) (Math.random() * 4 + 1), handler));
            invalidate();
        }
    }

    public boolean checkMonsterCollision(Ennemie e){
        boolean result = false;

        if(e.getmRectangle().intersects(p.getmRectangle().left, p.getmRectangle().top, p.getmRectangle().right, p.getmRectangle().bottom)){
            Toast.makeText(ctx, "U GOT HIT BOI", Toast.LENGTH_SHORT).show();

        }

        return result;
    }
}