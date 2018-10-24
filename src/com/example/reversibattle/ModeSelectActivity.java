package com.example.reversibattle;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ModeSelectActivity extends Activity {
	int isStartNewGame;
	private Button modeOne;
	private Button modeTwo;
	private Handler handler = new Handler(new Handler.Callback() {
		
		@SuppressLint("NewApi")
		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Button button = (Button) msg.obj;
			int buttonResource = msg.arg1;
			button.setBackground(ModeSelectActivity.this.getResources().getDrawable(buttonResource));
			return false;
		}
	});
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mode_select);
		
		Intent intent = getIntent();
		isStartNewGame = ConstDatas.isStartNewGame;			
		SoundService.startMusic();
		
		modeOne = (Button) findViewById(R.id.mode_one_button);
		modeTwo = (Button) findViewById(R.id.mode_two_button);
		//200*2是按钮的长度，60*2是按钮的宽度
		if(this.getWindowManager().getDefaultDisplay().getWidth()<this.getWindowManager().getDefaultDisplay().getHeight()){
			modeOne.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/3.0*1-60));
			modeOne.setX((float) (this.getWindowManager().getDefaultDisplay().getWidth()/2.2));
			modeTwo.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/5.0*4-60));
			modeTwo.setX((float) (this.getWindowManager().getDefaultDisplay().getWidth()/2.2));
		}
		else{
			modeOne.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/3.0*1-60));
			modeOne.setX((float) (this.getWindowManager().getDefaultDisplay().getWidth()/1.5));
			modeTwo.setY((float) (this.getWindowManager().getDefaultDisplay().getHeight()/5.0*4-60));
			modeTwo.setX((float) (this.getWindowManager().getDefaultDisplay().getWidth()/1.5));
		}
		MenuButtonClick menuButtonClick = new MenuButtonClick(this, handler);
		menuButtonClick.setGameClickListener(modeOne, isStartNewGame, ModeOneGameActivity.class, ConstDatas.COMMEN_BUTTON_GROUP);
		menuButtonClick.setGameClickListener(modeTwo, isStartNewGame, ModeTwoGameActivity.class, ConstDatas.COMMEN_BUTTON_GROUP);
	}
	@Override
	protected void onPause(){
		super.onResume();
		SoundService.pauseMusic();
	}
	@Override
	protected void onRestart(){
		super.onResume();
		isStartNewGame=0;
		SoundService.startMusic();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
