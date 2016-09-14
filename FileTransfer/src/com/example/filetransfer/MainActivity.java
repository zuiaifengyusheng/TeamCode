package com.example.filetransfer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



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
	
	private Button btn_send;
	private Button btn_receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send = (Button)findViewById(R.id.fa);
    	btn_receiver = (Button)findViewById(R.id.shou);
        initView();
        initEvents();
        setSelect(0);
        
        btn_send.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v0) {
    			// TODO Auto-generated method stub
    			Intent intent = new Intent();
    			intent.setClass(MainActivity.this, ChooseActivity.class);
    			startActivity(intent);
    			
    			/*connManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
    			NetworkInfo activeNetworkInfo = connManager.getActiveNetworkInfo();
    			boolean connect = activeNetworkInfo.isConnected();
    			if(connect){
    				Toast.makeText(getApplicationContext(), "��ǰ���������ӿ���", Toast.LENGTH_SHORT).show();
    			}
    			else{
    				Toast.makeText(getApplicationContext(), "��ǰ���������Ӳ�����", Toast.LENGTH_SHORT).show();
    				//����Ǵ�״̬�͹رգ�����ǹرվʹ�
    				flag =!flag;
    				setWifiApEnabled(flag);
    			}
    			State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
    			if(State.CONNECTED == state){
    				Toast.makeText(getApplicationContext(), "GPRS����������", Toast.LENGTH_SHORT).show();
    			}
    			state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
    			if(State.CONNECTED == state){
    				Toast.makeText(getApplicationContext(), "WIFI����������", Toast.LENGTH_SHORT).show();
    				
    			}*/
    			/*wifiManager = (WifiManager)MainActivity.this.getSystemService(Context.WIFI_SERVICE);
    			wifiManager.setWifiEnabled(true);//����WiFi		
    */			}
    	});
    	btn_receiver.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v1) {
    			// TODO Auto-generated method stub
    			/*connManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
    			NetworkInfo activeNetworkInfo = connManager.getActiveNetworkInfo();
    			boolean connect = activeNetworkInfo.isConnected();
    			if(connect){
    				Toast.makeText(getApplicationContext(), "��ǰ�������ӿ���", Toast.LENGTH_SHORT).show();
    			}
    			else{
    				Toast.makeText(getApplicationContext(), "��ǰ�������Ӳ�����", Toast.LENGTH_SHORT).show();
    				wifiManager.startScan();ͨ����ť�¼������ȵ�
    			}*/
    		}
    	});
    }
    
   
    
    //�¼�
	private void initEvents() {
		// TODO Auto-generated method stub
		mTabFile.setOnClickListener(this);
		mTabPicture.setOnClickListener(this);
		mTabMusic.setOnClickListener(this);
		mTabVideo.setOnClickListener(this);
		mTabApp.setOnClickListener(this);
	}


	//��ʼ���ؼ�
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
		//�ı��ǩ��ɫ��������������
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
		//��������ɫ��Ϊ��ɫ
		mTextFile.setTextColor(Color.parseColor("#855E42"));
		mTextPicture.setTextColor(Color.parseColor("#855E42"));
		mTextMusic.setTextColor(Color.parseColor("#855E42"));
		mTextVideo.setTextColor(Color.parseColor("#855E42"));
		mTextApp.setTextColor(Color.parseColor("#855E42"));
		
	}
}
