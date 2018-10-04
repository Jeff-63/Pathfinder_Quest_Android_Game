package com.chevy.tp_android.views;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


import com.chevy.tp_android.R;

import java.util.ArrayList;

import com.chevy.tp_android.entities.Personnage;
import com.chevy.tp_android.gameflow.MapUtils;

public class LayerPath extends View {

    private ArrayList<Rect> murs;
    private int[][] map;
    private Context ctx;
    private Path path;
    private Paint paint;
    private Personnage p;
    private Rect finish;
    private boolean touchFinish = false;
    ArrayList<Point> pathCharacter;
    AlertDialog.Builder builder;
    LayerCharacters lc;


    public LayerPath(Context context, int[][] map, LayerCharacters lc) {
        super(context);

        murs = new ArrayList<>();
        this.map = map;
        ctx = context;
        this.p = lc.getPersonnage();
        this.pathCharacter = lc.getPath();
        this.lc = lc;

        paint = new Paint();
        paint.setColor(Color.argb(125, 125, 20, 0));
        paint.setStrokeWidth(MapUtils.getImg_width(ctx) / 6);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int displayW = getWidth();
        int displayH = getHeight();

        int totalW = MapUtils.getImg_width(ctx) * 9;
        int totalH = MapUtils.getImg_height(ctx) * 7;

        double coeffW = (double) displayW / totalW;
        double coeffH = (double) displayH / totalH;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int val = map[i][j];
                Rect position;
                if (val == 5) {
                    position = new Rect((int) ((j * MapUtils.getImg_width(ctx)) * coeffW), (int) ((i * MapUtils.getImg_height(ctx)) * coeffH), (int) (((j * MapUtils.getImg_width(ctx)) + MapUtils.getImg_width(ctx)) * coeffW), (int) (((i * MapUtils.getImg_height(ctx)) + MapUtils.getImg_height(ctx)) * coeffH));
                    murs.add(position);
                } else if (val == 2) {
                    position = new Rect((int) ((j * MapUtils.getImg_width(ctx)) * coeffW), (int) ((i * MapUtils.getImg_height(ctx)) * coeffH), (int) (((j * MapUtils.getImg_width(ctx)) + MapUtils.getImg_width(ctx)) * coeffW), (int) (((i * MapUtils.getImg_height(ctx)) + MapUtils.getImg_height(ctx)) * coeffH));
                    finish = position;
                }
            }
        }
        pathCharacter.add(new Point(p.getmRectangle().centerX(), p.getmRectangle().centerY()));
        path.moveTo(p.getmRectangle().centerX(), p.getmRectangle().centerY());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x_touch = (int) event.getX();
        int y_touch = (int) event.getY();
        int pathSize = pathCharacter.size();

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            if (!touchFinish) {
                boolean collision = false;
                for (int i = 0; i < murs.size() && !collision; i++) {
                    if (murs.get(i).contains(x_touch, y_touch)) {
                        collision = true;
                    }
                }
                if (!collision) {
                    if (pathSize >=1) {
                        if((x_touch - 25 <= pathCharacter.get(pathSize - 1).x)&&(pathCharacter.get(pathSize - 1).x <= x_touch +25))
                        path.lineTo(x_touch, y_touch);
                        pathCharacter.add(new Point(x_touch, y_touch));
                        invalidate();
                    }
                }

                if (finish.contains(x_touch, y_touch)) {
                    touchFinish = true;

                    builder = new AlertDialog.Builder(ctx);
                    LayoutInflater inflater = LayoutInflater.from(ctx);
                    View test = inflater.inflate(R.layout.beginpathdialog, null);
                    /*View test = fullView.findViewById(R.id.layoutbeginpath);
                    ((ViewGroup)test.getParent()).removeView(test);*/
                    builder.setView(test);

                    Button btnbegin = test.findViewById(R.id.btn_begin);
                    Button btnreset = test.findViewById(R.id.btn_reset);

                    final AlertDialog dialog = builder.create();

                    btnbegin.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            lc.run();
                            path = new Path();
                            path.moveTo(p.getmRectangle().centerX(), p.getmRectangle().centerY());
                            invalidate();
                            dialog.dismiss();
                        }
                    });

                    btnreset.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            touchFinish = false;
                            path = new Path();
                            pathCharacter.clear();
                            pathCharacter.add(new Point(p.getmRectangle().centerX(), p.getmRectangle().centerY()));
                            path.moveTo(p.getmRectangle().centerX(), p.getmRectangle().centerY());
                            invalidate();
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }
}
