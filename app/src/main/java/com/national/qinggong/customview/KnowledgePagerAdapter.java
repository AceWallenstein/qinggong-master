package com.national.qinggong.customview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *
 */

public class KnowledgePagerAdapter extends FragmentPagerAdapter {
    private FragmentManager mfragmentManager;
    private List<Fragment> mlist;

    public KnowledgePagerAdapter(FragmentManager fm, List<Fragment> mlist) {
        super(fm);
        this.mlist = mlist;
        this.mfragmentManager = fm;
    }
    public KnowledgePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist == null?0:mlist.size();
    }
}
