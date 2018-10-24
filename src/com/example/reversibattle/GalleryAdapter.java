package com.example.reversibattle;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

public class GalleryAdapter extends BaseAdapter
{
	private int[] images; 
	private Context context;
	public GalleryAdapter(int []images,Context context){
		super();
		this.images = images;
		this.context = context;
		
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
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

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		ImageView imageView = new ImageView(context);
//		DisplayMetrics dm=context.getResources().getDisplayMetrics();
//		android.widget.Gallery.LayoutParams params = new Gallery.LayoutParams(dm2.heightPixels/14, dm2.widthPixels/10);
//		imageView.setLayoutParams(new Gallery.LayoutParams((int) (dm.widthPixels*1.2),(int) (dm.heightPixels )));
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setImageResource(images[position]);
		return imageView;
	}

}
