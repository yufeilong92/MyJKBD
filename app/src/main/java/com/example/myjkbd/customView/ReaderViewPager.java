package com.example.myjkbd.customView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.lang.reflect.Field;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyJKBD
 * @Package com.example.myjkbd.customView
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.11.05 下午 2:04
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class ReaderViewPager extends ViewPager {
    public ReaderViewPager(@NonNull Context context) {
        super(context);
    }

    public ReaderViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        setScrollerDuration();
    }

    private void setScrollerDuration() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(), new DecelerateInterpolator());
            field.set(this, scroller);
            scroller.setmDuration(300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setReadEffect() {
        setPageTransformer(true, new PageTransformer() {
            public static final float MIN_SCALE = 0.75f;

            @Override
            public void transformPage(@NonNull View view, float position) {
                int pagewidth = view.getWidth();
                int pageHeight = view.getHeight();
                if (position<-1){
                    view.setAlpha(0);
                }else if (position<=0){
                    view.setAlpha(1);
                    view.setTranslationX(0);
                    view.setScaleX(1);
                    view.setScaleY(1);
                }else if (position<=1){
                    view.setAlpha(1);
                    view.setTranslationX(pagewidth*-position);
                }else {
                    view.setAlpha(0);
                }

            }
        });
    }
}
