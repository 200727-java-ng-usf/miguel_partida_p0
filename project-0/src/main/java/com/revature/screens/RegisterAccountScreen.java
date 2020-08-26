package com.revature.screens;

import com.revature.service.UserServices;

public class RegisterAccountScreen extends Screen{
    private UserServices userServices;

    public RegisterAccountScreen(UserServices userServices){
        super("RegisterAccountScreen","/register_account");
    }

    @Override
    public void render() {

    }
}
