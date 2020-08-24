package com.revature.service;

import com.revature.exceptions.AutheticationException;
import com.revature.models.AppUser;
import com.revature.repos.UserRepository;

import javax.naming.AuthenticationException;

import static com.revature.AppDriver.app;

public class UserServices {
    private UserRepository userRepo;

    public UserServices(UserRepository repo){
        System.out.println("LOG Instantiating"+this.getClass().getName());
        userRepo = repo;
        //        userRepo = new UserRepository(); // tight coupling! ~hard~ impossible to unit test

    }

    public void authenticate(String username, String password){

        // validate that the provided username and password are not non-values
        if(username==null|| username.trim().equals("")||password == null||password.trim().equals("")){
            throw new RuntimeException("Invalid credential values provided!");
        }
        AppUser authUser = userRepo.findUserByCredentials(username,password)
                .orElseThrow(AutheticationException::new);

                app.setCurrentUser(authUser);
        }

    }


