package com.example.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.shuxing.PictureInfor;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;

import android.provider.MediaStore;

public class PictureInforService {

	private Context context;
	private List<PictureInfor> list=new ArrayList<PictureInfor>();
	private ArrayList<String> listpath;
	public PictureInforService(Context context)
	{
		this.context=context;
	}

	public List<PictureInfor> getPictureInfor()
	{
		MyPicture mp=new MyPicture();
		listpath=mp.doInBackground();
		
		if(listpath.size()==0)
		{
			return null;			
		}

		for(int i=0;i<listpath.size();i++)
		{
			File file=new File(listpath.get(i));
			PictureInfor picture=new PictureInfor();
			//Í¼Æ¬Ãû³Æ
			picture.setName(file.getName());
			//Í¼Æ¬Â·¾¶
			picture.setPath(file.getAbsolutePath());
			
			//Í¼Æ¬´óÐ¡
	        int value=Integer.valueOf((int)file.length());
	        BigDecimal pSize=parseApkSize(value);
	        String size=pSize.toString();
			picture.setSize(size);
		
			//Í¼Æ¬ÈÕÆÚ
			String date=null;
		    try{
			     ExifInterface exif = new ExifInterface(file.getPath());
			     date=exif.getAttribute(ExifInterface.TAG_DATETIME);
			}
			catch(Exception ee){
			}
			picture.setData(date);
			
			list.add(picture);
			
		}
		return list;
	}
	private BigDecimal parseApkSize(int size) {
	    BigDecimal bd = new BigDecimal((double)size/(1024*1024));
	                BigDecimal setScale = bd.setScale(3, BigDecimal.ROUND_DOWN);
	    return setScale;
	}
	private Bitmap getImage(String imagePath) {
        Bitmap bitmap = null;
        
        bitmap = BitmapFactory.decodeFile(imagePath);
        
        return bitmap;
    }
	public class MyPicture extends AsyncTask<String, Integer, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
        	ArrayList<String> lpath=new ArrayList<String>();
			String[] proj={MediaStore.Images.Media.DATA};
            Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentResolver mContentResolver =context.getContentResolver();
			Cursor cursor=mContentResolver.query(mImageUri, proj, null, null, MediaStore.Images.Media.DATE_MODIFIED + " DESC");
			while(cursor.moveToNext())
			{
				String path=cursor.getString(0);
				lpath.add(path);
			}
			/*
			 Uri mImageUri1 = MediaStore.Images.Thumbnails.INTERNAL_CONTENT_URI;
	            ContentResolver mContentResolver1 =context.getContentResolver();
				Cursor cursor1=mContentResolver1.query(mImageUri1,proj, null, null, null);
			Cursor cursor1=context.getContentResolver().query(uri1, proj1, null, null, null);
			while(cursor1.moveToNext())
			{
				String path=cursor1.getString(0);
				lpath.add(new File(path).getAbsolutePath());
			}*/
			cursor.close();
			/*cursor1.close();*/
			return lpath;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> bytes) {
            super.onPostExecute(bytes);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
	}
}
