package com.example.Service;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.os.Environment;

import com.example.shuxing.FileInfor;

public class FileInforService {

	public File path1;
	public File path2;
    public ArrayList<FileInfor> listfile;
	
	/*获取所有文件信息*/
	public ArrayList<FileInfor> getAllFileList()
	{	
		listfile=new ArrayList<FileInfor>();
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{              
			path1= Environment.getExternalStorageDirectory();      			
		}
		path2=Environment.getDataDirectory();
		File[] files=path2.listFiles();
		
		
		/*getAllFiles(path1);*/
		getAllFiles(files);
		return listfile;
		
	}
	@SuppressLint("SimpleDateFormat")
	private void getAllFiles(File[] files)
	{                   
		if(files != null)
		{                
		    for (File f : files)
		    {                    
		    	if(f.isDirectory())
		    	{                       
		    		getAllFiles(f.listFiles());                   
		    	}
		    	else
		    	{                  
		    		String filename=f.getName();
		    		String filePath=f.getPath();
		    		int value=Integer.valueOf((int)f.length());
			        BigDecimal apkSize=parseApkSize(value);
			        String fileSize=apkSize.toString();
			        Calendar cal = Calendar.getInstance();  
			        long fTime = f.lastModified();  
			        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			        cal.setTimeInMillis(fTime); 
			        String fileTime=formatter.format(cal.getTime());
		    		FileInfor map=new FileInfor();
		    		map.setName(filename);
		    		map.setPath(filePath);
		    		map.setSize(fileSize);
		    		map.setData(fileTime);
		    		listfile.add(map);
		    	}           
		    }
		}  
	}
	private BigDecimal parseApkSize(int size) {
	    BigDecimal bd = new BigDecimal((double)size/(1024*1024));
	                BigDecimal setScale = bd.setScale(2, BigDecimal.ROUND_DOWN);
	    return setScale;
	}
}
