package com.epam.runner.file;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

public class Upload {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1251";
    private static final int CR = (int)'\r';
    private static final int LF = (int)'\n';
    public static int[] extractData(HttpServletRequest request) throws IOException
    {
// Содержимое пришедших байтов их запроса (содержимое приходящего файла)
        InputStream is = request.getInputStream();
        int[] data = new int[request.getContentLength()];
        int bytes;
        int counter = 0;
        while((bytes=is.read())!=-1)
        {
            data[counter]=bytes;
            counter++;
        }
        is.close();

// Определение индексов срезки
        int i;
        int beginSliceIndex = 0;
// Конечный индекс срезки - длина границы + доп. символы.
        int endSliceIndex = data.length - getBoundary(request).length()-9;

        for(i = 0; i < data.length; i++)
        {
// Начальный индекс срезки: после того как встретятся 2 раза подряд \r\n
            if(data[i] == CR && data[i+1] == LF && data[i+2] == CR && data[i+3] == LF)
            {
                beginSliceIndex = i+4;
                break;
            }
        }

        int[] dataSlice = new int[endSliceIndex-beginSliceIndex+1];
        for(i = beginSliceIndex; i<=endSliceIndex; i++)
        {
            dataSlice[i-beginSliceIndex]=data[i];
        }

        return dataSlice;
    }

    // Поиск границы
    public static String getBoundary(HttpServletRequest request)
    {
        String cType = request.getContentType();
        return cType.substring(cType.indexOf("boundary=")+9);
    }
}



