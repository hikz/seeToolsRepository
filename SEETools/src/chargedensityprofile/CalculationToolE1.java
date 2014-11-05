package chargedensityprofile;

//This program will run when the combo target material is None.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CalculationToolE1 implements ActionListener {

    double nWater = 4.04E23; //no of free electrons /cm3 in water
    double restMass = 511; //kev
    double avogadroNum = 6.02E23;
    //reading input
    JTextField inputOne;
    JTextField inputThree;
    JComboBox inputSix, inputSeven;
    JComboBox incidentIonType, targetMaterial;
    //define incident ion variables
    String incidentI, targetM;
    //incidnet ion variables
    double incidentIonEnergy, incidentIonAtomicNumber, incidentIonAtomicWeight, incidentIonIonizationEnergy;
    //target material
    double targetMaterialAtomicNumber, targetMaterialDensity, targetMaterialAtomicWeight, z;
    double zEffectiveTarget, targetMaterialIonizationEnergy, targetFreeElectronDensity;
    // calculated variables
    double beta, gamma, zEffectiveIon, omeggaMax;
    double yFactor, step = 5E-9;
    int i, n = 1000, l, m = 200; //temporary number 
    double[] deltaThickness = new double[n];
    double[] rMinusT = new double[n];
    double[] IonEnergy = new double[n];
    double[] residualEnergy = new double[n];
    double[] transmittedEnergy = new double[n];
    int xAxisScale;
    double firstTerm, secondTerm, denom, ionKineticEnergy;
    ArrayList<Double> rMinusTT = new ArrayList<>();
    double[] c14 = new double[n];
    double[] c15 = new double[n];
    String symbol;
    int numClicks, inputFour;
    //  MainMenuE mmE = new MainMenuE();

    CalculationToolE1(JComboBox incidentIonType, JTextField incidentIonEnergy, JComboBox targetMaterial, JTextField xAxisScaleFld, int numClicks) {
       // super();

        inputOne = incidentIonEnergy;
        inputSix = incidentIonType;
        inputSeven = targetMaterial;
        inputThree = xAxisScaleFld;
        inputFour = numClicks;
    }

    @SuppressWarnings("empty-statement")
    public void calc() {

        incidentI = (String) inputSix.getSelectedItem();
        targetM = (String) inputSeven.getSelectedItem();
        incidentIonEnergy = Double.parseDouble(inputOne.getText().trim());
        xAxisScale = Integer.parseInt(inputThree.getText().trim());
        numClicks = inputFour;
        //getting incident ion propertie from Elements.class
        Elements1 elms = new Elements1();
        for (int k = 0; k < 1; k++) {
            for (int j = 0; j < elms.data.length; j++) {
                if (String.valueOf(elms.data[j][k]).equals(incidentI)) {
                    // if(String.valueOf(elms.data[j][k]).equals(incidentI))
                    for (int count = 0; count < 1; count++) {
                        String rowJ = String.valueOf(elms.data[j][1]);
                        symbol = rowJ;
                        rowJ = String.valueOf(elms.data[j][2]);
                        incidentIonAtomicNumber = Double.parseDouble(rowJ);
                        rowJ = String.valueOf(elms.data[j][3]);
                        incidentIonAtomicWeight = Double.parseDouble(rowJ);
                        rowJ = String.valueOf(elms.data[j][5]);
                        incidentIonIonizationEnergy = Double.parseDouble(rowJ);
                    }
                }
            }
        }
        //getting target material properties from Elements.class
        for (int k = 0; k < 1; k++) {
            for (int j = 0; j < elms.data.length; j++) {
                if (String.valueOf(elms.data[j][k]).equals(targetM)) {
                    //    if(String.valueOf(elms.data[j][k]).equals(targetM))
                    for (int count = 0; count < 1; count++) {
                        String rowJ = String.valueOf(elms.data[j][4]);
                        targetMaterialDensity = Double.parseDouble(rowJ);
                        rowJ = String.valueOf(elms.data[j][6]);
                        targetFreeElectronDensity = Double.parseDouble(rowJ);
                        rowJ = String.valueOf(elms.data[j][2]);
                        targetMaterialAtomicNumber = Double.parseDouble(rowJ);
                        rowJ = String.valueOf(elms.data[j][3]);
                        targetMaterialAtomicWeight = Double.parseDouble(rowJ);
                        rowJ = String.valueOf(elms.data[j][5]);
                        targetMaterialIonizationEnergy = Double.parseDouble(rowJ);
                    }
                }
            }
        }

        //Incident ion properites caculations
        gamma = gamma(incidentIonEnergy, incidentIonAtomicWeight);

        beta = beta(gamma);

        zEffectiveIon = zEffectiveIon(beta, incidentIonAtomicNumber);

        omeggaMax = omeggaMax(gamma, beta);

        //target g & h calculations
        double g = (9.2 * Math.pow(targetMaterialAtomicNumber, -.2)) + 16 * Math.pow(targetMaterialAtomicNumber, -2.2);
        double h = (0.63 * targetMaterialAtomicNumber / targetMaterialAtomicWeight) + 0.27;
        double Z = targetMaterialAtomicNumber;
        double A = (0.81 * Math.pow(Z, -.38) + 0.18) * 1E-3;   //g/cm2-keV
        double B = 0.22 * Math.pow(Z, -0.555) + 0.78;
        double C = (1.1 * Math.pow(Z, 0.29) + 0.21) * 1E-3;   //per KeV
        double range = A * omeggaMax * (1 - B / (1 + C * omeggaMax));  //  gm/cm2
        System.out.println("Range          " + range);
        //calculate time step
        for (i = 0; i < n - 1; i++) {
            ionKineticEnergy = 0;
            ionKineticEnergy = 1E-2 + ionKineticEnergy;  //ionization energy = 1E-2
            IonEnergy[i] = ionKineticEnergy + 1E-3 * targetMaterialIonizationEnergy; //in KeV
            yFactor = 0.8 * Math.pow(i, 3) + 0.1 * Math.pow(i, 2) + 0.2 * i + 1.0;
            deltaThickness[i] = yFactor * step;  //assumemed in gm/cm2
            //calculate time step and n
            rMinusT[i] = (range - deltaThickness[i]);
            double num = 1 + Math.exp(-g * h);
            double denum = 1 + Math.exp(g * ((deltaThickness[i] / range) - h));  //
            double nrt = (num / denum);
            firstTerm = -(A - (A * B) - (rMinusT[i] * C));  //g/cm2-keV  
            secondTerm = Math.sqrt((Math.pow(firstTerm, 2.0)) + (4 * A * C * rMinusT[i]));
            denom = 2 * A * C;
            residualEnergy[i] = ((firstTerm + secondTerm) / denom);
            //transmitted energy calculations
            transmittedEnergy[i] = (nrt * (residualEnergy[i]));

            if (deltaThickness[i] > range) {
                break;
            }
        }

        for (int k = 0; k <= i - 1; k++) {

            rMinusTT.add(rMinusT[k]);
            rMinusTT.remove(0.0);
            l = rMinusTT.size();
        }

        for (int j = 0; j < l; j++) {
            double[] dTransmittedEnergy = new double[l];
            dTransmittedEnergy[j] = (transmittedEnergy[j] - transmittedEnergy[j + 1]);
            double dt = (rMinusT[j] - rMinusT[j + 1]);
            double dissipatedEnergy = (1E-3 * dTransmittedEnergy[j] / dt);
            double pyt = 1 / (2 * Math.PI * deltaThickness[j]); //1/gm/cm2
            double feD = 85 * targetFreeElectronDensity * 1E-6 / nWater; //no of free electrons ratio to water
            double aN = feD * Math.pow(zEffectiveIon, 2) / (Math.pow(beta, 2) * Math.pow(IonEnergy[j], 2));
            double aO = Math.pow(beta, 2) * IonEnergy[j] / omeggaMax;
            double aP = Math.PI * beta * Math.pow(zEffectiveIon, 2) / 137;
            double aQ = Math.sqrt(IonEnergy[j] / omeggaMax);
            double aR = 1 - (IonEnergy[j] / omeggaMax);
            double aS = aP * aQ * aR;
            double aT = 1 - aO - aS;
            double aU = aN * aT;
            double aV = aU * 0.001;     //dn/dw
            double aW = (1 / IonEnergy[j]) - (1 / omeggaMax);
            double aX = aV * aW;
            double aY = pyt * 1000 * dissipatedEnergy * aV * IonEnergy[j];
            double aZ = targetMaterialDensity * 1000 * 1000 * 1.6E-19;
            double bA = aY * aZ;
            double bC = 0.001 * (1 / 1.6E-019) * targetMaterialDensity / 3.6;
            double bD = bA * bC;
            double bE = deltaThickness[j] * 1E7 / targetMaterialDensity; //units are in nm
            n = j;
            //the following is not working:
            c14[j] = bE;  // x axis
            if ((c14[j] < 0.0) || (c15[j] < 0.0)) {
                break;
            }
            c15[j] = bD; //y axis
            int nmRange = (int) (range * 1E7 / targetMaterialDensity);
            if (xAxisScale > nmRange) {
                xAxisScale = 0;
            }
          //  System.out.println(c14[j] + "        " + c15[j]);
        }

        CombinedArrays1 combinedArrays;
        combinedArrays = new CombinedArrays1(c14, c15, n, xAxisScale, incidentIonEnergy, symbol, numClicks);
    }
    //Clculating methods

    private double gamma(double incidentIonEnergy, double incidentIonAtomicWeight) {
        gamma = 1 + (incidentIonEnergy / (incidentIonAtomicWeight * 938));
        return gamma;
    }

    private double beta(double gamma) {
        beta = Math.pow(1 - Math.pow(gamma, -2), 0.5);
        return beta;
    }

    private double zEffectiveIon(double beta, double incidentIonAtomicNumber) {
        zEffectiveIon = incidentIonAtomicNumber * (1 - Math.exp(-125 * beta * (Math.pow(incidentIonAtomicNumber, -.667))));
        return zEffectiveIon;
    }

    private double zEffectiveTarget(double gamma, double targetMaterialAtomicNumber) {
        zEffectiveTarget = targetMaterialAtomicNumber * (1 - Math.exp(-125 * beta * (Math.pow(targetMaterialAtomicWeight, -.667))));
        return zEffectiveTarget;
    }

    private double omeggaMax(double gamma, double beta) {
        omeggaMax = 2 * 511 * Math.pow(gamma, 2) * Math.pow(beta, 2);
        return omeggaMax;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
