package com.revature.repos;

import com.revature.models.Account;
import com.revature.models.AppUser;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.AppDriver.app;

public class AccountRepository {

    public AccountRepository(){
        System.out.println("LOG - Instatitating"+ this.getClass().getName());
    }

    public Optional<Account> findById(Integer id){
        Optional<Account> _account = Optional.empty();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT user_id FROM project0.account_user "+
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

    public static int fundAccount(String account_name, double balance){
        int rowsInserted = 0;

        try(Connection conn= ConnectionFactory.getInstance().getConnection()){
            String sql = "UPDATE project0.accounts SET balance = balance + ? "+
                    "WHERE account_name = ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1,balance);
            pstmt.setString(2, account_name);
            rowsInserted = pstmt.executeUpdate();

            

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return rowsInserted;
    }

    public static int deFundAccount(String account_name, double balance){
        int rowsInserted =0;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "UPDATE project0.accounts SET balance = balance - ? "+
                    "WHERE account_name = ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1,balance);
            pstmt.setString(2, account_name);

            rowsInserted = pstmt.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }
        return rowsInserted;
    }

    public void save(Account account){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "INSERT into project0.accounts "+
                    "(user_id,account_name, balance) " +
                    "VALUES (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"account_id"});

            pstmt.setInt(1,app.getCurrentUser().getId());
            pstmt.setString(2,account.getAccount_name());
            pstmt.setDouble(3,account.getBalance());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                account.setId(rs.getInt(1));
            }


        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public static void showBalance(String account_name){

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT balance FROM project0.accounts " +
                    "WHERE account_name = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,account_name);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                System.out.print("Amount: $");
                System.out.println(rs.getDouble(1));
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
            temp.setAccount_name(rs.getString("account_name"));
            temp.setBalance(rs.getDouble("balance"));

            accounts.add(temp);

        }
        return accounts;

    }

}
