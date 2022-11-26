package com.pruebasan.android_cesde_social_network.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static String getDate() {
        Date date = new Date();
        return formatter.format(date);
    }

    public static Date getDate(String date) throws ParseException {
        return formatter.parse(date);
    }
}
