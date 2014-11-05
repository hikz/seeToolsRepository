package chargedensityprofile;


import java.awt.*;
import java.io.Serializable;
import org.jfree.ui.RefineryUtilities;

public class CombinedArrays1 implements Serializable {

    double[][] final23 = new double[2][];
    int n;   
    
    CombinedArrays1() {
    }

    @SuppressWarnings("empty-statement")
    CombinedArrays1(double[] c14, double[] c15, int n, int xAxisScale, double incidentIonEnergy, String symbol, int numClicks) {
        super();
      System.out.println("xAxisScaleww000www: " + xAxisScale);
        int len = c15.length;
        int count = 0;
        if (xAxisScale != 0) {
            for (int i = 0; i < len; i++) {
                if (c14[i] < 1) {
                    count++;
                }
            }
        }
        final23[0] = new double[len - count];
        final23[1] = new double[len - count];
        
        int k = 0;
        n = len;
        for (int j = 0; j < len; j++) {
            if (xAxisScale != 0) {
                if (c14[j] < 1.0) {
                    continue;
                }
                if (xAxisScale != 0 && c14[j] > xAxisScale) {
                    n = k;
                    break;
                }

                final23[1][k] = c15[j];
                final23[0][k] = c14[j];
                k++;
            }
        else {
                if ((c14[j] == 0.0) || (c15[j] == 0.0)) {
                    n = j;
                    break;
                }
                final23[0][j] = c14[j];
                final23[1][j] = c15[j];
                
            }
        }

        for (int j = 2; j < n; j++) {
           //  System.out.println( "c14        " + c15[j]+ "   c15   "+ c14[j]);
             
             
        }
        LineChartDemo21 demo = new LineChartDemo21("My Chart Demo1", final23, n, xAxisScale, incidentIonEnergy, symbol, numClicks);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(false);

    }
}
