package models;

public class Staff{
    private String fullName;
    private int age;
    private String role;

    public Staff(String name, int age, String role){
        this.fullName = name;
        this.age = age;
        this.role = role;
    }
    public String getName(){
        return fullName;
    }
    public int getAge(){
        return age;
    }
    public String getRole(){
        return role;
    }
}