package com.revature.repos;

import com.revature.models.Account;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AccountRepository {

    public AccountRepository(){
        System.out.println("LOG - Instatitating"+ this.getClass().getName());
    }

    public Optional<Account> findById(Integer id){
        Optional<Account> _account = Optional.empty();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM project0.account "+
                    "WHERE account_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();
            _account = mapResultSet(rs).stream().findFirst();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return _account;
    }

    public void save(Account account){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "INSERT into project0.accounts "+
                    "(account_name, balance) " +
                    "VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"account_id","user_id"});

            pstmt.setString(1,account.getAccount_name());
            pstmt.setDouble(2,account.getBalance());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    account.setId(rs.getInt("id"));
                }
            }

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    private Set<Account> mapResultSet(ResultSet rs) throws SQLException{
        Set<Account> accounts = new HashSet<>();

        while(rs.next()){
            Account temp = new Account();
            temp.setId(rs.getInt("account_id"));
            temp.setBalance(rs.getDouble("balance"));

            accounts.add(temp);

        }
        return accounts;

    }

}
