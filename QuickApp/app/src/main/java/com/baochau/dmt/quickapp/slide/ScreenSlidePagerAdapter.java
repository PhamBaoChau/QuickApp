package com.baochau.dmt.quickapp.slide;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.baochau.dmt.quickapp.questions.FirstFragment;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return SlidePageFragment.create(i);
    }

    @Override
    public int getCount() {
        return FirstFragment.listQuestion.size();
    }

}
