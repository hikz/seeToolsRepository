package chargedensityprofile;


import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JTextField;
import javax.swing.Box;

public class MainMenuE1 implements ActionListener {

    static private final String newline = "\n";
    JFrame jfrm;
    JMenuBar jmb;
    // int xAxisScale;
    JLabel jlabSelSim, incidentIon, ionEnergy, targetMaterial;
    JLabel customMaterial, materialName, atomicNumber, atomicWeight, density;
    JLabel xAxisScaleLbl, xAxisScaleLbl2; //, simulationConditionsLbl;
    JLabel incIonTypeLbl, ionEnergyLbl, targetMaterialLbl, spacerLbl;
    JTextField ionEnergy1, incidentIonEnergy, customMaterial1, atomicNumber1, atomicWeight1, density1;
    JTextField xAxisScaleFld, xAxisScaleFld2;
    JTextArea jta;
    JLabel incIonTypeFld, ionEnergyFld, targetMaterailFld;
    JComboBox IncIons, targetMat, incidentIonType;
    public JPanel jpnl1, jpnl2, jpnl4, jpnl5, jpnl6, jpnl7, jpnl8, jpnl9, jpnl10, jpnl11, jpnl12, jpnl13;
    public JPanel jpnl14, jpnl15, jpnl16, jpnl17, jpnl18, jpnl19, jpnl20, jpnl21, jpnl22;
    public JPanel jpnl23, jpnl24;
    public static JPanel jpnl3;
    JFileChooser jfc;
    JButton printButton, saveButton, exportButton, exitButton, runButton, helpButton, submitButton;
    JButton runButton2;
    String None;
    double[] dataToBePlotted;
    Box box1, box2, box4;
    public static Box box3;
    boolean isClicked = false;
    private int numClicks;

