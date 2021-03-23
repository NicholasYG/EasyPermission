package com.gbs.easypermission;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdy
 * @date 3/16/21
 */
public class ActivityCollector {

    private static final List<Activity> activityList = new ArrayList<>();
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    //获取栈顶的activity
    public static Activity getTopActivity(){
        if (activityList.isEmpty()) {
            return null;
        }else {
            return activityList.get(activityList.size() - 1);
        }
    }
}
