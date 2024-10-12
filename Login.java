package bankmanagementsystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Login implements Serializable {
    private String accountNumber;
    private String password;
    
    Login(){
    
    }
    Login(String account,String pass,Customer c){
        accountNumber=account;
        password=pass;
        //to get user logged in
        login(c);
        c.display();
    }
    Login(Login l){
        accountNumber=l.accountNumber;
        password=l.password;
    }
    
    public String getAccountNumber(){
        return accountNumber;
    }
    public String getPassword(){
        return password;
    }
    

    
    private boolean searchInRegisterations(Login l){
        boolean flag=false;
        
        ArrayList<Customer> list=Customer.readAllCustomers();
        for(Customer c: list){
            System.out.println("number dekh");
            System.out.println("Checking account: " + c.getAccountNumber() + " with login account: " + l.getAccountNumber());
            System.out.println("Checking password: " + c.getRegister().getPassword() + " with login password: " + l.getPassword());
            
            if(c.getAccountNumber().equals(l.getAccountNumber()) && 
                c.getRegister().getPassword().equals(l.getPassword())){
                flag=true;
                System.out.println("search ho gya");
            }
        }
        System.out.println("search not");
        return flag;
    }
    
    private String generateOTP(){//Generating otp for more security
        String otp="";
        for(int i=0;i<4;i++){
            int pass=(int)(Math.random()*10);
            otp+=pass;
        }
        return otp;
    }
    
    private void login(Customer c){
        Scanner input=new Scanner(System.in);
        
        if(searchInRegisterations(Login.this)){
            String otp=generateOTP();
            JOptionPane.showMessageDialog(null,"Your generated OTP is "+otp);
//            System.out.println("Your generated otp: "+otp);

            //making new frame for otp validation
            JFrame otpVerificarion=new JFrame("OTP Verification");
        
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
//                        System.out.println("Successfully logged in customer account");
                        JOptionPane.showMessageDialog(null,"Successfully log into the account");
                        //also writing to the logins file to keep user login track
                        writeToLogin(Login.this); // Explicitly reference the outer class's instance;
                        
                        //to let UserOperationsGUI have the accessof Customer and Login
                        new UserOperationsGUI(c,Login.this);
                        
                        
                    }
                    else{
//                        System.out.println("Wrong Account number or password.\nUnable to login.");
                          JOptionPane.showMessageDialog(null,"Unable to login.\nWrong OTP");
                    }
               
                }
            
            });
//            System.out.print("Please enter the otp: ");
//            String pas=input.next();
        
            }
        else{
            JOptionPane.showMessageDialog(null,"Invalid Account number or password");
//            System.out.println("Customer Account not registered");
        }
    }
    
    public static void writeToLogin(Login l){
        try{
            File f=new File("logins.ser");
            ObjectOutputStream oos;
            LoginTime t=new LoginTime(l); 
            
            //creating the object of LoginTime class
            if(f.exists()){
                oos=new MyObjectOutputStream(new FileOutputStream(f,true));
            }
            else{
                oos=new ObjectOutputStream(new FileOutputStream(f));
            }
            
            oos.writeObject(t);
            oos.close();
            System.out.println("Login time written successfully in file");
        }
        catch(IOException e){
            System.out.println("Error writing in logins file");
            e.printStackTrace();
        }
    }
    
    public static ArrayList<LoginTime> readAllLogins(){
        ArrayList<LoginTime> list=new ArrayList<LoginTime>();
        
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("logins.ser"));
            while(true){
                LoginTime t=(LoginTime)ois.readObject();
                System.out.println(t.currentTime);
                list.add(t);
            }
        }
        catch(ClassNotFoundException e1){
            System.out.println("Class not found exception in logins file reading");
        }
        catch(EOFException e2){
            return list;
        }
        catch(IOException e3){
            System.out.println("Error in file reading in logins file");
            e3.printStackTrace();
        }
        
        return list;
    }
    
    public static boolean searchLogin(Login L){
        ArrayList<LoginTime> list=readAllLogins();
        
        for(LoginTime t:list){
            if(t.login.getAccountNumber().equals(t.login.getAccountNumber())
                && t.login.getPassword().equals(t.login.getPassword())){
                return true;
            }
        }
        return false;
    }
    
    public String toString(){
        return "Account number: "+accountNumber+"    Password: "+password;
    }
    public void display(){
        System.out.println("Account Number: "+accountNumber+"\nPassword: "+password);
    }
}
