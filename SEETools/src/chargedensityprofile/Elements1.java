package chargedensityprofile;

import java.awt.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;


class Elements1 {
    
    JLabel jlab;
    
    String[] headings = {"Element", "Symbol", "Atomic Number", "Atomic Weight", " Density gm/cm3", "Ionization Energy eV", "Free Electron Density" };
    Object[][] data = {
{       "Hydrogen",     "H"     , 1     ,       1.0079  ,       0.09    ,       13.5984 ,  5.38E+028 },
{       "helium",       "He"    , 2     ,       4.002602,       0.18    ,       24.5874 ,  5.00E+020 },
{	"Lithium" ,     "Li"	, 3	,	6.941	,	0.53	,	5.3917	,  4.60E+028 },
{	"Beryllium",    "Be"	, 4	,	9.0122	,	1.85	,	9.3227	,  2.47E+029 },
{	"Boron"  ,      "B"	, 5	,	10.811	,	2.34	,	8.298	,  3.91E+029 },
{	"Carbon" ,      "C"	, 6	,	12.0107	,	2.26	,	11.2603	,  2.27E+029 },
{	"Nitrogen" ,    "N"	, 7	,	14.0067	,	1.25	,	14.5341	,  1.61E+029 },
{	"Oxygen"   ,    "O"	, 8	,	15.9994	,	1.43	,	13.6181	,  1.08E+029 },
{	"Fluorine" ,	"F"	, 9	,	18.9984	,	1.7	,	17.4228	,  5.39E+028 },
{	"Neon"    ,	"Ne"	, 10	,	20.1797	,	0.9	,	21.5645	,  5.00E+023 },
{	"Sodium"   ,	"Na"	, 11	,	22.9897	,	0.97	,	5.1391	,  2.54E+028 },
{	"Magnesium",	"Mg"	, 12	,	24.305	,	1.74	,	7.6462	,  8.61E+028 },
{	"Aluminium",	"Al"	, 13	,	26.9815	,	2.7	,	5.9858	,  6.03E+028 },
{	"Silicon"  ,	"Si"	, 14	,	28.0855	,	2.33	,	8.1517	,  7.00E+029 },
{	"Phosphorus" ,	"P"	, 15	,	30.9738	,	1.82	,	10.4867	,  1.06E+029 },
{	"Sulfur"    ,  "S"	, 16	,	32.065	,	2.07	,	10.36	,  7.78E+028 },
{	"Chlorine"   ,	"Cl"	, 17	,	35.453	,	3.21	,	12.9676	,  5.45E+028 },
{       "Argone"     ,  "Ar"    , 18    ,       39.0983 ,       1.78    ,       15.7596 ,  2.30E+020 },
{	"Potassium"  ,	"K"	, 19	,	39.948	,	0.86	,	4.3407	,  1.30E+028 },
{	"Calcium" ,	"Ca"	, 20	,	40.078	,	1.55	,	6.1132	,  4.66E+028 },
{	"Scandium"   ,	"Sc"	, 21	,	44.9559	,	2.99	,	6.5615	,  1.20E+029 },
{	"Titanium"   ,	"Ti"	, 22	,	47.867	,	4.54	,	6.8281	,  1.71E+029 },
{	"Vanadium"   ,	"V"	, 23	,	50.9415	,	6.11	,	6.7462	,  1.44E+029 },
{	"Chromium"   ,	"Cr"	, 24	,	51.9961	,	7.19	,	6.7665	,  1.67E+029 },
{	"Manganese"  ,	"Mn"	, 25	,	54.938	,	7.43	,	7.434	,  1.63E+029 },
{	"Iron" ,	"Fe"	, 26	,	55.845	,	7.87	,	7.9024	,  1.70E+029 },
{	"Cobalt",	"Co"	, 27	,	58.6934	,	8.9	,	7.881	,  1.83E+029 },
{	"Nickel",	"Ni"	, 28	,	58.9332	,	8.9	,	7.6398	,  1.82E+029 },
{	"Copper",	"Cu"	, 29	,	63.546	,	8.96	,	7.7264	,  8.49E+028 },
{	"Zinc"   ,	"Zn"	, 30	,	65.39	,	7.13	,	9.3942	,  1.31E+029 },
{	"Gallium"  ,	"Ga"	, 31	,	69.723	,	5.91	,	5.9993	,  1.02E+029 },
{	"Germanium"  ,	"Ge"	, 32	,	72.64	,	5.32	,	7.8994	,  1.76E+029 },
{	"Arsenic" ,	"As"	, 33	,	74.9216	,	5.72	,	9.7886	,  1.38E+029 },
{	"Selenium"  ,	"Se"	, 34	,	78.96	,	4.79	,	9.7524	,  7.31E+028 },
{	"Bromine"  ,	"Br"	, 35	,	79.904	,	3.12	,	11.8138	,  2.35E+028 },
{	"Krypton" ,	"Kr"	, 36	,	83.8	,	3.75	,	13.9996	,  6.60E+027 },
{	"Rubidium"   ,	"Rb"	, 37	,	85.4678	,	1.63	,	4.1771	,  1.15E+028 },
{	"Strontium"  ,	"Sr"	, 38	,	87.62	,	2.54	,	5.6949	,  3.49E+028 },
{	"Yttrium"  ,	"Y"	, 39	,	88.9059	,	4.47	,	6.2173	,  9.08E+028 },
{	"Zirconium" ,	"Zr"	, 40	,	91.224	,	6.51	,	6.6339	,  1.72E+029 },
{	"Niobium" ,	"Nb"	, 41	,	92.9064	,	8.57	,	6.7589	,  1.67E+029 },
{	"Molybdenum" ,	"Mo"	, 42	,	95.94	,	10.22	,	7.0924	,  1.28E+029 },
{	"Technetium" ,	"Tc"	, 43	,	98	,	11.5	,	7.28	,  1.41E+029 },
{	"Ruthenium"  ,	"Ru"	, 44	,	101.07	,	12.37	,	7.3605	,  2.21E+029 },
{	"hodium"     ,	"Rh"	, 45	,	102.9055,	12.41	,       7.4589	,  2.18E+029 },
{	"Palladium"  ,	"Pd"	, 46	,	106.42	,	12.02	,	8.3369	,  1.36E+029 },
{	"Silver" ,	"Ag"	, 47	,	107.8682,       10.5	,	7.5762	,  5.86E+028 },
{	"Cadmium",	"Cd"	, 48	,	112.411	,	8.65	,	8.9938	,  4.63E+028 },
{	"Indium"   ,	"In"	, 49	,	114.818	,	7.31	,	5.7864	,  3.83E+028 },
{	"Tin"  ,	"Sn"	, 50	,	118.71	,	7.31	,	7.3439	,  7.42E+028 },
{	"Antimony"   ,	"Sb"	, 51	,	121.76	,	6.68	,	8.6084	,  9.91E+028 },
{	"Tellurium"  ,	"Te"	, 52	,	126.9045,	6.24	,	9.0096	,  5.92E+028 },
{	"Iodine" ,	"I"	, 53	,	127.6	,	4.93	,	10.4513	,  2.33E+028 },
{	"Xenon",	"Xe"	, 54	,	131.293	,	5.9	,	12.1298	,  5.00E+027 },
{	"Caesium"  ,	"Cs"	, 55	,	132.9055,	1.87	,	3.8939	,  8.47E+027 },
{	"Barium"   ,	"Ba"	, 56	,	137.327	,	3.59	,	5.2117	,  3.15E+028 },
{	"Lanthanum"  , 	"La"	, 57	,	138.9055,	6.15	,	5.5769	,  8.00E+028 },
{	"Cerium" ,	"Ce"	, 58	,	140.116	,	6.77	,	5.5387	,  8.73E+028 },
{	"Praseodymium",	"Pr"	, 59	,	140.9077,	6.77	,	5.473	,  8.68E+028 },
{	"Neodymium"   ,	"Nd"	, 60	,	144.24	,	7.01	,	5.525	,  8.78E+028 },
{	"Promethium"  ,	"Pm"	, 61	,	145	,	7.3	,	5.582	,  9.10E+028 },
{	"Samarium"    ,	"Sm"	, 62	,	150.36	,	7.52	,	5.6437	,  6.02E+028 },
{	"Europium"    ,	"Eu"	, 63	,	151.964	,	5.24	,	5.6704	,  4.15E+028 },
{	"Gadolinium"  ,	"Gd"	, 64	,	157.25	,	7.9	,	6.1501	,  9.08E+028 },
{	"Terbium"    ,	"Tb"	, 65	,	158.9253,	8.23	,	5.8638	,  9.36E+028 },
{	"Dysprosium"  ,	"Dy"	, 66	,	162.5	,	8.55	,	5.9389	,  9.51E+028 },
{	"Holmium"    ,	"Ho"	, 67	,	164.9303,	8.8	,	6.0215	,  9.64E+028 },
{	"Erbium"   ,	"Er"	, 68	,	167.259	,	9.07	,	6.1077	,  9.80E+028 },
{	"Thulium"  ,	"Tm"	, 69	,	168.9342,	9.32	,	6.1843	,  6.65E+028 },
{	"Ytterbium"   ,	"Yb"	, 70	,	173.04	,	6.9	,	6.2542	,  4.80E+028 },
{	"Lutetium"    ,	"Lu"	, 71	,	174.967	,	9.84	,	5.4259	,  1.02E+029 },
{	"Hafnium"   ,	"Hf"	, 72	,	178.49	,	13.31	,	6.8251	,  1.80E+029 },
{	"Tantalum"    ,	"Ta"	, 73	,	180.9479,	16.65	,	7.5496	,  1.66E+029 },
{	"Tungsten"    ,	"W"	, 74	,	183.84	,	19.35	,	7.864	,  1.27E+029 },
{	"Rhenium"   ,	"Re"	, 75	,	186.207	,	21.04	,	7.8335	,  6.81E+028 },
{	"Osmium"  ,	"Os"	, 76	,	190.23	,	22.6	,	8.4382	,  1.43E+029 },
{	"Iridium" ,	"Ir"	, 77	,	192.217	,	22.4	,	8.967	,  2.11E+029 },
{	"Platinum"    ,	"Pt"	, 78	,	195.078	,	21.45	,	8.9587	,  1.32E+029 },
{	"Gold"   ,	"Au"	, 79	,	196.9665,	19.32	,	9.2255	,  5.90E+028 },
{	"Mercury"  ,	"Hg"	, 80	,	200.59	,	13.55	,	10.4375	,  4.07E+028 },
{	"Thallium"    ,	"Tl"	, 81	,	204.3833,	11.85	,	6.1082	,  3.49E+028 },
{	"Lead"   ,	"Pb"	, 82	,	207.2	,	11.35	,	7.4167	,  6.60E+028 },
{	"Bismuth"  ,	"Bi"	, 83	,	208.9804,	9.75	,	7.2856	,  8.43E+028 },
{	"Polonium"    ,	"Po"	, 84	,	209	,	9.3	,	8.417	,  5.36E+028 },
{	"Radon"   ,	"Rn"	, 86	,	222	,	9.73	,	10.7485	,  1.00E+023 },
{	"Radium"  ,	"Ra"	, 88	,	226	,	5.5	,	5.2784	,  1.47E+028 },
{	"Actinium"    ,	"Ac"	, 89	,	227	,	10.07	,	5.17	,  8.02E+028 },
{	"Thorium"   ,	"Th"	, 90	,	232.0381,	11.72	,	6.3067	,  1.22E+029 },
{	"Protactinium",	"Pa"	, 91	,	231.0359,	15.4	,	5.89	,  1.61E+029 },
{	"Uranium"   ,	"U"	, 92	,	238.0289,	18.95	,	6.1941	,  1.44E+029 },
};
    
