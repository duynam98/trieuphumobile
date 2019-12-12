package com.duynam.ailatrieuphu.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.interface_.ItemClickListener;

public class Score2 extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView img_avatar;
    public TextView tv_username_rank, tv_score;
    public ItemClickListener itemClickListener;

    public Score2(@NonNull View itemView) {
        super(itemView);

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
