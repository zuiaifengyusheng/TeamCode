package com.example.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.example.common.Common;
import com.example.shuxing.VideoInfor;

public class VideoInforService {

	private Context context;
	private Common common;
	private List<VideoInfor> list=new ArrayList<VideoInfor>();
	public VideoInforService(Context context)
	{
		this.context=context;
		common=new Common();
	}

	public List<VideoInfor> getVideoInfor()
	{
		MyVideo mv=new MyVideo();
		list=mv.doInBackground();
		return list;
	}
	public Bitmap getVideoThumbnail(String filePath) {  
        Bitmap bitmap = null;  
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();  
        try {  
            retriever.setDataSource(filePath);  
            bitmap = retriever.getFrameAtTime();  
        }   
        catch(IllegalArgumentException e) {  
            e.printStackTrace();  
        }   
        catch (RuntimeException e) {  
            e.printStackTrace();  
        }   
        finally {  
            try {  
                retriever.release();  
            }   
            catch (RuntimeException e) {  
                e.printStackTrace();  
            }  
        }  
        return bitmap;  
    }

	public class MyVideo extends AsyncTask<String, Integer, ArrayList<VideoInfor>> {

        @Override
        protected ArrayList<VideoInfor> doInBackground(String... params) {
        	ArrayList<VideoInfor> lpath=new ArrayList<VideoInfor>();

            Uri mVideoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            ContentResolver mContentResolver =context.getContentResolver();
			Cursor cursor=mContentResolver.query(mVideoUri, null, null, null,null);
			while(cursor.moveToNext())
			{
				VideoInfor video=new VideoInfor();
				
				//��ȡ��Ƶid
				int id=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
				//��ȡ��Ƶ����
				String name=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
				//��ȡ��Ƶ·��
				String path=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
				//��ȡ��Ƶʱ��
				long updateTime=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED));
				updateTime = updateTime*1000;
				//��ʽ��ʱ�䣬��ȡ�꣬�£���
				String[] times =  common.getTimeInfo(updateTime);
			    String date=times[0]+"��"+times[1]+"��"+times[2]+"��    "+times[3]+":"+times[4]+":"+times[5];
				//��ȡ��Ƶ����
				long ltime=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
				String vtime=common.GetTime(ltime);
				//��ȡ��Ƶ��С
				int lsize=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
				BigDecimal pSize=common.parseApkSize(lsize);
		        long size=pSize.longValue();
				video=new VideoInfor(name,path,date,size,vtime);
				lpath.add(video);
				
			}
			cursor.close();
			return lpath;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<VideoInfor> bytes) {
            super.onPostExecute(bytes);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
	}
}
