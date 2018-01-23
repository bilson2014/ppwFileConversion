package com.panfeng.resource.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.panfeng.service.CoreService;

@RestController
public class CoreController {
	@Autowired
	CoreService coreService;

	@RequestMapping("/convert")
	public void test(MultipartFile file,final HttpServletResponse response) {
		File resfile=coreService.convertFile(file);
		try {
			InputStream inputStream=new FileInputStream(resfile);
			ServletOutputStream servletOutputStream=response.getOutputStream();
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream");
			response.setContentLength(inputStream.available());
			String filename=URLEncoder.encode(resfile.getName(), "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+  filename+ "\"\r\n");
			byte[] data = new byte[1024];
			int index = 0;
			while ((index = inputStream.read(data)) != -1) {
				servletOutputStream.write(data, 0, index);
			}
			servletOutputStream.flush();
			inputStream.close();
			servletOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/convertPdf")
	public void convertPdf(MultipartFile file,final HttpServletResponse response) {
		File resfile=coreService.convertToPdf(file);
		try {
			InputStream inputStream=new FileInputStream(resfile);
			ServletOutputStream servletOutputStream=response.getOutputStream();
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream");
			response.setContentLength(inputStream.available());
			String filename=URLEncoder.encode(resfile.getName(), "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+  filename+ "\"\r\n");
			byte[] data = new byte[1024];
			int index = 0;
			while ((index = inputStream.read(data)) != -1) {
				servletOutputStream.write(data, 0, index);
			}
			servletOutputStream.flush();
			inputStream.close();
			servletOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//删除无用文件
		if(resfile!=null && resfile.exists()){
			resfile.delete();
		}
	}
}
