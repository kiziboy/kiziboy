package com.kizi.myfirstwork.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by ASUS on 2016/8/10.
 */
public class RefreshHeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {
    public RefreshHeaderView(Context context) {
        super(context);
    }
    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void onRefresh() {
        setText("REFRESHING");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean b1) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                setText("RELEASE TO REFRESH");
            } else {
                setText("SWIPE TO REFRESH");
            }
        } else {
            setText("REFRESH RETURNING");
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        setText("COMPLETE");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
