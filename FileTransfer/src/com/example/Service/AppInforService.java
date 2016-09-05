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
	
	/*�õ��ֻ������е�Ӧ�ó�����Ϣ*/
	public List<AppInfor> getAppInfor() throws Exception
	{
		//����Ҫ���صļ��϶���
		List<AppInfor> appInfor =new ArrayList<AppInfor>();
		//��ȡ�ֻ������а�װ��Ӧ�ü���
		List<ApplicationInfo> applicationInfor=pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		//�������е�Ӧ�ü���
		for(ApplicationInfo info : applicationInfor)
		{
			if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0)  
	        {  
				AppInfor appInfo=new AppInfor();
				
				//��ȡӦ�ó����ͼ��
				Drawable app_icon=info.loadIcon(pm);
				appInfo.setIcon(app_icon);
				
				//��ȡӦ������
				String app_name=info.loadLabel(pm).toString();
				appInfo.setName(app_name);
				
				//��ȡӦ�õİ���
				String packageName=info.packageName;
				appInfo.setPackageName(packageName);
				
				//��ȡ�汾��Ϣ
				try
				{
					PackageInfo packageInfo=pm.getPackageInfo(packageName, 0);
					String appversion=packageInfo.versionName;
					appInfo.setAppversion(appversion);
				}catch(NameNotFoundException e){
					e.printStackTrace();
				}
				//��ȡ·��
				String path=info.sourceDir;
				appInfo.setPath(path);
				
				//������ʾ��ǰ���ô�С��Ϣ
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
