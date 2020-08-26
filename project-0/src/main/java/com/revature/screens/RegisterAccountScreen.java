package com.revature.screens;

import com.revature.models.Account;
import com.revature.service.AccountServices;

import static com.revature.AppDriver.app;

public class RegisterAccountScreen extends Screen{
    private AccountServices accService;

    public RegisterAccountScreen(AccountServices accService){
        super("RegisterAccountScreen","/register_account");
        this.accService = accService;
    }

    @Override
    public void render() {
        String account_name;
        System.out.println("Create you new account" );


        try {
            System.out.println("Account Name: ");
            account_name = app.getCosole().readLine();

            Account newAcc = new Account(app.getCurrentUser().getId(),account_name, 0.0d );

            accService.register(newAcc);

            if(app.isSessionValid()){
                app.getRouter().navigate("/options");
            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
