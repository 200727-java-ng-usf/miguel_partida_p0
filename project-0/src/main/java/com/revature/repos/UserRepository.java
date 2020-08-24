package com.revature.repos;

import com.revature.models.AppUser;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {
    //data access object

    public UserRepository() {
        System.out.println("LOG - Instatitating" + this.getClass().getName());
    }

    public Optional<AppUser> findUserByCredentials(String username, String password) {
        Optional<AppUser> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){



        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return _user;
    }

    public void save(AppUser newUser) {


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO revabooks.app_users " +
                    "VALUES(?,?, ?, ?, ? ); ";

            //second parameter here is used to indicate colum names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});

            pstmt.setString(1, newUser.getPassWord());
            pstmt.setString(2, newUser.getFirstName());
            pstmt.setString(3, newUser.getLastName());
            pstmt.setString(4, newUser.getEmail());


            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
