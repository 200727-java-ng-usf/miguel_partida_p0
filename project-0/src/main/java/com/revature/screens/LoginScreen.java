package com.revature.screens;

import com.revature.models.AppUser;
import com.revature.service.UserServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.revature.AppDriver.app;

public class LoginScreen extends Screen{

    private UserServices userService;


    public LoginScreen(UserServices userService) {
        super("LoginScreen", "/login");

        this.userService = userService;
        // this is loosely coupled , becasue this class is not responsible for instantiation of a userService
//        userService = new UserService();
    }

    @Override
    public void render() {
        /**
         * Renders the login screen menu to the console.
         */
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String email, password;
        try {
            System.out.println("Please provide your login credentials");
            System.out.println("Email: ");
            email = console.readLine();
            System.out.println("Password: ");
            password = console.readLine();

             userService.authenticate(email,password);

             if(app.isSessionValid()){
                 app.getRouter().navigate("/options");
             }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
