/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Reuven
 */
public class User {
     private String type;
    private String name;
    private String birthdate;
    private int age;
    private double height;
    private double weight;
    private int userID;
    private String username;
    private String password;
    
    public User(String name, String birthdate, int age, double height, double weight, int userID, String username, String password){
        this.name = name;
        this.birthdate = birthdate;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.userID = userID;
        this.username = username;
        this.password = password;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getBirthDate(){
        return this.birthdate;
    }
    
    public int getAge(){
	return this.age;
    }
    
    public double getHeight(){
	return this.height;
    }
    
    public double getWeight(){
	return this.weight;
    }
    
    public int getUserID(){
	return this.userID;
    }
    
    public String getUsername(){
	return this.username;
    }
    
    public String getPassword(){
	return this.password;
    }
}

class Admin {
     private String username;
     private String type;
}