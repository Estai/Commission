package com.epam.action;

import com.epam.runner.file.Upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UploadAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        FileOutputStream fos=null;
        OutputStream os=null;
//        resp.setContentType("text/plain");resp.setHeader("Content-Disposition", "attachment; filename=1.rar");
       try {

           os = resp.getOutputStream();
           fos = new FileOutputStream("D:/estai.txt");
           int[] dataSlice = Upload.extractData(req);
           int i;
           for(i=0;i < dataSlice.length; i++)os.write(dataSlice[i]);


       }
        catch (IOException e)
        {
            System.err.println("Ошибка");
        }
        finally {
           try {
               fos.flush();
               fos.close();
               os.flush();
               os.close();
           } catch (IOException e) {
               System.err.println("Ошибка");
           }

       }

        return null;
    }
}
