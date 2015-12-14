package dataBase;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import request.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Светлана
 */
public class Authorizer {
    private final Connection connection;

    public Authorizer() {
        connection = new DConnection().connect();
    }

    public boolean register(User user) {
        String sq1 = "select * from users where login =?";
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sq1);
            String logindata = user.getName();
            ps.setString(1, logindata);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("User exist " + rs.getString("login"));
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sq = "INSERT INTO users VALUES ('" + user.getName() + "', '" + user.getPassword() + "' )";
        try {
            Statement ps = (Statement) connection.createStatement();

            ps.executeUpdate(sq);
            return true;

        } catch (SQLException e) {
            System.err.println(e);
        }
        return false;
    }

    //метод запускается только одним потоком, два и более одновременно работать не могут
    public boolean authorize(User user) {

        String sq1 = "select * from users where login =? and password =?";
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sq1);
            String passwordData = user.getPassword();
            String logindata = user.getName();
            ps.setString(1, logindata);
            ps.setString(2, passwordData);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("login"));
                System.out.println(rs.getString("password"));
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }

}
