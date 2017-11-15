package com.panfeng.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;


public interface CoreService {
	public String getFile();
	public File convertFile(MultipartFile multipartFile);
}
