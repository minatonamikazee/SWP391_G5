/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Admin
 */
public class UserDAO extends MyDAO {

    public User getUser(String email) {
        User x = null;
        xSql = "SELECT * FROM [User] WHERE [email] = ? ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                x = new User(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getDate(8), rs.getString(7), rs.getInt(9));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public void addUser(User x) {
        xSql = "INSERT INTO [User]\n"
                + "           ([fullname]\n"
                + "           ,[gender]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[password]\n"
                + "           ,[avatar]\n"
                + "           ,[createDate]\n"
                + "           ,[roll_id])\n"
                + "     VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getFullname());
            ps.setBoolean(2, x.isGender());
            ps.setString(3, x.getEmail());
            ps.setString(4, x.getPhone());
            ps.setString(5, x.getPassword());
            ps.setString(6, x.getAvatar());
            ps.setDate(7, x.getCreateDate());
            ps.setInt(8, x.getRollId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getAccount(String email, String password) {
        User user = null;
        try {
            String sql = "Select url from [User],Roll,Roll_Feature where [User].roll_id = Roll.id \n"
                    + "and Roll.id = Roll_Feature.roll_id \n"
                    + "and[User].email = ?\n"
                    + "and [User].password = ?";
            PreparedStatement statement;

            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (user == null) {
                    user = new User();
                }
                user.setEmail(email);
                user.setPassword(password);
                user.getUrl().add(rs.getString("url"));
            }
            return user;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }

    public int UpdateUser(String email, String fullname, String phone, boolean gender) {
        int n = 0;
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [fullname] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[phone] = ?\n"
                + "     \n"
                + " WHERE email = ?";
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, fullname);
            statement.setBoolean(2, gender);
            statement.setString(3, phone);
            statement.setString(4, email);
            n = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changePassword(String email, String password) {
        int n = 0;
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [password] = ?\n"
                + " WHERE email = ?";
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2, email);

            n = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public String getRollName(String email) {
        String roll_name = "";
        try {
            String sql = "Select Roll.name from [User],Roll\n"
                    + "where\n"
                    + "[User].roll_id = Roll.id\n"
                    + "and\n"
                    + "email = ?";
            PreparedStatement statement;

            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
           
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                roll_name = rs.getString(1);
            }
            return roll_name;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roll_name;
    }
    
    
}
