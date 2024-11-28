/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Reuven
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
        //User user = new User("reuven", "02022006", 0, 0, 0, 0, "ruben", "password");
	Login login = new Login();
	UserForm userform = new UserForm();
	Controller ctrl = new Controller(login, userform);
	
    }
    
}
