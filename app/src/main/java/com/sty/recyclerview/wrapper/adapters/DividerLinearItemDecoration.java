package com.sty.recyclerview.wrapper.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by shity on 2017/9/27/0027.
 */

public class DividerLinearItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = DividerLinearItemDecoration.class.getSimpleName();

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;
    private int mOrientation;

    private int dividerLeftOffset;  //vertical方向时水平线的左偏移量
    private boolean isLastLineFill; //最后一行分隔线是否填满Item

    public DividerLinearItemDecoration(Context context, int orientation){
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);

        dividerLeftOffset = 0;
        isLastLineFill = true;
    }

    public void setOrientation(int orientation){
        if(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    public void setDividerLeftOffset(int dividerLeftOffset){
        this.dividerLeftOffset = dividerLeftOffset;
    }

    public void setDividerLeftOffset(int dividerLeftOffset, boolean isLastLineFill){
        this.dividerLeftOffset = dividerLeftOffset;
        this.isLastLineFill = isLastLineFill;
    }

    //onDraw方法优先于drawChildren
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Log.i(TAG, "onDraw()");
        if(mOrientation == VERTICAL_LIST){
            drawVertical(c, parent);
        }else{
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent){
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();

            if(i == 0){  //第一行头布局的分割线，将该线画全
                mDivider.setBounds(left, top, right, bottom);
            }else if (i == childCount - 1){ //如果是最后一行的分割线
                if (isLastLineFill) { //看参数设置情况决定是否将线画全
                    mDivider.setBounds(left, top, right, bottom); //将该线画全
                } else {
                    mDivider.setBounds(left + dividerLeftOffset, top, right, bottom);
                }
            }else {
                mDivider.setBounds(left + dividerLeftOffset, top, right, bottom);
            }
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent){
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //onDrawOver在drawChildren之后；与onDraw选择其一复写即可
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    //getItemOffsets可以通过outRect.set()为每个Item设置一定的偏移量，类似margin,padding, 主要用于绘制Decorator
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == VERTICAL_LIST){
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight()); //1
            //Log.i(TAG, mDivider.getIntrinsicHeight() + "----------");
        }else{
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);  //1
        }
    }

    /**
     * Sets the {@link Drawable} for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    public void setDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        mDivider = drawable;
    }
}
