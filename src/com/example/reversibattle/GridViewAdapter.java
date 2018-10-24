package com.example.reversibattle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter
{

	private int[] chess;
	private Context context;
	private int[] is_selected;
	public GridViewAdapter(int[]chess,Context context){
		this.chess = chess;
		this.context = context;
		is_selected = new int[65];
		for(int i=0;i<65;i++)
			is_selected[i]=0;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return chess.length-2;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		int resId = chess[position];
		ImageView image = new ImageView(context);
		image.setAlpha(255);
		if(resId<0){
			resId=-resId;
			image.setPadding(10, 10, 10, 10);
			image.setBackground(context.getResources().getDrawable(R.drawable.border));
			SoundService.bu();
		}
		image.setImageResource(resId);
		DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
		LayoutParams params = new LayoutParams(dm2.widthPixels/10, dm2.heightPixels/18);
		    image.setLayoutParams(params);
		    Log.d("text","text");
		if(resId==R.drawable.nothing){
				image.setClickable(false);
		}
		
		return image;
	}
	

}
