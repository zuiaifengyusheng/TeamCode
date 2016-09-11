package com.example.filetransfer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Service.FileInforService;
import com.example.adapter.FileAdapter;

import com.example.listener.OneListItemExpandListener;
import com.example.shuxing.FileInfor;

import android.content.Context;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class FileFragment extends Fragment {

	
	
	static List<Map<String,Object>> listFile;
	
	public ExpandableListView list_file;
	public FileAdapter fileAdapter;
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		String[] groupList=new String[5];
		groupList[0]="Word文件";
		groupList[1]="Ppt文件";
		groupList[2]="Excel文件";
		groupList[3]="电子书";
		groupList[4]="压缩文件";
		
		
		//找到这个控件  
        list_file= (ExpandableListView)view.findViewById(R.id.listFile);  
          
        list_file.setAdapter(new FileAdapter(getActivity(), list_file, groupList));
        list_file.expandGroup(0);
        list_file.setOnGroupExpandListener(new OneListItemExpandListener(list_file)); 		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.activity_file,container, false);
	}
	
	
}
