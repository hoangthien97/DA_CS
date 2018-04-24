package com.thienthien97.noobstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thienthien97.noobstore.R;
import com.thienthien97.noobstore.model.Loaihang;

import java.util.ArrayList;

/**
 * Created by thien on 08/04/2018.
 */


public class LoaihangAdapter extends BaseAdapter{

    ArrayList<Loaihang> arrlstLoaihang;
    Context context;

    public LoaihangAdapter(ArrayList<Loaihang> arrlstLoaihang, Context context) {
        this.arrlstLoaihang = arrlstLoaihang;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrlstLoaihang.size();
    }

    @Override
    public Object getItem(int i) {
        return arrlstLoaihang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txttenloai;
        ImageView imgloai;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewgroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaihang,null);
            viewHolder.txttenloai = (TextView) view.findViewById(R.id.textviewloaihang);
            viewHolder.imgloai = (ImageView) view.findViewById(R.id.imageviewloaihang);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Loaihang loaihang = (Loaihang) getItem(i);
        viewHolder.txttenloai.setText(loaihang.getTenloaihang());
        Picasso.with(context).load(loaihang.getAnhloaihang())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgloai);

        return view;
    }
}
