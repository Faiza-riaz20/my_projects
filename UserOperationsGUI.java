package bankmanagementsystem;

//import static bankmanagementsystem.Login.writeToLogin;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class UserOperationsGUI {
    
    Account a;
    Customer customer;
    Login login;
    private String otp;
    
    UserOperationsGUI(Customer c,Login l){
//        c.display();
        customer=c;
        login=l;
        
        JFrame newly=new JFrame("User Operations");
        newly.setSize(400,400);
        newly.setLayout(new GridLayout(3,0));
        newly.setVisible(true);
        newly.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JButton b1=new JButton("Account Creation");
        JButton b2=new JButton("Account Login");
        JButton b3=new JButton("User Home");
        
        newly.add(b1);
        newly.add(b2);
        newly.add(b3);
        
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                JFrame info=new JFrame("Account Creation");
                
                info.setSize(400,400);
                info.setLayout(new GridLayout(4,2));
                info.setVisible(true);
                info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                JLabel l1=new JLabel("Account Type");
                JTextField t1=new JTextField(20);
                JLabel l2=new JLabel("Cash");
                JTextField t2=new JTextField(20);
                
                JButton button1=new JButton("Create Account");
                JButton button2=new JButton("Back to User Operation page");
                
                info.add(l1);
                info.add(t1);
                info.add(l2);
                info.add(t2);
                info.add(button1);
                info.add(button2);
                
                button1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        String type=t1.getText();
                        double cash=Integer.parseInt(t2.getText());
                        
                        a=new Account(customer,type,cash);
                        JOptionPane.showMessageDialog(null,"Account created successfully\nYour Account ID is "+a.getAccountID());
                        
                        
                        JFrame created=new JFrame("User Operations");
                        created.setSize(400,400);
                        created.setLayout(new GridLayout(9,0));
                        created.setVisible(true);
                        created.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        
                        JButton bro1=new JButton("Check Balance");
                        JButton bro2=new JButton("Withdraw Money");
                        JButton bro3=new JButton("Deposit Money");
                        JButton bro4=new JButton("Transfer Money");
                        JButton bro5=new JButton("Edit Account Details");
                        JButton bro6=new JButton("Transaction History");
                        JButton bro7=new JButton("Login History");
                        JButton bro8=new JButton("Delete an Account");
                        JButton bro9=new JButton("Log out");
                        
                        created.add(bro1);
                        created.add(bro2);
                        created.add(bro3);
                        created.add(bro4);
                        created.add(bro5);
                        created.add(bro6);
                        created.add(bro7);
                        created.add(bro8);
                        created.add(bro9);

                        
                        
                        bro1.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                JOptionPane.showMessageDialog(null,"Your current balance is "+a.getBalance());
                            } 
                        });
                        
                        bro2.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Withdraw Money");
                                with.setSize(400,400);
                                with.setLayout(new GridLayout(3,0));
                                with.setVisible(true);
                                with.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                
                                JLabel lab1=new JLabel("Amount to be Withdrawn");
                                JTextField m=new JTextField(20);
                                JButton btn1=new JButton("Withdraw");
                                
                                with.add(lab1);
                                with.add(m);
                                with.add(btn1);
                                
                                
                                btn1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ae){
                                        double cash=Integer.parseInt(m.getText());
                                        a.withdraw(cash);

                                    }
                                });
                            } 
                        });
                        
                        bro3.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Deposit Money");
                                with.setSize(400,400);
                                with.setLayout(new GridLayout(3,0));
                                with.setVisible(true);
                                with.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                
                                JLabel lab1=new JLabel("Amount to be Deposited");
                                JTextField m=new JTextField(20);
                                JButton btn1=new JButton("Deposit");
                                
                                with.add(lab1);
                                with.add(m);
                                with.add(btn1);
                                
                                
                                btn1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ae){
                                        double cash=Integer.parseInt(m.getText());
                                        a.deposit(cash);

                                    }
                                });
                            } 
                        });
                        
                    
                        bro4.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Tranfer Money");
                                with.setSize(400,400);
                                with.setLayout(new GridLayout(5,0));
                                with.setVisible(true);
                                with.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                
                                JLabel lab1=new JLabel("Amount to be Transfered");
                                JTextField m=new JTextField(20);
                                JLabel lab2=new JLabel("Account ID of receiver Account");
                                JTextField m1=new JTextField(20);
                                JButton btn1=new JButton("Transfer");
                                
                                with.add(lab1);
                                with.add(m);
                                with.add(lab2);
                                with.add(m1);
                                with.add(btn1);
                                
                                
                                btn1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ae){
                                        double cash=Integer.parseInt(m.getText());
                                        String id=m1.getText();
                                        
                                        a.transfer(cash, id);
                                    }
                                });
                            } 
                        });
                        
                    
                        bro5.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Edit Account Details");
                                with.setSize(400,400);
                                with.setLayout(new GridLayout(11,0));
                                with.setVisible(true);
                                with.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                
                                JLabel lab1=new JLabel("Account Number");
                                JTextField m=new JTextField(20);
                                JLabel lab2=new JLabel("Password");
                                JTextField m1=new JTextField(20);
                                JLabel lab3=new JLabel("New Email");
                                JTextField m2=new JTextField(20);
                                JLabel lab4=new JLabel("New Phone");
                                JTextField m3=new JTextField(20);
                                JLabel lab5=new JLabel("New Address");
                                JTextField m4=new JTextField(20);
                                
                                JButton btn1=new JButton("Update");
                                
                                with.add(lab1);
                                with.add(m);
                                with.add(lab2);
                                with.add(m1);
                                with.add(lab3);
                                with.add(m2);
                                with.add(lab4);
                                with.add(m3);
                                with.add(lab5);
                                with.add(m4);
                                with.add(btn1);
                                
                                
                                btn1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ae){
                                        String accountNum=m.getText();
                                        String pass=m1.getText();
                                        String email=m2.getText();
                                        long phone=Integer.parseInt(m3.getText());
                                        String address=m4.getText();
                                        double amount=0;
                                        Account.updateAnAccount(accountNum,pass,email,phone,address,amount);
                                        JOptionPane.showMessageDialog(null,"Account updated Successfully");
                                    }
                                });
                            } 
                        });
                        
                        
                        bro6.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Transaction History");
                             
                                StringBuilder history=new StringBuilder();
                                ArrayList<AccountTransaction> list=AccountTransaction.readAllAccountTransactions();
                                for(AccountTransaction i:list){
                                    if(i.getAccount().getAccountID().equals(a.getAccountID())){
                                        history.append(i).append("\n");
                                    }
                                }
                                if(history.isEmpty()){
                                    history.append("No transactions made yet");
                                }
                                
                                with.setSize(400, 300);
                                JTextArea textArea = new JTextArea(history.toString());
                                textArea.setEditable(false);
                                with.add(new JScrollPane(textArea));
                                with.setVisible(true);
                                    
                            }    
                        });
                
                        
                        bro7.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Login History");
                             
                                StringBuilder history=new StringBuilder();
                                ArrayList<LoginTime> list=Login.readAllLogins();
                                for(LoginTime i:list){
                                    System.out.println("hota ha");
                                    if(i.getLogin().getAccountNumber().equals(login.getAccountNumber())
                                        && i.getLogin().getPassword().equals(login.getPassword())){
                                        history.append(i).append("\n");
                                    }
                                }
                                if(history.isEmpty()){
                                    history.append("No logins yet");
                                }
                                
                                with.setSize(400, 300);
                                JTextArea textArea = new JTextArea(history.toString());
                                textArea.setEditable(false);
                                with.add(new JScrollPane(textArea));
                                with.setVisible(true);
                                    
                            }    
                        });
                        
                        
                        bro8.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                            
                                otp="";
                                for(int i=0;i<4;i++){
                                    int pass=(int)(Math.random()*10);
                                    otp+=pass;
                                }
                               
                                JOptionPane.showMessageDialog(null,"Your generated OTP is "+otp);
