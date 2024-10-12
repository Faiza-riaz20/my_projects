package bankmanagementsystem;

import java.io.*;
import java.util.*;


public class LoginTime implements Serializable{
    Login login;
    String currentTime;
    
    public LoginTime(){

    }
    LoginTime(Login l1){
        login=l1;
        currentTime=findTime();
    }
    
    private String findTime(){
        Date d = new Date();
        return d.toString();
    }
    
    public Login getLogin(){
        return login;
    }
    public String getCurrentTime(){
        return currentTime;
    }
    
    public String toString(){
        return login.toString()+"    Current Time: "+currentTime;
    }
    public void display(){
        login.display();
        System.out.println("Current Time: "+currentTime);
    }
}
