package com.litchi.pocketcommunity.util;

import android.app.Activity;

import java.util.ArrayList;

public class MyActivityManager {

    private static ArrayList<Activity> activityArrayList = new ArrayList<>();

    public static void add(Activity activity){
        activityArrayList.add(activity);
    }

    public static void remove(Activity activity){
        activityArrayList.remove(activity);
    }
    
    public static void finishAll(){
        for (Activity activity :
                activityArrayList) {
            activity.finish();
        }
    }

}
