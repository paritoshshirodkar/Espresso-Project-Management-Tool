package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        
        
    // method to update Task
        
    public void updateTask(int task_id, String board_name, String task_name, String employee_first_name, int status_id, int changed, int priority_id, int weightage, String deadline){
        int rows=0;
//        String query = "UPDATE `task` SET `task_id`=" + task_id + 
//                ",`board_name`=" + board_name + 
//                ",`task_name`=" + task_name + 
//                ",`employee_first_name`=" + employee_first_name + 
//                ",`status_id`=" + status_id +
//                ",`changed`=" + 1 +
//                ",`priority_id`=" + priority_id +
//                ",`weightage`=" + weightage + 
//                ",`deadline`=" + deadline +  
//                "WHERE task_id=" + task_id + ";";
           String query = "UPDATE `task` SET `board_name`= ?,`task_name`= ?,`employee_first_name`= ?,`status_id`= ?,`changed`= ?,`priority_id`= ?,`weightage`= ?,`deadline`= ? WHERE `task_id`= ? ;";
        
        System.out.println(query);
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, board_name);
            pst.setString(2, task_name);
            pst.setString(3, employee_first_name);
            pst.setInt(4, status_id);
            pst.setInt(5, changed);
            pst.setInt(6, priority_id);
            pst.setInt(7, weightage);
            pst.setString(8, deadline);
            pst.setInt(9, task_id);
           
            rows = pst.executeUpdate();
            pst.close();
            System.out.println(rows + " no. of row/s affected. Task updated successfully");
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Task updated successfully");
            alert.showAndWait();
            LogDAO ldao = new LogDAO();
            ldao.connect();
            ldao.updateLog(task_id,task_name,deadline);
            ldao.closeConnection();
        } catch(SQLException ex){
            System.out.println("SQLException in updateTask()");
        }   
     
    }
    
    
    
        
        
        
        
        
        
        
        
    // method returns Task names
    public ArrayList<String> getTaskNames(String boardName){
        
        ArrayList<String> taskNames = new ArrayList<String>();
        String query = "select task_name from task where board_name='" + boardName + "';";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                taskNames.add(rs.getString("task_name"));
            }
            
            return taskNames;
        } catch (SQLException ex) {
            System.out.println("SQLException in getTaskNames(String boardName)");
        }
        return taskNames;       
    }
    
    
    // method returns Task weightage
    public ArrayList<Integer> getTaskWeightage(String boardName){
        
        ArrayList<Integer> taskWeightage = new ArrayList<Integer>();
        String query = "select weightage from task where board_name='" + boardName + "';";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                taskWeightage.add(rs.getInt("weightage"));
            }
            
            return taskWeightage;
        } catch (SQLException ex) {
            System.out.println("SQLException in getTaskWeightage(String boardName)");
        }
        return taskWeightage;       
    }
    
    
    // method to count the number of tasks
    public int taskCount() {

        int count = 0;
        TaskDAO tdao = new TaskDAO();
        tdao.connect();
        try {
            String query = "SELECT COUNT(*) FROM task;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
            tdao.closeConnection();
            return count;
        } catch (SQLException ex) {
            tdao.closeConnection();
            System.out.println("SQLException in taskCount()");
        }
        return count;
    }
    
    
    
}
