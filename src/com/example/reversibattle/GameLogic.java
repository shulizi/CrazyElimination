package com.example.reversibattle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameLogic {
	int chess[];
	Context context;
	int originalColor;
	int score=2;
	private int [] colors = new int[5];
	//两个构造函数,第一个是点击单个item时传入的，所以多一个参数
	//第二个只是为了调用restartGame等方法
	public GameLogic(int itemNumber, int chess[], Context context,
			int[]colors) {
		// TODO Auto-generated constructor stub
		this.chess = chess;
		this.context = context;
		this.colors=colors;
		originalColor = chess[itemNumber];
		
	}

	public GameLogic(int chess[], Context context,int []colors) {
		// TODO Auto-generated constructor stub
		this.chess = chess;
		this.context = context;
		this.colors=colors;
	}

	public void changeSameColor(int itemNumber) {
		
		// 如果右边一个跟前面一个颜色相同，则变换颜色
		// flag=true表示存在相同的棋子
		if ((itemNumber + 1) % 8 != 0 && (itemNumber + 1) != -1) {
			if (chess[itemNumber + 1] == originalColor) {
				chess[itemNumber + 1] = -1;
				changeSameColor(itemNumber + 1);
				score*=2;
			}
		}
		// 如果左边一个跟前面一个颜色相同，则变换颜色
		if (itemNumber % 8 != 0 && (itemNumber - 1) != -1) {
			if (chess[itemNumber - 1] == originalColor) {
				chess[itemNumber - 1] = -1;
				changeSameColor(itemNumber - 1);
				score*=2;
			}
		}

		// 如果上边一个跟前面一个颜色相同，则变换颜色
		if (itemNumber / 8 != 0 && (itemNumber - 8) != -1) {
			if (chess[itemNumber - 8] == originalColor) {
				chess[itemNumber - 8] = -1;
				changeSameColor(itemNumber - 8);
				score*=2;
			}
		}

		// 如果下边一个跟前面一个颜色相同，则变换颜色
		if (itemNumber / 8 != 7 && (itemNumber + 8) != -1) {
			if (chess[itemNumber + 8] == originalColor) {
				chess[itemNumber + 8] = -1;
				changeSameColor(itemNumber + 8);
				score*=2;
			}
		}
		
	}
	//将空位补齐
	public void fillTheBlank(){
		for (int i = 0; i < 8; i++) {
			int count = 0;
			for (int j = i; j <= i + 56; j += 8) {
				if (chess[j] == -1) {
					for (int k = j; k >= i; k -= 8) {
						if (k - 8 >= 0) {
							chess[k] = chess[k - 8];
						} else{
							chess[k] = R.drawable.nothing;
							SoundService.boom();
						}
					}
				}
				if (chess[j] == R.drawable.nothing)
					count++;
			}
			// 向右移补齐空位
			if (count == 8) {
				for (int l = i; l >= 0; l--) {
					if (l - 1 >= 0) {
						for (int m = l; m <= l + 56; m += 8)
							chess[m] = chess[m - 1];
					} else {
						for (int m = l; m <= l + 56; m += 8)
							chess[m] = R.drawable.nothing;
					}
				}
			}
		}
		
	}
	// 调用此方法进行对棋子的再构造
	public boolean hundle() {
		int v;
		for(v=0;v<64;v++)
			if(chess[v]==-1)
				break;
		//used click when v!=64 
		if(v!=64){
		// 将空位补齐
					//检测是第几次点击
					int i=0;
					for( i=0;i<64;i++)
						if(chess[i]<-1)
							break;
					//如果i=64，表示两次点击,chess[65]为1表示已经监听到点击了一次
					if(i==64  && chess[65]==1){
						fillTheBlank();
						chess[64]+=score-2;
						chess[65]=0;
					}
					//第一次点击
					else if(i==64 && chess[65]==0){
							for(int j=0;j<64;j++)
								if(chess[j]==-1){
									chess[j]=-originalColor;
								}
							chess[65]=1;
						}
					//两次点得不一样
					else if (i!=64 && chess[65]==1){
						for(int j=0;j<64;j++){
							if(chess[j]<-1)
								chess[j]=-chess[j];
							else if(chess[j]==-1){
								chess[j]=-originalColor;
							}
						}
					}
			}
				//表示不存在有可消除的情况，无效点击
			else{
				for(int i=0;i<64;i++)
					if(chess[i]<-1)
						chess[i]=-chess[i];
				chess[65]=0;
			}

		
		// 检测游戏是否结束
		boolean isGameOver = true;
		for (int i = 0; i < 64; i++) {
			if ((i + 1) % 8 != 0 && chess[i] != R.drawable.nothing
					&& chess[i + 1] != R.drawable.nothing) {
				if (chess[i] == chess[i + 1]) {
					isGameOver = false;
					break;
				}
			}
			if (i / 8 != 7 && chess[i] != R.drawable.nothing
					&& chess[i + 8] != R.drawable.nothing) {
				if (chess[i] == chess[i + 8]) {
					isGameOver = false;
					break;
				}
			}
		}
		
		return isGameOver;
	}
	
}
