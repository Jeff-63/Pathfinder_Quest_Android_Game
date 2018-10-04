package com.chevy.tp_android.gameflow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.chevy.tp_android.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapUtils {

    public static int[][] getMapByFile(String fileName, Context ctx) {
        InputStreamReader input = null;
        BufferedReader reader = null;
        int[][] map = new int[7][9];
        InputStream mInput = null;
        try {

            mInput = ctx.getAssets().open(fileName);

            input = new InputStreamReader(mInput);
            reader = new BufferedReader(input);
            int j = 0;

            String c;

            while ((c = reader.readLine())!= null) {

                char[] tab = c.toCharArray();

                for (int i = 0; i < tab.length; i++) {
                    map[j][i] = (tab[i] - '0');
                }
                j++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null)
                try {
                    input.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return map;
    }

    public static int getImg_width(Context ctx)
    {
        Bitmap tileset = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.tileset);
        return tileset.getWidth()/6;
    }

    public static int getImg_height(Context ctx)
    {
        Bitmap tileset = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.tileset);
        return tileset.getHeight();
    }
}

