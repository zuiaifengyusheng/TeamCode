package com.example.shuxing;

public class VideoInfor {

	public String name;//��Ƶ����
	public String path;//��Ƶ�洢·��
	public long size;//��Ƶ��С
	public String data;//��������
	public String mtime;//��Ƶʱ��
	
	public VideoInfor()
	{
		super();
	}

	public VideoInfor(String name,String path,
			String data,long size)
	{
		super();
		this.name=name;
		this.path=path;
		this.data=data;
		this.size=size;
	}
	public VideoInfor(String name,String path,
			String data,long size,String mtime)
	{
		super();
		this.name=name;
		this.path=path;
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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	
}
