package edu.saulmm.letters.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.saulmm.letters.R;
import edu.saulmm.letters.Utils;
import edu.saulmm.letters.svg.IntroView;
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

        Utils.CURRENT_BACKGROUND += Utils.BACKGROUND_COLOR_OFFSET;
        Utils.CURRENT_FOREGROUND = Utils.CURRENT_BACKGROUND + Utils.STROKE_COLOR_OFFSET;

        Log.d("[DEBUG]", "edu.saulmm.letters.fragments.LetterFragment - onCreateView" +
                "\n[Current background " + Utils.CURRENT_BACKGROUND + "]");

        letterView = (IntroView) rootView.findViewById(R.id.intro);
        letterView.setSvgResource(letterResource);
        letterView.startSvgAnimation();
        letterView.setBackgroundColor(Utils.CURRENT_BACKGROUND);

        letterView.setStrokeColor((Utils.CURRENT_FOREGROUND));

        return rootView;
    }

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
