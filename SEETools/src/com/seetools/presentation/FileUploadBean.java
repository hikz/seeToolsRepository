package com.seetools.presentation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputHidden;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import com.seetools.businesslayer.SeeToolsServiceImpl;
import com.seetools.businesslayer.hipconv.process.HipconverterFinalInput;
import com.seetools.businesslayer.hipconv.process.HipconverterFinalOutput;
import com.seetools.businesslayer.hipconv.process.HipconverterInput;
import com.seetools.presentation.common.SessionManager;

@ManagedBean
@SessionScoped
public class FileUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Part inputFile;	
	private HipconverterFinalOutput hipconverterFinalOutput = new HipconverterFinalOutput();
	private List<HipconverterInput> hipconverterInputList;
	
	private Boolean displayOutput = new Boolean(false);
	
	private Boolean displayInput = new Boolean(false);
	
	private HtmlInputHidden addCount = new HtmlInputHidden();
	
	

	public String initializeTool() {
		return this.clearAll();
	}

	/*
	 * This method will accept the form data which is sent in from table and convert it into output table
	 * and also generate the chart with the output data
	 */
	public String fetchOutput() {

		System.out.println("Inside upload method");
		String status = new String();
		try {
			SeeToolsServiceImpl seeToolsServiceImpl = new SeeToolsServiceImpl();
			HipconverterFinalInput hipconverterFinalInput = new HipconverterFinalInput();
			hipconverterFinalInput.setHipconverterInputList(hipconverterInputList);
			
			this.setHipconverterFinalOutput(seeToolsServiceImpl.getHipConverterOutput(hipconverterFinalInput,((Integer)addCount.getValue()).intValue()));
			
			if (this.getHipconverterFinalOutput().getHipconverterOutputList().size() > 0) {
				SessionManager.addSessionAttribute("hipconverterFinalOutput", this.getHipconverterFinalOutput());
				System.out.println("display is setting true");
				setOuputFlags();
			}
			return "hipConverterTool";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
		
	public void addNewRow(){
		
		System.out.println("Inside add new method");
		// Add new MyData item to the data list.
        hipconverterInputList.add(new HipconverterInput());
        addCount.setValue(((Integer) addCount.getValue()) + 1);
        setInputFlags();
	}
	public String displayInputFormDetails(){
		
		String status = new String();
		try {
			/*SeeToolsServiceImpl seeToolsServiceImpl = new SeeToolsServiceImpl();
			hipconverterInputList = ((seeToolsServiceImpl.getInputFormFromFile(inputFile).getHipconverterInputList()));*/
			
			/*if (this.getHipconverterInputList().size() > 0) {
				SessionManager.addSessionAttribute("hipconverterInputList", this.getHipconverterInputList());*/
				System.out.println("show form is setting true");
				setInputFlags();
			/*}*/
			
			return "hipConverterTool";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	/*
	 * This method will accept input file uploaded and will convert it into a form where user can add more rows or edit the uploaded data.
	 */
	public void loadDataFromInputFile(){
		try {
			SeeToolsServiceImpl seeToolsServiceImpl = new SeeToolsServiceImpl();
			if(inputFile != null){
				hipconverterInputList = ((seeToolsServiceImpl.getInputFormFromFile(inputFile).getHipconverterInputList()));
			}
			
			//SessionManager.addSessionAttribute("inputFile", this.getInputFile());
			
			//As this load is called every time the page is first loaded, we will check if any addCount value is set and add row for it.
			for (int i = 0; i < (Integer) addCount.getValue(); i++) {
				hipconverterInputList.add(new HipconverterInput());
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void drawChart(OutputStream out, Object data) throws IOException {

		HipconverterFinalOutput hipconverterFinalOutput = (HipconverterFinalOutput)SessionManager.getSessionAttribute("hipconverterFinalOutput");
		SeeToolsServiceImpl seeToolsServiceImpl = new SeeToolsServiceImpl();
		
		BufferedImage bufferedImage = seeToolsServiceImpl.getChart(hipconverterFinalOutput).createBufferedImage(500, 500);
		ImageIO.write(bufferedImage, "gif", out);
		cleanUpOutputVariables();
		out.flush();
		out.close();
	}
	
	/*
	 * This method clears the uploaded form and file details from the session. 
	 */
	public String clearAll(){
		this.hipconverterInputList = null;
		setInitialFlags();		
		return "hipConverterTool";
	}
	
	private void cleanUpOutputVariables(){
		//this.hipconverterInputList = null;
		this.hipconverterFinalOutput = null;		
		this.getAddCount().setValue(0);
	}

	public List<HipconverterInput> getHipconverterInputList() {
		if(hipconverterInputList == null){
			loadDataFromInputFile();
		}
		return hipconverterInputList;
	}


	public void addHipconverterInputList(HipconverterInput hipconverterInput){
		hipconverterInputList.add(hipconverterInput);
	}

	public HtmlInputHidden getAddCount() {
		return addCount;
	}

	public void setAddCount(HtmlInputHidden addCount) {
		this.addCount = addCount;
	}

	public void setHipconverterInputList(
			List<HipconverterInput> hipconverterInputList) {
		this.hipconverterInputList = hipconverterInputList;
	}
	

	
	public FileUploadBean() {
		//Initialize the abb count to zero to avoid Null pointer. This is used when ever user tries to add new row.
		 addCount.setValue(0);
	}

	
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

	public Boolean getDisplayOutput() {
		return displayOutput;
	}

	public void setDisplayOutput(Boolean displayOutput) {
		this.displayOutput = displayOutput;
	}

	public Boolean getDisplayInput() {
		return displayInput;
	}

	public void setDisplayInput(Boolean displayInput) {
		this.displayInput = displayInput;
	}
	

	private void setInitialFlags(){
		this.setDisplayInput(false);
		this.setDisplayOutput(false);
	}
	
	private void setInputFlags(){
		this.setDisplayInput(true);
		this.setDisplayOutput(false);
	}

	private void setOuputFlags(){
		this.setDisplayOutput(true);
		this.setDisplayInput(false);
}
}
