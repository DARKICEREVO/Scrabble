/**
 * GameDisplay
 */
public class GameDisplay 
{
    private GameDisplay() 
    {
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
    public static void showPlayerRack() 
    {
        
    }
    public static void clearScreen() 
    {
        for (int i = 0; i < 50; i++) 
        {
            System.out.println("");
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