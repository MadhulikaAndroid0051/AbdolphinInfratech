package com.example.abdolphininfratech.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.abdolphininfratech.R;


public class LevelBeamView extends View {

    private int mLevel;

    private int mPadding;
    private int mLinesWidth;
    private int mLinesOffset;
    private Paint mLinePaint;

    public LevelBeamView(Context context) {
        super(context);
        init();
    }

    public LevelBeamView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LevelBeamView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setLevel(int level) {
        mLevel = level;
        requestLayout();
    }

    private void init() {
        mPadding = (int) getResources().getDimension(R.dimen.desc_padding);
        mLinesWidth = (int) getResources().getDimension(R.dimen.level_beam_line_width);
        mLinesOffset = (int) getResources().getDimension(R.dimen.level_beam_line_offset);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(mLinesWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = mPadding + (mLevel + 1) * (mLinesWidth + mLinesOffset);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int lvl = 0; lvl <= mLevel; lvl++) {
            float LINE_X = mPadding + lvl * mLinesWidth;
            if (lvl >= 1) {
                LINE_X += lvl * mLinesOffset;
            }
            mLinePaint.setColor(getColor(getColorResIdForLevel(lvl)));
            canvas.drawLine(LINE_X, 0, LINE_X, getHeight(), mLinePaint);
        }
    }

    private int getColor(int colorResId) {
        return ContextCompat.getColor(getContext(), colorResId);
    }

    private int getColorResIdForLevel(int level) {
        switch (level) {
            case 0:
                return R.color.colorPrimary;
            case 1:
                return R.color.colorPrimary;
            case 2:
                return R.color.colorPrimary;
            case 3:
                return R.color.colorPrimary;
            case 4:
                return R.color.colorPrimary;
            case 5:
                return R.color.colorPrimary;
            default:
                return R.color.colorPrimary;
        }
    }

}
