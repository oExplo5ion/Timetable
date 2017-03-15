package com.kosenin.konstantin2.timetable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Konstantin2 on 15.03.2017.
 */

public class SchedulePagerAdapter extends FragmentPagerAdapter {
    public SchedulePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return DayFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 6;
    }
}
