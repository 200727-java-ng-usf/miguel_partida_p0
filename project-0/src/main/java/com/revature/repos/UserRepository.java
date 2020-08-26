package com.revature.repos;

import com.revature.models.AppUser;
import com.revature.models.Roles;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserRepository {
    //data access object

    public UserRepository() {
        super();
    }

    public Optional<AppUser> findUserByCredentials(String email, String password) {
        Optional<AppUser> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM project0.account_user au " +
                    "JOIN project0.user_roles ur "+
                    "ON au.role_id = ur.id "+
                    "WHERE email = ? AND passwrd = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return _user;
    }


    public Optional<AppUser> findUserByEmail(String email){
        Optional<AppUser> _user = Optional.empty();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT email FROM project0.account_user " +
                    "WHERE email = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,email);

            ResultSet rs = pstmt.executeQuery();

            _user = mapResultSet(rs).stream().findFirst();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return _user;
    }

    public void save(AppUser newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project0.account_user " +
                    "(first_name,last_name,passwrd,email,role_id) " +
                    "VALUES (?,?,?,?,?) ";

            //second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql,new String[] {"user_id"});

            pstmt.setString(1, newUser.getFirstName());
            pstmt.setString(2, newUser.getLastName());
            pstmt.setString(3, newUser.getPassWord());
            pstmt.setString(4, newUser.getEmail());
            pstmt.setInt(5,newUser.getRole().ordinal() +1);


            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                 rs.next();
                 newUser.setId(rs.getInt(1));
                }


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    private Set<AppUser> mapResultSet(ResultSet rs) throws SQLException{
        Set<AppUser> users = new HashSet<>();

        while (rs.next()) {
            AppUser temp = new AppUser();

            temp.setId(rs.getInt("id"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setFirstName(rs.getString("last_name"));
            temp.setFirstName(rs.getString("passwrd"));
            temp.setFirstName(rs.getString("email"));
            temp.setRole(Roles.getByName(rs.getString("role_id")));

            users.add(temp);
        }
         return users;
        }
    }
