package com.seetools.businesslayer.hipconv.process;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class HipconverterWriterTest {

	public static void main(String[] args) throws IOException, ParsePropertyException, InvalidFormatException, Exception {
	      
		//readInput(8);
		writeOutput();
		readOutput();
	}

	private static HipconverterFinalInput readInput(int numberOfBits) throws Exception {
								 
		String configFileName = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/input_config/hipconverter_sampleInput.xml";
		String inputFileName  = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/input/hipconverter_reader_input.xls"; 
		
		InputStream inputXML = new BufferedInputStream(new FileInputStream(configFileName));
        XLSReader mainReader =  ReaderBuilder.buildFromXML( inputXML );
        InputStream inputXLS = new BufferedInputStream(new FileInputStream(inputFileName));
       
        HipconverterFinalInput hipconverterFinalInput = new HipconverterFinalInput();
        Map<String,HipconverterFinalInput> beans = new HashMap<String, HipconverterFinalInput>();
        beans.put("hipconverterFinalInput", hipconverterFinalInput);  
        
        System.out.println("Before reading : "+ hipconverterFinalInput.getHipconverterInputList().size());
        XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
        System.out.println("After reading : "+hipconverterFinalInput.getHipconverterInputList().size());
       
        
       for(HipconverterInput hipconverterInput : hipconverterFinalInput.getHipconverterInputList()){
    	   System.out.println(hipconverterInput.getLet() + "/" + hipconverterInput.getXsec() + "/" + hipconverterInput.getEnergy());
       }
       
       hipconverterFinalInput.setNumberOfBits(numberOfBits);
       return hipconverterFinalInput;
	}
	
	private static void writeOutput() throws Exception {		
	       
		String templateFileName = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/output_templates/hipconverterTemplate.xls";
		String destFileName     = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/output/hipconverterOutput.xls";
	       HipconverterFinalInput hipconverterFinalInput = readInput(8);
	       int numberOfBits = 8; 
	       hipconverterFinalInput.setNumberOfBits(numberOfBits);
	       
	       Map<String,HipconverterFinalInput> beans = new HashMap<String, HipconverterFinalInput>();
	       beans.put("hipconverterFinalInput",hipconverterFinalInput);
	       XLSTransformer transformer = new XLSTransformer();
	       transformer.transformXLS(templateFileName, beans, destFileName);	     
	       System.out.println("Your are done");
	}
	
	private static HipconverterFinalOutput readOutput() throws Exception {
		 
	   String outputFileName  = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/output/hipconverterOutput.xls";        
	   	
	   FileInputStream fis = new FileInputStream(outputFileName);
       Workbook wb = new HSSFWorkbook(fis);
       Sheet sheet = wb.getSheetAt(0);
       FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
       
       HipconverterFinalOutput hipconverterFinalOutput = new HipconverterFinalOutput();
       List<HipconverterOutput> hipconverterOutputs = new ArrayList<HipconverterOutput>();
       
       for(int i=8; i<23;i++)
       {
		       HipconverterOutput hipconverterOutput = new HipconverterOutput();
		       
		       //Reading energy values
		       CellReference energyCellReference = new CellReference("K"+i); 
		       Row energyRow = sheet.getRow(energyCellReference.getRow());
		       Cell energyCell = energyRow.getCell(energyCellReference.getCol()); 
		       CellValue energyCellValue = evaluator.evaluate(energyCell);
		       System.out.println("here u go-fit criteria-");
		       hipconverterOutput.setEnergy(energyCellValue.getNumberValue());
               
		       //Reading proton cross section values
         	   CellReference protonXSecCellReference = new CellReference("L"+i); 
               Row protonXSecRow = sheet.getRow(protonXSecCellReference.getRow());
               Cell protonXSecCell = protonXSecRow.getCell(protonXSecCellReference.getCol()); 

               CellValue protonXSecCellValue = evaluator.evaluate(protonXSecCell);
               
               hipconverterOutput.setProtonCrossSection(protonXSecCellValue.getNumberValue());
               hipconverterOutputs.add(hipconverterOutput);
       }
       
       hipconverterFinalOutput.setHipconverterOutputList(hipconverterOutputs);
       for(HipconverterOutput output : hipconverterFinalOutput.getHipconverterOutputList()){
    	   System.out.println(output.getProtonCrossSection() + "--" + output.getEnergy());
       }
       return hipconverterFinalOutput;
	
	}
}
