package com.weibank.com.weibankapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/3/24.
 */
public class DefineGridView extends GridView{

    public DefineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefineGridView(Context context) {
        super(context);
    }

    public DefineGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
