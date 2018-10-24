package com.example.reversibattle;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GameProgressService {
	private int[] chess;
	private int[] colors;
	private Context context;
	
	public GameProgressService( int[] chess, int[] colors, Context context){
		this.chess=chess;
		this.colors=colors;
		this.context =context;
		
		
	}
	
	public void saveGame(String mode) {
		// 储存游戏进度
		String chessString = "";
		for (int i = 0; i < 64; i++) {
			chessString+=String.valueOf(chess[i]+",");
		}
		//存储分数,为免于建立更多的文件夹，分数与chess存储在一个文件夹中
		chessString+=String.valueOf(chess[64]);
		//存储颜色
		String colorsString="";
		for(int i =0;i<5;i++)
			colorsString+=String.valueOf(String.valueOf(colors[i])+",");
		FileService fileService = new FileService(context);
		fileService.saveContent(mode+"gameSaved.txt", chessString);
		fileService.saveContent(mode+"colorSaved.txt", colorsString);
	}
	
	// 读取储存数据
	public void readGameSaved(String mode) {
		try{
			FileService fileService = new FileService(context);
			String chessString = fileService.readContent(mode+"gameSaved.txt");
			String colorString = fileService.readContent(mode+"colorSaved.txt");
			String[] chessStrings = chessString.split(",");
			String[] colorStrings = colorString.split(",");
			for(int j=0;j<5;j++)
				colors[j]=Integer.parseInt(colorStrings[j]);
			for (int i = 0; i < 64; i++) {
				chess[i]=Integer.parseInt(chessStrings[i]);
			}
				chess[64]=Integer.parseInt(chessStrings[64]);
		}
		//如果没有保存的数据则创建新的数据
		catch(Exception e){
			newGame();
		}
	}
	//创建新游戏，所有初始值归零
	public void newGame(){
		//the first five colorsResource in ConstDatas COLORS
		for(int i =0;i<5;i++)
			colors[i] = ConstDatas.COLORS[i];
		for(int i=0;i<64;i++)
			chess[i]=colors[(int) (Math.random() * 5)];
		chess[64]=0;
		chess[65]=0;
		Log.d("test","test");
	}
	//重新开始游戏
	public void restartGame( final TextView textView,final GridView gridView ){
				for(int i=0;i<64;i++)
					chess[i]=colors[(int) (Math.random() * 5)];
				chess[64]=0;
	}
}
