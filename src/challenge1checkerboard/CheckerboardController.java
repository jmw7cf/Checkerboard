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
    
    private double boardWidth;
    private double boardHeight;
    private Color lightColor = Color.RED;
    private Color darkColor = Color.BLACK;
    
    @FXML
    private void handleChangeSize(ActionEvent event) {
        MenuItem menuItem = (MenuItem)(event.getSource());
        System.out.println(menuItem);
        
        switch(menuItem.getId()){
            case "size3":
                numRows = 3;
                numColumns = 3;
                break;
            case "size8":
                numRows = 8;
                numColumns = 8;
                break;
            case "size16":
                numRows = 16;
                numColumns = 16;
                break;
            case "size32":
                numRows = 32;
                numColumns = 32;
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
        
        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            refresh();
        };
        
        stage.widthProperty().addListener(lambdaChangeListener);
        stage.heightProperty().addListener(lambdaChangeListener);
        
        refresh();
    }
    
    public void refresh(){
        boardHeight = stage.getHeight();
        boardWidth = stage.getWidth();
        
        Checkerboard checkerboard = new Checkerboard(numRows, numColumns, boardWidth, boardHeight, lightColor, darkColor);
        AnchorPane board = checkerboard.getBoard();
        
        clearBoard();

        anchorPane.getChildren().addAll(board);
    }
    
    public void clearBoard(){
        anchorPane.getChildren().clear();
    }
}
