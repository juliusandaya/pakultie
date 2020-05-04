package com.example.pakultie;

import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isMobileNumberInvalid(@NonNull String mobileNumber) {
        final String MOBILE_REGEX = "^(09|\\+639|639|9)\\d{9}$";
        Pattern pattern = Pattern.compile(MOBILE_REGEX);
        Matcher matcher = pattern.matcher(mobileNumber);
        return !matcher.matches();
    }


}
