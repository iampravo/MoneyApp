package com.revolut.appsByPravin.MoneyApp.dao;

import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.model.Account;
import com.revolut.appsByPravin.MoneyApp.model.User;
import com.revolut.appsByPravin.MoneyApp.utils.ResultStatus;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    public List<User> getAllUsers2() {
        List<User> users = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(123213, "Saving", new BigDecimal(123.123), "USD"));
        users.add(new User("ab", 123213, accounts));
        return users;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = " select * from bank_user ";

        try (Connection connection = H2Database.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        /*String query = "select user_id, user_name from user where user_id = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement st = conn.prepareStatement(query)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(id, rs.getString("user_name")));
                } else {
                    return Optional.empty();
                }
            }
        }*/
        return Optional.empty();

    }

        /*
            SQL Methods below. Not Ginger Related. Remember, none of this file is Ginger related.
     */

   /* public static String fetchById(String id) {
        ResultSet rs;
        String json = "{";
        try {
            rs = H2Sample.statement.executeQuery("select * from test where id = " + id);
            while (rs.next()) {
                json += "" id ":" "+rs.getString(" id ")+" "";
                json += "" name ":" "+rs.getString(" name ")+" "";
                json += "," completed ":" + rs.getBoolean("completed");
            }
            json += "}";
            return json;
        } catch (Exception e) {
            return "{" error ": " Fetch By ID Failed."}";
        }
    }

    public static String fetchAll() {
        ResultSet rs;
        String json = "[";
        try {
            rs = H2Sample.statement.executeQuery("select * from test");
            while (rs.next()) {
                json += "{";
                json += "" id ":" "+rs.getString(" id ")+" "";
                json += "" name ":" "+rs.getString(" name ")+" "";
                json += "," completed ":" + rs.getBoolean("completed");
                json += "},";
            }
            json = json.substring(0, json.length() - 1);
            json += "]";
            return json;
        } catch (Exception e) {
            return "{" error ": " Fetch All Failed. "}";
        }
    }

    public static String save(String name, boolean completed) {
        try {
            String query = "insert into test (name, completed) values('" + name + "', " + completed + ")";
            System.out.println(query);
            H2Sample.statement.execute(query);
            return "{" save ": true}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{" error ": " Save Failed."}";
        }
    }

    public static String deleteById(String id) {
        try {
            H2Sample.statement.execute("delete from test where id = " + id);
            return "{" delete ": true}";
        } catch (Exception e) {
            return "{" error ": " Fetch By ID Failed."}";
        }
    }
*/
}
