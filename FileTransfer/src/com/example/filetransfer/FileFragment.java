package com.example.filetransfer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.Service.FileInforService;
import com.example.shuxing.FileInfor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FileFragment extends Fragment {

	private ArrayList<FileInfor> list;
	private FileInforService service;
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		service=new FileInforService();
		/*list=service.getAllFileList();*/
		/*Toast.makeText(getActivity(), list.get(0)+"", Toast.LENGTH_SHORT).show();*/
		list=service.getAllFileList();
		if(list==null)
		{
			Toast.makeText(getActivity(), "nininini", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(getActivity(), list.size()+"", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.activity_file,container, false);
	}
	
	

}
