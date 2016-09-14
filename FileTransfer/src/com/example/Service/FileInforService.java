package com.example.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import com.example.shuxing.FileInfor;
import com.example.shuxing.MusicInfor;


@SuppressLint("NewApi")
public class FileInforService {

	public File path1;
    public ArrayList<FileInfor> listfile;
    public Context context;
   
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
		/*MyFile my=new MyFile();
		ArrayList<String> listStr=new ArrayList<String>();
		listStr=my.doInBackground();
		for(int i=0;i<listStr.size();i++)
		{
			afile.add(new File(listStr.get(i)));
		}*/
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
	public class MyFile extends AsyncTask<String, Integer, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
        	ArrayList<String> lpath=new ArrayList<String>();
         	Uri mDocumentUri = MediaStore.Files.getContentUri("external");
         	System.out.println(mDocumentUri+"");
     		// 要查询的列
     		String[] mediaColumns = new String[] {
     			MediaStore.Files.FileColumns.DATA,};
     		    
     		    ContentResolver contentResolver =context.getContentResolver();
     		    // 获取文档
     		   String selection = MediaStore.Files.FileColumns.MIME_TYPE + "=? or "  
     				    + MediaStore.Files.FileColumns.MIME_TYPE + "=? or "  
     				    + MediaStore.Files.FileColumns.MIME_TYPE + "=? or "  
     				    + MediaStore.Files.FileColumns.MIME_TYPE + "=? or "  
     				    + MediaStore.Files.FileColumns.MIME_TYPE + "=? ";  
     		   
     			MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();  
     			String mimeTypeZip = mimeTypeMap.getMimeTypeFromExtension("zip");  
     			String mimeTypeRAR = mimeTypeMap.getMimeTypeFromExtension("pdf");
     			String mimeTypeWord = mimeTypeMap.getMimeTypeFromExtension("doc");
     			String mimeTypePPT = mimeTypeMap.getMimeTypeFromExtension("ppt");
     			String mimeTypeE= mimeTypeMap.getMimeTypeFromExtension("xlsx");
     			/*String mimeTypeTXT = mimeTypeMap.getMimeTypeFromExtension("txt");*/
     			String[] selectionArgs = new String[] { mimeTypeZip, mimeTypeRAR,mimeTypeWord,mimeTypePPT,mimeTypeE};
     		    Cursor cursor = contentResolver.query(mDocumentUri, mediaColumns,selection, selectionArgs, null);
     		    System.out.println(cursor.getCount()+"");
     		    while(cursor.moveToFirst())
     		    {
     		    	String path=cursor.getString(0);
     		    	System.out.println(path);
        		    	File file=new File(path);
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
