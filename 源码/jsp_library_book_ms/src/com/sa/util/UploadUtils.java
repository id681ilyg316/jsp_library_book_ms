package com.sa.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sa.domain.Book;
import com.sa.domain.User;


public class UploadUtils {
	public static Object UploadUtils(String kind, HttpServletRequest request) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 5);
		try {
			List<FileItem> /* FileItem */items = upload.parseRequest(request);
			// 2. 遍历 items:
			for (FileItem item : items) {
				// 若是一个一般的表单域, 打印信息
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					System.out.println(name + ": " + value);
				}
				// 若是文件域则把文件保存到 d:\\files 目录下.
				else {
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String contentType = item.getContentType();
					long sizeInBytes = item.getSize();

					System.out.println(fieldName);
					System.out.println(fileName);
					System.out.println(contentType);
					System.out.println(sizeInBytes);

					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;

					fileName = "d:\\files\\" + fileName;
					System.out.println(fileName);

					OutputStream out = new FileOutputStream(fileName);

					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}

					out.close();
					in.close();
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return null;
	}
}
