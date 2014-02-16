package edu.saulmm.letters.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LetterBar extends LinearLayout {
    private final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final Context context;

    public LetterBar(Context context) {
        super(context);
        this.context = context;

        init();
    }

    public LetterBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init();
    }

    public LetterBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);

        final LayoutParams lp = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        lp.setMargins(10,0,10,0);

        char letter;

        for (int i = 0; i < LETTERS.length(); i++) {
            letter = LETTERS.charAt(i);

            TextView b = new TextView(this.context);

            b.setGravity(Gravity.CENTER);
            b.setTypeface(Typeface.create("sans-serif-light",
                    Typeface.NORMAL));


            b.setText(letter+"");

            this.addView(b, lp);
        }
        
        
    }
}
