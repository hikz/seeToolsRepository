package com.seetools.businesslayer;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.block.ColumnArrangement;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import com.seetools.businesslayer.hipconv.process.HipconverterFinalInput;
import com.seetools.businesslayer.hipconv.process.HipconverterFinalOutput;
import com.seetools.businesslayer.hipconv.process.HipconverterInput;
import com.seetools.businesslayer.hipconv.process.HipconverterOutput;
import com.seetools.framework.HIXTemplateResource;
import com.seetools.framework.HIXTemplateResourceManager;

public class SeeToolsServiceImpl {

	private static final String configFileName = "hipConverterInputConfig.xml";

	/*public HipconverterFinalOutput processFileUpload(Part inputFile)
			throws Exception {
		
		
		return processData(inputFile.getInputStream());
	}*/

	/**
	 * This method returns Input list from the file uploaded.
	 * @param inputFile
	 * @return
	 * @throws Exception
	 */
	public HipconverterFinalInput getInputFormFromFile(Part inputFile) throws Exception {
		return readInput(inputFile.getInputStream(), 8);
	}
	
	public static HipconverterFinalInput readInput(InputStream inputStream,
			int numberOfBits) throws Exception {

		// String inputFileName =
		// "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/input/hipconverter_reader_input.xls";

		/*InputStream inputXML = new BufferedInputStream(new FileInputStream(
				configFileName));*/
		InputStream inputXML = SeeToolsServiceImpl.class.getClassLoader().getResourceAsStream(configFileName);
		XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
		InputStream inputXLS = new BufferedInputStream(inputStream);

		HipconverterFinalInput hipconverterFinalInput = new HipconverterFinalInput();
		Map<String, HipconverterFinalInput> beans = new HashMap<String, HipconverterFinalInput>();
		beans.put("hipconverterFinalInput", hipconverterFinalInput);

		System.out.println("Before reading : "
				+ hipconverterFinalInput.getHipconverterInputList().size());
		XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
		System.out.println("After reading : "
				+ hipconverterFinalInput.getHipconverterInputList().size());

		for (HipconverterInput hipconverterInput : hipconverterFinalInput
				.getHipconverterInputList()) {
			System.out.println(hipconverterInput.getLet() + "/"
					+ hipconverterInput.getXsec() + "/"
					+ hipconverterInput.getEnergy());
		}
		// Populate Energy fields for input
		double defaultEnergy = 10;

		for (HipconverterInput hipconverterInput : hipconverterFinalInput
				.getHipconverterInputList()) {
			hipconverterInput.setEnergy(defaultEnergy);
			defaultEnergy = defaultEnergy + 10;
		}
		hipconverterFinalInput.setNumberOfBits(numberOfBits);
		return hipconverterFinalInput;
	}

	/*private HipconverterFinalOutput processData(InputStream inputStream) throws Exception {

		String templateFileName = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/output_templates/hipconverterTemplate.xls";
		String destFileName = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/output/hipconverterOutput.xls";
		HipconverterFinalInput hipconverterFinalInput = readInput(inputStream,
				8);
		int numberOfBits = 8;
		hipconverterFinalInput.setNumberOfBits(numberOfBits);

		Map<String, HipconverterFinalInput> beans = new HashMap<String, HipconverterFinalInput>();
		beans.put("hipconverterFinalInput", hipconverterFinalInput);
		XLSTransformer transformer = new XLSTransformer();
		HIXTemplateResource resource = HIXTemplateResourceManager.getInstance().getHIXTemplate();
		Workbook workbook = transformer.transformXLS(resource.getStream(), beans);
		HipconverterFinalOutput output = processWorkbook(workbook);
		System.out.println("Your are done");
		return output;
	}*/

