package espresso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 *
 * @author Paritosh
 */
public class Answer {

    private final SimpleIntegerProperty answerID;
    private final SimpleIntegerProperty qID;
    private final SimpleStringProperty answer;
    private final SimpleIntegerProperty employeeID;
    private final SimpleIntegerProperty upvotes;
    private final SimpleIntegerProperty downvotes;

    public Answer(int answerID, int qID, String answer, int employeeID, int upvotes, int downvotes) {

        this.answerID = new SimpleIntegerProperty(answerID);
        this.qID = new SimpleIntegerProperty(qID);
        this.answer = new SimpleStringProperty(answer);
        this.employeeID = new SimpleIntegerProperty(employeeID);
        this.upvotes = new SimpleIntegerProperty(upvotes);
        this.downvotes = new SimpleIntegerProperty(downvotes);

    }

    public int getAnswerID() {
        return answerID.get();
    }

    public int getqID() {
        return qID.get();
    }

    public String getAnswer() {
        return answer.get();
    }

    public int getEmployeeID() {
        return employeeID.get();
    }

    public int getUpvotes() {
        return upvotes.get();
    }

    public int getDownvotes() {
        return downvotes.get();
    }
    
    
    // method to load the data into the table from the database
    @FXML
    public static ObservableList<Answer> loadAnswerTable() {

        // List to store the Answer
        ObservableList<Answer> answerList = FXCollections.observableArrayList();

        try {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException in loadAnswerTable()");
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmt", "root", "");
            } catch (SQLException ex) {
                System.out.println("SQLException in  loadAnswerTable() while loading driver");
            }
            String query = "SELECT * FROM answer;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                int answerID = rs.getInt("answer_id");
                int questionID = rs.getInt("q_id");
                String answer  = rs.getString("answer");
                int employeeID = rs.getInt("employee_id");
                int upvotes = rs.getInt("up_votes");
                int downvotes = rs.getInt("down_votes");
                // adding the Question to the questionList
                answerList.add(new Answer(answerID, questionID, answer, employeeID, upvotes, downvotes));
                

            }
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException in loadAnswerTable() while closing connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException in loadAnswerTable()");
        }

        return answerList;
    }

    // method to load the data into the table from the database for the given qID
    @FXML
    public static ObservableList<Answer> loadAnswerTable(int qID) {

        // List to store the Answer
        ObservableList<Answer> answerList = FXCollections.observableArrayList();

        try {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException in loadAnswerTable(int qID)");
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmt", "root", "");
            } catch (SQLException ex) {
                System.out.println("SQLException in  loadAnswerTable(int qID) while loading driver");
            }
            
            String query = "SELECT * FROM answer WHERE q_id=" + qID + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                int answerID = rs.getInt("answer_id");
                int questionID = rs.getInt("q_id");
                String answer  = rs.getString("answer");
                int employeeID = rs.getInt("employee_id");     
                int upvotes = rs.getInt("up_votes");
                int downvotes = rs.getInt("down_votes");
                // adding the Question to the questionList
                answerList.add(new Answer(answerID, questionID, answer, employeeID, upvotes, downvotes));

            }
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException in loadAnswerTable(int qID) while closing connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException in loadAnswerTable(int qID)");
        }

        return answerList;
    }

}
