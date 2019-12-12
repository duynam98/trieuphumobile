package com.duynam.ailatrieuphu.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.interface_.ItemClickListener;

public class ListUser extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView img_avatar;
    public TextView tv_username_rank, tv_score, tv_stt;
    public ItemClickListener itemClickListener;

    public ListUser(@NonNull View itemView) {
        super(itemView);

        tv_stt = itemView.findViewById(R.id.tv_stt);
        img_avatar = itemView.findViewById(R.id.img_avatarrank);
        tv_score = itemView.findViewById(R.id.tv_score);
        tv_username_rank = itemView.findViewById(R.id.tv_username_rank);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), true);
    }
}