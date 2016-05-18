package com.weibank.com.weibankapp.views;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Administrator on 2016/1/21.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    //设置分割线的默认方向
    private int mOritation = LinearLayoutManager.VERTICAL;

    //设置分割线默认大小
    private int mSize = 1;

    //定义画笔对象
    private Paint mPaint;

    public DividerItemDecoration(Context context,int oritation){

        this.mOritation = oritation;
        if(oritation != LinearLayoutManager.HORIZONTAL
                && oritation != LinearLayoutManager.VERTICAL){

            throw  new IllegalArgumentException("输入的参数有误");
        }
        mSize = (int) TypedValue.applyDimension(mSize,
                TypedValue.COMPLEX_UNIT_DIP,
                context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mPaint.setColor(0x969696);
        mPaint.setARGB(255,207,207,207);
        mPaint.setStyle(Paint.Style.FILL);
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        super.onDraw(c, parent, state);
        if(mOritation == LinearLayoutManager.VERTICAL){

            drawVertical(c,parent);
        }else{

            drawHorizontal(c, parent);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);
        if(mOritation == LinearLayoutManager.VERTICAL){

            outRect.set(0,0,0,mSize);
        }else{

            outRect.set(0,0,mSize,0);
        }
    }

    //垂直方向的分割线
    private void drawVertical(Canvas canvas,RecyclerView parent){

        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth()  - parent.getPaddingRight();
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {

            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                    child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mSize;

            canvas.drawRect(left,top,right,bottom,mPaint);
        }
    }

    //水平方向的分割线
    private void drawHorizontal(Canvas canvas,RecyclerView parent){

        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {

            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                    child.getLayoutParams();
            final int left = params.rightMargin + child.getRight();
            final int right = left + mSize;
            canvas.drawRect(left,top,right,bottom,mPaint);
        }
    }
}
