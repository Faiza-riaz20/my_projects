package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SimpleGUI {
    JFrame home=new JFrame("Home");
    JButton button1;
    JButton button2;

    
    SimpleGUI(){
        home.setSize(400,400);
        home.setDefaultCloseOperation(EXIT_ON_CLOSE);
        home.setVisible(true);
        home.setLayout(new GridLayout());
        button1=new JButton("User");
        button2=new JButton("Admin");

        home.add(button1);
        home.add(button2);
        
        MyActionListener a=new MyActionListener();
        button1.addActionListener(a);
        button2.addActionListener(a);

        


    }  
}


