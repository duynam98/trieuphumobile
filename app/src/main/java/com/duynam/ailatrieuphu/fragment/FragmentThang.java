package com.duynam.ailatrieuphu.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.adapter.BangxephangAdapter;
import com.duynam.ailatrieuphu.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class FragmentThang extends Fragment {

    private RecyclerView rv_thang;
    private BangxephangAdapter adapter;
    private List<User> userList;
    private Date date;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thangnay, container, false);

        userList = new ArrayList<>();
        rv_thang = view.findViewById(R.id.rv_thangnay);
        getUsser();


        return view;
    }

    private void getUsser() {
        date = new Date();
        String currendate = new SimpleDateFormat("MM,yyyy").format(date.getTime());
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query homnay = database.getReference("Users").orderByChild("month").equalTo(currendate);

        homnay.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String photo;
                    String email = snapshot.child("email").getValue().toString();
                    String name = snapshot.child("username").getValue().toString();
                    if (snapshot.child("photo").getValue() != null) {
                        photo = snapshot.child("photo").getValue().toString();
                    } else {
                        photo = null;
                    }
                    long score = (long) snapshot.child("score").getValue();
                    String timestamp = snapshot.child("timestamp").getValue().toString();
                    String level = snapshot.child("level").getValue().toString();
                    String month = snapshot.child("month").getValue().toString();

                    userList.add(new User(email, name, photo, score, timestamp, level, month));

                    Collections.sort(userList, new Comparator<User>() {
                        @Override
                        public int compare(User sv1, User sv2) {
                            if (sv1.getScore() < sv2.getScore()) {
                                return 1;
                            } else {
                                if (sv1.getScore() == sv2.getScore()) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        }
                    });

                    adapter = new BangxephangAdapter(getContext(), userList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    rv_thang.setLayoutManager(layoutManager);
                    rv_thang.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}
