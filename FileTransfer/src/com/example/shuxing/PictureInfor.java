package com.example.shuxing;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class PictureInfor {
	
	public String name;//�ļ�����
	public String path;//�ļ��洢·��
	public String size;//�ļ���С
	public String data;//��������
	
	public PictureInfor()
	{
		super();
	}

	public PictureInfor(String name,String path,String data)
	{
		super();
		this.name=name;
		this.path=path;
		this.data=data;
	}
	public PictureInfor(String name,String path,String data,String size)
	{
		super();
		this.name=name;
		this.path=path;
		this.data=data;
		this.size=size;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
