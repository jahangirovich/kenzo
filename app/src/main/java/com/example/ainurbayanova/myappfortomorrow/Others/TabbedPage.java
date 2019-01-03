package com.example.ainurbayanova.myappfortomorrow.Others;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ainurbayanova.myappfortomorrow.fragments.DoneFragment;
import com.example.ainurbayanova.myappfortomorrow.fragments.GoalFragment;

public class TabbedPage extends FragmentStatePagerAdapter {
    private int numberOfTabs;
    public TabbedPage(FragmentManager fm,int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new GoalFragment();
            case 1:
                return new DoneFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
