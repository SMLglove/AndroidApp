/*
 This code is a modified version of the Vertical-SeekBar-Android project written by Selvakumat.
 This code can be found at https://github.com/AndroSelva/Vertical-SeekBar-Android.
 */

package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by stuart on 4/12/2015.
 */
public class Finger extends SeekBar {
    private float angle = 90;

    public Finger(Context context) {
        super(context);
    }

    public Finger(Context context, AttributeSet attributes, int defStyle) {
        super(context, attributes, defStyle);
    }

    public Finger(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas canvas) {
        canvas.rotate(-angle);
        canvas.translate(-getHeight(), 0);

        super.onDraw(canvas);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!isEnabled()) return false;

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                setProgress(getMax() - (int)(getMax() * event.getY() / getHeight()));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;
    }

    public void changeAngle(float delta) {
        angle += delta;
    }
}
