package com.yc.lifehelper;

import android.app.Activity;
import android.os.Bundle;

import com.yc.applicationlib.activity.ActivityLifecycleListener;

public class MainActivityListener extends ActivityLifecycleListener {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        super.onActivityCreated(activity, savedInstanceState);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        super.onActivityDestroyed(activity);
    }
}
