/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
All imports needed for the application
*/
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Neethan's Sri
 */

/*
A ConnectFourapplication class that inherits from the application class and uses the 
observer class
*/
public class ConnectFourApplication extends Application implements Observer{
    
    /*
    Public final static variables used to set grid size, number of checkers to win
    and button size
    */
    public final static int NUM_COLUMNS = 4;
    public final static int NUM_ROWS = 4;
    public final static int NUM_TO_WIN = 4;
    public final static int BUTTON_SIZE = 20;
    
    /*
    private variables used to have the game code and buttons initialzed
    */
    private ConnectFourGame gameEngine;
    private ConnectButton[][] buttons;
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        // Creating the game with the public variables
        gameEngine = new ConnectFourGame(NUM_ROWS,NUM_COLUMNS,NUM_TO_WIN,ConnectFourEnum.BLACK);
        gameEngine.addObserver(this); // added to the obeserver 
        
        ButtonHandler buttonhand = new ButtonHandler(); // Creating the button handler variable

        BorderPane root = new BorderPane(); // Creating the border pane
        GridPane gd = new GridPane(); // Creating the Grid pane
        
        /*
        Creating the Textfield and setting it up in the border pane
        */
        TextField tf = new TextField();
        tf.setEditable(false);
        tf.setText(gameEngine.getTurn()+ " begins");
        root.setTop(tf);
        
      
        /*
        Creating the buttons for where the checkers and setting up there 
        position and size
        */
        buttons = new ConnectButton[NUM_ROWS][NUM_COLUMNS];
        for(int i = 0; i < NUM_ROWS; i++){
            for(int j = 0; j < NUM_COLUMNS; j++){
               buttons[i][j] = new ConnectButton(ConnectFourEnum.EMPTY.name(),i,j); 
               buttons[i][j].setMinHeight(BUTTON_SIZE);
               buttons[i][j].setMaxWidth(Double.MAX_VALUE);
               buttons[i][j].setOnAction((ActionEvent event) -> {
                    buttonhand.handle(event);
               });
               gd.add(buttons[i][j],j,NUM_ROWS - i); // adding it into the grid pane
            }
        }
        root.setCenter(gd); // setting the gridpane to be center of the border pane
        
        /*
        Creating the confirm button and its event handler
        */
        Button confirm = new Button();
        confirm.setText("Take My Turn");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(buttonhand.Selected == true){ 
                    buttonhand.Selected = false; //setting false so it correctly updates
                    
                    if(gameEngine.takeTurn(buttonhand.getRow(),buttonhand.getColumn()).compareTo(ConnectFourEnum.IN_PROGRESS) != 0){ // helps check for when the game is over
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Connect Four Game");
                        alert.setHeaderText("Game Over");
                        
                        if(gameEngine.getGameState().compareTo(ConnectFourEnum.DRAW) != 0){ // helps check for winner
                            alert.setContentText("Player " + gameEngine.getGameState().name() + " Wins");
                            
                        }else{
                            // prints the game state
                            alert.setContentText("This game was a " + gameEngine.getGameState().toString());
                        }
                        alert.showAndWait();
                        gameEngine.reset(ConnectFourEnum.RED);
                        start(primaryStage);
                        
                        
                    }
                    tf.setText("it's " +gameEngine.getTurn()+"'s turn"); // prints out the player instruction
                }
        }
        });
        
        
        root.setBottom(confirm); // sets the confirm button to the bottom of the border pane
        Scene scene = new Scene(root, 430, 255); // created the scene
        primaryStage.setTitle("Connect Four"); // sets the application title
        primaryStage.setScene(scene); //sets what is show in the application
        primaryStage.show(); // shows the application
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
    * A two argument method inherited from the observer method to help update the grid
    * @param obs
    * @param arg 
    */
    @Override
    public void update(Observable obs, Object o) {
        if(o instanceof ConnectMove){
            ConnectMove move = (ConnectMove)o; // casting object o to a connectmove object
            int row = move.getRow(); // gets the row value
            int column = move.getColumn(); // gets the column value
            String colour = move.getColour().name(); // gets the colour thats being placed
            buttons[row][column].setText(colour); // sets tje button with that colour
            buttons[row][column].setDisable(true); // makes it so that button can't be placed with another checker again
        }        
    }  
}
/*
Button handler class
*/
class ButtonHandler implements EventHandler<ActionEvent>{
    
    /*
    Private variables created for the handler
    */
    private int column;
    private int row;
    private ConnectButton pressed;
    
    public boolean Selected; // a boolean variable used to help with the logic of the handler
    
    @Override
    public void handle(ActionEvent event) {
        pressed = (ConnectButton) event.getSource(); // keeps track of what is being pressed
        Selected = true; // true when a button is selected
        this.row = pressed.getRow(); // row value
        this.column = pressed.getColumn(); // column value
    }
    
    /**
     * Public method  used to get the column value
     * @return column value
     */
    public int getColumn(){
        return this.column;
    }
    
    /**
     * Public method used to get row value
     * @return row value
     */
    public int getRow(){
        return this.row;
    }
   
    
}