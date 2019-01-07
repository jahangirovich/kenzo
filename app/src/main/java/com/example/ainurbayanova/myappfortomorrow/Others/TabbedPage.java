package com.example.ainurbayanova.myappfortomorrow.Others;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ainurbayanova.myappfortomorrow.fragments.DoneFragment;
import com.example.ainurbayanova.myappfortomorrow.fragments.GoalFragment;

public class TabbedPage extends FragmentStatePagerAdapter {
    private int numberOfTabs;
    public static final int CURRENT_TASK_FRAGMENT = 0;
    public static final int DONE_TASK_FRAGMENT = 1;

    GoalFragment goalFragment;
    DoneFragment doneFragment;

    public TabbedPage(FragmentManager fm,int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        doneFragment = new DoneFragment();
        goalFragment = new GoalFragment();
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return goalFragment;
            case 1:
                return doneFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
