package edu.saulmm.letters.fragments;

import android.graphics.Color;
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

    private int red, blue, green;


    public LetterFragment(int letterResource) {
        this.letterResource = letterResource;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

//        Utils.CURRENT_BACKGROUND += Utils.BACKGROUND_COLOR_OFFSET;
        Utils.CURRENT_FOREGROUND = Utils.CURRENT_BACKGROUND + Utils.STROKE_COLOR_OFFSET;


        letterView = (IntroView) rootView.findViewById(R.id.intro);
        letterView.setSvgResource(letterResource);
        letterView.startSvgAnimation();
        letterView.setBackgroundColor(Color.rgb(Utils.RED, Utils.GREEN, Utils.BLUE));
        letterView.setStrokeColor((Utils.CURRENT_FOREGROUND));

        MainLetterActivity.addViewpagerListener(onPageChangeListener);

        return rootView;
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            String debug1 = "Blue: "+Utils.BLUE+ ", OffsetPixels: "+positionOffsetPixels+", OffsetPixels % 255: "+(positionOffsetPixels % 255);
            String debug2 = "[onPageChangeListener position: "+position+" positionOffset: "+ (int)(positionOffset * 10)+" positionOffsetPixels: "+positionOffsetPixels;

//            Log.d("[DEBUG]",debug2);
            
            if (Utils.RED < 255)
                Utils.RED++;

            if (Utils.RED >= 255 && Utils.GREEN >= 0)
                Utils.GREEN--;

//            if (Utils.BLUE >= Utils.MAX_RANGE && Utils.RED == 0)
//                Utils.GREEN = (positionOffsetPixels / 3) % 255;
////
//            if (Utils.BLUE >= Utils.MAX_RANGE -1 && Utils.GREEN >= Utils.MAX_RANGE -1)
////                Utils.RED =  positionOffsetPixels % 255;
////
            d("[DEBUG]", "[positionOffsetPixels]: "+positionOffsetPixels+"| Blue: "+Utils.BLUE+ " Green: "+Utils.GREEN+" Red: "+Utils.RED);

//            Utils.CURRENT_BACKGROUND += (positionOffsetPixels / 255) + 3;
            letterView.setBackgroundColor(Color.rgb(Utils.RED, Utils.GREEN, Utils.BLUE));

        }

        @Override
        public void onPageSelected(int i) {
            d("[DEBUG]","[Selected...\n]");
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            d("[DEBUG]", "[ScrollStateChanged...\n]");
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
