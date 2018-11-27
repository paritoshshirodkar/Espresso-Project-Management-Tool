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
    
    
    // method to update the log for any changes in task
    public void updateLog(int task_id, String task_name, String deadline){
        
        String q = "INSERT INTO log VALUES (?,?,?,?);";
        try{
            
            PreparedStatement pst = connection.prepareStatement(q);
            pst.setInt(1, logCount()+1);
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
    
    
}
