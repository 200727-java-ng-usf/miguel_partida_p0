package com.revature.service;

import com.revature.exceptions.AutheticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.AppUser;
import com.revature.repos.UserRepository;

import javax.naming.AuthenticationException;

import java.util.Optional;

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

    public void register(AppUser newUser) {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user field values provided during registration!");
        }

        Optional<Object> existingUser = Optional.ofNullable(userRepo.findUserByEmail(newUser.getEmail()));
        if (existingUser.isPresent()) {
            // TODO implement a custom ResourcePersistenceException
            throw new RuntimeException("Provided email is already in use!");
        }

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

    }


