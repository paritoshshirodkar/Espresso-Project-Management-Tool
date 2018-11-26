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
public class Question {

    private final SimpleIntegerProperty questonID;
    private final SimpleStringProperty question;
    private final SimpleStringProperty boardName;

    public Question(int questonID, String question, String boardName) {
        this.questonID = new SimpleIntegerProperty(questonID);
        this.question = new SimpleStringProperty(question);
        this.boardName = new SimpleStringProperty(boardName);
    }

    public int getQuestonID() {
        return questonID.get();
    }

    public String getQuestion() {
        return question.get();
    }

    public String getBoardName() {
        return boardName.get();
    }
    
    
    // method to load the data into the table from the database
    @FXML
    public static ObservableList<Question> loadQuestionTable() {

        // List to store the questions
        ObservableList<Question> questionList = FXCollections.observableArrayList();
  
        try {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException in loadQuestionTable()");
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmt", "root", "");
            } catch (SQLException ex) {
                System.out.println("SQLException in  loadQuestionTable() while loading driver");
            }
            String questionListQuery = "SELECT * FROM question;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(questionListQuery);
            while (rs.next()) {
                int questionID = rs.getInt("q_id");
                String question = rs.getString("question");
                String boardName = rs.getString("board_name");
                

                // adding the Question to the questionList
                questionList.add(new Question(questionID, question, boardName));

            }
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException in loadQuestionTable() while closing connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException in loadQuestionTable()");
        }
        
        return questionList;
    }
    
    
    // method to load the data into the table from the database for the given bn (board name)
    @FXML
    public static ObservableList<Question> loadQuestionTable(String bn) {

        // List to store the questions
        ObservableList<Question> questionList = FXCollections.observableArrayList();
  
        try {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException in loadQuestionTable()");
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmt", "root", "");
            } catch (SQLException ex) {
                System.out.println("SQLException in  loadQuestionTable() while loading driver");
            }
            String questionListQuery = "SELECT * FROM question WHERE board_name=" + bn + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(questionListQuery);
            while (rs.next()) {
                int questionID = rs.getInt("q_id");
                String question = rs.getString("question");
                String boardName = rs.getString("board_name");
                

                // adding the Question to the questionList
                questionList.add(new Question(questionID, question, boardName));

            }
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException in loadQuestionTable() while closing connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException in loadQuestionTable()");
        }
        
        return questionList;
    }

}
