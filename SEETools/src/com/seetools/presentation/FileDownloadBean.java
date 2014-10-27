package com.seetools.presentation;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seetools.presentation.common.FacesManager;
import com.seetools.presentation.common.RequestManager;
import com.seetools.util.Constants;

@ManagedBean
@ViewScoped
public class FileDownloadBean {
	
	final Logger logger = LoggerFactory.getLogger(FileDownloadBean.class);
	
	
	public void downLoad() throws IOException {
		
		FacesContext facesContext = FacesManager.getFacesContext();
		ExternalContext ec = facesContext.getExternalContext();
		OutputStream outputStream = (OutputStream)ec.getResponseOutputStream();
		
		String resourceName = getResourceNameForTool();
		logger.debug("Sample Input File Resource");
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
		
		if (is == null) {
			ec.responseSendError(HttpServletResponse.SC_NOT_FOUND, "File currently not found. Please try later");
			return;
		}
		ec.responseReset();
		ec.setResponseBufferSize(Constants.File.DEFAULT_BUFFER_SIZE);
		ec.setResponseContentType(Constants.File.FILE_DOWNLOAD_CONTENT_TYPE);
		
		//ec.setResponseHeader("Content-Length", String.valueOf(file.length()));
		ec.setResponseHeader(Constants.File.FILE_DOWNLOAD_HEADER_NAME, "attachment;filename=\"" + resourceName + "\"");
		
		BufferedInputStream buffInputStream = null;
		
		try {
			buffInputStream = new BufferedInputStream(is, Constants.File.DEFAULT_BUFFER_SIZE);
			byte[] buffer = new byte[Constants.File.DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = buffInputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
		} finally {
			buffInputStream.close();
			outputStream.close();
		}
		facesContext.responseComplete();
	}

	
	private String getResourceNameForTool() throws IOException {
		
		String parameterValue = RequestManager.getRequestParameter("fromTool");
		
		if(parameterValue != null) {
			Properties toolInputFileNamesProp = new Properties();
			InputStream toolInputFileNamesStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.File.TOOL_INPUT_FILE_NAMES);
			
			toolInputFileNamesProp.load(toolInputFileNamesStream);
			return (String)toolInputFileNamesProp.get(parameterValue);
		}
		return null;
	}
}
