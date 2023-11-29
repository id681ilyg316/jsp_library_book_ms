package com.sa.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sa.domain.Book;
import com.sa.domain.User;

public class ImgUtils {
	public static Object uploadUserImg(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 500);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 5);
		Map<String, String> maps = new HashMap<String, String>();
		try {
			List<FileItem> items = upload.parseRequest(request);
			// 2. 遍历 items:
			for (FileItem item : items) {
				// 若是一个一般的表单域, 打印信息
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					maps.put(name, value);
				}
				// 若是文件域则把文件保存到 d:\\files 目录下.
				else {
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String contentType = item.getContentType();
					long sizeInBytes = item.getSize();
					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					maps.put("imgPath", fileName);
					int len = 0;
					fileName = request.getServletContext().getRealPath("/img/user/") + fileName;

					OutputStream out = new FileOutputStream(fileName);

					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					out.close();
					in.close();
				}
			}
			//照片和用户 ID 一致
//			if(maps.get("id")!=null && maps.get("imgPath")!=null) {
//				maps.put("imgPath", maps.get("id")+".jpg");
//			}
			User user = new User(maps.get("id"), maps.get("name"), maps.get("college"),
					maps.get("major"), maps.get("classes"), maps.get("id"), maps.get("imgPath"));
			System.out.println(user);
			return user;
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Object uploadBookImg(HttpServletRequest request) throws ParseException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 500);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 5);
		Map<String, String> maps = new HashMap<String, String>();
		try {
			List<FileItem> items = upload.parseRequest(request);
			// 2. 遍历 items:
			for (FileItem item : items) {
				// 若是一个一般的表单域, 打印信息
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					maps.put(name, value);
				}
				// 若是文件域则把文件保存到 d:\\files 目录下.
				else {
					
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String contentType = item.getContentType();
					long sizeInBytes = item.getSize();
					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					if(!fileName.endsWith(".pdf")) {
						maps.put("imgPath", fileName);
						fileName = request.getServletContext().getRealPath("/img/book/") + fileName;
					}else {
						maps.put("pdfPath", fileName);
						fileName = request.getServletContext().getRealPath("/pdf/book/") + fileName;
					}
					
					OutputStream out = new FileOutputStream(fileName);

					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					out.close();
					in.close();
				}
			}
			//照片和用户 ID 一致
//			if(maps.get("id")!=null && maps.get("imgPath")!=null) {
//				maps.put("imgPath", maps.get("id")+".jpg");
//			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date pubTime = new java.sql.Date(sdf.parse(maps.get("pubTime")).getTime());
			Book book = new Book(maps.get("bookId"), maps.get("bookName"), maps.get("type"),
					maps.get("isbn"), maps.get("author"), maps.get("press"), pubTime,
					Integer.parseInt(maps.get("allQuantity")), Integer.parseInt(maps.get("allQuantity")), maps.get("imgPath"), maps.get("pdfPath"));
			return book;
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
