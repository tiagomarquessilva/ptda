/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;


/**
 *
 * @author joao_
 */
public class User {
    
    private String username;
    private String password;
    private String name;
    private String profile;

   
    public User(String username, String password, String name, String profile) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.profile = profile;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getProfile() {
        return profile;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}