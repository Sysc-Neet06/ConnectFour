/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Observable;
import java.util.Random;


/**
 *
 * @author Neethan Sri
 */
public class ConnectFourGame extends Observable {

    
    private int nColumns; // column number
    private int nRows; // row number
    private int numToWin; // number to win value
    private ConnectFourEnum[][] grid; // grid initialized
    private ConnectFourEnum gameState; // helps keep track of the game state
    private ConnectFourEnum turn; // turn tracker
    private ConnectFourEnum[] gameStates = {ConnectFourEnum.IN_PROGRESS, ConnectFourEnum.RED, ConnectFourEnum.BLACK, ConnectFourEnum.DRAW, ConnectFourEnum.EMPTY}; // gamestate array to help with tracking game states 
    
    /**
     * 1-Argument constructor to create the board with default values
     * @param initialTurn 
     */
    public ConnectFourGame(ConnectFourEnum initialTurn ){
        this(8,8,4,initialTurn); // initilizes the gameboard with default values
        
    
    }
    
    /**
     * A 4-Argument constructor to construct the game with different size boards and rules
     * @param nRows
     * @param nColumns
     * @param numToWin
     * @param initialTurn 
     */
    public ConnectFourGame(int nRows, int nColumns, int numToWin, ConnectFourEnum initialTurn ){
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.numToWin = numToWin;
        this.turn = initialTurn;
        this.reset(this.turn);
    }
    
    /**
     * Reset method to reset the board and randomize which player starts
     * @param intialTurn 
     */
    public void reset(ConnectFourEnum intialTurn){
        this.gameState = gameStates[0];
        Random random = new Random();
        turn = gameStates[random.nextInt(2)+1];
        grid = new ConnectFourEnum[this.nRows][this.nColumns];
        for(int countRows = 0; countRows < nRows; countRows++){
            for(int countColumns = 0; countColumns < nColumns; countColumns++){
                grid[countRows][countColumns] = gameStates[4];
            }
        }
    }
    
    /**
     * TakeTurn method used to find the winner and keep track of the players movement
     * @param row
     * @param column
     * @return the winner or the In.Progress gameState to keep the game flowing
     */
    public ConnectFourEnum takeTurn(int row, int column){
        for(int check = 0; check <= row; check++){ // iterates all squares before the row inputed
            if (grid[check][column].compareTo(gameStates[4]) == 0){ // if the square is empty
                if(check != row){ // if row selected is to high
                    throw new IllegalArgumentException ("You need to pick the lowest avalible sqaure in the column"); // throw exception
                }
                grid[row][column] = this.getTurn(); // populates grid with turn
            }  
        }
        //winner check 
        int acrossCount = 0; // horizontal counter set to zero
        int downCount = 0; // vertical counter set to zero
        
        //Horizontal check
        for (int i = 0; i < this.nColumns; i++){
            if(grid[row][i].compareTo(this.getTurn()) == 0){
                acrossCount++;
            }else{
                if(acrossCount!= this.numToWin){
                    acrossCount = 0;
                }
            }
            
        }
        //Vertical check
        for (int j = 0; j < this.nRows; j++){
            if(grid[j][column].compareTo(this.getTurn()) == 0){
                downCount++;
            }else{
                if(downCount!= this.numToWin){
                    downCount = 0;
                }
            }
            
        }
        // returns winner when found
        if(downCount >= this.numToWin || acrossCount >= this.numToWin){
            this.gameState = this.getTurn();
        }
        
        int countEmpty = 0; // sets empty counter to zero
        
        //keeps track of empty squares
        for (int i = 0; i < this.nColumns; i++){
            if(grid[this.nRows-1][i].compareTo(this.gameStates[4]) == 0){
                countEmpty++;
            }
        }
        
        //Tracks squares for draw
        if( countEmpty == 0){
            this.gameState = this.gameStates[3];
            return this.getGameState();
        }
        
         ConnectMove cm = new ConnectMove(row,column,this.turn); // Connect move created
        
         // Switches players after each turn
        if(this.getTurn().compareTo(this.gameStates[1]) == 0){
            this.turn = this.gameStates[2];
        }else{
            this.turn = this.gameStates[1];
        }
        
       // Observer pattern
        setChanged(); // sets the change made
        notifyObservers(cm); // and changes the grid
  
        
        return this.getGameState(); // return the gamte state 
    }
    
    /**
     * Method to get game state
     * @return one of the four states in the enum
     */
    public ConnectFourEnum getGameState(){
        return this.gameState;
    }
    
    /**
     * Method to get whose turn it is
     * @return the player that had their turn
     */
    public ConnectFourEnum getTurn(){
        return this.turn;
    }
    
    /**
     * ToString method to print the game board
     * @return the game board in string representation
     */
    public String toString(){
        String gridPrint ="";
        for(int i = 0; i < this.nColumns; i++){
            for(int j = 0; j < this.nRows; j++){
                gridPrint += "" + this.grid[i][j] + " | ";
            }
            gridPrint += "\n";
        }
        return gridPrint; 
    }
}
    
 