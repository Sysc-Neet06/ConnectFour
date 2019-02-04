
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neethan's PC
 */
public class ConnectFourTestClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.BLACK); // Initialized the game
        Scanner scanner = new Scanner(System.in);   //Initializes the Scanner 

        do { 
            System.out.println(game.toString()); // prints gameboard
            System.out.println(game.getTurn() +  // Prints instruction to user
                ": Where do you want to place? Enter row column");
            int row = scanner.nextInt(); // Takes in row input
            int column = scanner.nextInt(); // Takes in Column input
            scanner.nextLine(); 
            game.takeTurn(row, column); //uses inputs to take users turn
            
        } while (game.getGameState() == ConnectFourEnum.IN_PROGRESS); // keeps the game going till user is found
        System.out.println(game.getGameState()); // prints winner
       
    }

    }

