package bankmanagementsystem;

import java.util.*;
import java.io.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Customer implements Serializable{
    private Registration register;
    private String accountNumber;
    
    Customer(){
        accountNumber="";
    }
    Customer(Registration r1){
        Scanner input=new Scanner(System.in);//As the Scanner class is not serializable 
        //so to avoid its serialization we're not gonna declare it as instance variable
        
        register=r1;
        accountNumber="";
        
        String otp=generateOTP();
        JOptionPane.showMessageDialog(null,"Your generated OTP is "+otp);
//        System.out.println("Your generated otp: "+otp);
        
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
                   accountNumber=generateAccoutNumber(); 
                   JOptionPane.showMessageDialog(null,"Your Account is successfully registered.\nYour account number is "+accountNumber);
                   
//                   UserGUI u=new UserGUI();
//                    System.out.println("Account created successfully");
//                    System.out.println("Your account number is "+accountNumber);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Sorry.\nIncorrect OTP...Account is not registered");
                }
           } 
        });
        
        
//        System.out.print("Please enter the otp: ");
//        String pas=input.next();
    }
    Customer(Customer c){
        register=c.register;
        accountNumber=c.accountNumber;
    }
    
    public void setRegister(Registration r){
        register=r;
    }
    public void setAccountNumber(String a){
        accountNumber=a;
    }
    
    public Registration getRegister(){
        return register;
    } 
    public String getAccountNumber(){
        return accountNumber;
    }

    
    private String generateOTP(){//Generating otp for more security
        String otp="";
        for(int i=0;i<4;i++){
            int pass=(int)(Math.random()*10);
            otp+=pass;
        }
        return otp;
    }
    
    private String generateAccoutNumber(){//Generating the random Account Number
        String account="";     
        char alphabets;
        int numbers;
        for(int i=0;i<7;i++){
            if(i==0 || i==1){
                alphabets=(char)('A'+ Math.random()*26);
                account+=alphabets;
            }
            else{
                numbers=(int)(Math.random()*10);
                account+=numbers;
            }
        }
        accountNumber=account;
        Registration.writeToRegister(Customer.this);
        Customer.writeToCustomer(Customer.this);
        return accountNumber;
    }    
    
    public void display(){
        register.display();
        System.out.println("Account Number: "+accountNumber);
    }
    
    public String toString(){
        return register.toString()+"    Account Number: "+accountNumber;
    }

    
    
    public static void writeToCustomer(Customer c){
        
    try{
        File f=new File("customers.ser");
        ObjectOutputStream oos;
        
        if(f.exists()){
                oos=new MyObjectOutputStream(new FileOutputStream(f,true));
        }
        else{
            oos=new ObjectOutputStream(new FileOutputStream(f));
        }
            oos.writeObject(c);
            oos.close();
            System.out.println(c.register.getName()+" is written successfully in the customers file");
        }
        catch(IOException e){
            System.out.println("Error in file handling for writing in customers file");
        }
    }
    
    
    public static ArrayList<Customer> readAllCustomers(){
        ArrayList<Customer> list=new ArrayList<Customer>();
        
        ObjectInputStream ois;
        try{
            ois=new ObjectInputStream(new FileInputStream("customers.ser"));
            while(true){
                Customer c=(Customer)ois.readObject();
                list.add(c);
                System.out.println(c.register.getName());
            }
        }
        catch(ClassNotFoundException e1){
            System.out.println("Class Not found for reading from customers file");
        }
        catch(EOFException e2){
            return list;
        }
        catch(IOException e3){
            System.out.println("Error in file reading of customers file");
            e3.printStackTrace();
        }
        return list;
    }
    
    public static void deleteACustomer(String accountNum,String pass){
        ArrayList<Customer> list=readAllCustomers();
        
        boolean flag=true;
        for(int i=0;i<list.size();i++){
            if(list.get(i).accountNumber.equals(accountNum) && list.get(i).register.getPassword().equals(pass)){
                System.out.println(list.get(i).register.getName()+" is deleted suucessfully in customers file");
                list.remove(i);
                flag=false;
            }
        }
        if(flag){
            System.out.println("Customer not found to be deleted from customers file");
        }
        
        try{
            ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream("customers.ser"));
            //also deleting the particular customer from registerations file
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("registerations.ser"));
            for(int i=0;i<list.size();i++){
                oos.writeObject(list.get(i));
                obj.writeObject(list.get(i));
            }
        }
        catch(IOException e){
            System.out.println("Error in file writing in delete method of customers file");
        }
    }
    
    public static void updateACustomer(String accountNum,String pass,String email,long phone,String address){
        ArrayList<Customer> list=readAllCustomers();
        
        boolean flag=true;
        for(int i=0;i<list.size();i++){
            if(list.get(i).accountNumber.equals(accountNum) && list.get(i).register.getPassword().equals(pass)){
                list.get(i).register.setEmail(email);
                list.get(i).register.setPhone(phone);
                list.get(i).register.setAddress(address);
                System.out.println(list.get(i).register.getName()+"'s email, phone and home address updated successfully");
                flag=false;
            }
        }
        if(flag){
            System.out.println("Customer not found to be updated in customers file");
        }
        
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("customers.ser"));
            //also writing the updated customer in registrations file
            ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream("registerations.ser"));
            for(int i=0;i<list.size();i++){
                obj.writeObject(list.get(i));
                oos.writeObject(list.get(i));
            }
        }
        catch(IOException e){
            System.out.println("Error in writing file in update function in customers file");
        }
        
    }
    
}
