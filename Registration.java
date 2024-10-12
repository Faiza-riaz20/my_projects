package bankmanagementsystem;

import java.io.*;
import java.util.*;


public class Registration implements Serializable {
    private String name;
    private String email;
    private String CNIC;
    private String address;
    private long phone;
    private String password;
    
    
    Registration(){
    
    }
    Registration(String n,String e,String c,String a,long p,String pas){
        name=n;
        email=e;
        CNIC=c;
        address=a;
        phone=p;
        password=pas;
    }
    Registration(Registration r){
        name=r.name;
        email=r.email;
        CNIC=r.CNIC;
        address=r.address;
        phone=r.phone;
        password=r.password;
    }
    
    public void setName(String n){
        name=n;
    }
    public void setEmail(String e){
        email=e;
    }
    public void setCNIC(String c){
        CNIC=c;
    }
    public void setAddress(String a){
        address=a;
    }
    public void setPhone(long p){
        phone=p;
    }
    public void setPassword(String pass){
        password=pass;
    }
    
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getCNIC(){
        return CNIC;
    }
    public String getAddress(){
        return address;
    }
    public long getPhone(){
        return phone;
    }
    public String getPassword(){
        return password;
    }
    
//    public static void writeAdminToRegister(Customer c){
//        
//    try{
//        File f=new File("registrations.ser");
//        ObjectOutputStream oos;
//        
//        if(f.exists()){
//                oos=new MyObjectOutputStream(new FileOutputStream(f,true));
//        }
//        else{
//            oos=new ObjectOutputStream(new FileOutputStream(f));
//        }
//            oos.writeObject(c);
//            oos.close();
//            System.out.println(c.getRegister().getName()+" Admin is written successfully in the customers file");
//        }
//        catch(IOException e){
//            System.out.println("Error in file handling for writing in customers file");
//        }
//    }
    
    public String toString(){
        return "Name: "+name+"    Email: "+email+"    CNIC: "+CNIC+"    Address: "+address+"    Phone: "+phone
                +"    Password: "+password;
    }
    
    public void display(){
        System.out.println("Name: "+name+"\nEmail: "+email+"\nCNIC: "+CNIC+"\nAddress: "+address+"\nPhone: "+phone
            +"\nPassword: "+password);
    }
    
    public static void writeToRegister(Customer c){
        
        File f=new File("registrations.ser");
        ObjectOutputStream oos;
        try{
            if(f.exists()){
                oos=new MyObjectOutputStream(new FileOutputStream(f,true));
            }
            else{
                oos=new ObjectOutputStream(new FileOutputStream(f));
            }
            if(!searchARegistration(c)){
                oos.writeObject(c);
                oos.close();
                System.out.println(c.getRegister().getName()+" written successfully in registrations file");
            }
            else{
                System.out.println("Already Registered.Can't write in registrations file");
            }
            
        }
        catch(IOException e){
            System.out.println("Error in file writing of registrations file");
        }
    }
    
    public static ArrayList<Customer> readAllRegistrations(){
        ArrayList<Customer> list=new ArrayList<Customer>();
        
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("registrations.ser"));
            
            while(true){
                Customer c=(Customer)ois.readObject();
                System.out.println(c.getRegister().getName());
                list.add(c);
            }
        }
        catch(ClassNotFoundException e1){
            System.out.println("Class not found for file reading in registrations file");
        }
        catch(EOFException e2){
            return list;
        }
        catch(IOException e3){
            System.out.println("Error in file reading in registrations file");
        }
        return list;
    } 
    
    public static boolean searchARegistration(Customer c){
        ArrayList<Customer> list=readAllRegistrations();
        
        boolean flag=false;
        for(Customer c1:list){
            if(c.getRegister().getEmail().equals(c1.getRegister().getEmail())
                && c.getRegister().getCNIC().equals(c1.getRegister().getCNIC())
                && c.getRegister().getAddress().equalsIgnoreCase(c1.getRegister().getAddress())
                && c.getRegister().getPhone()==(c1.getRegister().getPhone())){
                flag=true;
            }
        }
        return flag;
    }
    
    
}
