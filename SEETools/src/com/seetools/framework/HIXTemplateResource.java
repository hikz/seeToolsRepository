package com.seetools.framework;

import java.io.InputStream;

public class HIXTemplateResource implements Resource {
	
	
	private int fileId;
	private InputStream stream;
	private String baseTemplateFileName="hipconverterTemplate";

	
	public HIXTemplateResource(int currentCounter)
	{
		this.fileId = currentCounter;
		init();
	}
	
	private void init()
	{
		String fileName = baseTemplateFileName + "-"+fileId+".xls";
		setStream(extractFile(fileName));
	}
	
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public InputStream getStream() {
		return stream;
	}
	public void setStream(InputStream stream) {
		this.stream = stream;
	}
	public String getFileName() {
		return baseTemplateFileName;
	}
	public void setFileName(String fileName) {
		this.baseTemplateFileName = fileName;
	}
	
	private InputStream extractFile(String fileName)
	{
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
		return in;
	}

}
