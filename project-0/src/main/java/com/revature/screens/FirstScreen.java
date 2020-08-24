package com.revature.screens;

import static com.revature.AppDriver.app;

public class FirstScreen extends Screen {

    public FirstScreen(){
        super("FirstScreen","/first");
        System.out.println("Log");
    }

    @Override
    public void render() {
        System.out.println("First Screen");
        System.out.println("1: Login");
        System.out.println("2: Register");
        System.out.println("3: Close");
        System.out.println("+-------------------+");

        try{

            String userSelection = app.getCosole().readLine().trim();

            switch(userSelection){
                case "1":
                    app.getRouter().navigate("/login");
                    break;
                case "2":
                    app.getRouter().navigate("/register");
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