    JTable jtabEmail;
   
        
        Elements1() {
        //create a new JFrame container
        JFrame jfrm = new JFrame("Table of elements");

        // *** Use FlowLayout for the content pane. *** 
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Give the frame an initial size.   
        jfrm.setSize(950, 560);

        // Terminate the program when the user closes the application. 
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //create a label that will display the table selection
        jlab = new JLabel();
        
        //create a table that displays the e-mail data
        jtabEmail = new JTable(data, headings);

        // wrap the data in scrol pane 
        JScrollPane jscrlp = new JScrollPane(jtabEmail);
        
        //set the viewport size
        jtabEmail.setPreferredScrollableViewportSize(new Dimension(850, 500));
        
        //get the list selection model for row selection
        ListSelectionModel lsmRow = jtabEmail.getSelectionModel();
        
        //listen for row selection events
        lsmRow.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent le) {
                String str = "Selected Rows: ";
         //get a list of all selected rows
                int[] rows = jtabEmail.getSelectedRows();
                
                //create a string that contains the indices
                for(int i=0; i<rows.length;i++) {
                    str += rows[i] + " ";
                }
                //display the indicies
                jlab.setText(str);
                
            }

       });
        
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jlab);
        
        //Display the frame
        jfrm.setVisible(false);
        }
        
  
}