    MainMenuE1() {

        //Declare variables
        String[] incI = {"Hydrogen", "Helium", "Lithium", "Beryllium",
            "Boron", "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Neon",
            "Sodium", "Magnesium", "Aluminium", "Silicon", "Phosphorus",
            "Sulfur", "Chlorine", "Argone", "Potassium", "Calcium",
            "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese",
            "Iron", "Cobalt", "Nickel", "Copper", "Zinc", "Gallium",
            "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton",
            "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium",
            "Molybdenum", "Technetium", "Ruthenium", "hodium", "Palladium",
            "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium",
            "Iodine", "Xenon", "Caesium", "Barium", "Lanthanum", "Cerium",
            "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium",
            "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium",
            "Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium",
            "Osmium", "Iridium", "Platinum", "Gold", "Mercury", "Thallium", "Lead",
            "Bismuth", "Polonium", "Radon", "Radium", "Actinium", "Thorium", "Protactinium",
            "Uranium"};
        String[] TaregtI = {"Silicon", "Hydrogen", "Lithium", "Beryllium",
            "Boron", "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Sodium", "Magnesium", "Aluminium", "Phosphorus",
            "Sulfur", "Chlorine", "Potassium", "Calcium",
            "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese",
            "Iron", "Cobalt", "Nickel", "Copper", "Zinc", "Gallium",
            "Germanium", "Arsenic", "Selenium", "Bromine", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium",
            "Molybdenum", "Technetium", "Ruthenium", "hodium", "Palladium",
            "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium",
            "Iodine", "Caesium", "Barium", "Lanthanum", "Cerium",
            "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium",
            "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium",
            "Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium",
            "Osmium", "Iridium", "Platinum", "Gold", "Mercury", "Thallium", "Lead",
            "Bismuth", "Polonium", "Radon", "Radium", "Actinium", "Thorium", "Protactinium",
            "Uranium"};

        //create a frame
        jfrm = new JFrame("Energetic Particle Interaction With Matter");

        // *** Use FlowLayout for the content pane. *** 
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Give the frame an initial size.   
        jfrm.setSize(900, 800);

        // Terminate the program when the user closes the application. 
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create the menu bar
        jmb = new JMenuBar();

        // Make the labels. 
        jlabSelSim = new JLabel("Select Simulation Conditions                          ");
        //   jlabOr = new JLabel("Alternate Target Material Definition");

        incidentIon = new JLabel("Incident Ion:       ");
        ionEnergy = new JLabel("Energy (MeV):               ");
        targetMaterial = new JLabel("Target Material: ");

        customMaterial = new JLabel("Enter Compound Target Properties:       ");
        materialName = new JLabel(" Material Name:          ");
        atomicNumber = new JLabel(" Atomic Number:          ");
        atomicWeight = new JLabel(" Atomic Weight:           ");
        density = new JLabel(" Density (gm/cm3):      ");
        xAxisScaleLbl = new JLabel("Max. X-Axis Range (nm):");
        xAxisScaleLbl2 = new JLabel("Max. X-Axis Range (nm):");
        //   simulationConditionsLbl = new JLabel("Selected Run Conditions       ");
        incIonTypeLbl = new JLabel("Incident Ion:     ");
        ionEnergyLbl = new JLabel("Ion Energy:          ");
        targetMaterialLbl = new JLabel("Target Material:    ");
        spacerLbl = new JLabel("                              ");
        JLabel spacer = new JLabel("                                     ");

        //Make text fields
        ionEnergy1 = new JTextField(8);
        customMaterial1 = new JTextField(8);
        atomicNumber1 = new JTextField(8);
        atomicWeight1 = new JTextField(8);
        density1 = new JTextField(8);
        xAxisScaleFld = new JTextField(8);
        xAxisScaleFld2 = new JTextField(8);
        incIonTypeFld = new JLabel();
        ionEnergyFld = new JLabel();
        targetMaterailFld = new JLabel();

        jta = new JTextArea();

        //Make Combo Boxes
        IncIons = new JComboBox(incI);
        targetMat = new JComboBox(TaregtI);

        // Create  panels
        jpnl1 = new JPanel();
        jpnl2 = new JPanel();
        jpnl3 = new JPanel();
        jpnl4 = new JPanel();
        jpnl5 = new JPanel();
        jpnl6 = new JPanel();
        jpnl7 = new JPanel();
        jpnl8 = new JPanel();
        jpnl9 = new JPanel();
        jpnl10 = new JPanel();
        jpnl11 = new JPanel();
        jpnl12 = new JPanel();
        jpnl13 = new JPanel();
        jpnl14 = new JPanel();
        jpnl15 = new JPanel();
        jpnl16 = new JPanel();
        jpnl17 = new JPanel();
        jpnl18 = new JPanel();
        jpnl19 = new JPanel();
        jpnl20 = new JPanel();
        jpnl21 = new JPanel();
        jpnl22 = new JPanel();
        jpnl23 = new JPanel();
        jpnl24 = new JPanel();
        //Description of 1st panel
        //Set the size of the first panel
        jpnl1.setPreferredSize(new Dimension(380, 270));
        //make the panel opaque
        jpnl1.setOpaque(true);

        //Add a blue border
        jpnl1.setBorder(BorderFactory.createLineBorder(Color.darkGray));

        ////Description of 2nd panel
        //Set the size of the first panel
        jpnl2.setPreferredSize(new Dimension(380, 270));
        //make the panel opaque
        jpnl2.setOpaque(true);
        //Add a blue border
        jpnl2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        //Description of 3rd panel
        //Set the size of the first panel
        jpnl3.setPreferredSize(new Dimension(440, 440));
        //make the panel opaque
        jpnl3.setOpaque(true);
        //Add a blue border
        jpnl3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        //Description of 4th panel
        //Set the size of the first panel
        jpnl4.setPreferredSize(new Dimension(330, 35));
        //make the panel opaque
        jpnl4.setOpaque(true);
        //Add a blue border
        jpnl4.setBorder(null);//BorderFactory.createLineBorder(Color.YELLOW));

        jpnl5.setPreferredSize(new Dimension(340, 30));
        //make the panel opaque
        jpnl5.setOpaque(true);
        jpnl5.setBorder(null); //BorderFactory.createLineBorder(Color.ORANGE));

        jpnl6.setPreferredSize(new Dimension(340, 30));
        //make the panel opaque
        jpnl6.setOpaque(true);
        jpnl6.setBorder(null); //BorderFactory.createLineBorder(Color.RED));

        jpnl7.setPreferredSize(new Dimension(35, 15));
        //make the panel opaque
        jpnl7.setOpaque(true);
        jpnl7.setBorder(null); //BorderFactory.createLineBorder(Color.BLUE));

        jpnl8.setPreferredSize(new Dimension(330, 30));
        //make the panel opaque
        jpnl8.setOpaque(true);
        jpnl8.setBorder(null); //BorderFactory.createLineBorder(Color.YELLOW));

        jpnl9.setPreferredSize(new Dimension(340, 45));
        //make the panel opaque
        jpnl9.setOpaque(true);
        jpnl9.setBorder(null); //BorderFactory.createLineBorder(Color.RED));

        jpnl10.setPreferredSize(new Dimension(340, 30));
        //make the panel opaque
        jpnl10.setOpaque(true);
        jpnl10.setBorder(null); //BorderFactory.createLineBorder(Color.RED));

        jpnl11.setPreferredSize(new Dimension(20, 15));
        //make the panel opaque
        jpnl11.setOpaque(true);
        jpnl11.setBorder(null); //BorderFactory.createLineBorder(Color.BLUE));

        jpnl12.setPreferredSize(new Dimension(340, 80));
        //make the panel opaque
        jpnl12.setOpaque(true);
        jpnl12.setBorder(BorderFactory.createLineBorder(Color.CYAN));

        jpnl13.setPreferredSize(new Dimension(15, 15));
        //make the panel opaque
        jpnl13.setOpaque(true);
        jpnl13.setBorder(null); //BorderFactory.createLineBorder(Color.BLUE));

        jpnl14.setPreferredSize(null); //new Dimension(340, 30));
        //make the panel opaque
        jpnl14.setOpaque(true);
        jpnl14.setBorder(null); //BorderFactory.createLineBorder(Color.BLUE));

        jpnl15.setPreferredSize(new Dimension(340, 30));
        //make the panel opaque
        jpnl15.setOpaque(true);
        jpnl15.setBorder(null); //BorderFactory.createLineBorder(Color.BLUE));

        jpnl16.setPreferredSize(new Dimension(340, 30));
        //make the panel opaque
        jpnl16.setOpaque(true);
        jpnl16.setBorder(null); //BorderFactory.createLineBorder(Color.BLUE));

        jpnl17.setPreferredSize(new Dimension(340, 30));
        //make the panel opaque
        jpnl17.setOpaque(true);
        jpnl17.setBorder(null); //BorderFactory.createLineBorder(Color.BLUE));

        jpnl18.setPreferredSize(new Dimension(340, 30));
        //make the panel opaque
        jpnl18.setOpaque(true);
        jpnl18.setBorder(null); //BorderFactory.createLineBorder(Color.BLUE));

        jpnl19.setPreferredSize(new Dimension(340, 80));
        //make the panel opaque
        jpnl19.setOpaque(true);
        jpnl19.setBorder(null); //BorderFactory.createLineBorder(Color.CYAN));

        //make the panel opaque
        jpnl20.setPreferredSize(new Dimension(340, 30));
        //make the panel opaque
        jpnl20.setOpaque(true);
        jpnl20.setBorder(null); //BorderFactory.createLineBorder(Color.CYAN));
        //make the panel opaque
        jpnl21.setPreferredSize(new Dimension(340, 30));
        jpnl21.setOpaque(true);
        jpnl21.setBorder(null); //BorderFactory.createLineBorder(Color.CYAN));

        jpnl22.setPreferredSize(new Dimension(195, 20));
        jpnl22.setOpaque(true);
        jpnl22.setBorder(null); //BorderFactory.createLineBorder(Color.CYAN));

        jpnl23.setPreferredSize(new Dimension(10, 10));
        jpnl23.setOpaque(true);
        jpnl23.setBorder(null); //BorderFactory.createLineBorder(Color.CYAN));

        jpnl24.setPreferredSize(new Dimension(15, 10));
        jpnl24.setOpaque(true);
        jpnl24.setBorder(null); //BorderFactory.createLineBorder(Color.CYAN));

        // Add the buttons and label to the panel
        jpnl10.add(jlabSelSim);
        jpnl1.add(jpnl10);
        jpnl6.add(incidentIon);
        jpnl6.add(IncIons);
        jpnl1.add(jpnl6);
        jpnl9.add(ionEnergy);
        jpnl9.add(jpnl11);
        jpnl9.add(ionEnergy1);
        jpnl1.add(jpnl9);
        jpnl5.add(targetMaterial);
        jpnl5.add(targetMat);
        jpnl1.add(jpnl5);

        // Add the some stuf to the 2nd panel
        jpnl14.add(customMaterial);
        jpnl15.add(materialName);
        jpnl15.add(customMaterial1);
        jpnl16.add(atomicNumber);
        jpnl16.add(atomicNumber1);
        jpnl17.add(atomicWeight);
        jpnl17.add(atomicWeight1);
        jpnl18.add(density);
        jpnl18.add(density1);
        jpnl2.add(jpnl14);
        jpnl2.add(jpnl15);
        jpnl2.add(jpnl16);
        jpnl2.add(jpnl17);
        jpnl2.add(jpnl18);
        jpnl2.add(jpnl19);
        //    jpnl4.add(simulationConditionsLbl);
        jpnl8.add(xAxisScaleLbl);
        jpnl8.add(jpnl13);
        jpnl8.add(xAxisScaleFld);
        jpnl8.add(jpnl11);
        //jpnl4.add(incIonTypeLbl);
        //jpnl4.add(incIonTypeFld);
        jpnl20.add(xAxisScaleLbl2);
        jpnl20.add(jpnl23);
        jpnl20.add(xAxisScaleFld2);
        jpnl20.add(jpnl24);
        jpnl4.add(jpnl7);
        jpnl1.add(jpnl9);
        jpnl19.add(jpnl20);
        runButton2 = new JButton("Run");
        numClicks = 0;
        jpnl21.add(jpnl22);
        jpnl21.add(runButton2);
        jpnl19.add(jpnl21);
        //  jpnl8.add(runButton);
        jpnl1.add(jpnl8);

        runButton = new JButton("Run");

        //RunButton listener = new RunButton();
        jpnl4.add(jpnl7);
        jpnl4.add(spacer);
        jpnl4.add(runButton);
        jpnl1.add(jpnl4);
        jpnl2.add(ionEnergyFld);

        jpnl12.add(jpnl8);
        jpnl12.add(jpnl4);
        jpnl1.add(jpnl12);
        // Create three vertical boxes. 
        box1 = Box.createVerticalBox();
        box2 = Box.createVerticalBox();
        box3 = Box.createVerticalBox();
        box4 = Box.createVerticalBox();
        //Create invisible borders around the boxes. 
        box1.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box2.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box3.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the components to the boxes. 
        box1.add(jpnl1);
        box2.add(jpnl2);
        box3.add(jpnl3);
        //   box4.add(jpnl4);

        //Disable custom target labels
        customMaterial.setEnabled(false);
        materialName.setEnabled(false);
        atomicNumber.setEnabled(false);
        atomicWeight.setEnabled(false);
        density.setEnabled(false);
        xAxisScaleLbl2.setEnabled(false);

        //Disable custom target fields
        customMaterial1.setEnabled(false);
        atomicNumber1.setEnabled(false);
        atomicWeight1.setEnabled(false);
        density1.setEnabled(false);
        xAxisScaleFld2.setEnabled(false);

        runButton2.setEnabled(false);
        //create a file chooser
        jfc = new JFileChooser();

        //creat the file menu
        printButton = new JButton("Print");
        PrintButton1 listener1 = new PrintButton1();
        printButton.addActionListener(listener1);

        saveButton = new JButton("Save");
        SaveButton1 listener2 = new SaveButton1();
        saveButton.addActionListener(listener2);

        exportButton = new JButton("Export");
        ExportButton1 listener3 = new ExportButton1();
        exportButton.addActionListener(listener3);

        exitButton = new JButton("Exit");
        ExitButton1 listener4 = new ExitButton1();
        exitButton.addActionListener(listener4);

        helpButton = new JButton("Help");
        HelpButton1 listener5 = new HelpButton1();
        helpButton.addActionListener(listener4);

        //add buttons to menubar 
        //jmb.add(runButton);
        jmb.add(printButton);
        jmb.add(saveButton);
        jmb.add(exportButton);
        jmb.add(exitButton);
        jmb.add(helpButton);

        jfrm.add(jmb);

        //add action listeners for the menu items
        printButton.addActionListener(this);
        saveButton.addActionListener(this);
        exportButton.addActionListener(this);
        exitButton.addActionListener(this);
        helpButton.addActionListener(this);
        runButton.addActionListener(this);

        //add the menu bar to the frame
        jfrm.setJMenuBar(jmb);

        //Display the frame
        //   jfrm.setVisible(true);

        // Add the boxes to the content pane . 
        jfrm.getContentPane().add(box1);
        jfrm.getContentPane().add(box4);
        jfrm.getContentPane().add(box2);
        jfrm.getContentPane().add(box3);

        // Display the frame.  

        jfrm.setVisible(true);
        ionEnergy1.requestFocus(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String outpt = "";
        CalculationToolE1 cTE;
        //     LineChartDemo2 lineChart = new LineChartDemo2("Electron Density Profile");

        if (e.getSource() == runButton) {
            numClicks++;
            //  ionEnergy1.setText("Button Clicked " + numClicks + " times");
            System.out.println("Data: " + IncIons.getSelectedItem());
            incIonTypeFld.setText((String) IncIons.getSelectedItem());
            targetMaterailFld.setText((String) targetMat.getSelectedItem());
            ionEnergyFld.setText((String) ionEnergy1.getText() + "  MeV");

            if (targetMat.toString().equals(None)) {
            } else {

                if (("".equals(ionEnergy1.getText().trim())) || ("".equals(xAxisScaleFld.getText().trim()))) {
                    //   if (xAxisScaleFld.getText().trim() == "") {
                    JOptionPane.showMessageDialog(ionEnergy1, " You must enter the ion energy");


                } else {
                    try {
                        Double incidentIonEnergy = Double.parseDouble(ionEnergy1.getText().trim());
                        int xAxisScale = Integer.parseInt(xAxisScaleFld.getText().trim());

                        String series = "series1";
                        cTE = new CalculationToolE1(IncIons, ionEnergy1, targetMat, xAxisScaleFld, numClicks);
                        cTE.calc();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ionEnergy1, "You must enter a number");
                    }
                }

            }
        }
    }

    public static void main(String[] arguments) {
        MainMenuE1 jfrm;
        jfrm = new MainMenuE1();
    }
}
