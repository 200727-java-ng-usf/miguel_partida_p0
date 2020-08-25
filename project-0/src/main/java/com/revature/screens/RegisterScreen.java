package com.revature.screens;

import com.revature.exceptions.InvalidRequestException;
import com.revature.models.AppUser;
import com.revature.service.UserServices;

import static com.revature.AppDriver.app;

public class RegisterScreen  extends Screen{
    private UserServices userServices;

    public RegisterScreen(UserServices userServices){
        super("RegisterScreen","/register");

        this.userServices = userServices;// this is loosely coupled , becasue this class is not responsible for instantiation of a userService
//        userService = new UserService(); - this is tight coupling - thats bad we aim for looseness

    }


    @Override
    public void render() {

        String firstname, lastname, password, email;

        try{
            System.out.println("Sign up for a new account!" );

            System.out.println("First Name: ");
            firstname = app.getCosole().readLine();

            System.out.println("Last Name: ");
            lastname = app.getCosole().readLine();

            System.out.println("Password: ");
            password = app.getCosole().readLine();

            System.out.println("Email: ");
            email = app.getCosole().readLine();

            AppUser newUser = new AppUser(firstname,lastname,password,email);

            userServices.register(newUser);

            if(app.isSessionValid()){
                app.getRouter().navigate("/options");
            }

        }catch(InvalidRequestException e) {
            System.out.println("Registration unsuccessful, invalid values provided");
        }catch (Exception e){
            System.err.println("[ERROR] - An unexpected exception occurred: " + e.getMessage());
            System.out.println("[LOG] - Shutting down application");
            app.setAppRunning(false);
        }
    }

}
