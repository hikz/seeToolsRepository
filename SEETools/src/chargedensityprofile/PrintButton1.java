package chargedensityprofile;

//Print.java
import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

class PrintButton1 implements ActionListener {
    
    static void addActionListener(ExportButton1 listener1) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
        public void actionPerformed(ActionEvent e) {
        
    System.out.println("I am printing...Goodbye!");
    
    PrinterJob pj = PrinterJob.getPrinterJob();
    if (pj.printDialog()) {
      System.out.println("here is working");  
        try {
        pj.setPageable(null);
        pj.print();}
        catch (PrinterException exc) {
            System.out.println(exc);
         }
     }   
    }

    }
