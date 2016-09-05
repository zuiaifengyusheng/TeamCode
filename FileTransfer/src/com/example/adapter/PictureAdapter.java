package com.example.adapter;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PictureAdapter extends SimpleAdapter {

	private int[] imageTo;
	private String[] imageFrom;
	private ViewBinder imageViewBinder;
	private List<?extends Map<String,?>> imageData;
	private int imageResource;
	private LayoutInflater imageInflater;
	private Context context;
	
	//¹¹ÔìÆ÷
	public PictureAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub

		this.context=context;
		imageData=data;
		imageResource=resource;
		imageFrom=from;
		imageTo=to;
		imageInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	public View getView(int position,View convertView,ViewGroup parent)
	{
		return createViewFromResource(position, convertView, parent, imageResource); 
	}

	private View createViewFromResource(int position,View convertView,ViewGroup parent,int resource)
	{
		View v;
		 if(convertView == null)
		 {              
			 v = imageInflater.inflate(resource, parent,false);             
			 final int[] to = imageTo;              
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
		final Map dataSet=imageData.get(position);
		
		if(dataSet==null)
		{
			return;
		}
		
		final ViewBinder binder=imageViewBinder;
		final View[] holder=(View[])v.getTag();
		final String[] from=imageFrom;
		final int[] to=imageTo;
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
						
						Picasso.with(context).load(new File(data.toString())).fit().into((ImageView)view);
					}
					else
					{
						throw new IllegalStateException(view.getClass().getName()+"is not a"+"view that can be bounds by this SimpleAdapter");
					}
				}
				
			}
		}		
	}
}