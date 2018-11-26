/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxbarchart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

/**
 * FXML Controller class
 *
 * @author Paritosh
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private BarChart<?, ?> WorkDoneBarChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
