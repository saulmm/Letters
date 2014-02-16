package edu.saulmm.letters.views.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.saulmm.letters.fragments.LetterFragment;

/**
 * Created by wtf on 16/02/14.
 */
public class LetterAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_ITEMS = 26;

    public LetterAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        return new LetterFragment();
    }


}
