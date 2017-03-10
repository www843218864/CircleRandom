package com.bwie.uu.circlerandom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

/**
 * Created by 闫雷 on 2017/3/10.
 */
public class CustomView extends View {

    private TypedArray a;
    private int radius_out;
    private int radius_in;
    private int radius_out_color;
    private String text;
    private int textSize;
    private Paint paint;
    private Rect rect;

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCircle, 0, 0);
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.CustomCircle_radius_out:
                    radius_out = a.getInt(index, 100);

                    break;
                case R.styleable.CustomCircle_radius_in:
                    radius_in = a.getInt(index, 60);
                    break;
                case R.styleable.CustomCircle_radius_out_color:
                    radius_out_color = a.getColor(index, Color.BLUE);
                    break;
                case R.styleable.CustomCircle_text:
                    text = a.getString(index);
                    break;
                case R.styleable.CustomCircle_textSize:
                    int def = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 30, getResources().getDisplayMetrics());
                    textSize = a.getDimensionPixelSize(index, def);
                    break;

            }
        }
        paint = new Paint();
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int num = random.nextInt(9000) + 1000;
                text = num + "";
                postInvalidate();
            }
        });


    }

    public CustomView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(radius_out_color);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius_out, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius_in, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText(text,getMeasuredWidth()/2-rect.width()/2,getMeasuredHeight()/2+rect.height()/2,paint);
    }
}
