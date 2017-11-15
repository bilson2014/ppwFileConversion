package com.panfeng.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.panfeng.service.CoreService;
import com.panfeng.util.Constants;
import com.panfeng.util.FileUtils;
import com.panfeng.util.PDFConverHtml;
import com.panfeng.util.RuntimeUtils;
import com.panfeng.util.VerifyFileUtils;

@Service
public class CoreServiceImpl implements CoreService {

	String pdf2html = Constants.PDF2HTML;

	@Override
	public File convertFile(MultipartFile multipartFile) {

		String fileName = multipartFile.getOriginalFilename();
		File file = new File(Constants.TEMP_DIR, multipartFile.getOriginalFilename());
		try {
			InputStream inputStream = multipartFile.getInputStream();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			FileUtils.saveTo(inputStream, fileOutputStream);
			fileOutputStream.flush();
			inputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String extName = FileUtils.getExtName(fileName, ".");
		boolean isDoc = VerifyFileUtils.verifyDocFile(extName);
		if (isDoc) {
			File pdfFile;
			String name = fileName.substring(0, fileName.indexOf('.'));
			String namepdf = name + ".pdf";
			if (!extName.toLowerCase().equals("pdf")) {
				// 将文件转换至pdf
				pdfFile = new File(Constants.TEMP_DIR, namepdf);
				PDFConverHtml pdfConverHtml = new PDFConverHtml(file, pdfFile);
				pdfConverHtml.conver();
			} else {
				pdfFile = file;
			}

			File output = new File(Constants.DOC_DIR);
			String command = String.format("%s %s --dest-dir %s", pdf2html,
					pdfFile.getAbsolutePath(), output.getAbsolutePath());
			RuntimeUtils ru = new RuntimeUtils(command);
			ru.start();
			File f=new File(output.getAbsolutePath(),name+".html");
			return f;
		}
		return null;
	}

	public String getFile() {
		return "";
	}
}
