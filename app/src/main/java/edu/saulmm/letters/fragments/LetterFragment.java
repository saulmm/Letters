package edu.saulmm.letters.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.saulmm.letters.R;
import edu.saulmm.letters.Utils;
import edu.saulmm.letters.activities.MainLetterActivity;
import edu.saulmm.letters.svg.IntroView;

import static android.util.Log.*;

public class LetterFragment extends Fragment {
    private final int letterResource;
    private IntroView letterView;
    private int backgroundColorId;
    private int foregroundColorId;

    public LetterFragment(int letterResource) {
        this.letterResource = letterResource;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

//        Utils.CURRENT_BACKGROUND += Utils.BACKGROUND_COLOR_OFFSET;
        Utils.CURRENT_FOREGROUND = Utils.CURRENT_BACKGROUND + Utils.STROKE_COLOR_OFFSET;

        d("[DEBUG]", "edu.saulmm.letters.fragments.LetterFragment - onCreateView" +
                "\n[Current background " + Utils.CURRENT_BACKGROUND + "]");

        letterView = (IntroView) rootView.findViewById(R.id.intro);
        letterView.setSvgResource(letterResource);
        letterView.startSvgAnimation();
        letterView.setBackgroundColor(Utils.CURRENT_BACKGROUND);
        letterView.setStrokeColor((Utils.CURRENT_FOREGROUND));

        MainLetterActivity.addViewpagerListener(onPageChangeListener);

        return rootView;
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i2) {
            d("[DEBUG]", "edu.saulmm.letters.fragments.LetterFragment - onPageScrolled" +
                    "\n[Scrolling... i: "+i+" v: "+v+" i2: "+i2+"]");

            Utils.CURRENT_BACKGROUND += (i2 / 255) + 3;
            letterView.setBackgroundColor(Utils.CURRENT_BACKGROUND);

        }

        @Override
        public void onPageSelected(int i) {
            d("[DEBUG]", "edu.saulmm.letters.fragments.LetterFragment - onPageSelected" +
                    "\n[Selected...]");
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            d("[DEBUG]", "edu.saulmm.letters.fragments.LetterFragment - onPageScrollStateChanged" +
                    "\n[ScrollStateChanged...]");
        }
    };

    /**
     * Sets the background color of the letterView
     * @param colorID the resource id of the color;
     */
    public void setBackgroundViewColor(int colorID) {
        this.backgroundColorId = colorID;

    }

    /**
     * Set the path color
     * @param colorID the resource of the color
     */
    public void setForegroundColor(int colorID) {
        this.foregroundColorId = colorID;
    }
}
