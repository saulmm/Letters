package edu.saulmm.letters.svg;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import edu.saulmm.letters.R;

import static android.util.Log.d;
import static android.util.Log.e;

@SuppressWarnings({"ForLoopReplaceableByForEach", "UnusedDeclaration"})
public class IntroView extends View {

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final SvgHelper mSvg = new SvgHelper(mPaint);

    private boolean mShouldStart = false;
    private int mSvgResource = R.raw.a;

    private final Object mSvgLock = new Object();
    private List<SvgHelper.SvgPath> mPaths = new ArrayList<SvgHelper.SvgPath>(0);
    private Thread mLoader;

    private SvgHelper.SvgPath mWaitPath;
    private SvgHelper.SvgPath mDragPath;

    private Paint mArrowPaint;
    private int mArrowLength;
    private int mArrowHeight;

    private float mPhase;
    private float mDrag;

    private int mDuration;
    private float mFadeFactor;

    private int mRadius;

    private ObjectAnimator mSvgAnimator;
    private ObjectAnimator mWaitAnimator;

	private int height, width;


	public IntroView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    /**
     * Constructor that manages the styleable attributes defined in attrs.xml and configured in the xml
     * @param context
     * @param attrs
     * @param defStyle
     */
    public IntroView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs,
		        R.styleable.IntroView,
		        defStyle, 0);

	    try {
            if (a != null) {
                mPaint.setStrokeWidth(a.getFloat(R.styleable.IntroView_strokeWidth, 1.0f));
                mPaint.setColor(a.getColor(R.styleable.IntroView_strokeColor, 0xff000000));
                mPhase = a.getFloat(R.styleable.IntroView_phase, 0.0f);
                mDuration = a.getInt(R.styleable.IntroView_duration, 4000);
                mFadeFactor = a.getFloat(R.styleable.IntroView_fadeFactor, 10.0f);
                mRadius = a.getDimensionPixelSize(R.styleable.IntroView_waitRadius, 50);
                mArrowLength = a.getDimensionPixelSize(R.styleable.IntroView_arrowLength, 32);
                mArrowHeight = a.getDimensionPixelSize(R.styleable.IntroView_arrowHeight, 18);
                mShouldStart = a.getBoolean(R.styleable.IntroView_shouldStart, false);
            }

        } finally {
            if (a != null) a.recycle();
        }

	    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
	    width = metrics.widthPixels;
	    height = metrics.heightPixels;


        if(mShouldStart) {
            init();
            totalPaths = 0;
            start(width, height);
        }
    }

    
	private void init() {
        mPaint.setStyle(Paint.Style.STROKE);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mSvgAnimator = ObjectAnimator.ofFloat(
            this, "phase", mPhase, 0.0f)
            .setDuration(mDuration);
    }


    public void setSvgResource(int resource) {
        if (mSvgResource == 0) {
            mSvgResource = resource;
        }
    }


	public IntroView (Context context) {
		super(context);
	}


	public void setmDuration (int mDuration) {
		this.mDuration = mDuration;
	}

	public void setmFadeFactor (float mFadeFactor) {
		this.mFadeFactor = mFadeFactor;
	}

	public void setmRadius (int mRadius) {
		this.mRadius = mRadius;
	}

	int totalPaths= 0;

	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        synchronized (mSvgLock) {
	        canvas.save();
            final int count = mPaths.size();

	        for (int i = 0; i < count; i++) {
                SvgHelper.SvgPath svgPath = mPaths.get(i);
		        totalPaths += i;




//		        We use the fade factor to speed up the alpha animation
		        int alpha = (int) (Math.min((1.0f - mPhase) * mFadeFactor, 1.0f) * 255.0f);
		        svgPath.paint.setAlpha(alpha);

                canvas.drawPath(svgPath.path, svgPath.paint);

            }

	        d("[DEBUG] org.curiouscreature.android.roadtrip.IntroView.onDraw ",
			        "\n\n");

            canvas.restore();

        }

        canvas.save();
        canvas.translate(0.0f, getHeight() - getPaddingBottom() - mRadius * 3.0f);
        canvas.restore();
	}


	public void startSvgAnimation () {
		totalPaths = 0;
		start(width, height);
	}


	private void start (final int w, final int h) {
		if (mLoader != null) {

            try {
				mLoader.join();

            } catch (InterruptedException ignored) {

			}
		}

		mLoader = new Thread(new Runnable() {
			@Override
			public void run() {
				mSvg.load(getContext(), mSvgResource);
                Log.d("[DEBUG]", "edu.saulmm.letters.svg.IntroView - run" +
                        "\n[The height of the resource is: ]" +mSvg.getHeight());

                int svgHeght = (int) mSvg.getHeight();

				synchronized (mSvgLock) {
					mPaths = mSvg.getPathsForViewport(
							w - getPaddingLeft() - getPaddingRight(),
							h - getPaddingTop() - getPaddingBottom() - (svgHeght / 2));

                    updatePathsPhaseLocked();
				}


				post(new Runnable() {
					@Override
					public void run() {

						if (mSvgAnimator != null) {
							// called when it's ready
							if (mSvgAnimator.isRunning()) {
								mSvgAnimator.cancel();
							}

							mSvgAnimator.start();
						}

					}
				});
			}
		}, "SVG Loader");
		mLoader.start();
	}


	private void updatePathsPhaseLocked() {
        final int count = mPaths.size();
        for (int i = 0; i < count; i++) {
	        SvgHelper.SvgPath svgPath = mPaths.get(i);
            svgPath.paint.setPathEffect(createPathEffect(svgPath.length, mPhase, 0.0f));
        }
    }

    public float getPhase() {
        return mPhase;
    }

    public void setPhase(float phase) {
        mPhase = phase;
        synchronized (mSvgLock) {
            updatePathsPhaseLocked();
        }
        invalidate();
    }

    public float getWait() {
	    return 0;
    }

    public void setWait(float wait) {
        invalidate();
    }

    public float getDrag() {
        return mDrag;
    }

    public void setDrag(float drag) {
        mDrag = drag;

        int alpha = (int) (Math.min((1.0f - mDrag) * mFadeFactor, 1.0f) * 255.0f);
        mDragPath.paint.setAlpha(alpha);

        invalidate();
    }

    private static PathEffect createPathEffect(float pathLength, float phase, float offset) {
        return new DashPathEffect(new float[] { pathLength, pathLength },
                Math.max(phase * pathLength, offset));
    }
}
