package espresso;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class LineChartFX {
    
    ArrayList<String> xValues;
    ArrayList<Integer> yValues;
    String xAxisLabel;
    String yAxisLabel;
    String boardName;
    public LineChartFX(ArrayList<String> xValues, ArrayList<Integer> yValues, String xAxisLabel, String yAxisLabel, String boardName){
        this.xValues = xValues;
        this.yValues = yValues;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.boardName = boardName;
    }
    
    
    public void showChart(){
        Stage st = new Stage();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lc = new LineChart<>(xAxis,yAxis);
        
        
        lc.setTitle(boardName);
        
        xAxis.setLabel(xAxisLabel);       
        yAxis.setLabel(yAxisLabel);
        
        XYChart.Series series1 = new XYChart.Series();
        
        series1.setName(boardName); 
        
        for(int i=0; i<xValues.size();i++){
            series1.getData().add(new XYChart.Data(xValues.get(i), yValues.get(i)));
        }
        
        Scene scene  = new Scene(lc,800,600);
        lc.getData().add(series1);
        st.setScene(scene);
        st.show();
        
    }
    
  
 
}