package espresso;

import dao.BoardDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

/**
 * FXML Controller class
 *
 * @author Paritosh
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Button username;

    @FXML
    private MenuItem accountMenuItem;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    private Button createABoardButton;

    @FXML
    private Button boardsButton;

    @FXML
    private AnchorPane dashboardAnchorPane;
    
    @FXML
    private Button qandaButton;

    @FXML
    private Button deleteBoardButton;

    @FXML
    private Button viewTaskButton;

    @FXML
    private Button createTaskButton;
    
    @FXML
    private HBox boardOptions;

    
    // Board Table FXML
    
    @FXML
    private TableView<Board> boardTableView;
    @FXML
    private TableColumn<Board, Integer> boardIDColumn;
    @FXML
    private TableColumn<Board, String> boardNameColumn;
    @FXML
    private TableColumn<Board, Integer> projectLeadColumn;
    
  
    
    // List to store all the boards 
    ObservableList<Board> boardList = FXCollections.observableArrayList();

    // List to store all the selected boards
    ObservableList<Board> selectedBoardList = FXCollections.observableArrayList();
    
    

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //method to initiate columns
        initColumns();
    }
    
    
    private void initColumns() {
        boardIDColumn.setCellValueFactory(new PropertyValueFactory<>("boardID"));
        boardNameColumn.setCellValueFactory(new PropertyValueFactory<>("boardName"));
        projectLeadColumn.setCellValueFactory(new PropertyValueFactory<>("projectLead"));
    }

    
    
    @FXML
    private void boardNameBox(ActionEvent event) {
        loadWindow("AddBoard.fxml", "Create a new Board");
        // to refresh the table
        //not working
        loadDataBoards();
        System.out.println("Board Table updated");
    }


    // method to load the data into the table from the database
    @FXML
    public void loadDataBoards() {

        // List to store the board names and project lead emails
        ObservableList<Board> boardList = FXCollections.observableArrayList();
  
        try {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException in loadData()");
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmt", "root", "");
            } catch (SQLException ex) {
                System.out.println("SQLException in  loadData() while loading driver");
            }
            String boardListQuery = "SELECT * FROM board;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(boardListQuery);
            while (rs.next()) {
                int boardID = rs.getInt("board_id");
                String boardName = rs.getString("board_name");
                int projectLeadID = rs.getInt("project_lead");

                // adding the Board to the boardList
                boardList.add(new Board(boardID, boardName, projectLeadID));

            }
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException in loadData() while closing connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException in loadData()");
        }

        boardTableView.getItems().setAll(boardList);
        System.out.println("Board list displayed");
        boardOptions.setVisible(true);
        System.out.println("Board options displayed");

    }

    // creating Board class
    public static class Board {

        private final SimpleIntegerProperty boardID;
        private final SimpleStringProperty boardName;
        private final SimpleIntegerProperty projectLead;

        public Board(int boardID, String boardName, int projectLead) {
            this.boardID = new SimpleIntegerProperty(boardID);
            this.boardName = new SimpleStringProperty(boardName);
            this.projectLead = new SimpleIntegerProperty(projectLead);
        }

        public int getBoardID() {
            return boardID.get();
        }

        public String getBoardName() {
            return boardName.get();
        }

        public int getProjectLead() {
            return projectLead.get();
        }

    }
    
    
    @FXML
    void viewTask(ActionEvent event) {
        selectedBoardList = boardTableView.getSelectionModel().getSelectedItems();
           
        
        System.out.println(selectedBoardList.size());
        String board_name = selectedBoardList.get(0).boardName.getValue();
        System.out.println(board_name + " board selected");
        //loadWindowTask("TaskTable.fxml", "paritosh", board_name);
        loadWindow("TaskTable.fxml", board_name);
        
    }
    
    @FXML
    void createNewTask(ActionEvent event) {
        System.out.println(event.getTarget().toString());
        loadWindow("AddTask.fxml", "Create new Task");
    }
    
    @FXML
    private void showQuestions(ActionEvent event) {
        loadWindow("QuestionTable.fxml", "Questions");
        System.out.println("Question Table displayed");
    }      
    
    
    
    // method for generating windows dynamically
    public void loadWindow(String loc, String title) {
        try {
            System.out.println(title + "created");
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    // method for generating windows dynamically
    public void loadWindowTask(String loc, String title, String boardName) {
        try {
            System.out.println(title + " created");
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    
    
    @FXML
    void deleteBoard(ActionEvent event) {        
        boardList = boardTableView.getItems();
        selectedBoardList = boardTableView.getSelectionModel().getSelectedItems();
        System.out.println("Deleted Board/s");
        for(int i = 0; i<selectedBoardList.size(); i++){
            System.out.println(" " + selectedBoardList.get(i).getBoardID() + "\t" + selectedBoardList.get(i).getBoardName() + "\t" + selectedBoardList.get(i).getProjectLead());
        }
        selectedBoardList.forEach(boardList::remove);
        BoardDAO bdao = new BoardDAO();
        bdao.connect();
        bdao.removeBoard(selectedBoardList.get(0).getBoardID());
        bdao.closeConnection();
    }
    
}
