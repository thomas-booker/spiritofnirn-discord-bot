package uk.co.thomasbooker.spritofnirn.trial;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrialDateTimeFormatter {

    private static final String DATE_TIME_FORMAT = "HH:mm dd/MM/yyyy";

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private static final String TIME_FORMAT = "HH:mm";

    public static LocalDateTime parseTimeStringAndDateString(String timeString, String dateString){
        return LocalDateTime.parse(timeString + " " + dateString, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    public static String parseLocalDateTime(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    public static String parseLocalTime(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public static String parseLocalDate(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

}
