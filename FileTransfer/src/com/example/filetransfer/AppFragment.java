package com.example.filetransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Service.AppInforService;
import com.example.adapter.AppAdapter;
import com.example.shuxing.AppInfor;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class AppFragment extends Fragment {

	public ListView mListView;
	static List<Map<String,Object>> listapplet;
	static AppAdapter mlistItemAdapter;
	private AppInforService appInforService;
	

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	    mListView=(ListView) view.findViewById(R.id.listApplet);
		listapplet=getAppList();
		
		if(listapplet.size()!=0)
		{
			/*构造SimpleAdapter适配器，将它和ListView自定义的布局文件、List数据源关联*/
			mlistItemAdapter=new AppAdapter(getActivity(), listapplet, R.layout.activity_onelist, new String[]{"picture","name","appSize"}, new int[]{R.id.picture,R.id.name,R.id.time});
			mlistItemAdapter.notifyDataSetChanged();
			mListView.setAdapter(mlistItemAdapter);
		}
		
		
		
		
		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub	
		
		return inflater.inflate(R.layout.activity_applet,container, false);
		
	}
	
	private ArrayList<Map<String,Object>> getAppList()
	{

		ArrayList<Map<String,Object>> AppList=new ArrayList<Map<String,Object>>();
		List<AppInfor> listapp=new ArrayList<AppInfor>();
		appInforService=new AppInforService(getActivity());
		try {
			listapp=appInforService.getAppInfor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size=listapp.size();
		for(int i=0;i<size;i++)
		{
			AppInfor appInfor=listapp.get(i);
		    Map<String,Object> map=new HashMap<String,Object>();
			map.put("picture", appInfor.getIcon());
			map.put("name", appInfor.getName());
			map.put("path", appInfor.getPath());
			map.put("packageName",appInfor.getPackageName());
			map.put("appversion", appInfor.getAppversion());
		    map.put("appSize", appInfor.getSize()+" M");
		    AppList.add(map);
			
		}
		return AppList;
	}
}
