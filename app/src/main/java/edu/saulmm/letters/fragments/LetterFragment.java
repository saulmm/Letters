package edu.saulmm.letters.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.saulmm.letters.R;
import edu.saulmm.letters.svg.IntroView;

/**
 * Created by wtf on 16/02/14.
 */
public class LetterFragment extends Fragment {
    private final int letterResource;

    public LetterFragment(int letterResource) {
        this.letterResource = letterResource;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        IntroView letterView = (IntroView) rootView.findViewById(R.id.intro);
        letterView.setSvgResource(letterResource);
        letterView.startSvgAnimation();

        return rootView;
    }
}
