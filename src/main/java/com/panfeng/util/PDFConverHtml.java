package com.panfeng.util;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.stereotype.Component;

@Component
public class PDFConverHtml {

	private File inputFilePath;
	private File outputFilePath;
	private String officehome = Constants.OFFICEHOME;

	public void conver() {
		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
		String officeHome = officehome;
		System.out.println("---------officehome---"+officehome+"------------");
		config.setOfficeHome(officeHome);
		OfficeManager officeManager = config.buildOfficeManager();
		officeManager.start();
		OfficeDocumentConverter converter = new OfficeDocumentConverter(
				officeManager);
		converter.convert(inputFilePath, outputFilePath);
		officeManager.stop();
	}

	public File getInputFilePath() {
		return inputFilePath;
	}

	public void setInputFilePath(File inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public File getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(File outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	public PDFConverHtml(File inputFilePath, File outputFilePath) {
		super();
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
	}

	public PDFConverHtml() {
		super();
	}
}
