package com.example.reversibattle;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.content.Context;

public class FileService {
	private Context context;
	public FileService(Context context) {
	this.context = context;
	}
	public FileService() {
	}
	public void saveContent(String fileName, String content) {
		 FileOutputStream out = null;  
        try {  
            out = context.openFileOutput(fileName, Context.MODE_PRIVATE);  
            out.write(content.getBytes("UTF-8"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            try {  
                out.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
	}
	public String readContent(String fileName){
		FileInputStream in = null;  
		ByteArrayOutputStream bout = null;  
		byte[]buf = new byte[1024];  
		bout = new ByteArrayOutputStream();  
		int length = 0;  
		try {
			in = context.openFileInput(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //���������  
		try {
			while((length=in.read(buf))!=-1){  
			    bout.write(buf,0,length);  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		byte[] content = bout.toByteArray();  
			String string = "";
			try {
				string = new String(content,"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//�����ı���Ϊ��ȡ������  
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			bout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return string;
	}
	
}
