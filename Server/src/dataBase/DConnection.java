package dataBase;

//import java.sql.Driver;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

import java.sql.DriverManager;

/**
 * @author Светлана
 */
class DConnection {

    private Connection connection;

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection Successed");
          /*  }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println("Connection Fail"+cnfe);
        }*/
            String url = "jdbc:mysql://localhost:3306/mysql";
            Driver myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
     /*  try {
           DriverManager.registerDriver(DriverManager.getDriver("com.mysql.jdbc.Driver"));
       }
       catch(Exception e){
           e.printStackTrace();
       }*/

            connection = (Connection) DriverManager.getConnection(url, "root", "root");
            System.out.println("Database connected");
        } catch (Exception se) {
            se.printStackTrace();
            System.out.println("No Database");
        }
        return connection;
    }
}
