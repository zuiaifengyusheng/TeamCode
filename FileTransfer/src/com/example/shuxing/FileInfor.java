package com.example.shuxing;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class FileInfor {

	public String name;//文件名称
	public String path;//文件存储路径
	public String size;//文件大小
	public String data;//修改日期
	public Bitmap icon;//文件图片
	
	public FileInfor()
	{
		super();
	}
	public FileInfor(String name,String path,
			String data,String size)
	{
		super();
		this.name=name;
		this.path=path;
		this.data=data;
		this.size=size;
	}
	public FileInfor(String name,String path,Bitmap icon,
			String data,String size)
	{
		super();
		this.name=name;
		this.path=path;
		this.icon=icon;
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

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}
	
	
}
