package bankmanagementsystem;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class BankGUI {
    JFrame bank=new JFrame("Bank");
    JButton button1;
    
    
    BankGUI(){
        bank.setSize(400,400);
        bank.setDefaultCloseOperation(EXIT_ON_CLOSE);
        bank.setVisible(true);
        bank.setLayout(new GridLayout());
        
        button1=new JButton("WELCOME TO APNA SECURE BANK");
        
        bank.add(button1);
        
        button1.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               SimpleGUI g=new SimpleGUI();
           } 
        });
    }
}
