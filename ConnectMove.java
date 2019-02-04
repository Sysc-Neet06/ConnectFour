

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neethan Sri
 */
public class ConnectMove {
    /*
    Private variables for row,column and colour
    */
    private int row;
    private int column;
    private ConnectFourEnum colour;
    
    /**
     * A 3-Argument constructor that will help keep track of what checker is being placed
     * with the help of the observer pattern
     * @param row
     * @param column
     * @param colour 
     */
    public ConnectMove(int row, int column, ConnectFourEnum colour){
      this.row = row;
      this.column = column;
      this.colour = colour;
    }
    
    /**
     * A public method used to get row value
     * @return row value
     */
    public int getRow(){
        return this.row;
    }
    
    /**
     * A public column method used to get column value
     * @return column value
     */
    public int getColumn(){
        return this.column;
    }
    
    /**
     * A method that relies on the ConnectFourEnum to get the color of the checker
     * being placed
     * @return The respective Enum to the color that is being placed
     */
    public ConnectFourEnum getColour(){
        return this.colour;
    }
}
