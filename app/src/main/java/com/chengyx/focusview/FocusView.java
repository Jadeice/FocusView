package com.chengyx.focusview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class FocusView extends FrameLayout {
    private static final String TAG = "FocusView";
    private View newView;

    public FocusView(Context context) {
        super(context);
        init(null, 0);
    }

    public FocusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FocusView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
//        setBackgroundResource(R.drawable.sharedow);
        setClipChildren(false);
        setClipToPadding(false);
        newView = new View(getContext());
        LayoutParams params = new LayoutParams(100, 100);
        newView.setLayoutParams(params);
        newView.setBackgroundResource(R.drawable.sharedow);
        addView(newView);
    }

    public void flyBorder(View targetView) {
        if (!isShown()) setVisibility(VISIBLE);
        Log.e(TAG, "start fly border.");
        int targetViewHeight = targetView.getHeight();
        int targetViewWidth = targetView.getWidth();


        int[] targetLoc = new int[2];
        targetView.getLocationOnScreen(targetLoc);
        int targetX = targetLoc[0];
        int targetY = targetLoc[1];


        int width = newView.getWidth();
        int height = newView.getHeight();

        int[] currLoc = new int[2];
        newView.getLocationOnScreen(currLoc);
        int x = currLoc[0];
        int y = currLoc[1];

        ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", x, targetX);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", x, targetY);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(newView, "scaleX", 1f, targetViewWidth / width);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(newView, "scaleY", 1f, targetViewHeight / height);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationX).with(translationY).with(scaleX).with(scaleY);
        animSet.setDuration(5000);
        animSet.start();

        postInvalidate();

        Log.e(TAG, "targetViewHeight=" + targetViewHeight
                + ", targetViewWidth=" + targetViewWidth
                + ", targetX=" + targetX
                + ", targetY=" + targetY
                + ", width=" + width
                + ", height=" + height
                + ", x=" + x
                + ", y=" + y
        );

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    //
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int width;
//        int height;
//        if (widthMode == MeasureSpec.EXACTLY) {
//            // 明确了宽高
//            width = widthSize;
//        } else {
//            // 使用了wrap_content
//            width = getPaddingLeft() + getPaddingRight();
//        }
//
//        if (heightMode == MeasureSpec.EXACTLY) {
//            // 明确了宽高
//            height = heightSize;
//        } else {
//            height = getPaddingTop() + getPaddingBottom();
//        }
//
//        setMeasuredDimension(width, height);
//    }
}
