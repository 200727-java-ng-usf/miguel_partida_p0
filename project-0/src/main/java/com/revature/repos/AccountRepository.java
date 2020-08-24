package com.revature.repos;

import com.revature.models.Account;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AccountRepository {

    public AccountRepository(){
        System.out.println("LOG - Instatitating"+ this.getClass().getName());
    }

    public Optional<Account> findById(Integer id){
        Optional<Account> _account = Optional.empty();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "Select * FROM project0."

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return _account;
    }

}
