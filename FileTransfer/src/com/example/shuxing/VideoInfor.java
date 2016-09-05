package com.example.shuxing;

import android.graphics.Bitmap;

public class VideoInfor {

	public String name;//��Ƶ����
	public String path;//��Ƶ�洢·��
	public String size;//��Ƶ��С
	public String data;//��������
	public Bitmap media;//��ƵͼƬ
	public String mtime;//��Ƶʱ��
	
	public VideoInfor()
	{
		super();
	}

	public VideoInfor(String name,String path,Bitmap media,
			String data,String size)
	{
		super();
		this.name=name;
		this.path=path;
		this.media=media;
		this.data=data;
		this.size=size;
	}
	public VideoInfor(String name,String path,Bitmap media,
			String data,String size,String mtime)
	{
		super();
		this.name=name;
		this.path=path;
		this.media=media;
		this.data=data;
		this.size=size;
		this.mtime=mtime;
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

	public Bitmap getMedia() {
		return media;
	}

	public void setMedia(Bitmap media) {
		this.media = media;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	
}
