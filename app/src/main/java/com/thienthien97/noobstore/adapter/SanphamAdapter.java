package com.thienthien97.noobstore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thienthien97.noobstore.R;
import com.thienthien97.noobstore.model.Sanpham;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by thien on 21/04/2018.
 */

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> arraySanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraySanpham) {
        this.context = context;
        this.arraySanpham = arraySanpham;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //tạo lại view cho san pham
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_spmoinhat,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    //lấy dữ liệu gán lên layout
    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Sanpham sanpham = arraySanpham.get(position);
        holder.txttensp.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasp.setText("Giá: "+decimalFormat.format(sanpham.getGiasp())+" VND");
        Picasso.with(context).load(sanpham.getHinhsp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imghinhsp);
    }

    @Override
    public int getItemCount() {
        return arraySanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsp;
        public TextView txttensp, txtgiasp;

        public ItemHolder(View itemView) {
            super(itemView);
            imghinhsp = (ImageView) itemView.findViewById(R.id.imgviewsanpham);
            txtgiasp = (TextView) itemView.findViewById(R.id.textviewgiasp);
            txttensp = (TextView) itemView.findViewById(R.id.textviewtensp);
        }
    }
}
