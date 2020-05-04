package com.developer.smmousavi.foodino.util;

import java.util.concurrent.TimeUnit;

public class TimerUtils {

    public static long convertToTimeMillis(int hour, int min, int sec) {
        return (sec + min * 60 + hour * 3600) * 1000;
    }

    public static long[] convertToTimeFormat(long millis) {

        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        return new long[]{hours, minutes, seconds};
    }




}
