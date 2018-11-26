package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author Paritosh
 */
public class QuestionDAO {
    
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
    
    
    // method to count the number of Questions in the database
    public int questionCount(){

        int count = 0;
        QuestionDAO qdao = new QuestionDAO();
        qdao.connect();
        try{
            String questionCountQuery = "SELECT COUNT(*) FROM question;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(questionCountQuery);
            rs.next();
            count = rs.getInt(1);
            qdao.closeConnection();
            return count;
        } catch (SQLException ex) {
            qdao.closeConnection();
            System.out.println("SQLException in questionCount()");
        }
        return count;
    }
    
    
    // method to add a question
    public void addQuestion(int questionID, String boardName, String question) throws IOException{
        String addQuestionQuery = "insert into question values (?,?,?)";
        try{
            PreparedStatement pst = connection.prepareStatement(addQuestionQuery);
            pst.setInt(1, questionID);
            pst.setString(2, boardName);
            pst.setString(3, question);
            int rows = pst.executeUpdate();
            
            pst.close();
            System.out.println(rows + " no. of row/s affected. Question added successfully");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Question added successfully");
            alert.showAndWait();
        } catch(SQLException ex){
            System.out.println("SQLException in addQuestion()");
        }        
    }
    
}
