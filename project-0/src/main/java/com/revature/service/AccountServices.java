package com.revature.service;

import com.revature.exceptions.AutheticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.Account;
import com.revature.repos.AccountRepository;

import static com.revature.AppDriver.app;

public class AccountServices {
    private AccountRepository accRepo;

    public AccountServices(AccountRepository repo){
        accRepo= repo;
    }


    /**
     * this will make suer
     * that the accnt does have the correct values to help
     * @param accnt
     */
    public void register(Account accnt){
        if(!isAccountValid(accnt)){
            throw new InvalidRequestException("Invalid user field values provided during registration!");
        }

        accRepo.save(accnt);
        System.out.println(accnt);
        app.setAccountUser(accnt);
    }

    public boolean isAccountValid(Account accnt){
        if(accnt== null)return false;
        if(accnt.getAccount_name() ==null || accnt.getAccount_name().trim().equals("")) return false;
        if(accnt.getId()==0)return false;

        return true;
    }
}
