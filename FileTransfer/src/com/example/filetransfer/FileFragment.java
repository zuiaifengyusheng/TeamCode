package com.example.filetransfer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Service.FileInforService;

import com.example.shuxing.FileInfor;

import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FileFragment extends Fragment {

	
	private FileInforService service;
	static List<Map<String,Object>> listFile;
	private List<Map<String,Object>> listWord;
	private List<Map<String,Object>> listPpt;
	private List<Map<String,Object>> listExcel;
	private List<Map<String,Object>> listZip;
	private List<Map<String,Object>> listPdf;
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		service=new FileInforService(getActivity());
		/*list=service.getAllFileList();*/
		/*Toast.makeText(getActivity(), list.get(0)+"", Toast.LENGTH_SHORT).show();*/
		/*listFile=getFileList();*/
		listExcel=new ArrayList<Map<String,Object>>();
		listPdf=new ArrayList<Map<String,Object>>();
		listWord=new ArrayList<Map<String,Object>>();
		listPpt =new ArrayList<Map<String,Object>>();
		listZip=new ArrayList<Map<String,Object>>();
		getFileList();
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.activity_file,container, false);
	}
	
	private ArrayList<FileInfor> getFileList()
	{

		ArrayList<File> files=new ArrayList<File>();
		service=new FileInforService(getActivity());
		
		files=service.getAllFileList();
		
		int fsize=files.size();
		if(fsize==0)
		{
			return null;
		}
		for(int i=0;i<fsize;i++)
		{
			File file=files.get(i);
			String name=file.getName();
			int a=0;
			if(name.endsWith("xls")||name.endsWith("xlsx")||name.endsWith("xlsm")||name.endsWith("xltm")||name.endsWith("xlsb")||name.endsWith("xlam"))
			{
/*				Bitmap icon=BitmapFactory.decodeResource(getResources(), R.drawable.excel);	*/
				a=1;
			}
			if(name.endsWith("pptx")||name.endsWith("pptm")||name.endsWith("ppsx")||name.endsWith("potx")||name.endsWith("potm")||name.endsWith("ppam"))
			{
				a=2;
			}
			if(name.endsWith("docx")||name.endsWith("docm")||name.endsWith("dotx")||name.endsWith("dotm"))
			{
				a=3;
			}
			if(name.endsWith("umd")||name.endsWith("pdf")||name.endsWith("txt")||name.endsWith("edk")||name.endsWith("chm"))
			{
				a=4;
			}
			if(name.endsWith("zip")||name.endsWith("rar")||name.endsWith("iso")||name.endsWith("7z"))
			{
				a=5;
			}
			if(a==0)
			{
				break;
			}
		    Map<String,Object> map=new HashMap<String,Object>();
		    String str = "文件名称:" + file.getName() + " 路径:"+ file.getPath();  
            System.out.println(str);  
            FileInfor fileInfor=new FileInfor();           
            String path=file.getAbsolutePath();
            int size=(int)file.length();         	            	  
            String fileSize=parseApkSize(size);
            String date=null;
            ExifInterface exif;
			try {
				exif = new ExifInterface(file.getPath());
				date=exif.getAttribute(ExifInterface.TAG_DATETIME);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("name", name);
			Toast.makeText(getActivity(), fileInfor.getName(),Toast.LENGTH_SHORT).show();
			map.put("path", path);
			map.put("date",date);
		    map.put("size", fileSize);
		    
		    if(a==1)
		    {
		    	map.put("icon", R.drawable.excel);
		    	listExcel.add(map);
		    }
			if(a==2)
			{
				map.put("icon", R.drawable.ppt);
				listPpt.add(map);
			}
			if(a==3)
		    {
				map.put("icon", R.drawable.word);
		    	listWord.add(map);
		    }
			if(a==4)
			{
				map.put("icon", R.drawable.pdf);
				listPdf.add(map);
			}
			if(a==5)
		    {
				map.put("icon", R.drawable.zip);
		    	listZip.add(map);
		    }
			
		}
		return null;
	}
	private String parseApkSize(int size) {
		BigDecimal bd;
		BigDecimal setScale;
		String str=null;
		if(size/1024>0)
		{
			bd=new BigDecimal((double)size/(1024));
			setScale = bd.setScale(2, BigDecimal.ROUND_DOWN);
			str=setScale.toString()+"KB";  
		}
		else
		{
			bd = new BigDecimal((double)size/(1024*1024));
			setScale = bd.setScale(2, BigDecimal.ROUND_DOWN);
			str=setScale.toString()+"M";  
		}
	    
	          
	    return str;
	}
}
