package com.td.td.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class Shadow extends ViewGroup {
	public Drawable mSecondaryShadowDrawable;
	public int mShadowWidth;
	public View mContent;
	
	public Shadow(Context context) {
		this(context, null);
	}
	
	public Shadow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		drawShadow(null, canvas);
	}
	
	public void drawShadow(View content, Canvas canvas) {
		mSecondaryShadowDrawable.setBounds(0, 0, mShadowWidth, mContent.getHeight());
		mSecondaryShadowDrawable.draw(canvas);
	}
}
