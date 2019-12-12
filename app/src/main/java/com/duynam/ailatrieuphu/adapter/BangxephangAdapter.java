package com.duynam.ailatrieuphu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.activity.ChoithuActivity;
import com.duynam.ailatrieuphu.interface_.ItemClickListener;
import com.duynam.ailatrieuphu.interface_.Level;
import com.duynam.ailatrieuphu.model.User;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;
import com.duynam.ailatrieuphu.viewHolder.ListUser;
import com.duynam.ailatrieuphu.viewHolder.Score1;
import com.duynam.ailatrieuphu.viewHolder.Score2;
import com.duynam.ailatrieuphu.viewHolder.Score3;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BangxephangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<User> userList;

    public BangxephangAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.custorm_score1, parent, false);
                return new Score1(view);
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.custorm_score2, parent, false);
                return new Score2(view);
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.custorm_score3, parent, false);
                return new Score3(view);
            default:
                view = LayoutInflater.from(context).inflate(R.layout.list_user, parent, false);
                return new ListUser(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            ((Score1) holder).setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isOnCLick) {
                    long diem = userList.get(position).getScore();
                    String photo = userList.get(position).getPhoto();
                    String name = userList.get(position).getUsername();
                    String email = userList.get(position).getEmail();
                    SaveLogin.saveEmaildoithu(email, context);
                    SaveLogin.saveNamedoithu(name, context);
                    SaveLogin.savePhotodoithu(photo, context);
                    Intent intent = new Intent(context, ChoithuActivity.class);
                    intent.putExtra("diem", diem);
                    intent.putExtra("thachdau", true);
                    context.startActivity(intent);
                }
            });
            ((Score1) holder).tv_username_rank.setText(userList.get(position).getUsername());
            ((Score1) holder).tv_score.setText(Level.covert_score(userList.get(position).getScore()));
            if (userList.get(position).getPhoto() != null) {
                Picasso.get().load(userList.get(position).getPhoto()).into(((Score1) holder).img_avatar);
            } else {

            }
        } else if (getItemViewType(position) == 1) {
            ((Score2) holder).setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isOnCLick) {
                    long diem = userList.get(position).getScore();
                    String photo = userList.get(position).getPhoto();
                    String name = userList.get(position).getUsername();
                    String email = userList.get(position).getEmail();
                    SaveLogin.saveEmaildoithu(email, context);
                    SaveLogin.saveNamedoithu(name, context);
                    SaveLogin.savePhotodoithu(photo, context);
                    Intent intent = new Intent(context, ChoithuActivity.class);
                    intent.putExtra("diem", diem);
                    intent.putExtra("thachdau", true);
                    context.startActivity(intent);
                }
            });
            ((Score2) holder).tv_username_rank.setText(userList.get(position).getUsername());
            ((Score2) holder).tv_score.setText(Level.covert_score(userList.get(position).getScore()));
            if (userList.get(position).getPhoto() != null) {
                Picasso.get().load(userList.get(position).getPhoto()).into(((Score2) holder).img_avatar);
            } else {

            }
        } else if (getItemViewType(position) == 2) {
            ((Score3) holder).setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isOnCLick) {
                    long diem = userList.get(position).getScore();
                    String photo = userList.get(position).getPhoto();
                    String name = userList.get(position).getUsername();
                    String email = userList.get(position).getEmail();
                    SaveLogin.saveEmaildoithu(email, context);
                    SaveLogin.saveNamedoithu(name, context);
                    SaveLogin.savePhotodoithu(photo, context);
                    Intent intent = new Intent(context, ChoithuActivity.class);
                    intent.putExtra("diem", diem);
                    intent.putExtra("thachdau", true);
                    context.startActivity(intent);
                }
            });
            ((Score3) holder).tv_username_rank.setText(userList.get(position).getUsername());
            ((Score3) holder).tv_score.setText(Level.covert_score(userList.get(position).getScore()));
            if (userList.get(position).getPhoto() != null) {
                Picasso.get().load(userList.get(position).getPhoto()).into(((Score3) holder).img_avatar);
            } else {

            }
        } else {
            ((ListUser) holder).setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isOnCLick) {
                    long diem = userList.get(position).getScore();
                    String photo = userList.get(position).getPhoto();
                    String name = userList.get(position).getUsername();
                    String email = userList.get(position).getEmail();
                    SaveLogin.saveEmaildoithu(email, context);
                    SaveLogin.saveNamedoithu(name, context);
                    SaveLogin.savePhotodoithu(photo, context);
                    Intent intent = new Intent(context, ChoithuActivity.class);
                    intent.putExtra("diem", diem);
                    intent.putExtra("thachdau", true);
                    context.startActivity(intent);
                }
            });
            ((ListUser) holder).tv_stt.setText(position + 1 + "");
            ((ListUser) holder).tv_username_rank.setText(userList.get(position).getUsername());
            ((ListUser) holder).tv_score.setText(Level.covert_score(userList.get(position).getScore()));
            if (userList.get(position).getPhoto() != null) {
                Picasso.get().load(userList.get(position).getPhoto()).into(((ListUser) holder).img_avatar);
            } else {

            }
        }
    }

    @Override
    public int getItemCount() {
        if (userList.size() == 0) {
            return 0;
        } else {
            return userList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
        }
        return 3;
    }
}
