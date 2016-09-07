package com.example.filetransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Service.VideoInforService;
import com.example.adapter.VideoAdapter;
import com.example.shuxing.VideoInfor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class VideoFragment extends Fragment {
 
	public ListView mListView;
	static List<Map<String,Object>> listVideo;
	static VideoAdapter mlistItemAdapter;
	private VideoInforService videoInforService;
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
			
		/*构造SimpleAdapter适配器，将它和ListView自定义的布局文件、List数据源关联*/
		mlistItemAdapter=new VideoAdapter(getActivity(), listVideo, R.layout.activity_onevideo, new String[]{"path","vtime","size","name"}, new int[]{R.id.video,R.id.videoTime,R.id.videoSize,R.id.videoName});
		mlistItemAdapter.notifyDataSetChanged();
		mListView.setAdapter(mlistItemAdapter);
	    if(listVideo.size()!=0)
	    {
	    	Toast.makeText(getActivity(), "mmm", Toast.LENGTH_SHORT).show();
	    }
		
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
