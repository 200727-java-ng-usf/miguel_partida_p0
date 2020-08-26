package com.revature.service;

import com.revature.exceptions.AutheticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.Account;
import com.revature.models.AppUser;
import com.revature.models.Roles;
import com.revature.repos.AccountRepository;
import com.revature.repos.UserRepository;

import javax.naming.AuthenticationException;

import java.sql.SQLException;
import java.util.Optional;

import static com.revature.AppDriver.app;

public class UserServices {
    private UserRepository userRepo;

    public UserServices(UserRepository repo){
        System.out.println("LOG Instantiating"+this.getClass().getName());
        userRepo = repo;
        //        userRepo = new UserRepository(); // tight coupling! ~hard~ impossible to unit test

    }

    public void authenticate(String email, String password){

        // validate that the provided email and password are not non-values
        if(email==null|| email.trim().equals("")||password == null||password.trim().equals("")){
            throw new RuntimeException("Invalid credential values provided!");
        }
        AppUser authUser = userRepo.findUserByCredentials(email,password)
                .orElseThrow(AutheticationException::new);

                app.setCurrentUser(authUser);
        }

    public void register(AppUser newUser) {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user field values provided during registration!");
        }

        Optional<AppUser> existingUser = userRepo.findUserByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            // TODO implement a custom ResourcePersistenceException
            throw new RuntimeException("Provided email is already in use!");
        }

        newUser.setRole(Roles.BASIC_USER);
        userRepo.save(newUser);
        System.out.println(newUser);

        app.setCurrentUser(newUser);

    }

    /**
     * Validates that the given user and its fields are valid (not null or empty strings). Does
     * not perform validation on id or role fields.
     *
     * @param user
     * @return true or false depending on if the user was valid or not
     */
    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getPassWord() == null || user.getPassWord().trim().equals("")) return false;
        if (user.getEmail()==null || user.getEmail().trim().equals(""))return false;

        return true;
    }

    public int fundAccount(String account_name, double balance){
        int updated =0;

        try {
             updated= AccountRepository.fundAccount(account_name,balance);

        }catch (Exception e){
            e.printStackTrace();
        }
        return updated;
    }

    public int deFundAccount(String account_name, double deposit){
        int updated = 0;
        try{
            updated = AccountRepository.deFundAccount(account_name,deposit);

        }catch(Exception e){
            e.printStackTrace();
        }
        return updated;
    }

    }


