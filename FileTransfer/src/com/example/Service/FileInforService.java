package com.example.Service;

import java.io.File;
import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

import com.example.shuxing.FileInfor;


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

}
