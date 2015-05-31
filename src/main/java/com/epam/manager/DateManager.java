package com.epam.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateManager {
    public static Date installDate(String year,String month,String day)  {

        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
        String dateString=month+" "+day+", "+year;
        Date date= null;
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
