package com.example.reversibattle;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuButtonClick {
	
			private Context context;
			private Handler handler;
			public MenuButtonClick(Context context,Handler handler){
				this.context = context;
				this.handler=handler;
			}
			public void setGameClickListener(final Button button,final int isStartNewGame,final Class toClass,final int[] buttonGroupMode){
				   button.setOnLongClickListener(new OnLongClickListener() {
						
						@SuppressLint("NewApi")
						@Override
						public boolean onLongClick(View arg0) {
							// TODO Auto-generated method stub
							button.setBackground(context.getResources().getDrawable(buttonGroupMode[0]));
							new Thread(new Runnable(){

								@Override
								public void run() {
									// TODO Auto-generated method stub
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									Message msg = new Message();
									msg.obj = button;
									msg.arg1=buttonGroupMode[1];
									handler.sendMessage(msg);
									
								}}).start();
							return false;
						}
						
					});
			        button.setOnClickListener(new OnClickListener() {
						
						@SuppressLint("NewApi")
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							SoundService.ke();
							button.setBackground(context.getResources().getDrawable(buttonGroupMode[0]));
							new Thread(new Runnable(){

								@Override
								public void run() {
									// TODO Auto-generated method stub
									try {
										Thread.sleep(50);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									Message msg = new Message();
									msg.obj = button;
									msg.arg1=buttonGroupMode[1];
									handler.sendMessage(msg);
									
								}}).start();
							if(toClass!=null){
								Intent intent = new Intent();
								intent.setClass(context,toClass );
								ConstDatas.isStartNewGame=isStartNewGame;
								context.startActivity(intent);
							}
						}
					});
				
			}
			
			public  void setImageViewListener(final ImageView image){
			        image.setOnClickListener(new OnClickListener() {
						
						@SuppressLint("NewApi")
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							
							if(image.getTag().equals(R.drawable.voiceup))
								{image.setImageResource(R.drawable.voiceoff);
								SoundService.setMusicSt(false);
								image.setTag(R.drawable.voiceoff);
								}
							else {
								image.setImageResource(R.drawable.voiceup);
								SoundService.setMusicSt(true);
								SoundService.startMusic();
								image.setTag(R.drawable.voiceup);
							
							}
							}
					});
				
			}

}
