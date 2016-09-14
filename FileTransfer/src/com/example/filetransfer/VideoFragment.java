package com.example.filetransfer;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Service.VideoInforService;
import com.example.adapter.VideoAdapter;
import com.example.common.Common;
import com.example.dialog.MyDialog;
import com.example.shuxing.VideoInfor;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VideoFragment extends Fragment {
 
	public ListView mListView;
	static List<Map<String,Object>> listVideo;
	static VideoAdapter mlistItemAdapter;
	private VideoInforService videoInforService;
	private TextView path;
	private Common common;
	public int MID;
	Calendar cal = Calendar.getInstance();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.activity_video,container, false);
	}
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		mListView=(ListView) view.findViewById(R.id.listVideo);
	    listVideo=getVideoList();
	    common=new Common();	
	    
	    if(listVideo.size()!=0)
	    {
	    	/*构造SimpleAdapter适配器，将它和ListView自定义的布局文件、List数据源关联*/
			mlistItemAdapter=new VideoAdapter(getActivity(), listVideo, R.layout.activity_onevideo, new String[]{"path","vtime","size","name","path"}, new int[]{R.id.video,R.id.videoTime,R.id.videoSize,R.id.videoName,R.id.videoPath});
			mlistItemAdapter.notifyDataSetChanged();
			mListView.setAdapter(mlistItemAdapter);
			onItemLongClick();
	    }
		
		
	}
	//长按事件
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
    //弹出菜单点击事件
	@SuppressLint("InflateParams")
	public boolean onContextItemSelected(MenuItem item) {  
		   
         AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item  
                         .getMenuInfo();  
         MID = (int) info.id;// 这里的info.id对应的就是数据库中_id的值  
         LinearLayout layout = (LinearLayout)mListView.getChildAt(MID-mListView.getFirstVisiblePosition());
         path=(TextView) layout.findViewById(R.id.videoPath);
         
         final String str=(String) path.getText();
         Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();

         ContentResolver resolver = getActivity().getContentResolver();  
 	     Cursor cursor = resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Video.Media.DATA + "=?",  
 	            new String[] {str}, null);
 	     Toast.makeText(getActivity(), cursor.getCount()+"", Toast.LENGTH_SHORT).show();
 	     if(cursor.getCount()==0)
 	     {
 	    	 return false;
 	     }
 	     cursor.moveToFirst();
 	     final String nameStr=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
 	     
 	     final String pathStr=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
 	     
 	     long updateTime=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED));
 	     updateTime = updateTime*1000;
		 //格式化时间，获取年，月，日
		 String[] times =  common.getTimeInfo(updateTime);
		 String date=times[0]+"年"+times[1]+"月"+times[2]+"日    "+times[3]+":"+times[4]+":"+times[5];
 	     
		 long ltime=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
		 String time=common.GetTime(ltime);
		 
		 int lsize=cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
		 BigDecimal pSize=common.parseApkSize(lsize);
         long size=pSize.longValue();
         long id=0;
         id=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
         Map<String,Object> map=new HashMap<String,Object>();
		 map.put("name", nameStr);		 
		 map.put("path", pathStr);		 
		 map.put("date", date);
		 map.put("vtime", time);
		 map.put("size", size+" M"); 
       
         switch(item.getItemId()) {  
         case 0:  
                 // 删除操作  
        	    boolean result = false; 
        	    Toast.makeText(getActivity(), id+"", Toast.LENGTH_SHORT).show();
        	    if (id!=0) {  
        	        Uri contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;  
        	        Uri uri = ContentUris.withAppendedId(contentUri, id);  
        	        
        	        int count =getActivity().getContentResolver().delete(uri, null, null);      	 
        	    	
        	        if(count==1)
        	        {
        	        	 result = true;  
        	        }
        	       
        	    } else {  
        	        File file = new File(str);  
        	        result = file.delete();  
        	    }  
        	    if (result) {  
        	    	/*listMusic.remove(map);*/
        	    	Toast.makeText(getActivity(), map.get("name")+"", Toast.LENGTH_SHORT).show();
        	    	Toast.makeText(getActivity(), listVideo.remove(map)+"", Toast.LENGTH_LONG).show();
        	    	mlistItemAdapter.notifyDataSetChanged();
        	        Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();  
        	    } 
        	    break;
         case 1:   
        	     
        	     Toast.makeText(getActivity(), nameStr+" "+pathStr+" "+size+" "+date+" "+time, Toast.LENGTH_SHORT).show();
                 /*// 重命名
        	     final InputDialog.Builder Ibuilder = new InputDialog.Builder(getActivity());  
                 Ibuilder.setMessage(nameStr);  
                 Ibuilder.setTitle("重命名");  
                 Ibuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
                 public void onClick(DialogInterface dialog, int which) {  
                	 
                     String message = Ibuilder.getPrice();
                     
                     int i=nameStr.lastIndexOf(".");
                     if ((i >-1) && (i < (nameStr.length() - 1))) { 
                         message=message+nameStr.substring(i); 
                         Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
                     } 
                     
                     if(file1.exists())
                     {
                    	 File newPath=null;
                    	 int j=pathStr.lastIndexOf("/");
                    	 Toast.makeText(getActivity(),j+"", Toast.LENGTH_SHORT).show();
                         if ((j >-1) && (j < (pathStr.length() - 1))) { 
                        	 Toast.makeText(getActivity(),pathStr.substring(0,j+1), Toast.LENGTH_SHORT).show();
                             newPath=new File(pathStr.substring(0,j+1)+message); 
                         } 
                         Toast.makeText(getActivity(), newPath+"", Toast.LENGTH_SHORT).show();
                         File file2=new File(str);
                    	 file2.renameTo(newPath);
                    	 Intent mediaScanIntent = new Intent(
                    	            Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    	    Uri contentUri = Uri.fromFile(file2);
                    	    mediaScanIntent.setData(contentUri);
                    	    getActivity().sendBroadcast(mediaScanIntent);
                    	 Toast.makeText(getActivity(), file2.getAbsolutePath()+"   "+file2.getName(), Toast.LENGTH_SHORT).show();
                    	 listpicture.remove(map);
                         Map<String,Object> newMap=new HashMap<String,Object>();
                	        
                         String newName=file2.getName();
                         newMap.put("name", newName);
                		 String newpath=file2.getAbsolutePath();
                		 newMap.put("path", newpath);
                		 String newDate=null;
                		 try{
                			 ExifInterface exif = new ExifInterface(file2.getPath());
                			 newDate=exif.getAttribute(ExifInterface.TAG_DATETIME);
                		 }
                		 catch(Exception ee){
                		 }
                		 newMap.put("date", newDate);
                		 int newValue=Integer.valueOf((int)file2.length());
                		 Toast.makeText(getActivity(), file2.length()+"   ", Toast.LENGTH_SHORT).show();
                	     BigDecimal pSize=parseApkSize(newValue);
                	     String newSize=pSize.toString();
                	     newMap.put("size", newSize+" M"); 
                	     
                	     listpicture.add(newMap);
                	     mlistItemAdapter.notifyDataSetChanged();
                	     mListView.setAdapter(mlistItemAdapter);
                	     Toast.makeText(getActivity(), "重命名成功", Toast.LENGTH_LONG).show();
                     }
                     else
                     {
                    	 Toast.makeText(getActivity(), "文件已不存在", Toast.LENGTH_LONG).show();
                     }
                     //设置你的操作事项  
                     dialog.dismiss();  
                 }  
                 });  
                 Ibuilder.setNegativeButton("取消",  
                         new android.content.DialogInterface.OnClickListener() {  
                             public void onClick(DialogInterface dialog, int which) {  
                                 dialog.dismiss();  
                             }  
                         });  
                 Ibuilder.create().show();  */
                 break;  

         case 2:
        	    //属性
        	    MyDialog.Builder builder = new MyDialog.Builder(getActivity());  
        	 
                builder.setMessage("名称："+nameStr+"\n路径："+pathStr+"\n日期："+date+"\n大小："+size+"M \n时长："+time);  
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

	private ArrayList<Map<String,Object>> getVideoList()
	{

		ArrayList<Map<String,Object>> videoList=new ArrayList<Map<String,Object>>();
		List<VideoInfor> listvideo=new ArrayList<VideoInfor>();
		videoInforService=new VideoInforService(getActivity());
		
		listvideo=videoInforService.getVideoInfor();
		
		int size=listvideo.size();
		if(size==0)
		{
			return null;
		}
		for(int i=0;i<size;i++)
		{
			VideoInfor videoInfor=listvideo.get(i);
		    Map<String,Object> map=new HashMap<String,Object>();
			map.put("name", videoInfor.getName());
			map.put("path", videoInfor.getPath());
			map.put("date", videoInfor.getData());
			map.put("vtime", videoInfor.getMtime());
		    map.put("size", videoInfor.getSize()+" M");
		    videoList.add(map);
			
		}
		return videoList;
	}
	
	

}
