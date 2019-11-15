
package communication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo Neves
 */
public class DBConnection {
    Connection connection;
    String url = "jdbc:postgresql://estga-dev.clients.ua.pt/ptda-2018-gr2";
    String user = "ptda-2018-gr2";
    String password = "=3zyzdZ$4%";
    String Query;
    
    
    public Connection dbConnection(){
        try {
            Class.forName("org.postgresl.Dirver");
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Ligacao efetuada!");
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro de ligacao");
            return null;
        }
    }
    public ResultSet dbQuery(String query) throws SQLException{
        DBConnection dbconnection = new DBConnection();
        Connection conn = dbconnection.dbConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery(query);
        return rs;
    }
    
    public void dbJoin(String join) throws SQLException{
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.dbConnection();
        PreparedStatement preparedStmt = conn.prepareStatement(join);
        preparedStmt.execute();
    }
    
    public void dbUpdate(String update) throws SQLException{
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.dbConnection();
        PreparedStatement preparedStmt = conn.prepareStatement(update);
        preparedStmt.execute();
    }
    
    public void dbDelete(String delete) throws SQLException{
        DBConnection dBConnection = new DBConnection();
        Connection conn = dBConnection.dbConnection();
        PreparedStatement preparedStmt = conn.prepareStatement(delete);
        preparedStmt.execute();
    }
}
