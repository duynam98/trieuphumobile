package com.duynam.ailatrieuphu.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.duynam.ailatrieuphu.R;
import com.duynam.ailatrieuphu.sharepreference.SaveLogin;

public class FragmenSetting extends Fragment {

    ToggleButton toggleButton;
    AudioManager audioManager;
    int amluong;
    ImageView img_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        toggleButton = view.findViewById(R.id.toggleButton);
        img_back = view.findViewById(R.id.img_back);

        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        amluong = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SaveLogin.saveCurrentVolume(amluong, getContext());

        if (SaveLogin.getMedia(getContext())){
            toggleButton.setBackgroundResource(R.drawable.app__ic_toggle_on);
            toggleButton.setChecked(true);
        }else {
            toggleButton.setBackgroundResource(R.drawable.app__ic_toggle_off);
            toggleButton.setChecked(false);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        }

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (SaveLogin.getVolume(getContext()) == 0){
                        toggleButton.setChecked(true);
                        toggleButton.setBackgroundResource(R.drawable.app__ic_toggle_on);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 5, 5);
                    }else {
                        SaveLogin.saveNhacnen(true, getContext());
                        toggleButton.setChecked(true);
                        toggleButton.setBackgroundResource(R.drawable.app__ic_toggle_on);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, SaveLogin.getVolume(getContext()), SaveLogin.getVolume(getContext()));
                    }
                }else {
                    SaveLogin.saveNhacnen(false, getContext());
                    toggleButton.setBackgroundResource(R.drawable.app__ic_toggle_off);
                    toggleButton.setChecked(false);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(FragmenSetting.this).commit();
            }
        });

        return view;
    }
}
