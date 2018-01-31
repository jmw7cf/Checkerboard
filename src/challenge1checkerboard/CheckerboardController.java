/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge1checkerboard;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author julia
 */
public class CheckerboardController implements Initializable, Startable {
    
    private Stage stage;
    
    @FXML private AnchorPane anchorPane;
//    @FXML private VBox vBox;
    @FXML private MenuItem size3;
    @FXML private MenuItem size8;
    @FXML private MenuItem size10;
    @FXML private MenuItem size16;
    
    @FXML private MenuItem red;
    @FXML private MenuItem blue;
    
    private int numRows = 8;
    private int numColumns = 8;
    
    private double heightDisplacement=0;
    private double widthDisplacement=0;
    private double boardWidth;
    private double boardHeight;
    private Color lightColor = Color.RED;
    private Color darkColor = Color.BLACK;
    
    @FXML
    private void handleChangeSize(ActionEvent event) {
        MenuItem menuItem = (MenuItem)(event.getSource());
        
        switch(menuItem.getId()){
            case "size3":
                numRows = 3;
                numColumns = 3;
                break;
            case "size8":
                numRows = 8;
                numColumns = 8;
                break;
            case "size10":
                numRows = 10;
                numColumns = 10;
                break;
            case "size16":
                numRows = 16;
                numColumns = 16;
                break;
            default:
                numRows = 8;
                numColumns = 8;
        }
        refresh();
    }
    
    @FXML
    private void handleRedColor(ActionEvent event) {
        this.boardHeight = anchorPane.getHeight();
        this.boardWidth = anchorPane.getWidth();
        
        lightColor = Color.RED;
        darkColor = Color.BLACK;
        
        Checkerboard checkerboard = new Checkerboard(numRows, numColumns, boardWidth, boardHeight, lightColor, darkColor);
        AnchorPane board = checkerboard.getBoard();
        
        clearBoard();

        anchorPane.getChildren().addAll(board);
    }
    
    @FXML
    private void handleBlueColor(ActionEvent event) {
        this.boardHeight = anchorPane.getHeight();
        this.boardWidth = anchorPane.getWidth();
        
        lightColor = Color.LIGHTBLUE;
        darkColor = Color.DARKBLUE;
        
        Checkerboard checkerboard = new Checkerboard(numRows, numColumns, boardWidth, boardHeight, lightColor, darkColor);
        AnchorPane board = checkerboard.getBoard();
        
        clearBoard();

        anchorPane.getChildren().addAll(board);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void start(Stage stage) {
        this.stage = stage;
        heightDisplacement = stage.getHeight() - anchorPane.getHeight();
        widthDisplacement = stage.getWidth() - anchorPane.getWidth();
        
        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
//            heightDisplacement = stage.getHeight() - anchorPane.getHeight();
//            widthDisplacement = stage.getWidth() - anchorPane.getWidth();
            System.out.println("stageheight: " + stage.getHeight() + " anchorHeight: " + anchorPane.getHeight() + " displacement: " + heightDisplacement);
            refresh();
        };
        
        stage.widthProperty().addListener(lambdaChangeListener);
        stage.heightProperty().addListener(lambdaChangeListener);
        
        refresh();
    }
    
    public void refresh(){
        System.out.println("Stage height: " + stage.getHeight() + " heightDisplacement: " + heightDisplacement + " anchorPaneHeight: " + anchorPane.getHeight());
        System.out.println("Stage width: " + stage.getWidth() + " widthDisplacement: " + widthDisplacement + " anchorPaneWidth: " + anchorPane.getWidth());
        
        boardHeight = stage.getHeight() - heightDisplacement;
        boardWidth = stage.getWidth() - widthDisplacement;
        Checkerboard checkerboard = new Checkerboard(numRows, numColumns, boardWidth, boardHeight, lightColor, darkColor);
        AnchorPane board = checkerboard.getBoard();
        
        clearBoard();
        anchorPane.getChildren().addAll(board);
        anchorPane.clearConstraints(board);
    }
    
    public void clearBoard(){
        anchorPane.getChildren().clear();
    }
}
