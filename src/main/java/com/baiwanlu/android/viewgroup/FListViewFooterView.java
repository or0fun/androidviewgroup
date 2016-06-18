package com.baiwanlu.android.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baiwanlu.android.viewgroup.util.ViewUtils;

/**
 * lsitview 的 footer  提示没有更多
 * Created by lufei on 5/11/16.
 */
public class FListViewFooterView extends LinearLayout {

    TextView textview;

    public FListViewFooterView(Context context) {
        super(context);
        init(context);
    }

    public FListViewFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FListViewFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.f_viewgroup_listview_foot, this);
        textview = (TextView) findViewById(R.id.list_view_foot_tv);
    }

    public TextView getTextView() {
        return textview;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            setPadding(0, 0, 0, 0);
        }else {
            int height = -getHeight();
            if (height == 0) {
                height = ViewUtils.dp2px(getContext(), 80);
            }
            setPadding(0, -height, 0, 0);
        }
    }
}
