package com.revature.screens;

import static com.revature.AppDriver.app;

public class AccountOptions extends Screen{

    public AccountOptions(){
        super("Account Options","/options");
    }

    @Override
    public void render() {
        System.out.println("Welcome please select a task");
        System.out.println("1: Withdrawal");
        System.out.println("2: Deposit");
        System.out.println("3: Logout and close");
        System.out.println("+-------------------+");



        try{

            String userSelection = app.getCosole().readLine().trim();

            switch(userSelection){
                case "1":
                    app.getRouter().navigate("/withdrawl");
                    break;
                case "2":
                    app.getRouter().navigate("/deposit");
                    break;
                case "3":
                    app.setAppRunning(false);
                    break;
                default:
                    System.out.println("Please Try again");
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
