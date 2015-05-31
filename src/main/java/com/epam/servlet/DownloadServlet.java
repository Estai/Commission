package com.epam.servlet;

import com.epam.file.dir.DownloadFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class DownloadServlet extends HttpServlet{
 
  private static final int BYTES_DOWNLOAD = 1024;
 
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
	  String file = request.getParameter("file");
	  String download = DownloadFile.encodeContentDispositionForDownload(request, file, false);
	  response.setContentType("text/plain");response.setHeader("Content-Disposition", download);
	ServletContext ctx = getServletContext();
	  InputStream is = null;
	  try {
		  is = ctx.getResourceAsStream("/static/www/file/"+file);
	  } catch (Exception e) {
		  e.printStackTrace();
	  }

	  int read=0;
	byte[] bytes = new byte[BYTES_DOWNLOAD];
	OutputStream os = response.getOutputStream();
 
	while((read = is.read(bytes))!= -1){
		os.write(bytes, 0, read);
	}
	os.flush();
	os.close();	
   }
}