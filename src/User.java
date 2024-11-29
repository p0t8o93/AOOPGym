public class User {
    private int userId;
    private String accountType;
    private String username;
    private String password;
    private String name;
    private String phoneNo;
    private int age;
    private String birthdate;
    private String gender;
    private double height;
    private double weight;
    private double bmi;

    // Constructor
    public User(int userId, String accountType, String username, String password, String name, String phoneNo, int age,
                String birthdate, String gender, double height, double weight, double bmi) {
        this.userId = userId;
	this.accountType = accountType;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNo = phoneNo;
        this.age = age;
        this.birthdate = birthdate;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }
    
    public boolean getPrivileges(){
	 return accountType == "Admin";
    }
}