package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class MyActionListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae){
            
            if(ae.getActionCommand().equals("User")){
                UserGUI q=new UserGUI();
                
            }
            
            else if(ae.getActionCommand().equals("Admin")){
                ManagerGUI m=new ManagerGUI();
            }

            
            
    }
}