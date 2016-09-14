package com.example.filetransfer;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bumptech.glide.util.Util;
import com.example.Service.AppInforService;
import com.example.Service.PictureInforService;
import com.example.adapter.AppAdapter;
import com.example.adapter.PictureAdapter;
import com.example.common.Common;
import com.example.dialog.InputDialog;
import com.example.dialog.MyDialog;
import com.example.shuxing.AppInfor;
import com.example.shuxing.FileInfor;
import com.example.shuxing.PictureInfor;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PictureFragment extends Fragment {

	public ListView mListView;
	public ImageView image;
	static List<Map<String,Object>> listpicture;
	static PictureAdapter mlistItemAdapter;
	private PictureInforService pictureInforService;
	private TextView path;
	private Common common;
	public int MID;  
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.activity_picture,container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onViewCreated(view, savedInstanceState);
		mListView=(ListView) view.findViewById(R.id.listPicture);
	    listpicture=getPictureList();
	    image=(ImageView)view.findViewById(R.id.picture);
	    common=new Common();
	    
	    if(listpicture.size()!=0)
	    {
	    	/*构造SimpleAdapter适配器，将它和ListView自定义的布局文件、List数据源关联*/
			mlistItemAdapter=new PictureAdapter(getActivity(), listpicture, R.layout.activity_onepicture, new String[]{"path","date","size","path"}, new int[]{R.id.image,R.id.textDate,R.id.textSize,R.id.picturePath});
			mlistItemAdapter.notifyDataSetChanged();
			mListView.setAdapter(mlistItemAdapter);
			onItemLongClick();
	    }
		
		
		
	}
	
	public void onItemLongClick() {
		
		mListView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  

			    @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) { 
                        menu.add(0,0,0,"删除");  
                        menu.add(0,1,0,"重命名"); 
                        menu.add(0,2,0,"属性"); 

                }
        });  
	}
	@SuppressLint("InflateParams")
	public boolean onContextItemSelected(MenuItem item) {  
		   
         AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item  
                         .getMenuInfo();  
         MID = (int) info.id;// 这里的info.id对应的就是数据库中_id的值  
         LinearLayout layout = (LinearLayout)mListView.getChildAt(MID-mListView.getFirstVisiblePosition());
         path=(TextView) layout.findViewById(R.id.picturePath);
         final String str=(String) path.getText();
         final Map<String,Object> map=new HashMap<String,Object>();
         final File file1=new File(str);
	        
         final String nameStr=file1.getName();
		 map.put("name", nameStr);
		 final String pathStr=file1.getAbsolutePath();
		 map.put("path", pathStr);
		 String date=null;
		 try{
			 ExifInterface exif = new ExifInterface(file1.getPath());
			 date=exif.getAttribute(ExifInterface.TAG_DATETIME);
		 }
		 catch(Exception ee){
		 }
		 map.put("date", date);
		 int value=Integer.valueOf((int)file1.length());
	     BigDecimal pSize=common.parseApkSize(value);
	     final String size=pSize.toString();
		 map.put("size", size+" M"); 
         
         switch(item.getItemId()) {  
         case 0:  
                 // 删除操作  
        	    ContentResolver resolver = getActivity().getContentResolver();  
        	    Cursor cursor = MediaStore.Images.Media.query(resolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { MediaStore.Images.Media._ID }, MediaStore.Images.Media.DATA + "=?",  
        	            new String[] {str}, null);  
        	    boolean result = false;  
        	    if (cursor.moveToFirst()) {  
        	        long id = cursor.getLong(0);  
        	        Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;  
        	        Uri uri = ContentUris.withAppendedId(contentUri, id);  
        	        
        	        int count =getActivity().getContentResolver().delete(uri, null, null);       	       
        	        if(count == 1)
        	        {
        	        	 result = true;  
        	        }
        	       
        	    } else {  
        	        File file = new File(str);  
        	        result = file.delete();  
        	    }  
        	  
        	    if (result) {  
        	    	listpicture.remove(map);
        	    	mlistItemAdapter.notifyDataSetChanged();
/*        	    	mListView.setAdapter(mlistItemAdapter);*/
        	        Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();  
        	    } 
        	    break;
         case 1:   
        	    
                 // 重命名
        	     final InputDialog.Builder Ibuilder = new InputDialog.Builder(getActivity());  
                 Ibuilder.setMessage(nameStr);  
                 Ibuilder.setTitle("重命名");  
                 Ibuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
                 public void onClick(DialogInterface dialog, int which) {  
                	 
                   
                     dialog.dismiss();  
                 }  
                 });  
                 Ibuilder.setNegativeButton("取消",  
                         new android.content.DialogInterface.OnClickListener() {  
                             public void onClick(DialogInterface dialog, int which) {  
                                 dialog.dismiss();  
                             }  
                         });  
                 Ibuilder.create().show(); 
                 break;  

         case 2:
        	    //属性
        	    MyDialog.Builder builder = new MyDialog.Builder(getActivity());  
        	    if(date==null)
        	    {
        		    date="无";
        	    }
        	 
                builder.setMessage("名称："+nameStr+"\n路径："+pathStr+"\n拍摄日期："+date+"\n大小："+size+"M");  
                builder.setTitle("属性");  
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
                public void onClick(DialogInterface dialog, int which) {  
                     dialog.dismiss();  
                     //设置你的操作事项  
                 }  
                });  
                builder.create().show();  
        	 
        	    break;
         default:  
                 break;  
         }  
         return super.onContextItemSelected(item);  

    }  
	
	
	private ArrayList<Map<String,Object>> getPictureList()
	{

		ArrayList<Map<String,Object>> AppList=new ArrayList<Map<String,Object>>();
		List<PictureInfor> listapp=new ArrayList<PictureInfor>();
		pictureInforService=new PictureInforService(getActivity());
		
		listapp=pictureInforService.getPictureInfor();
		
		int size=listapp.size();

		for(int i=0;i<size;i++)
		{
			PictureInfor pictureInfor=listapp.get(i);
		    Map<String,Object> map=new HashMap<String,Object>();
			map.put("name", pictureInfor.getName());
			map.put("path", pictureInfor.getPath());
			map.put("date", pictureInfor.getData());
		    map.put("size", pictureInfor.getSize()+" M");
		    AppList.add(map);
			
		}
		return AppList;
	}
	

}
