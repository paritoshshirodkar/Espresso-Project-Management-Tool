package espresso;

import dao.EmployeeDAO;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paritosh
 */
public class SignupController implements Initializable {

    
    @FXML
    private AnchorPane signupPane;

    @FXML
    private AnchorPane ap;

    @FXML
    private TextField employeeID;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Label emailValidationLabel;

    @FXML
    private Label passwordValidationLabel;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    private Button signUpButton;
    
    @FXML
    void signUp(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        emailValidationLabel.setVisible(false);
        passwordValidationLabel.setVisible(false);
        if(validateEmail() && validatePassword()){
            EmployeeDAO edao = new EmployeeDAO();
            edao.connect();
            PBKDF2WithHmacSHA1 pwd = new PBKDF2WithHmacSHA1(confirmPassword.getText());
            String encryptedPassword = pwd.getHash();
            edao.addEmployee(Integer.parseInt(employeeID.getText()), (String)firstName.getText(), (String)lastName.getText(), (String)email.getText(), (String)encryptedPassword);
            edao.closeConnection();
            closeStage(signupPane);
            //loadWindow2("FXMLDocument.fxml", "Sign In");
        }
    }
    
    boolean validateEmail(){
        Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = usernamePattern.matcher(email.getText());
        if (m.find() && m.group().equals(email.getText())) {
            return true;
        } else {
            emailValidationLabel.setText("Invalid username");
            email.clear();
            emailValidationLabel.setVisible(true);
            email.requestFocus();
            return false;
        }
        
    }
        

    private boolean validatePassword() {
        if(password.getText().equals(confirmPassword.getText()))
            return true;
        else{
            passwordValidationLabel.setText("Please re-enter the password");
            password.clear();
            confirmPassword.clear();
            passwordValidationLabel.setVisible(true);
            password.requestFocus();
            confirmPassword.clear();
            return false;
        }
    }
    
//    public void loadWindow2(String loc, String title) {
//        try {
//            System.out.println(title + " created");
//            AnchorPane signinPane = FXMLLoader.load(getClass().getResource(loc));
//            signupPane.getChildren().setAll(signinPane);
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//
//    }
    
    
    public void closeStage(AnchorPane pane){
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }
    
    
    
            
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

    
