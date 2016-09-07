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
import android.widget.Toast;

import com.example.shuxing.VideoInfor;

public class VideoInforService {

	private Context context;
	private List<VideoInfor> list=new ArrayList<VideoInfor>();
	public VideoInforService(Context context)
	{
		this.context=context;
	}

	public List<VideoInfor> getVideoInfor()
	{
		MyVideo mv=new MyVideo();
		list=mv.doInBackground();
		return list;
	}
	private BigDecimal parseApkSize(int size) {
	    BigDecimal bd = new BigDecimal((double)size/(1024*1024));
	                BigDecimal setScale = bd.setScale(3, BigDecimal.ROUND_DOWN);
	    return setScale;
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
	public String VideoTime(long ltime)
	{
		long ms=ltime%1000;
		long s=ltime/1000;
		long m=s/60;
		s=s%60;
		long h=m/60;
		m=m%60;
		String str="";
		if(h<=0)
		{
			str=str+"00:";
		}
		else if(h<10)
		{
			str=str+"0"+h+":";
		}
		else
		{
			str=str+h+":";
		}

		if(m<=0)
		{
			str=str+"00:";
		}
		else if(m<10)
		{
			str=str+"0"+m+":";
		}
		else
		{
			str=str+m+":";
		}
		if(s<=0)
		{
			str=str+"00";
		}
		else if(s<10)
		{
			str=str+"0"+s;
		}
		else
		{
			str=str+s;
		}

		return str;
	}
	public class MyVideo extends AsyncTask<String, Integer, ArrayList<VideoInfor>> {

        @Override
        protected ArrayList<VideoInfor> doInBackground(String... params) {
        	ArrayList<VideoInfor> lpath=new ArrayList<VideoInfor>();

            Uri mVideoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            Toast.makeText(context, mVideoUri.toString(), Toast.LENGTH_SHORT).show();
            ContentResolver mContentResolver =context.getContentResolver();
			Cursor cursor=mContentResolver.query(mVideoUri, null, null, null,null);
			while(cursor.moveToNext())
			{
				VideoInfor video=new VideoInfor();
				
				int id=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
				String name=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
				String path=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
				String data=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED));
				long ltime=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
				int lsize=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
				String vtime=VideoTime(ltime);
				BigDecimal pSize=parseApkSize(lsize);
		        long size=pSize.longValue();
				video=new VideoInfor(name,path,data,size,vtime);
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
