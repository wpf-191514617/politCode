package com.zodiac.polit.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by john on 2018/10/12.
 */

public abstract class BaseSignInfo extends LinearLayout {
    public BaseSignInfo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseSignInfo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
