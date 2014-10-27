package chargedensityprofile;


//GoodBye.java
import java.awt.*;
import java.awt.event.*;

class ExitButton1 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("I am exiting...Goodbye!");
        System.exit(0);
    }
}
