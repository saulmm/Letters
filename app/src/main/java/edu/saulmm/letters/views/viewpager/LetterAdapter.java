package edu.saulmm.letters.views.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.saulmm.letters.R;
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

        switch (position) {
            case 0:
                return new LetterFragment(R.raw.aa);

            case 1:
                LetterFragment letterBFragment = new LetterFragment(R.raw.bb);
                return letterBFragment;

            case 2:
                return new LetterFragment(R.raw.cc);

            case 3:
                return new LetterFragment(R.raw.dd);

            case 4:
                return new LetterFragment(R.raw.ee);

            case 5:
                return new LetterFragment(R.raw.ff);

            case 6:
                return new LetterFragment(R.raw.gg);

            case 7:
                return new LetterFragment(R.raw.hh);

            case 8:
                return new LetterFragment(R.raw.ii);

            case 9:
                return new LetterFragment(R.raw.jj);

            case 10:
                return new LetterFragment(R.raw.kk);

            case 11:
                return new LetterFragment(R.raw.ll);

            case 12:
                return new LetterFragment(R.raw.mm);

            case 13:
                return new LetterFragment(R.raw.nn);

            case 14:
                return new LetterFragment(R.raw.oo);

            case 15:
                return new LetterFragment(R.raw.pp);

            case 16:
                return new LetterFragment(R.raw.qq);

            case 17:
                return new LetterFragment(R.raw.rr);

            case 18:
                return new LetterFragment(R.raw.ss);

            case 19:
                return new LetterFragment(R.raw.tt);

            case 20:
                return new LetterFragment(R.raw.uu);

            case 21:
                return new LetterFragment(R.raw.ww);

            case 22:
                return new LetterFragment(R.raw.xx);

            case 23:
                return new LetterFragment(R.raw.yy);

            case 24:
                return new LetterFragment(R.raw.zz);

            default:
                return new LetterFragment(R.raw.aa);
        }




    }


}
