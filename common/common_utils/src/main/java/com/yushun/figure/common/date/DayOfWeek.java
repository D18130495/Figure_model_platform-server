package com.yushun.figure.common.date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public class DayOfWeek {
    private String dayOfWeek;

    public String getWeek(DateTime dateTime) {
        switch (dateTime.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                dayOfWeek = "Sunday";
                break;
            case DateTimeConstants.MONDAY:
                dayOfWeek = "Monday";
                break;
            case DateTimeConstants.TUESDAY:
                dayOfWeek = "Tuesday";
                break;
            case DateTimeConstants.WEDNESDAY:
                dayOfWeek = "Wednesday";
                break;
            case DateTimeConstants.THURSDAY:
                dayOfWeek = "Thursday";
                break;
            case DateTimeConstants.FRIDAY:
                dayOfWeek = "Friday";
                break;
            case DateTimeConstants.SATURDAY:
                dayOfWeek = "Saturday";
                break;
        }

        return dayOfWeek;
    }
}
