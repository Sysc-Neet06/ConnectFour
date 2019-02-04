/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.scene.control.Button;
/**
 *
 * @author Neethan Sri
 */
public class ConnectButton extends Button {
    /*
    Private variables used to keep track of row and columns
    */
    private int row;
    private int column;
    /**
     * A 3-Parameter Constructor used to create the button that will store player
     * moves
     * @param label
     * @param row
     * @param column 
     */
    public ConnectButton(String label, int row, int column){
        super(label);
        this.row = row;
        this.column = column;
    }
    /**
     * A public method to get the row the button is on
     * @return the row value
     */
    public int getRow(){
        return this.row;
    }
    
    /**
     * A public method to get the column the button is on
     * @return the column value
     */
    public int getColumn(){
        return this.column;
    }
    
    /**
     * The to string method to print out the row and column values in a string 
     * representation
     * @return A string representation of the row and column the button is on
     */
    public String toString(){
        return "( "+ this.row + "," + this.column + " )";
    }
}
