package bankmanagementsystem;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Account implements Serializable {
    private Customer customer;
    private String accountType;
    private double balance;
    private String accountID;
    
    Account(){
        
    }
    Account(Customer cust,String type,double cash){
        Scanner input=new Scanner(System.in);
        
        customer=cust;
        accountType=type;
        balance=cash;
        
//        String otp=generateOTP();
//        System.out.println("Your generated otp: "+otp);
//        System.out.print("Please enter the otp: ");
//        String pas=input.next();
//        
//        if(pas.equals(otp)){ 
            accountID=generateAccoutID();
            System.out.println("Account created successfully");
            writeToAccount(this);
            System.out.println("Your account ID is "+accountID);
//        }
//        else{
//            System.out.println("Sorry...Incorrect password...Account not created");
//        }
    }
    Account(Account a){
        customer=a.customer;
        accountType=a.accountType;
        balance=a.balance;
        accountID=a.accountID;
    }
    
    public void setAccountType(String type){
        accountType=type;
    }
    public void setBalance(double bal){
        balance=bal;
    }
    public void setAccountID(String id){
        accountID=id;
    }
    public void setCustomer(Customer c){
        customer=c;
    }
    
    public String getAccountType(){
        return accountType;
    }
    public double getBalance(){
        return balance;
    }
    public String getAccountID(){
        return accountID;
    }
    public Customer getCustomer(){
        return customer;
    }
//    private String generateOTP(){//Generating otp for more security
//        String otp="";
//        for(int i=0;i<4;i++){
//            int pass=(int)(Math.random()*10);
//            otp+=pass;
//        }
//        return otp;
//    }
    
    private String generateAccoutID(){//Generating the random Account ID
        String account="";     
        char alphabets;
        int numbers;
        for(int i=0;i<11;i++){
            if(i==0 || i==1){
                alphabets=(char)('A'+ Math.random()*26);
                account+=alphabets;
            }
            else{
                numbers=(int)(Math.random()*10);
                account+=numbers;
            }
        }
        return account;
    }


    public void display(){
        customer.display();
        System.out.println("Account ID: "+accountID);
        System.out.println("Account Type: "+accountType);
        System.out.println("Balance: "+balance);
    }
    
    public String toString(){
        return customer.toString()+"    Account ID: "+accountID+"    Account Type: "+accountType
            +"    Balance: "+balance;
    }

    
    
    
    public static void writeToAccount(Account a){
        
    try{
        File f=new File("accounts.ser");
        ObjectOutputStream oos;
        
        if(f.exists()){
                oos=new MyObjectOutputStream(new FileOutputStream(f,true));
        }
        else{
            oos=new ObjectOutputStream(new FileOutputStream(f));
        }
            oos.writeObject(a);
            oos.close();
            System.out.println(a.getAccountID()+" is written successfully in the accounts file");
        }
        catch(IOException e){
            System.out.println("Error in file handling for writing in accounts file");
        }
    }
    
    public static void updateAnAccount(String accountNum,String pass,String email,long phone,String address,double amount){
        ArrayList<Account> list=readAllAccounts();
        
        boolean flag=true;
        for(int i=0;i<list.size();i++){
            Customer cust = list.get(i).getCustomer();
            if(list.get(i).getCustomer()!=null){
                if(list.get(i).getCustomer().getAccountNumber().equals(accountNum) && list.get(i).getCustomer().getRegister().getPassword().equals(pass)){
                    list.get(i).getCustomer().updateACustomer(accountNum,pass,email,phone,address);
//                  list.get(i).setBalance(amount);
//                    JOptionPane.showMessageDialog(null,"Account updated Successfully");
                    System.out.println("Account updated successfully");
                    flag=false;
                }
            }
            else{
                System.out.println("customer is null in updateAnAccount method");
            }
        }
        if(flag){
            JOptionPane.showMessageDialog(null,"Invalid credentials\nAccount not found");
            System.out.println("Account not found to be updated in accounts file");
        }
        
        //writing the updated list into the accounts file
        try{
            ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream("accounts.ser"));
            for(int i=0;i<list.size();i++){
                obj.writeObject(list.get(i));
            }
        }
        catch(IOException e){
            System.out.println("Error in file writing in delete method of accounts file");
        }
         
    }
    
    
    public static ArrayList<Account> readAllAccounts(){
        ArrayList<Account> list=new ArrayList<Account>();
        
        ObjectInputStream ois;
        try{
            ois=new ObjectInputStream(new FileInputStream("accounts.ser"));
            while(true){
                Account a=(Account)ois.readObject();
                list.add(a);
                System.out.println(a.getAccountID());
            }
        }
        catch(ClassNotFoundException e1){
            System.out.println("Class Not found for reading from accounts file");
        }
        catch(EOFException e2){
            return list;
        }
        catch(IOException e3){
            System.out.println("Error in file reading of accounts file");
            e3.printStackTrace();
        }
        return list;
    }
    
    public static void deleteAnAccount(String ID,String type){
        ArrayList<Account> list=readAllAccounts();
        
        boolean flag=true;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getAccountID().equals(ID) && list.get(i).getAccountType().equals(type)){
                System.out.println(list.get(i).getAccountID()+" is deleted suucessfully in accounts file");
                list.remove(i);
                flag=false;
                JOptionPane.showMessageDialog(null,"Account deleted successfully");
            }
        }
        if(flag){
            JOptionPane.showMessageDialog(null,"Account not found");
            System.out.println("Customer not found to be deleted from accounts file");
        }
        
        try{
            ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream("accounts.ser"));
            for(int i=0;i<list.size();i++){
                obj.writeObject(list.get(i));
            }
        }
        catch(IOException e){
            System.out.println("Error in file writing in delete method of accounts file");
        }
    }
    
    public void withdraw(double amount){
        if(amount<=balance){
            balance-=amount;
            updateAnAccount(this.getCustomer().getAccountNumber(),this.getCustomer().getRegister().getPassword(),
                this.getCustomer().getRegister().getEmail(),this.getCustomer().getRegister().getPhone()
                ,this.getCustomer().getRegister().getAddress(),amount);
            Date d=new Date();
            String str=" Withdrawn "+amount+" on "+d;
            AccountTransaction a=new AccountTransaction(this,str);
            
            JOptionPane.showMessageDialog(null,amount+" withdrawn successfully from your account.\nYour current balance is "+this.getBalance());
//            System.out.println(amount+" withdrawn successfully from the account");
        }
        else{
            JOptionPane.showMessageDialog(null,"Sorry for the Transaction\n"+amount+" is greater than your Balance.\nYour current balance is "+this.getBalance());
//            System.out.println("Sorry.Your balance is less for this transaction");
        }
    }
    
    public void deposit(double amount){
            balance+=amount;
            updateAnAccount(this.getCustomer().getAccountNumber(),this.getCustomer().getRegister().getPassword(),
                this.getCustomer().getRegister().getEmail(),this.getCustomer().getRegister().getPhone()
                ,this.getCustomer().getRegister().getAddress(),amount);
            Date d=new Date();
            String str=" Deposited "+amount+" on "+d;
            AccountTransaction a=new AccountTransaction(this,str);
            JOptionPane.showMessageDialog(null,amount+" is deposited successfully into your account.\nYour current balance is "+this.getBalance());
//            System.out.println(amount+" deposited successfully in the account");
            
    }
    
    public void transfer(double amount,String id){
        ArrayList<Account> list=readAllAccounts();
        boolean notFound=true;
        boolean small=this.balance>=amount;
        
        for(int i=0;i<list.size();i++){
            if(id.equals(list.get(i).getAccountID())){
                notFound=false;
                if(small){
                    this.balance-=amount;
                    
                    updateAnAccount(this.getCustomer().getAccountNumber(),this.getCustomer().getRegister().getPassword(),
                        this.getCustomer().getRegister().getEmail(),this.getCustomer().getRegister().getPhone()
                        ,this.getCustomer().getRegister().getAddress(),this.balance);
                    Date d=new Date();
                    String str=" Transfered "+amount+" on "+d+" to account ID "+id;
                    AccountTransaction a1=new AccountTransaction(this,str);
//                    System.out.println(amount+" is transfered to other account "+id);
                    
                    double newAmount=list.get(i).getBalance()+amount;
                    list.get(i).setBalance(newAmount);
                    updateAnAccount(list.get(i).getCustomer().getAccountNumber(),list.get(i).getCustomer().getRegister().getPassword(),
                        list.get(i).getCustomer().getRegister().getEmail(),list.get(i).getCustomer().getRegister().getPhone()
                        ,list.get(i).getCustomer().getRegister().getAddress(),newAmount);
                    
                    Date d1=new Date();
                    String str1=" Received "+amount+" on "+d+" from account ID "+this.accountID;
                    AccountTransaction a2=new AccountTransaction(this,str1);
//                    System.out.println(amount+" is received from other account "+this.accountID);
                    
                    JOptionPane.showMessageDialog(null,amount+" is successfully transfered to other account with Account ID: "+id+"\nYour current balance is "+this.getBalance());
                }
            }
        }
        if(notFound){
            JOptionPane.showMessageDialog(null,"Sorry for the Transaction\nAccount with ID "+id+" not found");
//            System.out.println("Account not found to transfer money");
        }
        if(!small){
            JOptionPane.showMessageDialog(null,"Sorry for the transaction\nYou don't have sufficient balance to transfer to other account\nYour current balance is "+this.getBalance());
//            System.out.println("Sorry...You don't have sufficient balance to tranfer money");
        }
}
    
    
}
