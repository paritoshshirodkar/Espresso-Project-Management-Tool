package dao;
import java.io.IOException;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Paritosh
 */

public class EmployeeDAO {
    
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
    
    // method to add employee
    public void addEmployee(int employeeID, String employeeFirstName, String employeeLastName, String email, String password) throws IOException{
        String addEmployeeQuery = "insert into employee values (?,?,?,?,?)";
        try{
            PreparedStatement pst = connection.prepareStatement(addEmployeeQuery);
            pst.setInt(1, employeeID);
            pst.setString(2, employeeFirstName);
            pst.setString(3, employeeLastName);
            pst.setString(4, email);
            pst.setString(5, (String)password);
            int rows = pst.executeUpdate();
            
            pst.close();
            System.out.println(rows + " no. of row/s affected. Employee added successfully");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Employee added successfully");
            alert.showAndWait();
//            
//            // taking the user back to the login scene
//            Parent loginWindow = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//
//            // login scene
//            Scene scene = new Scene(loginWindow);
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setScene(scene);
//            stage.show();
        } catch(SQLException ex){
            System.out.println("SQLException in addEmployee()");
        }        
    }
    
    // method returns Employee object
    public Employee getEmployee(String username){
        String getEmployeeQuery = "select * from employee where email='" + username + "'";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(getEmployeeQuery);
            rs.next();
            Employee employee = new Employee(rs.getInt("employee_id"), rs.getString("employee_first_name"), rs.getString("employee_last_name"), rs.getString("email"), rs.getString("password"));
            rs.close();
            return employee;
        } catch (SQLException ex) {
            System.out.println("SQLException in getEmployee()");
        }
        return null;       
    }
    
    
    // method returns Employee object for Sign In purposes
    public Employee getEmployeeSignIn(String username){
        String getEmployeeQuery = "select * from employee where email='" + username + "'";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(getEmployeeQuery);
            rs.next();
            Employee employee = new Employee(rs.getInt("employee_id"), rs.getString("employee_first_name"), rs.getString("employee_last_name"), rs.getString("email"), rs.getString("password"));
            rs.close();
            return employee;
        } catch (SQLException ex) {
            Employee employeeSignIn = new Employee(0, "null", "null", "null", "null");
            return employeeSignIn;
        }
        //return null;       
    }
    
    
    
    
    
}
    