//            System.out.println("Your generated otp: "+otp);

            //making new frame for otp validation
                                JFrame otpVerificarion=new JFrame("Account Deletion");
        
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
                                            
                                            Account.deleteAnAccount(a.getAccountID(),a.getAccountType());                
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null,"Unable to login.\nWrong OTP");
                                        }
               
                                    }
                                });
                                
                            }
                        });
                        
                        
                        bro9.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                JOptionPane.showMessageDialog(null,"Loged out successfully");
                                UserGUI h=new UserGUI();
                            }
                        });

                    }
                });
                        
   
                    button2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae){
                            UserOperationsGUI g=new UserOperationsGUI(customer,login);   
                        }
                    });
        
                }
            });
        
        
        b2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                JFrame jeo=new JFrame("Account Login");
                JLabel l1=new JLabel("Account ID");
                JTextField t1=new JTextField(20);
                JLabel l2=new JLabel("Password");
                JTextField t2=new JTextField(20);
                
                JButton btn1=new JButton("Login");
                JButton btn2=new JButton("Back to User Operation page");
                
                jeo.add(l1);
                jeo.add(t1);
                jeo.add(l2);
                jeo.add(t2);
                jeo.add(btn1);
                jeo.add(btn2);
                
                jeo.setSize(400,400);
                jeo.setLayout(new GridLayout(6,0));
                jeo.setVisible(true);
                jeo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                
                
                btn1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        //for logging into the user bank account
                        String accountId=t1.getText();
                        String pass=t2.getText();
                        
                        ArrayList<Account> list=Account.readAllAccounts();
                        boolean flag=false;
                        for(Account i:list){
                            if(i.getAccountID().equals(accountId) && i.getCustomer().getRegister().getPassword().equals(pass)){
                                a=i;
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
//            System.out.println("Your generated otp: "+otp);

            //making new frame for otp validation
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
                                        
                                        Login.writeToLogin(login);
                                        JFrame created=new JFrame("User Operations");
                        created.setSize(400,400);
                        created.setLayout(new GridLayout(9,0));
                        created.setVisible(true);
                        created.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        
                        JButton bro1=new JButton("Check Balance");
                        JButton bro2=new JButton("Withdraw Money");
                        JButton bro3=new JButton("Deposit Money");
                        JButton bro4=new JButton("Transfer Money");
                        JButton bro5=new JButton("Edit Account Details");
                        JButton bro6=new JButton("Transaction History");
                        JButton bro7=new JButton("Login History");
                        JButton bro8=new JButton("Delete an Account");
                        JButton bro9=new JButton("Log out");
                        
                        created.add(bro1);
                        created.add(bro2);
                        created.add(bro3);
                        created.add(bro4);
                        created.add(bro5);
                        created.add(bro6);
                        created.add(bro7);
                        created.add(bro8);
                        created.add(bro9);

                        
                        
                        bro1.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                JOptionPane.showMessageDialog(null,"Your current balance is "+a.getBalance());
                            } 
                        });
                        
                        bro2.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Withdraw Money");
                                with.setSize(400,400);
                                with.setLayout(new GridLayout(3,0));
                                with.setVisible(true);
                                with.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                
                                JLabel lab1=new JLabel("Amount to be Withdrawn");
                                JTextField m=new JTextField(20);
                                JButton btn1=new JButton("Withdraw");
                                
                                with.add(lab1);
                                with.add(m);
                                with.add(btn1);
                                
                                
                                btn1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ae){
                                        double cash=Integer.parseInt(m.getText());
                                        a.withdraw(cash);

                                    }
                                });
                            } 
                        });
                        
                        bro3.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Deposit Money");
                                with.setSize(400,400);
                                with.setLayout(new GridLayout(3,0));
                                with.setVisible(true);
                                with.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                
                                JLabel lab1=new JLabel("Amount to be Deposited");
                                JTextField m=new JTextField(20);
                                JButton btn1=new JButton("Deposit");
                                
                                with.add(lab1);
                                with.add(m);
                                with.add(btn1);
                                
                                
                                btn1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ae){
                                        double cash=Integer.parseInt(m.getText());
                                        a.deposit(cash);

                                    }
                                });
                            } 
                        });
                        
                    
                        bro4.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Tranfer Money");
                                with.setSize(400,400);
                                with.setLayout(new GridLayout(5,0));
                                with.setVisible(true);
                                with.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                
                                JLabel lab1=new JLabel("Amount to be Transfered");
                                JTextField m=new JTextField(20);
                                JLabel lab2=new JLabel("Account ID of receiver Account");
                                JTextField m1=new JTextField(20);
                                JButton btn1=new JButton("Transfer");
                                
                                with.add(lab1);
                                with.add(m);
                                with.add(lab2);
                                with.add(m1);
                                with.add(btn1);
                                
                                
                                btn1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ae){
                                        double cash=Integer.parseInt(m.getText());
                                        String id=m1.getText();
                                        
                                        a.transfer(cash, id);
                                    }
                                });
                            } 
                        });
                        
                    
                        bro5.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Edit Account Details");
                                with.setSize(400,400);
                                with.setLayout(new GridLayout(11,0));
                                with.setVisible(true);
                                with.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                
                                JLabel lab1=new JLabel("Account Number");
                                JTextField m=new JTextField(20);
                                JLabel lab2=new JLabel("Password");
                                JTextField m1=new JTextField(20);
                                JLabel lab3=new JLabel("New Email");
                                JTextField m2=new JTextField(20);
                                JLabel lab4=new JLabel("New Phone");
                                JTextField m3=new JTextField(20);
                                JLabel lab5=new JLabel("New Address");
                                JTextField m4=new JTextField(20);
                                
                                JButton btn1=new JButton("Update");
                                
                                with.add(lab1);
                                with.add(m);
                                with.add(lab2);
                                with.add(m1);
                                with.add(lab3);
                                with.add(m2);
                                with.add(lab4);
                                with.add(m3);
                                with.add(lab5);
                                with.add(m4);
                                with.add(btn1);
                                
                                
                                btn1.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ae){
                                        String accountNum=m.getText();
                                        String pass=m1.getText();
                                        String email=m2.getText();
                                        long phone=Integer.parseInt(m3.getText());
                                        String address=m4.getText();
                                        double amount=0;
                                        Account.updateAnAccount(accountNum,pass,email,phone,address,amount);
                                        JOptionPane.showMessageDialog(null,"Account updated Successfully");
                                    }
                                });
                            } 
                        });
                        
                        
                        bro6.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Transaction History");
                             
                                StringBuilder history=new StringBuilder();
                                ArrayList<AccountTransaction> list=AccountTransaction.readAllAccountTransactions();
                                for(AccountTransaction i:list){
                                    if(i.getAccount().getAccountID().equals(a.getAccountID())){
                                        history.append(i).append("\n");
                                    }
                                }
                                if(history.isEmpty()){
                                    history.append("No transactions made yet");
                                }
                                
                                with.setSize(400, 300);
                                JTextArea textArea = new JTextArea(history.toString());
                                textArea.setEditable(false);
                                with.add(new JScrollPane(textArea));
                                with.setVisible(true);
                                    
                            }    
                        });
                
                        
                        bro7.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                                JFrame with=new JFrame("Login History");
                             
                                StringBuilder history=new StringBuilder();
                                ArrayList<LoginTime> list=Login.readAllLogins();
                                for(LoginTime i:list){
                                    if(i.getLogin().getAccountNumber().equals(login.getAccountNumber())
                                        && i.getLogin().getPassword().equals(login.getPassword())){
                                        history.append(i).append("\n");
                                    }
                                }
                                if(history.isEmpty()){
                                    history.append("No logins yet");
                                }
                                
                                with.setSize(400, 300);
                                JTextArea textArea = new JTextArea(history.toString());
                                textArea.setEditable(false);
                                with.add(new JScrollPane(textArea));
                                with.setVisible(true);
                                    
                            }    
                        });
                        
                        
                        bro8.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                
                            
                                otp="";
                                for(int i=0;i<4;i++){
                                    int pass=(int)(Math.random()*10);
                                    otp+=pass;
                                }
                               
                                JOptionPane.showMessageDialog(null,"Your generated OTP is "+otp);
//            System.out.println("Your generated otp: "+otp);

            //making new frame for otp validation
                                JFrame otpVerificarion=new JFrame("Account Deletion");
        
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
                                            
                                            Account.deleteAnAccount(a.getAccountID(),a.getAccountType());                
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null,"Unable to login.\nWrong OTP");
                                        }
               
                                    }
                                });
                                
                            }
                        });
                        
                        
                        bro9.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent ae){
                                JOptionPane.showMessageDialog(null,"Loged out successfully");
                                UserGUI h=new UserGUI();
                            }
                        });

                                        
                    }
                                    else{
                                        JOptionPane.showMessageDialog(null,"Wrong OTP\nUnable to login`");
                                    }
                                }
                            });    
                            
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Wrong credentilas\nUnable to login.");
                        }
                    }  
                    
                });
                
                btn2.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        UserOperationsGUI g=new UserOperationsGUI(customer,login);
                    }  
                    
                });
                
            }
        });
        
        
        b3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                UserGUI g=new UserGUI();
            }
        });
        
    
    }
}
