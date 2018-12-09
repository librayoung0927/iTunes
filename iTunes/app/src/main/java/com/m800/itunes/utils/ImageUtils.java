package com.m800.itunes.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

public class ImageUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();
    private static ImageUtils mInstance;

    public static ImageUtils getInstance() {
        if (null == mInstance) {
            mInstance = new ImageUtils();
        }
        return mInstance;
    }

    public static class RoundedCornersTransform implements Transformation {
        float radius;

        public RoundedCornersTransform(float radius) {
            this.radius = radius;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            //int size = Math.min(source.getWidth(), source.getHeight());

            /*int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());*/
            Bitmap bitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            //radius = size / 8f;
            //radius = 8f;
            canvas.drawRoundRect(new RectF(0, 0, source.getWidth(), source.getHeight()), radius, radius, paint);
            source.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "rounded_corners";
        }
    }

}
