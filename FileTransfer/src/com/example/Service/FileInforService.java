package com.example.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Images;
import android.widget.Toast;

import com.bumptech.glide.load.model.file_descriptor.FileDescriptorFileLoader;
import com.example.shuxing.FileInfor;
import com.example.shuxing.VideoInfor;


@SuppressLint("NewApi")
public class FileInforService {

	public File path1;
    public ArrayList<FileInfor> listfile;
    public Context context;
    public ArrayList<FileInfor> lpath;
    public ArrayList<File>  afile;
    public FileInforService(Context context)
	{
		this.context=context;
	}

    
	/*获取所有文件信息*/
	public ArrayList<File> getAllFileList()
	{	
		listfile=new ArrayList<FileInfor>();
		afile=new ArrayList<File>();
		File file=Environment.getExternalStorageDirectory();
		File[] files=file.listFiles();
		runFile(files);

		/*Thread thread=new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<afile.size();i++)
				{
					
				}
				
			}  	            		 
       	 };
 		 thread.start(); 
		Toast.makeText(context, listfile.size()+"", Toast.LENGTH_SHORT).show();*/
	    return afile;
	}
	public void runFile(File[] file)
	{
		
  	     if (file != null) {  
  	          for (int i=0;i<file.length;i++) {  
  	        	  File file2=file[i];
  	        	  System.out.println(file2.toString());
  	              if (file2.listFiles() == null) {  
  	            	  System.out.println(file2.isFile());
	            	  if(file2.isFile())
	            	  {
	            		 /* String str = "文件名称:" + file2.getName() + " 路径:"+ file2.getPath();  
		                  System.out.println(str);  
		            	  FileInfor fileInfor=new FileInfor();
		            	  fileInfor.setName(file2.getName());
		            	  fileInfor.setPath(file2.getAbsolutePath());
		            	  int size=(int)file2.length();
		            	  BigDecimal big=parseApkSize(size);	            	  
		            	  fileInfor.setSize(big.toString());
		            	  String date=null;
		            	  ExifInterface exif;
						  try {
							exif = new ExifInterface(file2.getPath());
							date=exif.getAttribute(ExifInterface.TAG_DATETIME);
						  } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						  }
		 			      
		            	  fileInfor.setData(date);
		            	  lpath.add(fileInfor); */
	            		  afile.add(file2);
		              }
	              }
  	              else
  	              { 
  	            	 final File[] newFileList=file2.listFiles();
  	            	 
  	            	 Thread thread=new Thread(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							 runFile(newFileList);
						}  	            		 
  	            	 };
  	      		     thread.start(); 
  	              }
  	          }
  	     }
	}
/*	public class MyFile extends AsyncTask<String, Integer, ArrayList<FileInfor>> {

		
        @SuppressLint("NewApi")
		@Override
        protected ArrayList<FileInfor> doInBackground(String... params) {
        	ArrayList<FileInfor> lpath=new ArrayList<FileInfor>();
        	File file=Environment.getExternalStorageDirectory();
            Uri uri = MediaStore.Files.getContentUri(file.getAbsolutePath());
            
            Toast.makeText(context,uri+"", Toast.LENGTH_SHORT).show();
            ContentResolver mContentResolver =context.getContentResolver();
			Cursor cursor=mContentResolver.query(uri, null, null, null,null);
			Toast.makeText(context, cursor.getCount()+"", Toast.LENGTH_SHORT).show();
            if(uri.getScheme().equals("file"))
            {
            	String path=uri.getEncodedPath();
            	if(path!=null)
            	{
            		path=Uri.decode(path);
            		ContentResolver cr=context.getContentResolver();
            		buff.append("(").append(Files.FileColumns.DATA).append("=").append(""+path+"").append(")");
            		
            		 Toast.makeText(context, buff, Toast.LENGTH_SHORT).show();
            	}
            
            
            
            Toast.makeText(context, , Toast.LENGTH_SHORT).show();
            
            ContentResolver mContentResolver =context.getContentResolver();
			Cursor cursor=mContentResolver.query(mVideoUri, null, null, null,null);
			Toast.makeText(context, cursor.getCount()+"", Toast.LENGTH_SHORT).show();
			while(cursor.moveToNext())
			{
				FileInfor video=new FileInfor();
				
				int id=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
				String name=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
				String path=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
				String data=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED));
				long ltime=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
				int lsize=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
				BigDecimal pSize=parseApkSize(lsize);
		        String size=pSize.toString();
				video=new FileInfor(name,path,data,size);
				lpath.add(video);
				
			}
			cursor.close();
			return lpath;
        }
        public void filePath(File file) {  
        	
    		Toast.makeText(context, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        	
    		
    		
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<FileInfor> bytes) {
            super.onPostExecute(bytes);
        }

	}*/
	
}
