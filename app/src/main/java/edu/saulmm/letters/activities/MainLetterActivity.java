package edu.saulmm.letters.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import edu.saulmm.letters.R;
import edu.saulmm.letters.views.viewpager.DepthPageTransformer;
import edu.saulmm.letters.views.viewpager.LetterAdapter;



public class MainLetterActivity extends FragmentActivity {
    static final int NUM_ITEMS = 27;

    LetterAdapter mAdapter;

    static ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.fragment_pager);

        mAdapter = new LetterAdapter(getSupportFragmentManager());
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
//        mPager.setPageTransformer(true, new DepthPageTransformer());

    }

    public static void addViewpagerListener(ViewPager.OnPageChangeListener listener) {
        mPager.setOnPageChangeListener(listener);
    }
}