	public HipconverterFinalOutput getHipConverterOutput(HipconverterFinalInput hipconverterFinalInput,int newRows) throws Exception{
		
		printClassPath();
		int numberOfBits = 8;
		hipconverterFinalInput.setNumberOfBits(numberOfBits);

		//new rows added in screen need to have output default values incremented to show on chart
		
		
		Map<String, HipconverterFinalInput> beans = new HashMap<String, HipconverterFinalInput>();
		beans.put("hipconverterFinalInput", updateInputForNewRows(hipconverterFinalInput, newRows));
		XLSTransformer transformer = new XLSTransformer();
		HIXTemplateResource resource = HIXTemplateResourceManager.getInstance().getHIXTemplate();
		Workbook workbook = transformer.transformXLS(resource.getStream(), beans);
		HipconverterFinalOutput output = processWorkbook(workbook,hipconverterFinalInput.getHipconverterInputList().size());
		System.out.println("Your are done");
		return output;
	}
	
	
	private HipconverterFinalInput updateInputForNewRows(HipconverterFinalInput hipconverterFinalInput,int newRows){
		
		//This will execute only if new rows are added and not for initial input.
		if(newRows > 0){
			int inputSize = hipconverterFinalInput.getHipconverterInputList().size();
			//Last Energy value of old list before adding new row.
			for(int i = (inputSize - newRows); i< inputSize; i++){
					double initialEnergy = hipconverterFinalInput.getHipconverterInputList().get(i-1).getEnergy();
					initialEnergy = initialEnergy + 10;
					hipconverterFinalInput.getHipconverterInputList().get(i).setEnergy(initialEnergy);
			}
		}
		
		return hipconverterFinalInput;
		
	}
	private HipconverterFinalOutput processWorkbook(Workbook wb,int inputSize) throws Exception {

		/*String outputFileName = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/output/hipconverterOutput.xls";

		FileInputStream fis = new FileInputStream(outputFileName);
		Workbook wb = new HSSFWorkbook(fis);*/
		Sheet sheet = wb.getSheetAt(0);
		FormulaEvaluator evaluator = wb.getCreationHelper()
				.createFormulaEvaluator();

		HipconverterFinalOutput hipconverterFinalOutput = new HipconverterFinalOutput();
		List<HipconverterOutput> hipconverterOutputs = new ArrayList<HipconverterOutput>();
		System.out.println("here u go-fit criteria-");
		int startingPoint = 8;
		int finalOutputSize = startingPoint+inputSize;
		for (int i = startingPoint; i < finalOutputSize; i++) {
			HipconverterOutput hipconverterOutput = new HipconverterOutput();

			// Reading energy values
			CellReference energyCellReference = new CellReference("K" + i);
			Row energyRow = sheet.getRow(energyCellReference.getRow());
			Cell energyCell = energyRow.getCell(energyCellReference.getCol());
			CellValue energyCellValue = evaluator.evaluate(energyCell);

			hipconverterOutput.setEnergy(energyCellValue.getNumberValue());

			// Reading proton cross section values
			CellReference protonXSecCellReference = new CellReference("L" + i);
			Row protonXSecRow = sheet.getRow(protonXSecCellReference.getRow());
			Cell protonXSecCell = protonXSecRow.getCell(protonXSecCellReference
					.getCol());

			CellValue protonXSecCellValue = evaluator.evaluate(protonXSecCell);

			hipconverterOutput.setProtonCrossSection(protonXSecCellValue
					.getNumberValue());
			hipconverterOutputs.add(hipconverterOutput);
		}

		hipconverterFinalOutput.setHipconverterOutputList(hipconverterOutputs);
		for (HipconverterOutput output : hipconverterFinalOutput
				.getHipconverterOutputList()) {
			System.out.println(output.getProtonCrossSection() + "--"
					+ output.getEnergy());
		}
		return hipconverterFinalOutput;

	}

	public JFreeChart getChart(HipconverterFinalOutput hipconverterFinalOutput) {

		XYSeries series1 = new XYSeries("Hip converter");
		JFreeChart chart = null;
		if (hipconverterFinalOutput.getHipconverterOutputList().size() > 0) {
			int i = 0;
			for (HipconverterOutput hipconverterOutput : hipconverterFinalOutput
					.getHipconverterOutputList()) {
				if (i != 0) {
					series1.add(hipconverterOutput.getEnergy(),
							hipconverterOutput.getProtonCrossSection());
				}
				i++;
			}

			// Displaying the data series
			for (int j = 0; j < series1.getItemCount(); j++) {
				System.out.println("X-value : " + series1.getX(j));
				System.out.println("Y-value : " + series1.getY(j));
			}
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);

			// DataSet initialization end
			chart = ChartFactory.createXYLineChart("HIP CONVERTER GRAPH",
					" ", " ", dataset, PlotOrientation.VERTICAL, true, true,
					false);

			// Customization of the plot.
			final XYPlot plot = chart.getXYPlot();
			plot.setDataset(dataset);
			plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
			plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
			plot.setBackgroundPaint(Color.WHITE);

			final XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
			renderer1.setSeriesShapesVisible(0, false);
			renderer1.setSeriesShapesVisible(1, false);
			renderer1.setSeriesPaint(0, Color.red);
			plot.setRenderer(0, renderer1);

			// Range - Y axis, Domain - X axis
			// final NumberAxis domainAxis = new NumberAxis("Energy(MeV)");
			// final LogAxis rangeAxis = new LogAxis("Log(Proton X Sec(cm-2))");
			final LogarithmicAxis domainAxis = new LogarithmicAxis(
					"Energy(MeV)");
			final LogarithmicAxis rangeAxis = new LogarithmicAxis(
					"Log (Proton Xsec(cm-2))");

			rangeAxis.setExpTickLabelsFlag(true);

			plot.setRangeAxis(rangeAxis);
			plot.setDomainAxis(domainAxis);

			/*
			 * DecimalFormat numFormat = new DecimalFormat("0E0");
			 * rangeAxis.setNumberFormatOverride(numFormat);
			 * rangeAxis.setStandardTickUnits
			 * (LogAxis.createLogTickUnits(Locale.ENGLISH));
			 */

			rangeAxis.setRange(series1.getMinY(), series1.getMaxY());

			// rangeAxis1.setNumberFormatOverride(new
			// UnitNumberFormat(UnitValue.AMPS));
			rangeAxis.setLabelPaint(Color.red);

			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(rangeAxis);

			

			// Legend details
			LegendTitle legendTitle = chart.getLegend();
			LegendTitle legendTitleNew = new LegendTitle(plot,
					new ColumnArrangement(), new ColumnArrangement());
			legendTitleNew.setPosition(legendTitle.getPosition());
			legendTitleNew.setBackgroundPaint(legendTitle.getBackgroundPaint());
			legendTitleNew.setBorder(0.25, 0.25, 0.25, 0.25);
			chart.removeLegend();
			chart.addLegend(legendTitleNew);

			chart.getTitle().setBackgroundPaint(Color.lightGray);
			chart.getTitle().setFont(rangeAxis.getLabelFont());
			chart.getTitle().setExpandToFitSpace(true);
			chart.setPadding(new RectangleInsets(10, 10, 10, 10));
		
		}
		return chart;
	}

	
	private void printClassPath()
	{
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		 
        URL[] urls = ((URLClassLoader)cl).getURLs();
 
        for(URL url: urls){
        	System.out.println(url.getFile());
        }
	}
}
