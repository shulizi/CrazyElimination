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
		// ������Ϸ����
		String chessString = "";
		for (int i = 0; i < 64; i++) {
			chessString+=String.valueOf(chess[i]+",");
		}
		//�洢����,Ϊ���ڽ���������ļ��У�������chess�洢��һ���ļ�����
		chessString+=String.valueOf(chess[64]);
		//�洢��ɫ
		String colorsString="";
		for(int i =0;i<5;i++)
			colorsString+=String.valueOf(String.valueOf(colors[i])+",");
		FileService fileService = new FileService(context);
		fileService.saveContent(mode+"gameSaved.txt", chessString);
		fileService.saveContent(mode+"colorSaved.txt", colorsString);
	}
	
	// ��ȡ��������
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
		//���û�б���������򴴽��µ�����
		catch(Exception e){
			newGame();
		}
	}
	//��������Ϸ�����г�ʼֵ����
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
	//���¿�ʼ��Ϸ
	public void restartGame( final TextView textView,final GridView gridView ){
				for(int i=0;i<64;i++)
					chess[i]=colors[(int) (Math.random() * 5)];
				chess[64]=0;
	}
}
