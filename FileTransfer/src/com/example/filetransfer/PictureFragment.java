package com.example.filetransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Service.AppInforService;
import com.example.Service.PictureInforService;
import com.example.adapter.AppAdapter;
import com.example.adapter.PictureAdapter;
import com.example.shuxing.AppInfor;
import com.example.shuxing.PictureInfor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class PictureFragment extends Fragment {

	public ListView mListView;
	public ImageView image;
	static List<Map<String,Object>> listpicture;
	static PictureAdapter mlistItemAdapter;
	private PictureInforService pictureInforService;
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
		/*构造SimpleAdapter适配器，将它和ListView自定义的布局文件、List数据源关联*/
		mlistItemAdapter=new PictureAdapter(getActivity(), listpicture, R.layout.activity_onepicture, new String[]{"path","date","size"}, new int[]{R.id.image,R.id.textDate,R.id.textSize});
		mlistItemAdapter.notifyDataSetChanged();
		mListView.setAdapter(mlistItemAdapter);
		
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
