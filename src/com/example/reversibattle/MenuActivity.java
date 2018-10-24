package com.example.reversibattle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class MenuActivity extends Activity {
	
	private Button startGameButton;
	private Button restartGameButton;
	private Button helpButton;
	private Button exitButton;
	private ImageView voiceButton;
	
	
	private Handler handler = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Button button = (Button) msg.obj;
			int buttonResource = msg.arg1;
			button.setBackground(MenuActivity.this.getResources().getDrawable(buttonResource));
			return false;
		}
	});
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        startGameButton = (Button) findViewById(R.id.start_game);
        restartGameButton = (Button) findViewById(R.id.restart_game);
        helpButton = (Button) findViewById(R.id.help);
        exitButton = (Button) findViewById(R.id.exit);
		voiceButton =  (ImageView) findViewById(R.id.voice);
		voiceButton.setTag(R.drawable.voiceup);
		if(this.getWindowManager().getDefaultDisplay().getWidth()<this.getWindowManager().getDefaultDisplay().getHeight()){
			startGameButton.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/10*2));
			restartGameButton.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/10*3.2));
			helpButton.setY((float)(this.getWindowManager().getDefaultDisplay().getHeight()/10*4.4));
			exitButton.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/10*5.6));
			
		}
		else{
			startGameButton.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/10*2));
			restartGameButton.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/10*4));
			helpButton.setY((float)(this.getWindowManager().getDefaultDisplay().getHeight()/10*2));
			exitButton.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/10*4));
			
			startGameButton.setX((float) (-this.getWindowManager().getDefaultDisplay().getWidth()/5));
			restartGameButton.setX((float) (-this.getWindowManager().getDefaultDisplay().getWidth()/5));
			helpButton.setX((float)(this.getWindowManager().getDefaultDisplay().getWidth()/5));
			exitButton.setX((float) (this.getWindowManager().getDefaultDisplay().getWidth()/5));
		}
        MenuButtonClick menuButtonClick = new MenuButtonClick(this, handler);
        menuButtonClick.setGameClickListener(startGameButton,1,ModeSelectActivity.class,ConstDatas.COMMEN_BUTTON_GROUP);
        menuButtonClick.setGameClickListener(restartGameButton, 0,ModeSelectActivity.class,ConstDatas.COMMEN_BUTTON_GROUP);
        menuButtonClick.setGameClickListener(helpButton, -1,GameHelpActivity.class,ConstDatas.COMMEN_BUTTON_GROUP);
        menuButtonClick.setGameClickListener(exitButton,-1,ExitActivity.class,ConstDatas.SPECIAL_BUTTON_GROUP);
        menuButtonClick.setImageViewListener(voiceButton);
	
       
        SoundService.init(this);
        SoundService.startMusic();
        
	
	}	
	@Override
	  protected void onPause() {
		super.onPause();
		SoundService.pauseMusic();
		
		}
	@Override
	  protected void onStop() {
		super.onPause();
		SoundService.pauseMusic();
		
		}

	@Override
	  protected void onRestart() {
		super.onRestart();
		SoundService.startMusic();
		
		}
}
