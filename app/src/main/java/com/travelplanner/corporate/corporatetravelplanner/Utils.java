package com.travelplanner.corporate.corporatetravelplanner;

import android.text.TextUtils;

public class Utils {
    public static boolean isValidName(String name){
        return name.length()>=1;
    }

    public static boolean isValidEmailAddress(String email) {
        return  (!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public static boolean isValidPhone(String phone){
        return phone.length()>=6;
    }

    public static boolean isValidPassword(String password){
        return password.length()>=6;
    }
}
