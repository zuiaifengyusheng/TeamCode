package com.example.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.provider.MediaStore.Video;
import android.widget.Toast;

import com.example.Service.PictureInforService.MyPicture;
import com.example.shuxing.VideoInfor;
import com.example.shuxing.VideoPath;

public class VideoInforService {

	private Context context;
	private List<VideoInfor> list=new ArrayList<VideoInfor>();
	private ArrayList<VideoPath> listpath;
	public VideoInforService(Context context)
	{
		this.context=context;
	}

	public List<VideoInfor> getVideoInfor()
	{
		MyVideo mv=new MyVideo();
		listpath=mv.doInBackground();
		
		if(listpath.size()==0)
		{
			Toast.makeText(context, "xcvcvcxvbcvbc", Toast.LENGTH_SHORT).show();
			return null;			
		}
		else{
			Toast.makeText(context, "非空1", Toast.LENGTH_SHORT).show();
		}

		for(int i=0;i<listpath.size();i++)
		{
			File file=new File(listpath.get(i).getPath());
			VideoInfor video=new VideoInfor();
			//视频名称
			video.setName(file.getName());
			//视频路径
			video.setPath(file.getAbsolutePath());
			
			//视频大小
	        int value=Integer.valueOf((int)file.length());
	        BigDecimal pSize=parseApkSize(value);
	        String size=pSize.toString();
	        video.setSize(size);
			
	        //视频时长
	        String mtime;
			
			//视频缩略图
			Bitmap bitmap=MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(), listpath.get(i).getID(), Video.Thumbnails.MICRO_KIND, null);
			Toast.makeText(context, listpath.get(i).getID()+"", Toast.LENGTH_SHORT).show();
			video.setMedia(bitmap);
			
			//视频日期日期
			String date=null;
		    try{
			     ExifInterface exif = new ExifInterface(file.getPath());
			     date=exif.getAttribute(ExifInterface.TAG_DATETIME);
			}
			catch(Exception ee){
			}
		    video.setData(date);
			
			list.add(video);
			
		}
		return list;
	}
	private BigDecimal parseApkSize(int size) {
	    BigDecimal bd = new BigDecimal((double)size/(1024*1024));
	                BigDecimal setScale = bd.setScale(3, BigDecimal.ROUND_DOWN);
	    return setScale;
	}

	public class MyVideo extends AsyncTask<String, Integer, ArrayList<VideoPath>> {

        @Override
        protected ArrayList<VideoPath> doInBackground(String... params) {
        	ArrayList<VideoPath> lpath=new ArrayList<VideoPath>();
			String[] proj={MediaStore.Video.Thumbnails._ID,MediaStore.Video.Thumbnails.DATA};
            Uri mVideoUri = MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;
            ContentResolver mContentResolver =context.getContentResolver();
			Cursor cursor=mContentResolver.query(mVideoUri, proj, null, null,null);
			while(cursor.moveToNext())
			{
				VideoPath pp=new VideoPath();
				String path=cursor.getString(1);
				long id=Long.parseLong(cursor.getString(0));
				Toast.makeText(context, id+"", Toast.LENGTH_SHORT).show();
				
				pp.setID(id);
				pp.setPath(path);
				lpath.add(pp);
			}
			cursor.close();
			return lpath;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<VideoPath> bytes) {
            super.onPostExecute(bytes);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
	}
}
