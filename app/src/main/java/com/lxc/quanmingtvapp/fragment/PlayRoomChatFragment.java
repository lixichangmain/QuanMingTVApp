package com.lxc.quanmingtvapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxc.quanmingtvapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayRoomChatFragment extends Fragment {


    public PlayRoomChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_play_room_chat, container, false);
        return view;
    }

}
