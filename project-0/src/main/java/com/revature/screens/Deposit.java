package com.revature.screens;

import com.revature.service.UserServices;

import java.io.IOException;

import static com.revature.AppDriver.app;

public class Deposit extends Screen{
    private UserServices userServices;

    public Deposit(UserServices userServices){
        super("DepositScreen","/deposit");
        this.userServices= userServices;
    }

    @Override
    public void render() {
        System.out.println("Deposit");
        /**
         * this is the deposit method
         */

        while(app.isSessionValid()){

            try{
                System.out.println("Choose your account to deposit");
               String account_name = app.getCosole().readLine();

                System.out.println("Amount deposited: ");
                double deposit = Double.parseDouble(app.getCosole().readLine());

                int updated = userServices.fundAccount(account_name,deposit);

                if(updated == 0){
                    System.out.println("Invalid pls try again");
                }
                break;
            }catch(Exception e){
                e.printStackTrace();
            }
            break;
        }

        if(app.isSessionValid()){
            app.getRouter().navigate("/options");
        }

    }
}
