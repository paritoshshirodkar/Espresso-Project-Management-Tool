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
public class AnswerDAO {
    
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
    
    
    // method to count the number of Answers
    public int answerCount() {

        int count = 0;
        AnswerDAO adao = new AnswerDAO();
        adao.connect();
        try {
            String query = "SELECT COUNT(*) FROM answer;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
            adao.closeConnection();
            return count;
        } catch (SQLException ex) {
            adao.closeConnection();
            System.out.println("SQLException in answerCount()");
        }
        return count;
    }
    
    
    public int answerCount(int qID) {

        int count = 0;
        AnswerDAO adao = new AnswerDAO();
        adao.connect();
        try {
            String query = "SELECT COUNT(*) FROM answer WHERE q_id=" + qID + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
            adao.closeConnection();
            return count;
        } catch (SQLException ex) {
            adao.closeConnection();
            System.out.println("SQLException in answerCount(in qID)");
        }
        return count;
    }
    
    
    // method to add a Answer
    public void addAnswer(int answerID, int qID, String answer, int employeeID, int upvotes, int downvotes) throws IOException {
        String query = "insert into answer values (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, answerID);
            pst.setInt(2, qID);
            pst.setString(3, answer);
            pst.setInt(4, employeeID);
            pst.setInt(5, upvotes);
            pst.setInt(6, downvotes);
            int rows = pst.executeUpdate();

            pst.close();
            System.out.println(rows + " no. of row/s affected. Answer added successfully");
        } catch (SQLException ex) {
            System.out.println("SQLException in addAnswer()");
        }
    }
    
    // method to get the answers for the respective questionID
    public int getAnswers(int qID) {

        int count = 0;
        AnswerDAO adao = new AnswerDAO();
        adao.connect();
        try {
            String query = "SELECT * FROM answer WHERE q_id=" + qID + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
            adao.closeConnection();
            return count;
        } catch (SQLException ex) {
            adao.closeConnection();
            System.out.println("SQLException in answerCount()");
        }
        return count;
    } 
    
    // method to get the number of up_votes
    public int getUpVotes(int answerID){
        int upvotes = 0;
        AnswerDAO adao = new AnswerDAO();
        adao.connect();
        try {
            String query = "SELECT * FROM answer WHERE answer_id=" + answerID + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            upvotes = rs.getInt("up_votes");
            System.out.println(upvotes);
            adao.closeConnection();
            return upvotes;
        } catch (SQLException ex) {
            adao.closeConnection();
            System.out.println("SQLException in getUpVotes(int answerID)");
        }
        return upvotes;
    }


    // method to get the number of down_votes
    public int getDownVotes(int answerID){
        int downvotes = 0;
        AnswerDAO adao = new AnswerDAO();
        adao.connect();
        try {
            String query = "SELECT * FROM answer WHERE answer_id=" + answerID + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            downvotes = rs.getInt("down_votes");
            adao.closeConnection();
            return downvotes;
        } catch (SQLException ex) {
            adao.closeConnection();
            System.out.println("SQLException in getDownVotes(int answerID)");
        }
        return downvotes;
    }

    
      
    // method to update the number of up_votes
    public void updateUpVotes(int answerID, int upvotes){
        
        int count=0;
        String q = "UPDATE answer SET up_votes=" + upvotes + " WHERE answer_id=" + answerID +";";
        try{
           PreparedStatement pst = connection.prepareStatement(q);
           count = pst.executeUpdate();
           pst.close();
           System.out.println(count + " no. of row/s affected. up_votes added successfully");
        }catch(SQLException ex){
            System.out.println("SQLException in updateUpVotes(int answerID, int upvotes)");
        }
        
    }
    
    
    // method to update the number of down_votes
    public void updateDownVotes(int answerID, int downvotes){
        
        int count=0;
        String q = "UPDATE answer SET down_votes=" + downvotes + " WHERE answer_id=" + answerID +";";
        try{
           PreparedStatement pst = connection.prepareStatement(q);
           count = pst.executeUpdate();
           pst.close();
           System.out.println(count + " no. of row/s affected. down_votes added successfully");
        }catch(SQLException ex){
            System.out.println("SQLException in updateDownVotes(int answerID, int downvotes)");
        }
        
    }
  
    
}
