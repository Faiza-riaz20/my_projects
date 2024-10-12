package bankmanagementsystem;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class UserGUI {
    
    UserGUI(){
        JFrame userHome=new JFrame("User Home");
        JButton b1;
        JButton b2;
        JButton b3;
                
        userHome.setSize(400,400);
        userHome.setDefaultCloseOperation(EXIT_ON_CLOSE);
        userHome.setVisible(true);
        userHome.setLayout(new GridLayout(3,0));
        b1=new JButton("Register");
        b2=new JButton("Login");
        b3=new JButton("Home");
        
        userHome.add(b1);
        userHome.add(b2);
        userHome.add(b3);
                
        b1.addActionListener(new ActionListener(){
                    
                    //Anonymous class to avoid making many classes
            @Override
            public void actionPerformed(ActionEvent ae){
                JFrame userRegister=new JFrame("User Registration");
                        
                userRegister.setSize(400,400);
                userRegister.setDefaultCloseOperation(EXIT_ON_CLOSE);
                userRegister.setVisible(true);
                userRegister.setLayout(new GridLayout(7,1));
        
                JLabel l1=new JLabel("Name");
                JTextField t1=new JTextField(20);
                JLabel l2=new JLabel("Email");
                JTextField t2=new JTextField(20);
                JLabel l3=new JLabel("CNIC");
                JTextField t3=new JTextField(20);
                JLabel l4=new JLabel("Address");
                JTextField t4=new JTextField(20);
                JLabel l5=new JLabel("Phone");
                JTextField t5=new JTextField(20);
                JLabel l6=new JLabel("Password");
                JTextField t6=new JTextField(20);
                
                JButton button1=new JButton("Register");
                JButton button2=new JButton("User Home");
        
                userRegister.add(l1);
                userRegister.add(t1);
                userRegister.add(l2);
                userRegister.add(t2);
                userRegister.add(l3);
                userRegister.add(t3);
                userRegister.add(l4);
                userRegister.add(t4);
                userRegister.add(l5);
                userRegister.add(t5);
                userRegister.add(l6);
                userRegister.add(t6);
                
                userRegister.add(button1);
                userRegister.add(button2);
                                
                button1.addActionListener(new ActionListener(){
                    
                    public void actionPerformed(ActionEvent ae){
                        
                        String name=t1.getText();
                        String mail=t2.getText();
                        String cnic=t3.getText();
                        String adrs=t4.getText();
                        long ph=Integer.parseInt(t5.getText());
                        String pswd=t6.getText();
                        
                        Customer c=new Customer(new Registration(name,mail,cnic,adrs,ph,pswd));
                        
                    }
                });
                
                button2.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        UserGUI s=new UserGUI();
                    }
                });
            }
        });

        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JFrame userLogin=new JFrame("User Login");
                
                userLogin.setSize(400,400);
                userLogin.setDefaultCloseOperation(EXIT_ON_CLOSE);
                userLogin.setLayout(new GridLayout(3,1));
                userLogin.setVisible(true);
                
                
                JLabel l1=new JLabel("Account Number");
                JTextField t1= new JTextField(20);
                JLabel l2=new JLabel("Password");
                JTextField t2= new JTextField(20);
                
                JButton button1=new JButton("Login");
                JButton button2=new JButton("User Home");
                
                userLogin.add(l1);
                userLogin.add(t1);
                userLogin.add(l2);
                userLogin.add(t2);
                userLogin.add(button1);
                userLogin.add(button2);
                
                button1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        String accountNum=t1.getText();
                        String pass=t2.getText();
                        
                        
                        ArrayList<Customer> list=Customer.readAllCustomers();
                        for(Customer c1:list){
                            if(c1.getAccountNumber().equals(accountNum)){
                                if (c1 != null) {
                                    Login lo = new Login(accountNum, pass, c1);
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "No registered customer found. Please register first.");
                                }
                            }
                        }
                        
                          
                    }
                });
                
                button2.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        UserGUI u=new UserGUI();
                    }
                });
            }
        });
        
        b3.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               SimpleGUI a=new SimpleGUI();
           } 
        });
    }

}
