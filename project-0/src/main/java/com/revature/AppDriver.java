package com.revature;

import com.revature.util.AppState;

public class AppDriver {

    //used to be instantiated before the main
    public static AppState app = new AppState();

    public static void main(String[] args) {

        while(app.isAppRunning()){
            app.getRouter().navigate("/first");
        }

    }
}
