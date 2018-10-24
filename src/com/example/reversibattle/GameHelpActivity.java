package com.example.reversibattle;

import javax.security.auth.callback.Callback;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class GameHelpActivity extends Activity{
	
	private Button button;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Gallery gallery = (Gallery) findViewById(R.id.helpgallery);
       
        button = (Button) findViewById(R.id.help_button);
        
		GalleryAdapter adapter = new GalleryAdapter(ConstDatas.HELP_PICTURES, this);
		gallery.setAdapter(adapter);
		
		//点击跳转
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					final int index, long arg3) {
				// TODO Auto-generated method stub
				button.setBackground(GameHelpActivity.this.getResources().getDrawable(ConstDatas.HELP_BUTTONS[index]));
				button.setText("");
			
//				Toast.makeText(GameHelpActivity.this, String.valueOf(textView.getWidth())+":"+String.valueOf(GameHelpActivity.this.getWindowManager().getDefaultDisplay().getWidth()), 1).show();
				switch(index){
					case 3:
						button.setText("返回菜单");
						button.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								finish();
							}
						});
						break;
				}
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}	
	
	
}
