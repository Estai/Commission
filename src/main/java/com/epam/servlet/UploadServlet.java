package com.epam.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.entity.Enrollee;
import com.epam.entity.Role;
import com.epam.entity.User;
import com.epam.file.dir.Archiv;
import com.epam.file.dir.Director;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
public class UploadServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enrollee enrollee = (Enrollee) request.getSession().getAttribute("enrollee");
		//проверяем является ли полученный запрос multipart/form-data
		if (request.getSession().getAttribute("enrollee") != null ) {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		// Создаём класс фабрику
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Максимальный буфера данных в байтах,
		// при его привышении данные начнут записываться на диск во временную директорию
		// устанавливаем один мегабайт
		factory.setSizeThreshold(1024 * 1024);

		// устанавливаем временную директорию
		File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(tempDir);

		//Создаём сам загрузчик
		ServletFileUpload upload = new ServletFileUpload(factory);

		//максимальный размер данных который разрешено загружать в байтах
		//по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
		upload.setSizeMax(1024 * 1024 * 10);

		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (item.isFormField()) {
					//если принимаемая часть данных является полем формы
					processFormField(item);
				} else {
					//в противном случае рассматриваем как файл
					processUploadedFile(item,request);
				}
			}
//          if(){}
			File dir = Director.createDir("D:\\Java\\Books\\Java\\Commission\\src\\main\\webapp\\static\\www\\file\\" + enrollee.getLastName() + "_" + enrollee.getFirstName());
			File zipFile= Director.createFile("D:\\Java\\Books\\Java\\Commission\\src\\main\\webapp\\static\\www\\file\\"+enrollee.getLastName() + "_" + enrollee.getFirstName()+".zip");

			try {
				Archiv.directoryToZip(dir, zipFile);
				Director.deleteDirectory(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String location = request.getContextPath() + "/do/comission";
			response.sendRedirect(location);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/comission.jsp");
//			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}else {

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}

			// Создаём класс фабрику
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Максимальный буфера данных в байтах,
			// при его привышении данные начнут записываться на диск во временную директорию
			// устанавливаем один мегабайт
			factory.setSizeThreshold(1024 * 1024);

			// устанавливаем временную директорию
			File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(tempDir);

			//Создаём сам загрузчик
			ServletFileUpload upload = new ServletFileUpload(factory);

			//максимальный размер данных который разрешено загружать в байтах
			//по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
			upload.setSizeMax(1024 * 1024 * 10);

			try {
				List items = upload.parseRequest(request);
				Iterator iter = items.iterator();

				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					if (item.isFormField()) {
						//если принимаемая часть данных является полем формы
						processFormField(item);
					} else {
						//в противном случае рассматриваем как файл
						processUploadedFile(item,request);
					}
				}
				User user = (User) request.getSession().getAttribute("user");
				String location;
if(user!=null && user.getRole()== Role.ADMIN){ location= request.getContextPath() + "/do/admin/document";}
			else{	 location= request.getContextPath() + "/do/login";}
				response.sendRedirect(location);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/comission.jsp");
//			dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
		}
		}

		/**
		 * Сохраняет файл на сервере, в папке upload.
		 * Сама папка должна быть уже создана.
		 *
		 * @param item
		 * @throws Exception
		 */

	private void processUploadedFile(FileItem item,HttpServletRequest request) throws Exception {
		File uploadetFile = null;
		Enrollee enrollee = (Enrollee) request.getSession().getAttribute("enrollee");
		User user = (User) request.getSession().getAttribute("user");

		File dir;
		if(user!=null && user.getRole()!= Role.ADMIN) {
			 dir= Director.createDir("D:\\Java\\Books\\Java\\Commission\\src\\main\\webapp\\static\\www\\file\\" + enrollee.getLastName() + "_" + enrollee.getFirstName());

		}
		else {
			 dir = Director.createDir("D:\\Java\\Books\\Java\\Commission\\src\\main\\webapp\\static\\www\\file\\");
                request.setAttribute("success","Документы отправлены на сервер");
		}

		//выбираем файлу имя пока не найдём свободное
		do {
			String path = dir+"\\"+ item.getName();

//			"D:\\Java\\Books\\Java\\Commission\\src\\main\\webapp\\static\\www\\file\\"+request.getSession().getAttribute("enrollee")
// 		("src/main/webapp/static/www/"+random.nextInt() + item.getName());
			uploadetFile = new File(path);
			uploadetFile.delete();
		} while (uploadetFile.exists());

		//создаём файл
		uploadetFile.createNewFile();
		//записываем в него данные
		item.write(uploadetFile);

	}


	/**
	 * Выводит на консоль имя параметра и значение
	 * @param item
	 */
	private void processFormField(FileItem item) {
		System.out.println(item.getFieldName()+"="+item.getString());		
	}
}