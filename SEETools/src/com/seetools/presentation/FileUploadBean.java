package com.seetools.presentation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import com.seetools.businesslayer.SeeToolsServiceImpl;
import com.seetools.businesslayer.hipconv.process.HipconverterFinalOutput;
import com.seetools.presentation.common.SessionManager;

@ManagedBean
@RequestScoped
public class FileUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Part inputFile;	
	private HipconverterFinalOutput hipconverterFinalOutput;
	private boolean display = false;
	

	public HipconverterFinalOutput getHipconverterFinalOutput() {
		return hipconverterFinalOutput;
	}

	public void setHipconverterFinalOutput(
			HipconverterFinalOutput hipconverterFinalOutput) {
		this.hipconverterFinalOutput = hipconverterFinalOutput;
	}

	public Part getInputFile() {
		return inputFile;
	}

	public void setInputFile(Part inputFile) {
		this.inputFile = inputFile;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	
	public String upload() {

		String status = new String();
		try {
			SeeToolsServiceImpl seeToolsServiceImpl = new SeeToolsServiceImpl();
			this.setHipconverterFinalOutput(seeToolsServiceImpl
					.processFileUpload(inputFile));
			
			if (this.getHipconverterFinalOutput().getHipconverterOutputList()
					.size() > 0) {
				SessionManager.addSessionAttribute("hipconverterFinalOutput", this.getHipconverterFinalOutput());
				System.out.println("display is setting true");
				this.setDisplay(true);
			}
			return "hipConverterTool";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public void drawChart(OutputStream out, Object data) throws IOException {

		HipconverterFinalOutput hipconverterFinalOutput = (HipconverterFinalOutput)SessionManager.getSessionAttribute("hipconverterFinalOutput");
		SeeToolsServiceImpl seeToolsServiceImpl = new SeeToolsServiceImpl();
		
			BufferedImage bufferedImage = seeToolsServiceImpl.getChart(hipconverterFinalOutput).createBufferedImage(500, 500);
			ImageIO.write(bufferedImage, "gif", out);
			out.flush();
			out.close();
		}
	

}
