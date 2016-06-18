package com.baiwanlu.android.viewgroup;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * 长按弹框
 * Created by lufei on 5/12/16.
 */
public class FMenuPopWindow {

    LinearLayout rootView;

    Activity activity;

    Context context;

    int count = 0;

    MyDialogFragment myDialogFragment;

    static Map<Activity, FMenuPopWindow> instanceMap = new HashMap<Activity, FMenuPopWindow>();

    public FMenuPopWindow(Activity activity) {
        this.context = activity.getApplicationContext();
        this.activity = activity;
        rootView = new LinearLayout(context);
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public static FMenuPopWindow newInstance(Activity activity) {
        if (instanceMap.containsKey(activity)) {
            return instanceMap.get(activity);
        }
        FMenuPopWindow menuPopWindow = new FMenuPopWindow(activity);
        instanceMap.put(activity, menuPopWindow);
        return menuPopWindow;
    }

    public FMenuPopWindow addMenu(String title, View.OnClickListener listener) {
        if (count > 0) {
            rootView.addView(createLine());
        }
        View itemView = createItem(title);
        itemView.setOnClickListener(listener);
        rootView.addView(itemView);

        return this;

    }

    public void show() {
        if (null!= myDialogFragment) {
            myDialogFragment.dismiss();
        }
        myDialogFragment = new MyDialogFragment();
        myDialogFragment.init(activity, rootView);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.add(myDialogFragment, "MyDialogFragment");
        ft.commitAllowingStateLoss();
    }

    public void dismiss() {
        instanceMap.remove(activity);
        if (null != myDialogFragment) {
            myDialogFragment.dismissAllowingStateLoss();
        }
    }

    private View createLine() {
        View line = new View(context);
        line.setBackgroundColor(Color.parseColor("#EFEFEF"));
        line.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
        return line;
    }

    private View createItem(String title) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(getScreenWidth() - 100, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setTextColor(Color.parseColor("#4E4E4E"));
        textView.setBackgroundColor(Color.parseColor("#ffffff"));
        textView.setTextSize(20);
        textView.setText(title);
        textView.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));

        return textView;
    }

    private int dp2px(float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    private int getScreenWidth() {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static class MyDialogFragment extends DialogFragment {

        private View view;
        private Activity activity;

        public MyDialogFragment() {
            super();
        }

        public void init(Activity activity, View view) {
            this.activity = activity;
            this.view = view;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            return view;
        }

        @Override
        public void onStop() {
            super.onStop();
            instanceMap.remove(activity);
        }
    }
}
