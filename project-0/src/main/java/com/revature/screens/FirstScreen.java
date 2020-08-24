package com.revature.screens;

public class FirstScreen extends Screen {

    public FirstScreen(){
        super("FirstScreen","/First");
    }

    @Override
    public void render() {
        System.out.println("First Screen");
        System.out.println("1: Login");
        System.out.println("2: Register");
        System.out.println("3: Close");
        System.out.println("+-------------------+");

        try{


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
