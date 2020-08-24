package com.revature.util;

import com.revature.models.AppUser;
import com.revature.repos.UserRepository;
import com.revature.screens.*;
import com.revature.service.UserServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class AppState {
    private BufferedReader cosole;
    private AppUser currentUser;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState(){
        System.out.println("LOG - initializing application...");

        appRunning = true;
        cosole = new BufferedReader(new InputStreamReader(System.in));

        final UserRepository userRepo = new UserRepository();
        final UserServices userService = new UserServices(userRepo);

        router = new ScreenRouter();
        router.addScreen(new FirstScreen())
                .addScreen(new LoginScreen(userService))
                .addScreen(new RegisterScreen(userService))
                .addScreen(new Withdraw())
                .addScreen(new Deposit())
                .addScreen(new AccountOptions());

        System.out.println("LOG Application initialization complete");
    }

    public BufferedReader getCosole() {
        return cosole;
    }

    public void setCosole(BufferedReader cosole) {
        this.cosole = cosole;
    }

    public AppUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AppUser currentUser) {
        this.currentUser = currentUser;
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public void setRouter(ScreenRouter router) {
        this.router = router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

    public boolean isSessionValid(){
        return (this.currentUser != null);
    }
}
