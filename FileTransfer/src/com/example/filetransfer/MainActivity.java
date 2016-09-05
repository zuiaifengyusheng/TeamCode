package com.example.filetransfer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends FragmentActivity implements OnClickListener{

	private LinearLayout mTabFile;
	private LinearLayout mTabPicture;
	private LinearLayout mTabMusic;
	private LinearLayout mTabVideo;
	private LinearLayout mTabApp;
	
	private TextView mTextFile;
	private TextView mTextPicture;
	private TextView mTextMusic;
	private TextView mTextVideo;
	private TextView mTextApp;
	
	private Fragment mFrag01;
	private Fragment mFrag02;
	private Fragment mFrag03;
	private Fragment mFrag04;
	private Fragment mFrag05;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvents();
        setSelect(0);
    }
    
    //事件
	private void initEvents() {
		// TODO Auto-generated method stub
		mTabFile.setOnClickListener(this);
		mTabPicture.setOnClickListener(this);
		mTabMusic.setOnClickListener(this);
		mTabVideo.setOnClickListener(this);
		mTabApp.setOnClickListener(this);
	}


	//初始化控件
	private void initView() {
		// TODO Auto-generated method stub
		mTabFile=(LinearLayout) findViewById(R.id.bar_01);
		mTabPicture=(LinearLayout) findViewById(R.id.bar_02);
		mTabMusic=(LinearLayout) findViewById(R.id.bar_03);
		mTabVideo=(LinearLayout) findViewById(R.id.bar_04);
		mTabApp=(LinearLayout) findViewById(R.id.bar_05);
		
		mTextFile=(TextView) findViewById(R.id.filebar);
		mTextPicture=(TextView) findViewById(R.id.picturebar);
		mTextMusic=(TextView) findViewById(R.id.musicbar);		
		mTextVideo=(TextView) findViewById(R.id.videobar);
		mTextApp=(TextView) findViewById(R.id.appbar);
		
		mFrag01=new FileFragment();
		mFrag02=new PictureFragment();
		mFrag03=new MusicFragment();
		mFrag04=new VideoFragment();
		mFrag05=new AppFragment();
	}

	private void setSelect(int i)
	{
		FragmentManager fm=getSupportFragmentManager();
		FragmentTransaction tran=fm.beginTransaction();
		if(mFrag01==null)
		{
			mFrag01=new FileFragment();
		}
		if(mFrag02==null)
		{
			mFrag02=new PictureFragment();
		}
		if(mFrag03==null)
		{
			mFrag03=new MusicFragment();
		}
		if(mFrag04==null)
		{
			mFrag04=new VideoFragment();
		}
		if(mFrag05==null)
		{
			mFrag05=new AppFragment();
		}
		//改变标签颜色，设置内容区域
		switch (i) {
		case 0:
			
			tran.replace(R.id.framelayout,mFrag01);
			mTextFile.setTextColor(Color.parseColor("#23238E"));
			
			break;
		case 1:
			tran.replace(R.id.framelayout,mFrag02);
			mTextPicture.setTextColor(Color.parseColor("#23238E"));
			
			break;
		case 2:
			tran.replace(R.id.framelayout,mFrag03);
			mTextMusic.setTextColor(Color.parseColor("#23238E"));
			
			break;
		case 3:
			tran.replace(R.id.framelayout,mFrag04);
			mTextVideo.setTextColor(Color.parseColor("#23238E"));
			
			break;
		case 4:
			tran.replace(R.id.framelayout,mFrag05);
			mTextApp.setTextColor(Color.parseColor("#23238E"));
			
			break;

		default:
			break;
		}
		tran.commit();
	}

/*	private void hideFragment(FragmentTransaction tran) {
		// TODO Auto-generated method stub
		
		if(mFrag01!=null)
		{
			tran.hide(mFrag01);
			tran.remove(mFrag01);
			
		}
		if(mFrag02!=null)
		{
			tran.hide(mFrag02);
			tran.remove(mFrag02);
		}	
		if(mFrag03!=null)
		{
			tran.hide(mFrag03);
			tran.remove(mFrag03);
		}
		if(mFrag04!=null)
		{
			tran.hide(mFrag04);
			tran.remove(mFrag04);
		}
		if(mFrag05!=null)
		{
			tran.hide(mFrag05);
			tran.remove(mFrag05);
		}
	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		resemble();
		switch (v.getId()) {
		case R.id.bar_01:
			setSelect(0);
			break;

        case R.id.bar_02:
        	setSelect(1);
			break;
        case R.id.bar_03:
        	setSelect(2);
			break;
        case R.id.bar_04:
        	setSelect(3);
			break;
        case R.id.bar_05:
        	setSelect(4);
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 */
	private void resemble() {
		// TODO Auto-generated method stub
		//将字体颜色置为暗色
		mTextFile.setTextColor(Color.parseColor("#855E42"));
		mTextPicture.setTextColor(Color.parseColor("#855E42"));
		mTextMusic.setTextColor(Color.parseColor("#855E42"));
		mTextVideo.setTextColor(Color.parseColor("#855E42"));
		mTextApp.setTextColor(Color.parseColor("#855E42"));
		
	}
}
