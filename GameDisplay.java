import java.util.ArrayList;
import java.util.Iterator;

/**
 * GameDisplay
 */
public class GameDisplay 
{
    private GameDisplay() 
    {
    }

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
    public static void showBoard() 
    {
        GameBoard.viewBoard(); 
    }

    public static void displayMessage(String message) 
    {
        System.out.println(message);
    }

    public static void displayMessageInline(String message) 
    {
        System.out.print(message);
    }
    public static void showCurrentPlayerRack() 
    {
        Game.getCurrentPlayer().showTile();
    }
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
    public static void main(String[] args) 
    {
        GameBoard.initialize();
        TilePool.initialize();
        showBoard();
        GameBoard.placeTileTo(8, 8, TilePool.selectRandomTile());
        showBoard();

    }
}