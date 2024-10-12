package bankmanagementsystem;

import java.io.*;
import java.util.*;

public class AccountTransaction implements Serializable {
    private Account account;
    private String info;
    
    AccountTransaction(){
        
    }
    AccountTransaction(Account a,String i){
        account=a;
        info=i;
        
        writeToAccountTransaction(this);
    }
//    AccountTransaction(AccountTransaction a){
//        account=a.account;
//        info=a.info;
//    }
    
    public void setAccount(Account a){
        account=a;
    }
    public void setInfo(String i){
        info=i;
    }
    
    public Account getAccount(){
        return account;
    }
    public String getInfo(){
        return info;
    }
    
    public String toString(){
        return account.toString()+"    Transaction Info:"+info;
    }
    
    public void display(){
        account.display();
        System.out.println("Transaction Info: "+info);
    }
    
    public static void writeToAccountTransaction(AccountTransaction a){
        
        try{
            File f=new File("transactions.ser");
            ObjectOutputStream oos;
            
            if(f.exists()){
                oos=new MyObjectOutputStream(new FileOutputStream(f,true)); 
            }
            else{
                oos=new ObjectOutputStream(new FileOutputStream(f)); 
            }
            
            oos.writeObject(a);
            oos.close();
            System.out.println("Transaction written successfully in transactions file");
            
        }
        catch(IOException e1){
            System.out.println("Error in file writing in transactions file");
        }
    }
    
    public static ArrayList<AccountTransaction> readAllAccountTransactions(){
        ArrayList<AccountTransaction> list=new ArrayList<AccountTransaction>();
        
        ObjectInputStream ois;
        try{
            ois=new ObjectInputStream(new FileInputStream("transactions.ser"));
            while(true){    
                AccountTransaction a=(AccountTransaction)ois.readObject();
                list.add(a);
                System.out.println(a.info);
            }
        }
        catch(ClassNotFoundException e1){
            System.out.println("Class Not found for reading from transactions file");
        }
        catch(EOFException e2){
            return list;
        }
        catch(IOException e3){
            System.out.println("Error in file reading of transactions file");
        }
        return list;
    } 
    
    public static AccountTransaction searchTransaction(Account a){
        ArrayList<AccountTransaction> list=readAllAccountTransactions();
        
        for(AccountTransaction t:list){
            if(a.getAccountType().equals(t.getAccount().getAccountType()) && 
                a.getAccountID().equals(t.getAccount().getAccountID())){
                return t;
            }
        }
        return new AccountTransaction();
    }
    
}
