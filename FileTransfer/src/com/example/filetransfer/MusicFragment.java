package com.example.filetransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Service.MusicInforService;
import com.example.Service.VideoInforService;
import com.example.adapter.MusicAdapter;
import com.example.adapter.VideoAdapter;
import com.example.shuxing.MusicInfor;
import com.example.shuxing.VideoInfor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class MusicFragment extends Fragment {

	public ListView mListView;
	static List<Map<String,Object>> listMusic;
	static MusicAdapter mlistItemAdapter;
	private MusicInforService musicInforService;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.activity_music,container, false);
	}
	
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		mListView=(ListView) view.findViewById(R.id.listMusic);
	    listMusic=getVideoList();
			
		/*构造SimpleAdapter适配器，将它和ListView自定义的布局文件、List数据源关联*/
		mlistItemAdapter=new MusicAdapter(getActivity(), listMusic, R.layout.activity_onemusic, new String[]{"vtime","size","name"}, new int[]{R.id.musicTime,R.id.musicSize,R.id.musicName});
		mlistItemAdapter.notifyDataSetChanged();
		mListView.setAdapter(mlistItemAdapter);
	    if(listMusic.size()!=0)
	    {
	    	Toast.makeText(getActivity(), "mmm", Toast.LENGTH_SHORT).show();
	    }
		
	}
	
	private ArrayList<Map<String,Object>> getVideoList()
	{

		ArrayList<Map<String,Object>> videoList=new ArrayList<Map<String,Object>>();
		List<MusicInfor> listvideo=new ArrayList<MusicInfor>();
		musicInforService=new MusicInforService(getActivity());
		
		listvideo=musicInforService.getVideoInfor();
		
		int size=listvideo.size();
		if(size==0)
		{
			return null;
		}
		for(int i=0;i<size;i++)
		{
			MusicInfor musicInfor=listvideo.get(i);
		    Map<String,Object> map=new HashMap<String,Object>();
			map.put("name", musicInfor.getName());
			map.put("path", musicInfor.getPath());
			map.put("date", musicInfor.getData());
			map.put("vtime",musicInfor.getMtime());
		    map.put("size", musicInfor.getSize()+" M");
		    videoList.add(map);
			
		}
		return videoList;
	}

}
