package bankmanagementsystem;

import static bankmanagementsystem.Login.writeToLogin;

import java.io.*;
import java.util.*;

public class Manager implements Serializable{
    private String userName;
    private String email;
    private String password;
    
    Manager(){
        
    }
    Manager(String user,String mail,String pass){
        userName=user;
        email=mail;
        password=pass;
        writeToManager(this);
    }
    Manager(Manager m){
        userName=m.userName;
        email=m.email;
        password=m.password;
    }
    
    public String getUserName(){
        return userName;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    
    public String toString(){
        return "User Name: "+userName+"    Email: "+email+"    Password: "+password;
    }
    public void display(){
        System.out.println("User Name: "+userName+"\nEmail: "+email+"\nPassword: "+password);
    }
    
    public static ArrayList<Account> viewAllAccounts(){
        return Account.readAllAccounts();
    }
    
    public AccountTransaction viewTransactionHistory(String custName,String type){
        ArrayList<AccountTransaction> list=AccountTransaction.readAllAccountTransactions();
        
        for(AccountTransaction i:list){
            if(i.getAccount().getCustomer().getRegister().getName().equals(custName)
                && i.getAccount().getAccountType().equals(type)){
                return i;
            }
        }
        return null;
    }
    
    public void closeCustomerAccount(String id,String name,String type){
        ArrayList<Account> list=Account.readAllAccounts();
        
        boolean flag=true;
        for(Account i:list){
            if(i.getAccountID().equals(id) && i.getAccountType().equals(type)
                && i.getCustomer().getRegister().getName().equals(name)){
                flag=false;
                Account.deleteAnAccount(id,type);
                System.out.println(name+"'s Account removed successfully by manager");
            }
        }
        if(flag){
            System.out.println("Account not found to be deleted by manager.");
        }
    }
    
    public static void writeToManager(Manager m){
        try{
        File f=new File("manager.ser");
        ObjectOutputStream oos;
        
        if(f.exists()){
                oos=new MyObjectOutputStream(new FileOutputStream(f,true));
        }
        else{
            oos=new ObjectOutputStream(new FileOutputStream(f));
        }
            oos.writeObject(m);
            oos.close();
            System.out.println(m.getUserName()+" is written successfully in the manager file");
        }
        catch(IOException e){
            System.out.println("Error in file handling for writing in manager file");
        }
    }
    
    public static ArrayList<Manager> readAllManagers(){
        ArrayList<Manager> list=new ArrayList<Manager>();
        
        ObjectInputStream ois;
        try{
            ois=new ObjectInputStream(new FileInputStream("manager.ser"));
            while(true){
                Manager m=(Manager)ois.readObject();
                list.add(m);
                System.out.println(m.getUserName());
            }
        }
        catch(ClassNotFoundException e1){
            System.out.println("Class Not found for reading from manager file");
        }
        catch(EOFException e2){
            return list;
        }
        catch(IOException e3){
            System.out.println("Error in file reading of manager file");
        }
        return list;
    }
    
    public static boolean searchAManager(Manager m){
        ArrayList<Manager> list=readAllManagers();
        
        for(Manager i:list){
            if(i.getUserName().equals(m.getUserName())
                && i.getEmail().equals(m.getEmail()) && i.getPassword().equals(m.getPassword())){
                return true;
            }
        }
        return false;
    }
    
    public void managerLogin(){
        Scanner input=new Scanner(System.in);
        
        if(searchAManager(this)){
            String otp=generateOTP();
            System.out.println("Your generated otp: "+otp);
            System.out.print("Please enter the otp: ");
            String pas=input.next();
        
            if(pas.equals(otp)){ 
                System.out.println("Successfully logged in");

            }
            else{
                System.out.println("Can't login to Manager Account. Wrong Credentials");
            }
        }
        else{
            System.out.println("Invalid Manager's Account");
        }
    }
    
    private String generateOTP(){//Generating otp for more security
        String otp="";
        for(int i=0;i<4;i++){
            int pass=(int)(Math.random()*10);
            otp+=pass;
        }
        return otp;
    }
}
