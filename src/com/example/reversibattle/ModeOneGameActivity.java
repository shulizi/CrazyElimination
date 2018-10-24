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

public class ModeOneGameActivity extends Activity {
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
        
        score = (TextView) findViewById(R.id.score);
   	 	gridView = (GridView) findViewById(R.id.gridView1);
   	 	scoreImage = (ImageView) findViewById(R.id.scoreImage);
   	 	gameProgressService = new GameProgressService(chess, colors, this);	
   	 	
   	 	ImageView change = (ImageView) findViewById(R.id.change);
   	 	ImageView restart = (ImageView) findViewById(R.id.restart);
   	 	ViewGroup viewGroup1 =  (ViewGroup) change.getParent();
   	 	viewGroup1.removeView(change);
   	 	ViewGroup viewGroup2 =  (ViewGroup) restart.getParent();
   	 	viewGroup2.removeView(restart);
   	 	
        SoundService.changeAndPlayMusic();
        
        Intent intent = this.getIntent();
        
       
        if(ConstDatas.isStartNewGame==1){
        	gameProgressService.newGame();
        	ConstDatas.isStartNewGame=0;
        }
        else{
            gameProgressService.readGameSaved(ConstDatas.MODE_ONE);
            for(int i=0;i<64;i++)
            	if(chess[i]<-1)
            		chess[i]=-chess[i];
        }
        //��һ��������Adapter�������ӷ���gridView��
        setAdapter();
        gridView.setOnItemClickListener(new myOnItemClickListener());
    }
    
    private class myOnItemClickListener implements OnItemClickListener{
 			public void onItemClick(AdapterView<?> arg0, View view, int itemNumber,
 					long arg3) {
 				//������зǿ�item��onClick,�����GameLogic��ķ���
 				if(chess[itemNumber]!=R.drawable.nothing){
 					final GameLogic modeOneCross 
 					= new GameLogic(itemNumber, chess, ModeOneGameActivity.this,colors);
 					//���øı���ͬ��ɫ�ķ����������ֻ�Ǽ���������û����������Ļ�ϣ�����������chess������
 					modeOneCross.changeSameColor(itemNumber);
 					//�Լ��������н�һ������������ո�ͼ�������
 					//���η������ؽ������chess
 					//judge listen the dead time
 					Boolean isGameOver = modeOneCross.hundle();
 					if(isGameOver)
 						changeLevel();
 					setAdapter();
 				}
 			}
    }
    private void setAdapter(){
    	//�����������ص���Ļ��
    		gameProgressService.saveGame(ConstDatas.MODE_ONE);
			GridViewAdapter gridViewAdapter1 = new GridViewAdapter(chess, ModeOneGameActivity.this);
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
    public void changeLevel(){
		int i;
		int[] allColors=ConstDatas.COLORS;
 		for(i=0;i<allColors.length;i+=5)
 			if(colors[0]==allColors[i]&&colors[1]==allColors[i+1])
 				break;
 		i+=5;
 		try{
         for(int j =i;j<i+5;j++)
				colors[j%5]=allColors[j];
         for(int l=0;l<64;l++)
         	chess[l]=colors[(int) (Math.random() * 5)];
		 chess[65]=0;
		 Toast.makeText(ModeOneGameActivity.this, "��"+(i/5+1)+"��", 1).show();
		 
 		}
 		catch(Exception e){
 			
 			AlertDialog.Builder builder= new Builder((ModeOneGameActivity.this))
 			.setTitle("��ʾ")
 			.setMessage("���Ѿ�Ӯ�����йؿ����Ƿ����棿")
 			.setPositiveButton("��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					gameProgressService.newGame();
				}
			})
			.setNegativeButton("��", null);
 			AlertDialog alertDialog3 = builder.create();
 			alertDialog3.show();
 		
 		}
 		SoundService.yeah();
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
