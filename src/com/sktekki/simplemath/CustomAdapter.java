package com.sktekki.simplemath;

import com.sktekki.simplemath.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter 
{
	String [] result;
    Context context;
    String [] imageId;
      private static LayoutInflater inflater=null;
    public CustomAdapter(ResultActivity ResultActivity, String[] prgmNameList, String[] prgmImages) 
    {
       
        result=prgmNameList;
        context=ResultActivity;
        imageId=prgmImages;
         inflater = ( LayoutInflater )context.
                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }
 
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }
 
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    
    public class Holder
    {
        TextView tv;
        TextView tv1;;
    }
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) 
	{
		Holder holder=new Holder();
        View rowView;        
             rowView = inflater.inflate(R.layout.list_scores, null);
             holder.tv=(TextView) rowView.findViewById(R.id.textView1);
             holder.tv1=(TextView) rowView.findViewById(R.id.textView2);       
         holder.tv.setText(result[position]);
         holder.tv1.setText(imageId[position]); 
        return rowView;
	}

}
