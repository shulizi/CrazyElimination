package com.example.reversibattle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ModeTwoGameActivity extends Activity {
	 int [] chess = new int [66];
	 private int [] colors = new int[5];
	 private TextView score;
	 private GridView gridView;
	 private ImageView scoreImage;
	 private GameProgressService gameProgressService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SoundService.changeAndPlayMusic();
        
        score = (TextView) findViewById(R.id.score);
   	 	gridView = (GridView) findViewById(R.id.gridView1);
   	 	scoreImage = (ImageView) findViewById(R.id.scoreImage);
   	 	gameProgressService = new GameProgressService(chess, colors, this);	

        Intent intent = this.getIntent();

        
        if(ConstDatas.isStartNewGame==1){
        	gameProgressService.newGame();
        	ConstDatas.isStartNewGame=0;
        }
        else{
            gameProgressService.readGameSaved(ConstDatas.MODE_TWO);
            for(int i=0;i<64;i++)
            	if(chess[i]<-1)
            		chess[i]=-chess[i];
        }
            
        //做一个构造器Adapter，将棋子放在gridView里
      	 setAdapter();
      	 gridView.setOnItemClickListener(new myOnItemClickListener());
    }
    private class myOnItemClickListener implements OnItemClickListener{
			public void onItemClick(AdapterView<?> arg0, View view, int itemNumber,
					long arg3) {
				//如果被有非空item被onClick,则调用GameLogic类的方法
				if(chess[itemNumber]!=R.drawable.nothing){
					final GameLogic modeTwoCross 
					= new GameLogic(itemNumber, chess, ModeTwoGameActivity.this,colors);
					//调用改变相同颜色的方法，但结果只是计算结果，并没有体现在屏幕上，而是体现在chess数组中
					modeTwoCross.changeSameColor(itemNumber);
					//对计算结果进行进一步处理，包括填补空格和监听死局
					//两次方法返回结果还是chess
					//judge listen the dead time
					Boolean isGameOver = modeTwoCross.hundle();
					if(isGameOver){
						Toast toast = new Toast(getApplicationContext());
							toast=	Toast.makeText(getApplicationContext(), "YOU WIN!", 1);
							toast.setGravity(Gravity.CENTER, 0, 10);
							toast.show();
						restart(null);
					}
					else
						setAdapter();
				}
			}
}
    private  void setAdapter(){
    	//将计算结果返回到屏幕上
    		gameProgressService.saveGame(ConstDatas.MODE_TWO);
			GridViewAdapter gridViewAdapter1 = new GridViewAdapter(chess, ModeTwoGameActivity.this);
	        gridView.setAdapter(gridViewAdapter1);
			score.setText(String.valueOf(chess[64]));
			if(chess[64]>=8000)
					scoreImage.setImageResource(R.drawable.money5);
				else if(chess[64]>=4000)
					scoreImage.setImageResource(R.drawable.money4);
				else if(chess[64]>=2000)
					scoreImage.setImageResource(R.drawable.money3);
				else if(chess[64]>=1000)
					scoreImage.setImageResource(R.drawable.money2);
				else if(chess[64]>=0)
					scoreImage.setImageResource(R.drawable.money1);
    }
    public void change(View view){
    	AlertDialog.Builder builder = new Builder(ModeTwoGameActivity.this)
    	.setTitle("StyleChosen选择风格")
    	.setIcon(R.drawable.iron2)
    	.setSingleChoiceItems(ConstDatas.CHANGES
    	, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				for(int i =0;i<7;i++){
					if(which==i){
						for(int j =i*5;j<i*5+5;j++)
							colors[j%5]=ConstDatas.COLORS[j];
						restart(null);
						dialog.dismiss();
					}
				}
			}
		}).setNegativeButton("取消", null);
    	AlertDialog alertDialog = builder.create();
    	alertDialog.show();
    	
    }
    public void restart(View view){
    	AlertDialog.Builder builder = new Builder(ModeTwoGameActivity.this);
    	//设置dialog.builder的标题
    	builder.setTitle("Attention注意，是否重新开始").setIcon(R.drawable.iron2);
    	//设置dialog.builder的界面
    	View dialog = ModeTwoGameActivity.this.getLayoutInflater().inflate(R.layout.dialog, null);
    	builder.setView(dialog);
    	//设置dialog.builder的响应事件
    	builder.setPositiveButton("YES确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				gameProgressService.restartGame(score,gridView);
				setAdapter();
			}
		}).setNegativeButton("NO取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
			}
		});
    	//通过builder创建一个dialog
    	AlertDialog alertDialog = builder.create();
		alertDialog.show();
    }
    @Override
	  protected void onPause() {
		super.onPause();
		SoundService.pauseMusic();
		
		}
	@Override
	  protected void onRestart() {
		super.onPause();
		SoundService.startMusic();
		
		}
    
    
}
