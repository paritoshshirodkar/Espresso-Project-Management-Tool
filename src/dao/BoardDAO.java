package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Paritosh
 */
public class BoardDAO {
    
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
    
    
    // method to count the number of Boards
    public int boardCount() {

        int count = 0;
        BoardDAO bdao = new BoardDAO();
        bdao.connect();
        try {
            String boardCountQuery = "SELECT COUNT(*) FROM board;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(boardCountQuery);
            rs.next();
            count = rs.getInt(1);
            bdao.closeConnection();
            return count;
        } catch (SQLException ex) {
            bdao.closeConnection();
            System.out.println("SQLException in boardCount()");
        }
        return count;
    }
    
    
    // method to add a board
    public void addBoard(int boardID, String boardName, int employeeID) throws IOException{
        String addBoardQuery = "insert into board values (?,?,?)";
        try{
            PreparedStatement pst = connection.prepareStatement(addBoardQuery);
            pst.setInt(1, boardID);
            pst.setString(2, boardName);
            pst.setInt(3, employeeID);
            int rows = pst.executeUpdate();
            
            pst.close();
            System.out.println(rows + " no. of row/s affected. Board added successfully");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Board added successfully");
            alert.showAndWait();
        } catch(SQLException ex){
            System.out.println("SQLException in addBoard()");
        }        
    }
    
    // method to remove a board
    public int removeBoard(int boardID) {

        int count = 0;
        BoardDAO bdao = new BoardDAO();
        bdao.connect();
        try {
            String removeBoardQuery = "DELETE FROM board WHERE board.board_id=(?);";
            PreparedStatement pst = connection.prepareStatement(removeBoardQuery);
            pst.setInt(1, boardID+1);
            count = pst.executeUpdate();
            bdao.closeConnection();
            return count;
        } catch (SQLException ex) {
            bdao.closeConnection();
            System.out.println("SQLException in removeBoard(int boardID)");
        }
        return count;

    }
    

}
