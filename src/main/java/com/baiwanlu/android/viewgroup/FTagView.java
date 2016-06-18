package com.baiwanlu.android.viewgroup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baiwanlu.android.viewgroup.util.ViewUtils;

/**
 * 自定义标签 圆角标签
 * Created by lufei on 6/1/16.
 */
public class FTagView extends LinearLayout {

    GradientDrawable gradientDrawable;
    TextView textView;

    public FTagView(Context context) {
        super(context);
        init();
    }

    public FTagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FTagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.WHITE);
        gradientDrawable.setCornerRadius(30);
        gradientDrawable.setStroke(2, Color.parseColor("#C3C3C3"));
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        setBackgroundDrawable(gradientDrawable);

        int paddingLet = ViewUtils.dp2px(getContext(), 15);
        int paddingTop = ViewUtils.dp2px(getContext(), 5);
        setPadding(paddingLet, paddingTop, paddingLet, paddingTop);
        setGravity(Gravity.CENTER);

        textView = new TextView(getContext());
        addView(textView);
    }

    public String getText() {
        return textView.getText().toString();
    }

    public FTagView setText(CharSequence text) {
        textView.setText(text);
        return this;
    }

    public FTagView setTextColor(int color) {
        textView.setTextColor(color);
        return this;
    }

    public FTagView setTextSize(float size) {
        textView.setTextSize(size);
        return this;
    }

    public FTagView setBackgoundColor(int color) {
        gradientDrawable.setColor(color);
        return this;
    }

    public FTagView setCornerRadius(int radius) {
        gradientDrawable.setCornerRadius(radius);
        return this;
    }

    public FTagView setStroke(int width, int color) {
        gradientDrawable.setStroke(width, color);
        return this;
    }
}
