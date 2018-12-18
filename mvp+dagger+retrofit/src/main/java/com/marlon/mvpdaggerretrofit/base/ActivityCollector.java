package com.marlon.mvpdaggerretrofit.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc ActivityCollector
 * @author Marlon
 * @date 2018/12/18
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);

    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);

    }

    public static void removeAllActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }

}
