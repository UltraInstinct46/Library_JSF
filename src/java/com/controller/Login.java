/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Roger Simanjuntak
 */
@ManagedBean (name = "login_bean")
@SessionScoped
public class Login {

    private String username;
    private String password;
 
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean checkuser(){
        if(username.equals("admin") && password.equals("admin")){
            return true;
        }else{
            return false;
        }
        
    }
    public Login() {
    }
    
}
