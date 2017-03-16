package com.kosenin.konstantin2.timetable;

import android.app.Fragment;
import android.app.FragmentManager;
;


import android.support.v13.app.FragmentPagerAdapter;

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
    public CharSequence getPageTitle(int position) {

        position = position+1;
        return "День " + position;
    }
    @Override
    public int getCount() {
        return 6;
    }
}
