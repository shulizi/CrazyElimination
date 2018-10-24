package com.example.reversibattle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundService {
	   private static MediaPlayer music;
	    private static SoundPool soundPool;
	     
	    private static boolean musicSt = true; //���ֿ���
	    private static boolean soundSt = true; //��Ч����
	    private static Context context;
	     
	    private static final int[] musicId = ConstDatas.MISUCS;
	    private static Map<Integer,Integer> soundMap; //��Ч��Դid����ع������Դid��ӳ���ϵ��
	     
	    /**
	     * ��ʼ������
	     * @param c
	     */
	    public static void init(Context c)
	    {
	        context = c;
	 
	        initMusic();
	         
	        initSound();
	    }
	     
	    //��ʼ����Ч������
	    private static void initSound()
	    {
	        soundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,100);
	         
	        soundMap = new HashMap<Integer,Integer>();
	        soundMap.put(R.raw.focus, soundPool.load(context, R.raw.focus, 1));
	        soundMap.put(R.raw.delete, soundPool.load(context, R.raw.delete, 1));
	        soundMap.put(R.raw.onclick, soundPool.load(context, R.raw.onclick, 1));
	        soundMap.put(R.raw.winthegame, soundPool.load(context, R.raw.winthegame, 1));
	    }
	     
	    //��ʼ�����ֲ�����
	    private static void initMusic()
	    {
	        int r = new Random().nextInt(musicId.length);
	        music = MediaPlayer.create(context,musicId[r]);
	        music.setLooping(true);
	    }
	     
	    /**
	     * ������Ч
	     * @param resId ��Ч��Դid
	     */
	    public static void playSound(int resId)
	    {
	        if(soundSt == false)
	            return;
	         
	        Integer soundId = soundMap.get(resId);
	        if(soundId != null)
	            soundPool.play(soundId, 1, 1, 1, 0, 1);
	    }
	 
	    /**
	     * ��ͣ����
	     */
	    public static void pauseMusic()
	    {
	        if(music.isPlaying())
	            music.pause();
	    }
	     
	    /**
	     * ��������
	     */
	    public static void startMusic()
	    {
	        if(musicSt)
	            music.start();
	    }
	     
	    /**
	     * �л�һ�����ֲ�����
	     */
	    public static void changeAndPlayMusic()
	    {
	        if(music != null)
	            music.release();
	        initMusic();
	        startMusic();
	    }
	    /**
	     * �ص�����
	     */
	    public static void stopMusic()
	    {
	        if(music != null)
	            music.release();
	    }  
	    /**
	     * ������ֿ���״̬
	     * @return
	     */
	    public static boolean isMusicSt() {
	        return musicSt;
	    }
	     
	    /**
	     * �������ֿ���
	     * @param musicSt
	     */
	    public static void setMusicSt(boolean musicSt) {
	        SoundService.musicSt = musicSt;
	        if(musicSt)
	            music.start();
	        else
	            music.pause();
	    }
	 
	    /**
	     * �����Ч����״̬
	     * @return
	     */
	    public static boolean isSoundSt() {
	        return soundSt;
	    }
	 
	    /**
	     * ������Ч����
	     * @param soundSt
	     */
	    public static void setSoundSt(boolean soundSt) {
	    	SoundService.soundSt = soundSt;
	    }
	     
	    /**
	     * ������bu��������
	     */
	    public static void bu()
	    {
	        playSound(R.raw.focus);
	    }
	    /**
	     * ������boom��������
	     */
	    public static void boom()
	    {
	        playSound(R.raw.delete);
	    }
	    /**
	     * ������ke��������
	     */
	    public static void ke()
	    {
	        playSound(R.raw.onclick);
	    }
	    /**
	     * ������yeah��������
	     */
	    public static void yeah()
	    {
	        playSound(R.raw.winthegame);
	    }
	    
}
