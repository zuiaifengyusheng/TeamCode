package com.example.adapter;

import java.io.File;
import java.util.List;
import java.util.Map;
import com.bumptech.glide.Glide;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class VideoAdapter extends SimpleAdapter {

	private int[] videoTo;
	private String[] videoFrom;
	private ViewBinder videoViewBinder;
	private List<?extends Map<String,?>> videoData;
	private int videoResource;
	private LayoutInflater videoInflater;
	private Context context;
	
	//¹¹ÔìÆ÷
	public VideoAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub

		this.context=context;
		videoData=data;
		videoResource=resource;
		videoFrom=from;
		videoTo=to;
		videoInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	public View getView(int position,View convertView,ViewGroup parent)
	{
		return createViewFromResource(position, convertView, parent, videoResource); 
	}

	private View createViewFromResource(int position,View convertView,ViewGroup parent,int resource)
	{
		View v;
		 if(convertView == null)
		 {              
			 v = videoInflater.inflate(resource, parent,false);             
			 final int[] to = videoTo;              
			 final int count = to.length;               
			 final View[] holder = new View[count];                            
			 for(int i = 0; i < count; i++)
			 {                 
				 holder[i] = v.findViewById(to[i]);              
			 }  
			 v.setTag(holder);         
		}
		else 
		{              
			v = convertView;          
		}                  
		bindView(position, v);         
		return v;    
	}
	
	private void bindView(int position, View v) 
	{
		// TODO Auto-generated method stub
		final Map dataSet=videoData.get(position);
		
		if(dataSet==null)
		{
			return;
		}
		
		final ViewBinder binder=videoViewBinder;
		final View[] holder=(View[])v.getTag();
		final String[] from=videoFrom;
		final int[] to=videoTo;
		final int count=to.length;
		for(int i=0;i<count;i++)
		{
			final View view=holder[i];
			if(view!=null)
			{
				final Object data=dataSet.get(from[i]);
				String text=data==null?"":data.toString();
				if(text==null)
				{
					text="";
				}
				boolean bound=false;
				if(binder!=null)
				{
					bound=binder.setViewValue(view, data, text);
				}
				if(!bound)
				{
					if(view instanceof TextView)
					{
						setViewText((TextView)view, text);
					}
					else if(view instanceof ImageView)
					{
						Glide.with(context).load(new File(text)).into((ImageView)view);
					}
					else
					{
						throw new IllegalStateException(view.getClass().getName()+"is not a"+ " view that can be bounds by this SimpleAdapter");
					}
				}
			}
		}		
	}
}