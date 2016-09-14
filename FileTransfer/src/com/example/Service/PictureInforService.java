package com.example.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.common.Common;
import com.example.shuxing.PictureInfor;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;

import android.provider.MediaStore;

public class PictureInforService {

	private Context context;
	private List<PictureInfor> list=new ArrayList<PictureInfor>();
	private ArrayList<String> listpath;
	private Common common;
	public PictureInforService(Context context)
	{
		this.context=context;
		common=new Common();
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
			//图片名称
			picture.setName(file.getName());
			//图片路径
			picture.setPath(file.getAbsolutePath());
			
			//图片大小
	        int value=Integer.valueOf((int)file.length());
	        BigDecimal pSize=common.parseApkSize(value);
	        String size=pSize.toString();
			picture.setSize(size);
		
			//图片日期
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
	//异步获取图片信息
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
			cursor.close();
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
