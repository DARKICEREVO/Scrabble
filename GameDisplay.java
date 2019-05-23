import java.util.ArrayList;
import java.util.Iterator;

/**
 * GameDisplay.java
 * this class represent aa a Displayer always use to show message rack and board to player
 * created by Patipon Petchtone 59070501049
 * and        Puwit Yahom       59070501059
 * Date: 20 May 2019
 */
public class GameDisplay 
{
    private GameDisplay() 
    {
    }
    /**
     * show the general display of scrabble
     */
    public static void showGeneralDisplay()
    {
        clearScreen();
        showBoard();
        Player currentPlayer = Game.getCurrentPlayer();
        Iterator<String> playerOrdering = Game.getOrderedPlayerName().iterator();
        while(playerOrdering.hasNext())
        {
            String name = playerOrdering.next();
            displayMessageInline(name);
            if(playerOrdering.hasNext())
                displayMessageInline(" -> ");

        }
        displayMessage("");
        displayMessage(currentPlayer.getPlayerName()+"'s turn");
        displayMessage("Your rack:");
        currentPlayer.showTile();
    }
    /**
     * show the board of the game with square and tiles
     */
    public static void showBoard() 
    {
        GameBoard.viewBoard(); 
    }
    /**
     * display the message to user
     * @param message message that you need to show player
     */
    public static void displayMessage(String message) 
    {
        System.out.println(message);
    }
    /**
     * display the message to user in one line
     * @param message message that you need to show player in one line
     */
    public static void displayMessageInline(String message) 
    {
        System.out.print(message);
    }
    /**
     * show the rack of current player
     */
    public static void showCurrentPlayerRack() 
    {
        Game.getCurrentPlayer().showTile();
    }
    /**
     * clear the screen of the game CAUTION (Maybe command can't use with Linux Terminal)
     */
    public static void clearScreen() 
    {
        // for (int i = 0; i < 50; i++) 
        // {
        //     System.out.println("");
        // }
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("window"))
        {
            try 
            {
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } catch (Exception e) 
            {
                System.out.println(e);
            }
        }
        else
        {
            try 
            {
                new ProcessBuilder("/bin/sh","-c","clear").inheritIO().start().waitFor();
            } catch (Exception e) 
            {
                System.out.println(e);
            }
        }
    }
    /**
     * tester of display
     */
    public static void main(String[] args) 
    {
        GameBoard.initialize();
        TilePool.initialize();
        showBoard();
        GameBoard.placeTileTo(8, 8, TilePool.selectRandomTile());
        showBoard();

    }
}