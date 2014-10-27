   package chargedensityprofile;


import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

public class LineChartDemo21 extends ApplicationFrame {

    double[][] final23 = new double[2][];
    int LocalN, xAxisScale, numClciks;
    public static XYSeries series1, series2, series3, sereies4;

    public LineChartDemo21(String title, double[][] final23, int n, int xAxisScale, double incidentIonEnergy, String symbol, int numClicks) {
        super(title);

        XYDataset dataset = createDataset(final23, n, incidentIonEnergy, symbol, xAxisScale, numClicks);
        JFreeChart chart = createChart(dataset);
        ChartPanel cpnl = new ChartPanel(chart);
        cpnl.setPreferredSize(new Dimension(430, 430));
        MainMenuE1.jpnl3.add(cpnl);
        MainMenuE1.box3.add(MainMenuE1.jpnl3);
        int x = numClicks;
    }

    private XYDataset createDataset(double[][] final23, int n, double incidentIonEnergy, String symbol, int xAxisScale, int x) {

        String incidentIonEnergyX = String.valueOf(incidentIonEnergy);
        System.out.println("incidentionEnergyX   " + incidentIonEnergyX);
        System.out.println("numCLciks:   " + x);
        
        switch (x) {

            case 1: 
                
                series1 = new XYSeries("first"); //symbol+ ", " + incidentIonEnergy + " MeV");
                for (int j = 3; j < n; j++) {
                    series1.add(final23[1][j], final23[0][j]);
                    System.out.println("11111final23[1][j]   " + final23[1][j]);
                }
               // break;
            case 2:
                System.out.println("22222numClicks:   " + x);
                series2 = new XYSeries("second"); //symbol+ ", " + incidentIonEnergyX + " MeV");
                for (int j = 3; j < n; j++) {
                    series2.add(final23[1][j], final23[0][j]);
                    System.out.println("222final23[1][j]   " + final23[1][j]);
                }    
                break;
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    private static JFreeChart createChart(XYDataset dataset) {
        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Electron density profile",
                // chart title
                " ",
                // x axis label
                "Range (nm)",
                // y axis label
                dataset,
                // data
                PlotOrientation.HORIZONTAL,
                // orientation
                true,
                // include legend
                true,
                // tooltips
                false // urls
                );

        final XYPlot plot = chart.getXYPlot();
        final NumberAxis rangeAxis = new LogarithmicAxis("Range (nm)");
        final NumberAxis domainAxis = new LogarithmicAxis("Log(electron density (cm-3))");
        DecimalFormat numFormat = new DecimalFormat("0.##E0");
        //     rangeAxis.setNumberFormatOverride(numFormat);
        domainAxis.setNumberFormatOverride(numFormat);
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);

        //   rangeAxis.setNumberFormatOverride(numFormat);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.white);

        chart.addSubtitle(new TextTitle(" Calculations are based on Katz Model"));
        TextTitle source = new TextTitle("© Copyright 2013, The Radiation Group, All rights reserved");
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        chart.setBackgroundPaint(Color.CYAN);
        System.out.println("\n");
        //    chart.setBackgroundPaint((new Color(0x02D1C3)));//Light pink
        //    for (int i = 0; i < dataset.getItemCount(0); i++) {
        //        System.out.println("dataset: " + dataset.getYValue(0, i) + "," + dataset.getXValue(0, i));
        //  }

        return chart;

    }
}
