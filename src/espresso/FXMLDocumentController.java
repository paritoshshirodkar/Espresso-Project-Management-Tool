package espresso;

import dao.Employee;
import dao.EmployeeDAO;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Paritosh
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane signinPane;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button signInButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label passwordValidationLabel;

    @FXML
    private Label usernameValidationLabel;

    @FXML
    public void signIn(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        EmployeeDAO edao = new EmployeeDAO();
        edao.connect();
        Employee employee = edao.getEmployee(username.getText());
        
        // code for checking if entered password matches the encrypted password
        PBKDF2WithHmacSHA1 pwd = new PBKDF2WithHmacSHA1(password.getText());
        
        // validatePassword() will generate the hash for the entered password and then compare it with the one stored in the database
        // returns boolean
        boolean matched = pwd.validatePassword(pwd.password, employee.getPassword());
        
        // if block is entered if both username and password are correct
        if (username.getText().equals(employee.getEmail()) && matched) {
            loadWindow("Dashboard.fxml", "Espresso", username.getText());
            System.out.println("Sign In Successful");
            
            // code to close signinPane
            Stage stage = (Stage) signinPane.getScene().getWindow();
            stage.close();
        }

        // else if block entered if username is crroect but password is incorrect.
        else if (username.getText().equals(employee.getEmail()) && matched == false) {
            password.clear();
            passwordValidationLabel.setText("Ïnvalid password. Please re-enter password");
            passwordValidationLabel.setVisible(true);
            password.requestFocus();
        }
        // else block entered if password is crroect but username is incorrect.
        else
        {
            username.clear();
            usernameValidationLabel.setText("Ïnvalid Username. Please re-enter Username");
            usernameValidationLabel.setVisible(true);
            username.requestFocus();
        }
    
    
    }

    @FXML
    public void register(ActionEvent event){
        loadWindow("Signup.fxml", "Sign Up");
    }
    
    // method for generating windows dynamically without username parameter
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
    
    // method for generating windows dynamically with username parameter
    // reurns username
    
    public String loadWindow(String loc, String title, String username){
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
        return username;
    }
    
    
    public void loadWindow2(String loc, String title){
        try {
            System.out.println(title + "created");
            AnchorPane signupPane = FXMLLoader.load(getClass().getResource(loc));
            signinPane.getChildren().setAll(signupPane);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    
    
    // method forusername validation
    boolean validateUsername(){
        Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = usernamePattern.matcher(username.getText());
        if(m.find() && m.group().equals(username.getText()))
            return true;
        else{
            usernameValidationLabel.setText("Invalid username");
            username.clear();
            usernameValidationLabel.setVisible(true);
            username.requestFocus();
            return false;
        }      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
