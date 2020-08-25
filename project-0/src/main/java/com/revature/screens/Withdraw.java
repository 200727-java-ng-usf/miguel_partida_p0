package com.revature.screens;

import com.revature.service.UserServices;

public class Withdraw extends Screen{
    private UserServices userService;

    public Withdraw(UserServices userService){
        super("WithdrawalScreen","/withdrawal");
        this.userService = userService;
    }

    @Override
    public void render() {

    }
}
