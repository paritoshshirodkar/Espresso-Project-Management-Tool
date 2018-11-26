package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Paritosh
 */
public class TaskDAO {
    
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
    
    // method to add task
    
        public void addTask(int taskID, String boardName, String taskName, String employeeFirstName, int statusID, int changed, int priorityID, int weightage, String deadline) throws IOException{
        String addTaskQuery = "insert into task values (?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst = connection.prepareStatement(addTaskQuery);
            pst.setInt(1, taskID);
            pst.setString(2, boardName);
            pst.setString(3, taskName);
            pst.setString(4, employeeFirstName);
            pst.setInt(5, statusID);
            pst.setInt(6, changed);
            pst.setInt(7, priorityID);
            pst.setInt(8, weightage);
            pst.setString(9, deadline);
            int rows = pst.executeUpdate();
            
            
            pst.close();
            System.out.println(rows + " no. of row/s affected. Task added successfully");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Task added successfully");
            alert.showAndWait();
            
        } catch(SQLException ex){
            System.out.println("SQLException in addTask()");
        }        
    }
    
    
    
    
}
