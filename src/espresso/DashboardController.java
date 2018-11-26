package espresso;

import dao.AnswerDAO;
import dao.BoardDAO;
import dao.Employee;
import dao.EmployeeDAO;
import dao.QuestionDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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

    @FXML
    private Button inboxButton;
    @FXML
    private Button myTaskButton;
    @FXML
    private Button analysisButton;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private AnchorPane inboxAnchorPane;
    @FXML
    private AnchorPane addBoardPane;
    @FXML
    private TextField boardName;
    @FXML
    private TextField projectLeadEmail;
    @FXML
    private Label projectLeadValidationLabel;
    @FXML
    private Button createBoardButton;
    @FXML
    private AnchorPane boardsAnchorPane;
    @FXML
    private AnchorPane myTaskAnchorPane;
    @FXML
    private AnchorPane questionTableAnchorPane;
    @FXML
    private TableView<Question> questionTableView;
    @FXML
    private TableColumn<Question, Integer> questionIDColumn;
    @FXML
    private TableColumn<Question, String> questionColumn;
    @FXML
    private TableColumn<Question, String> qboardNameColumn;
    @FXML
    private Button lookAtAnswersButton;
    @FXML
    private HBox questionOptions;
    @FXML
    private Button postQuestionButton;
    @FXML
    private Button answerButton;
    @FXML
    private AnchorPane analysisAnchorPane;
    
    @FXML
    private AnchorPane taskAnchorPane;
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn<Task, Integer> taskIdColumn;
    @FXML
    private TableColumn<Task, String> tboardNameColumn;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TableColumn<Task, String> employeeNameColumn;
    @FXML
    private TableColumn<Task, Integer> statusColumn;
    @FXML
    private TableColumn<Task, Integer> priorityColumn;
    @FXML
    private TableColumn<Task, Integer> weightageColumn;
    @FXML
    private TableColumn<Task, String> deadlineColumn;
    @FXML
    private AnchorPane postQuestionAnchorPane;
    @FXML
    private TextField boardNameTextField;
    @FXML
    private TextArea postQuestionTextArea;
    @FXML
    private Button postButton;
    @FXML
    private Button cancelButton;
    @FXML
    private AnchorPane answerTableAnchorPane;
    @FXML
    private TableView<Answer> answerTableView;
    @FXML
    private TableColumn<Answer, Integer> answerIDColumn;
    @FXML
    private TableColumn<Answer, Integer> aquestionIDColumn;
    @FXML
    private TableColumn<Answer, String> answerColumn;
    @FXML
    private TableColumn<Answer, Integer> employeeIDColumn;
    @FXML
    private TableColumn<Answer, Integer> upvotesColumn;
    @FXML
    private TableColumn<Answer, Integer> downvotesColumn;
    @FXML
    private HBox answerTableOptions;
    @FXML
    private Button viewAnswerButton;
    @FXML
    private Button upvoteButton;
    @FXML
    private Button downvoteButton;
    
    
    
    // List to store all the boards 
    ObservableList<Board> boardList = FXCollections.observableArrayList();

    // List to store all the selected boards
    ObservableList<Board> selectedBoardList = FXCollections.observableArrayList();

    // List to store all the Questions 
    ObservableList<Question> questionList = FXCollections.observableArrayList();

    // List to store all the selected Questions
    ObservableList<Question> selectedQuestionsList = FXCollections.observableArrayList();
    
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

    private void initTaskColumns() {
        taskIdColumn.setCellValueFactory(new PropertyValueFactory<>("taskID"));
        tboardNameColumn.setCellValueFactory(new PropertyValueFactory<>("boardName"));
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusID"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priorityID"));
        weightageColumn.setCellValueFactory(new PropertyValueFactory<>("Weightage"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("priorityID"));
    }

    private void initQuestionTableColumn() {
        questionIDColumn.setCellValueFactory(new PropertyValueFactory<>("questonID"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        qboardNameColumn.setCellValueFactory(new PropertyValueFactory<>("boardName"));

        questionTableView.getItems().setAll(Question.loadQuestionTable());
        questionOptions.setVisible(true);
        
    }
    
    private void initAnswerTableColumn(){
        answerIDColumn.setCellValueFactory(new PropertyValueFactory<>("answerID"));
        aquestionIDColumn.setCellValueFactory(new PropertyValueFactory<>("qID"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        upvotesColumn.setCellValueFactory(new PropertyValueFactory<>("upvotes"));
        downvotesColumn.setCellValueFactory(new PropertyValueFactory<>("downvotes"));
        
        answerTableView.getItems().setAll(Answer.loadAnswerTable());
        answerTableOptions.setVisible(true);
        System.out.println("Answer options displayed");
        
    }

    private void boardNameBox(ActionEvent event) {
        loadWindow("AddBoard.fxml", "Create a new Board");
        // to refresh the table
        //not working
        loadDataBoards();
        System.out.println("Board Table updated");
    }

    // method to load the data into the table from the database
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

    // method to load the data into the table from the database
    public void loadDataTasks() {

        // List to store the tasks
        ObservableList<Task> taskList = FXCollections.observableArrayList();

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
            String taskListQuery = "SELECT * FROM task;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(taskListQuery);
            while (rs.next()) {
                int taskID = rs.getInt("task_id");
                String boardName = rs.getString("board_name");
                String taskName = rs.getString("task_name");
                String employeeName = rs.getString("employee_first_name");
                int statusID = rs.getInt("status_id");
                int priorityID = rs.getInt("priority_id");
                int weightage = rs.getInt("weightage");
                String deadline = rs.getString("deadline");

                taskList.add(new Task(taskID, boardName, taskName, employeeName, statusID, priorityID, weightage, deadline));

            }
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException in loadData() while closing connection");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException in loadData()");
        }

        taskTableView.getItems().setAll(taskList);
        System.out.println("Task list displayed");

    }

    // method to decide which AnchorPane is to be loaded
    @FXML
    private void handleSidePanelButtonClick(ActionEvent event) {
        if (event.getSource() == inboxButton) {
            inboxAnchorPane.toFront();
            inboxAnchorPane.setVisible(true);

        }
        if (event.getSource() == createABoardButton) {
            addBoardPane.toFront();
            addBoardPane.setVisible(true);

        }
        if (event.getSource() == boardsButton) {
            loadDataBoards();
            boardsAnchorPane.toFront();
            boardsAnchorPane.setVisible(true);

        }
        if (event.getSource() == myTaskButton) {
            loadDataTasks();
            myTaskAnchorPane.toFront();
            myTaskAnchorPane.setVisible(true);
        }
        if (event.getSource() == qandaButton) {
            questionTableAnchorPane.toFront();
            initQuestionTableColumn();
            questionTableAnchorPane.setVisible(true);
            questionTableView.setVisible(true);

        }
        if (event.getSource() == analysisButton) {
            analysisAnchorPane.toFront();
            analysisAnchorPane.setVisible(true);
        }

    }

    @FXML
    private void createBoard(ActionEvent event) throws IOException {
        projectLeadValidationLabel.setVisible(false);
        if (projectLeadExists(projectLeadEmail.getText())) {
            EmployeeDAO edao = new EmployeeDAO();
            edao.connect();
            Employee projectLead = edao.getEmployee(projectLeadEmail.getText());
            edao.closeConnection();
            BoardDAO bdao = new BoardDAO();
            bdao.connect();
            int boardCount = bdao.boardCount();
            System.out.println("Currently there are " + boardCount + " board/s in the database");
            bdao.addBoard(++boardCount, boardName.getText(), projectLead.getEmployeeID());
            bdao.closeConnection();
            loadDataBoards();

//            // code to close addBoardPane
//            Stage stage = (Stage) addBoardPane.getScene().getWindow();
//            stage.close();
        } else {
            projectLeadValidationLabel.setText("The entered email is invalid. Please re-enter the email.");
            projectLeadValidationLabel.setVisible(true);
        }

    }

    private boolean projectLeadExists(String projectLeadEmail) {
        boolean exists = false;
        EmployeeDAO edao = new EmployeeDAO();
        edao.connect();
        Employee projectLead = edao.getEmployee(projectLeadEmail);
        edao.closeConnection();
        if (projectLeadEmail.equals(projectLead.getEmail())) {
            exists = true;
            return exists;
        }
        return exists;

    }

    @FXML
    private void postQuestion(ActionEvent event) {
        postQuestionAnchorPane.toFront();
        postQuestionAnchorPane.setVisible(true);

    }

    @FXML
    private void showAnswers(ActionEvent event) {
        selectedQuestionsList = questionTableView.getSelectionModel().getSelectedItems();
        int selectedQID = selectedQuestionsList.get(0).getQuestonID();
        System.out.println(selectedQID);
        
//        AnswerDAO adao = new AnswerDAO();
//        adao.connect();
//        System.out.println(adao.answerCount(selectedQID));
        
        //answerTableView.getItems().setAll(Answer.loadAnswerTable(selectedQID));
//        System.out.println();
        answerTableView.getItems().setAll(Answer.loadAnswerTable());
        answerTableAnchorPane.toFront();
        answerTableAnchorPane.setVisible(true);
        answerTableView.setVisible(true);
        answerTableOptions.setVisible(true);

    }

    @FXML
    private void answer(ActionEvent event) {
    }

    @FXML
    private void cancelQuestion(ActionEvent event) {
        boardNameTextField.clear();
        postQuestionTextArea.clear();
    }

    @FXML
    private void saveQuestion(ActionEvent event) throws IOException {
        QuestionDAO qdao = new QuestionDAO();
        qdao.connect();
        int numQuestionsDB = qdao.questionCount();
        qdao.addQuestion(++numQuestionsDB, boardNameTextField.getText(), postQuestionTextArea.getText());
        qdao.closeConnection();
        initQuestionTableColumn();
        questionTableAnchorPane.toFront();
        questionTableAnchorPane.setVisible(true);

    }

    @FXML
    private void viewAnswer(ActionEvent event) {
        selectedQuestionsList = questionTableView.getSelectionModel().getSelectedItems();
        System.out.println(selectedQuestionsList.get(0).getQuestonID());
    }

    @FXML
    private void upvote(ActionEvent event) {
    }

    @FXML
    private void downvote(ActionEvent event) {
    }

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

    // creating class Task
    public static class Task {

        private final SimpleIntegerProperty taskID;
        private final SimpleStringProperty boardName;
        private final SimpleStringProperty taskName;
        private final SimpleStringProperty employeeName;
        private final SimpleIntegerProperty statusID;
        private final SimpleIntegerProperty priorityID;
        private final SimpleIntegerProperty weightage;
        private final SimpleStringProperty deadline;

        public Task(int taskID, String boardName, String taskName, String employeeName, int statusID, int priorityID, int weightage, String deadline) {
            this.taskID = new SimpleIntegerProperty(taskID);
            this.boardName = new SimpleStringProperty(boardName);
            this.taskName = new SimpleStringProperty(taskName);
            this.employeeName = new SimpleStringProperty(employeeName);
            this.statusID = new SimpleIntegerProperty(statusID);
            this.priorityID = new SimpleIntegerProperty(priorityID);
            this.weightage = new SimpleIntegerProperty(weightage);
            this.deadline = new SimpleStringProperty(deadline);

        }

        public int getTaskID() {
            return taskID.get();
        }

        public String getBoardName() {
            return boardName.get();
        }

        public String getTaskName() {
            return taskName.get();
        }

        public String getEmployeeName() {
            return employeeName.get();
        }

        public int getStatusID() {
            return statusID.get();
        }

        public int getPriorityID() {
            return priorityID.get();
        }

        public int getWeightage() {
            return weightage.get();
        }

        public String getDeadline() {
            return deadline.get();
        }
    }

    @FXML
    void viewTask(ActionEvent event) {
        selectedBoardList = boardTableView.getSelectionModel().getSelectedItems();

        System.out.println(selectedBoardList.size());
        String board_name = selectedBoardList.get(0).boardName.getValue();
        System.out.println(board_name + " board selected");
        //loadWindowTask("TaskTable.fxml", "paritosh", board_name);
        //loadWindow("TaskTable.fxml", board_name);
        taskAnchorPane.toFront();
        initTaskColumns();
        loadDataTasks();
        taskAnchorPane.setVisible(true);

    }

    @FXML
    void createNewTask(ActionEvent event) {
        System.out.println(event.getTarget().toString());
        loadWindow("AddTask.fxml", "Create new Task");
    }

    private void showQuestions(ActionEvent event) {
        questionTableAnchorPane.toFront();
        questionTableAnchorPane.setVisible(true);
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
        for (int i = 0; i < selectedBoardList.size(); i++) {
            System.out.println(" " + selectedBoardList.get(i).getBoardID() + "\t" + selectedBoardList.get(i).getBoardName() + "\t" + selectedBoardList.get(i).getProjectLead());
        }
        selectedBoardList.forEach(boardList::remove);

        BoardDAO bdao = new BoardDAO();
        bdao.connect();
        bdao.removeBoard(selectedBoardList.get(0).getBoardID());
        bdao.closeConnection();
    }

    

}
