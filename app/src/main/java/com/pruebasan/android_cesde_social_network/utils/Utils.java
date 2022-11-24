package com.pruebasan.android_cesde_social_network.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
