package com.example.aiyang.stickydecoration.commen;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.aiyang.stickydecoration.R;

public class ThemeUtil {

    public static class ThemeColors {
        public static final int THEME_GREEN = 1;
        public static final int ThEME_BLUE = 2;
        public static final int THEME_GREY = 3;
        public static final int THEME_YELLOW = 4;
    }

    public static void setBaseTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "MyThemeShared", context.MODE_PRIVATE);
        int themeType = sharedPreferences.getInt("theme_type", 0);
        int themeId;
        switch (themeType) {
            case ThemeColors.THEME_GREEN:
                themeId = R.style.AppThemeNoAction_Green;
                break;
            case ThemeColors.ThEME_BLUE:
                themeId = R.style.AppThemeNoAction_Blue;
                break;

            case ThemeColors.THEME_GREY:
                themeId = R.style.AppThemeNoAction_Grey;
                break;
            case ThemeColors.THEME_YELLOW:
                themeId = R.style.AppThemeNoAction_Yellow;
                break;
            default:
                themeId = R.style.AppThemeNoAction;
        }
        context.setTheme(themeId);
    }

    public static boolean setNewTheme(Context context, int theme) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "MyThemeShared", context.MODE_PRIVATE);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt("theme_type",theme);
//        e.apply();
        return  e.commit();//有返回值
    }
}
