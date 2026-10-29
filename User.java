
package model;

public class User {
    
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String contactNumber;
    
    public User(int id, String firstname, String lastname, String username, String password, String contactNumber){
        
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
        
    }
    
    public User(String firstname, String lastname, String username, String password){
        
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.contactNumber = "";
    }
    
    public User(String firstname, String lastname,String username, String password,String contactNumber) {
        
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    this.password = password;
    this.contactNumber = contactNumber;
        
    }
    public int getId(){
        return id;
    }
    
    public String getFirstname(){
        return firstname;
    }
    
    public String getLastname(){
        return lastname;
    }
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }   
    
    public String getContactNumber(){
        return contactNumber;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
        
    }
    
}
