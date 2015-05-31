package com.epam.runner;

import com.epam.file.dir.Archiv;
import com.epam.file.dir.Director;
import com.epam.manager.DateManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FillerBlank extends Canvas {

    public static void main(String[] args) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = sdf.parse("24-05-1993");
            System.out.println(df.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


}
