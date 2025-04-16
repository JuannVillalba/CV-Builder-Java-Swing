package cvbuilder.Models;

import javax.swing.*;


public class User {
    private String name;
    private String userProfileID;
    private String title;
    private String email;

    public User(String userProfileID, String title,String name, String email) {
        this.name = name;
        this.userProfileID = userProfileID;
        this.title = title;
        this.email = email;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserProfileID() {
        return userProfileID;
    }
    public void setUserProfileID(String userProfileID) {
        this.userProfileID = userProfileID;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public void setName(String name) {
        this.name=name;
    }
    public String getName(){
      return this.name;
    }

    
}
