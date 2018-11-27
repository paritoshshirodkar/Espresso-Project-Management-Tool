package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Paritosh
 */
public class LogDAO {
    
    Connection connection = null;
    
    // method to create a database connection
    
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException in connect()");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmt", "root", "");
        } catch (SQLException ex) {
            System.out.println("SQLException in  connect()");
        }
    
    }
    
    // method to close database connection
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException in closeConnection()");
        }
    }
    
    
    // method to count the number of logs
    public int logCount() {

        int count = 0;
        LogDAO ldao = new LogDAO();
        ldao.connect();
        try {
            String query = "SELECT COUNT(*) FROM log;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
            ldao.closeConnection();
            return count;
        } catch (SQLException ex) {
            ldao.closeConnection();
            System.out.println("SQLException in logCount()");
        }
        return count;
    }
    
    
    
    // method to get the highest log value from log
    public int highestlogID() {

        int count = 0;
        LogDAO ldao = new LogDAO();
        ldao.connect();
        try {
            String query = "SELECT log_id FROM log ORDER BY log_id DESC;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
            ldao.closeConnection();
            return count;
        } catch (SQLException ex) {
            ldao.closeConnection();
            System.out.println("SQLException in highestlogID()");
        }
        return count;
    }
    
    
    
      // method to get the highest log value from log_user
    public int highestUserlogID() {

        int count = 0;
        LogDAO ldao = new LogDAO();
        ldao.connect();
        try {
            String query = "SELECT log_id FROM log_user ORDER BY log_id DESC;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
            ldao.closeConnection();
            return count;
        } catch (SQLException ex) {
            ldao.closeConnection();
            System.out.println("SQLException in highestUserlogID()");
        }
        return count;
    }
    
    
    
    
    // method to update the log for any changes in task
    public void updateLog(int task_id, String task_name, String deadline){
        
        String q = "INSERT INTO log VALUES (?,?,?,?);";
        try{
            
            PreparedStatement pst = connection.prepareStatement(q);
            pst.setInt(1, highestlogID()+1);
            pst.setInt(2,task_id);
            String msg = task_name + " updated";
            //pst.setString(3,"log generated");
            pst.setString(3, msg);
            pst.setString(4,deadline);
            pst.execute();
            pst.close();
            
            //System.out.println(rows2 + " no. of log generated");
        }catch(SQLException ex){
            System.out.println("SQLException in updateLog(int task_id, String task_name, String deadline)");
            
        }
    
    }
    
        // method to update the log_user for any changes in task
    public void updateUserLog(int task_id, String task_name, String deadline){
        
        String q = "INSERT INTO log_user VALUES (?,?,?,?);";
        try{
            
            PreparedStatement pst = connection.prepareStatement(q);
            pst.setInt(1, highestUserlogID()+1);
            pst.setInt(2,task_id);
            String msg = task_name + " updated";
            //pst.setString(3,"log generated");
            pst.setString(3, msg);
            pst.setString(4,deadline);
            pst.execute();
            pst.close();
            
            //System.out.println(rows2 + " no. of log generated");
        }catch(SQLException ex){
            System.out.println("SQLException in updateUserLog(int task_id, String task_name, String deadline)");
            
        }
    
    }
    
    
    
    // method to remove a Log
    public int removeLog(int logID) {

        int count = 0;
        LogDAO ldao = new LogDAO();
        ldao.connect();
        try {
            String query = "DELETE FROM log_user WHERE log_id=(?);";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, logID+1);
            count = pst.executeUpdate();
            ldao.closeConnection();
            return count;
        } catch (SQLException ex) {
            ldao.closeConnection();
            System.out.println("SQLException in removeLog(int logID)");
        }
        return count;

    }
    
}
