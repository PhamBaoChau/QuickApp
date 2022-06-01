//package com.baochau.dmt.quickapp.fragment;
//
//import android.icu.text.CaseMap;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.baochau.dmt.quickapp.R;
//import com.baochau.dmt.quickapp.database.TopicHelper;
//
//public class FragmentItemTopic extends Fragment {
//    TopicHelper topicHelper;
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.item_topic,null);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        topicHelper=new TopicHelper(getContext(),null,null,0);
//        TextView title=view.findViewById(R.id.tvTitle);
//        title.setText();
//    }
//}
