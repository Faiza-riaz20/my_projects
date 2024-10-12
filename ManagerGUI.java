package bankmanagementsystem;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ManagerGUI {
    private String userName;
    private String email;
    private String password;
    private String otp;
    
    ManagerGUI(){
        Manager m=new Manager("admin","admin@gmail.com","12345");
        Manager.writeToManager(m);
        JFrame man=new JFrame("Manager Login");
        man.setSize(400,400);
                man.setDefaultCloseOperation(EXIT_ON_CLOSE);
                man.setVisible(true);
                man.setLayout(new GridLayout(8,0));
        
                JLabel l1=new JLabel("Name");
                JTextField t1=new JTextField(20);
                JLabel l2=new JLabel("Email");
                JTextField t2=new JTextField(20);
                JLabel l3=new JLabel("Password");
                JTextField t3=new JTextField(20);
                
                JButton button1=new JButton("Login");
                JButton button2=new JButton("Home");
        
                man.add(l1);
                man.add(t1);
                man.add(l2);
                man.add(t2);
                man.add(l3);
                man.add(t3);
                
                man.add(button1);
                man.add(button2);
                
                
                button1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        String n=t1.getText();
                        String m=t2.getText();
                        String p=t3.getText();
                        
      
                        boolean flag=false;
                        ArrayList<Manager> list=Manager.readAllManagers();
                        for(Manager i:list){
                            if(i.getUserName().equals(n) && i.getEmail().equals(m) && i.getPassword().equals(p)){
                                flag=true;
                            }
                        }
                        
                        if(flag){
                            otp="";
                            for(int i=0;i<4;i++){
                                int pass1=(int)(Math.random()*10);
                                otp+=pass1;
                            }
                               
                            JOptionPane.showMessageDialog(null,"Your generated OTP is "+otp);
                            JFrame otpVerificarion=new JFrame("Account Login");
        
                            JButton b1=new JButton("Enter");
        
                            JLabel l1=new JLabel("OTP");
                            JTextField t1=new JTextField(20);
        
                            otpVerificarion.setSize(400,400);
                            otpVerificarion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//only closes the current frame
                            otpVerificarion.setVisible(true);
                            otpVerificarion.setLayout(new GridLayout(3,0));
        
                            otpVerificarion.add(l1);
                            otpVerificarion.add(t1);
                            otpVerificarion.add(b1);
            
                            b1.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent ae){
                                    String pas=t1.getText();
                    
                                    if(pas.equals(otp)){ 
                                        
                                        JFrame created=new JFrame("Manager Operations");
                                        created.setSize(400,400);
                                        created.setLayout(new GridLayout(2,1));
                                        created.setVisible(true);
                                        created.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                        
                                        JButton btn1=new JButton("View Accounts");
                                        JButton btn2=new JButton("Manager Home");
                                        
                                        created.add(btn1);
                                        created.add(btn2);
                                        
                                        btn1.addActionListener(new ActionListener(){
                                            public void actionPerformed(ActionEvent ae){
                                                
                                                ArrayList<Account> list=Manager.viewAllAccounts();
                                                
                                                StringBuilder accounts=new StringBuilder();
                                                if(list.isEmpty())
                                                    accounts.append("No Accounts yet");
                                                
                                                for(Account i:list){
                                                    accounts.append(i).append("\n");
                                                }
                                                
                                                JFrame show=new JFrame("View All Accounts");
                                                show.setSize(400, 300);
                                                JTextArea textArea = new JTextArea(accounts.toString());
                                                textArea.setEditable(false);
                                                show.add(new JScrollPane(textArea));
                                                show.setVisible(true);
                                            } 
                                        });
                                        
                                        btn2.addActionListener(new ActionListener(){
                                            public void actionPerformed(ActionEvent ae){
                                                ManagerGUI g=new ManagerGUI();
                                            } 
                                        });
                                        
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null,"Wrong OTP\nUnable to login`");
                                    }
                                }
                    
                });
                        }else{
                            JOptionPane.showMessageDialog(null,"Invalid credentials\nUnable to login`");
                        }
                
                
                
        
    }
                });
                
                button2.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        SimpleGUI s=new SimpleGUI();
                    }
                });
    }
}
