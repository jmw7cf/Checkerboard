/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge1checkerboard;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author julia
 */
public class Checkerboard {
   
    private AnchorPane anchorPane = null;
    private int rows = 8;
    private int columns = 8;
    private double boardWidth;
    private double boardHeight;
    private double rectWidth;
    private double rectHeight;
    private Color lightColor;
    private Color darkColor;
    
    public Checkerboard(){
        
    }
    
    public Checkerboard(int rows, int columns, double boardWidth, double boardHeight){
        this.rows = rows;
        this.columns = columns;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }
    
    public Checkerboard(int numRows, int numCols, double boardWidth, double boardHeight, Color lightColor, Color darkColor){
        this(numRows, numCols, boardWidth, boardHeight);
        this.lightColor = lightColor;
        this.darkColor = darkColor;
    }
    
        
    public AnchorPane build(){
        rectWidth = Math.ceil(boardWidth / columns);
        rectHeight = Math.ceil(boardHeight / rows);

        anchorPane = new AnchorPane();
        Color color;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if((row % 2) == (col % 2))
                    color = lightColor;
                else
                    color = darkColor;
                
                Rectangle square = new Rectangle();
                square.setHeight(rectHeight);
                square.setWidth(rectWidth);
//                System.out.println("Square Height : " + square.getHeight() + " Square Width : " + square.getWidth());
                square.setY(rectHeight*col);
                square.setX(rectWidth*row);
                square.setFill(color);
                
                anchorPane.getChildren().add(square);
            }
        }
        
        return anchorPane;
    }
    
    public int getNumRows(){ 
        return rows;
    }
    public int getNumCols(){ 
        return columns;
    }
    public double getWidth(){ 
        return boardWidth; 
    }
    public double getHeight(){ 
        return boardHeight; 
    }
    public Color getLightColor(){ 
        return lightColor; 
    }
    public Color getDarkColor(){ 
        return darkColor; 
    }
    
    public double getRectangleWidth(){ 
        return rectWidth;
    }
    
    public double getRectangleHeight(){ 
        return rectHeight;
    }
    
    public AnchorPane getBoard(){
        if(anchorPane == null){
            return build();
        }
        else return null;
    }
}
