package com.revature.screens;

import com.revature.service.UserServices;

import static com.revature.AppDriver.app;

public class Withdraw extends Screen{
    private UserServices userService;

    public Withdraw(UserServices userService){
        super("WithdrawalScreen","/withdrawal");
        this.userService = userService;
    }

    @Override
    public void render() {

        /**
         * this is withdrawal
         */
        System.out.println("Withdrawal");

        while (app.isSessionValid()) {
            try {
                System.out.println("Choose your account to withdrawal");
                String account_name = app.getCosole().readLine();

                System.out.println("Amount to withdrawal: ");
                double withdrawal = Double.parseDouble(app.getCosole().readLine());

                int updated = userService.deFundAccount(account_name, withdrawal);

                if (updated == 0) {
                    System.out.println("Invalid please try again");
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        }
        if (app.isSessionValid()) {
            app.getRouter().navigate("/options");
        }
    }
}
