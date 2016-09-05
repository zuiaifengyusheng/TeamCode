package com.example.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.example.shuxing.AppInfor;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;


public class AppInforService {
	
	private Context context;
	private PackageManager pm;
	public AppInforService(Context context)
	{
		this.context=context;
		pm=context.getPackageManager();
	}
	
	/*得到手机中所有的应用程序信息*/
	public List<AppInfor> getAppInfor() throws Exception
	{
		//创建要返回的集合对象
		List<AppInfor> appInfor =new ArrayList<AppInfor>();
		//获取手机中所有安装的应用集合
		List<ApplicationInfo> applicationInfor=pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		//遍历所有的应用集合
		for(ApplicationInfo info : applicationInfor)
		{
			if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0)  
	        {  
				AppInfor appInfo=new AppInfor();
				
				//获取应用程序的图标
				Drawable app_icon=info.loadIcon(pm);
				appInfo.setIcon(app_icon);
				
				//获取应用名称
				String app_name=info.loadLabel(pm).toString();
				appInfo.setName(app_name);
				
				//获取应用的包名
				String packageName=info.packageName;
				appInfo.setPackageName(packageName);
				
				//获取版本信息
				try
				{
					PackageInfo packageInfo=pm.getPackageInfo(packageName, 0);
					String appversion=packageInfo.versionName;
					appInfo.setAppversion(appversion);
				}catch(NameNotFoundException e){
					e.printStackTrace();
				}
				//获取路径
				String path=info.sourceDir;
				appInfo.setPath(path);
				
				//更新显示当前包得大小信息
				String dir=info.publicSourceDir;
		        int value=Integer.valueOf((int)new File(dir).length());
		        BigDecimal apkSize=parseApkSize(value);
		        String size=apkSize.toString();
		        appInfo.setSize(size);
				appInfor.add(appInfo);
	        }
			
			
		}
		return appInfor;
	}
	private BigDecimal parseApkSize(int size) {
	    BigDecimal bd = new BigDecimal((double)size/(1024*1024));
	                BigDecimal setScale = bd.setScale(2, BigDecimal.ROUND_DOWN);
	    return setScale;
	}
}
