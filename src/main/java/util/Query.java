package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.binary.Hex;

import java.sql.Connection;

public class Query {

    // Add new registered user to the database.
    public Query() {}

    public static boolean addUser(String email, String password) {
        if (emailExists(email)) {
            return false; // Cannot add duplicate user.
        }

        Connection con = dbUtil.getConnection();

        String query = "INSERT INTO user(email, status, plevel, salt, pword)\n" +
                       "VALUES (?,?,?,?,?);";

        byte[] salt = Crypto.genSalt();
        char[] pword = password.toCharArray();
        String saltedPword = Security.encodePassword(pword, salt);
        String saltedSalt = Security.encodeSalt(salt);

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, 4);
            ps.setInt(3, 4);
            ps.setString(4, saltedSalt);
            ps.setString(5, saltedPword);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Could not query to register a user.");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean emailExists(String email) {
        String SELECT = "SELECT COUNT(*) as count\n";
        String FROM = "FROM user as u\n";
        String WHERE = "WHERE u.email = ?";

        String query = SELECT + FROM + WHERE;

        try {
            PreparedStatement ps = dbUtil.getConnection().prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("count") == 1) {
                    return true;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean loginSuccess(String email, String password) {

        // Verify that the salted inputted password matches the salted stored password
        // in the database for this email.

        String SELECT = "SELECT salt, pword\n";
        String FROM = "FROM user as u\n";
        String WHERE = "WHERE u.email = ?";

        String query = SELECT + FROM + WHERE;

        Connection con = dbUtil.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                byte[] salt = Hex.decodeHex(rs.getString("salt"));
                char[] inputPassword = password.toCharArray();
                String saltedInputPassword = Security.encodePassword(inputPassword, salt);
                if (saltedInputPassword.equals(rs.getString("pword"))) {
                    return true;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}