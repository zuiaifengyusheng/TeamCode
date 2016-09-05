package com.example.shuxing;


import android.graphics.drawable.Drawable;

public class AppInfor{
	
	public String name;//Ӧ������
	public String path;//Ӧ��·��
	public Drawable icon;//Ӧ��ͼ��
	public String packageName;//����
	public String appversion;//Ӧ�ð汾��
	public String size;//APP��С

	public AppInfor()
	{
		super();
	}
	public AppInfor(String name,String path,Drawable icon,
			String packageName,String appversion)
	{
		super();
		this.name=name;
		this.path=path;
		this.icon=icon;
		this.packageName=packageName;
		this.appversion=appversion;
	}
	public AppInfor(String name,String path,Drawable icon,
			String packageName,String appversion,String size)
	{
		super();
		this.name=name;
		this.path=path;
		this.icon=icon;
		this.packageName=packageName;
		this.appversion=appversion;
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
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getAppversion() {
		return appversion;
	}
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
}
