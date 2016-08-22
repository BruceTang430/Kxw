package com.kxw;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class Fonts extends TextView {
	public Fonts(Context context){
		super(context);
		init(context);
	}
	public Fonts(Context context,AttributeSet attrs){
		super(context,attrs);
		init(context);
	}
	public Fonts(Context context,AttributeSet attrs,int defStyle){
		super(context,attrs,defStyle);
		init(context);
	}
	private void init(Context context){
		AssetManager assetmgr=context.getAssets();
		Typeface font=Typeface.createFromAsset(assetmgr, "fonts/fzlxjt.ttf");
		setTypeface(font);
	}

}
