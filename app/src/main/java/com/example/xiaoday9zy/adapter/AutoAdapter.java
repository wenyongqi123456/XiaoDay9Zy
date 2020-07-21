package com.example.xiaoday9zy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiaoday9zy.R;
import com.example.xiaoday9zy.bean.ProBean;

import java.util.ArrayList;

public class AutoAdapter extends RecyclerView.Adapter<AutoAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProBean.DataBean.DatasBean> list;
    private ProBean.DataBean.DatasBean datasBean;
    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(AutoAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AutoAdapter(Context context, ArrayList<ProBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.auto_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        datasBean = list.get(position);

        holder.tv_title.setText(datasBean.getTitle());

        Glide.with(context).load(datasBean.getEnvelopePic()).into(holder.iv_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView tv_title;
        private final ImageView iv_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            iv_img = itemView.findViewById(R.id.iv_img);
        }
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }
}
