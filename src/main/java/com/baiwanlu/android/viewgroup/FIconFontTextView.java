package com.baiwanlu.android.viewgroup;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * iconfont 字体库
 * Created by lufei on 5/16/16.
 */
public class FIconFontTextView extends TextView {

    static Typeface iconFont;

    public FIconFontTextView(Context context) {
        super(context);
        init(context);
    }

    public FIconFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FIconFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        //字体资源放入assets文件夹中
        if (null == iconFont) {
            AssetManager am = context.getAssets();
            iconFont = Typeface.createFromAsset(am, "fonts/iconfont.ttf");
        }
        setTypeface(iconFont);
    }
}
