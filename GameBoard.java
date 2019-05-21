
import java.util.*;

public class GameBoard
{
    private static ArrayList<Square> squareGrid;
    
    private static ArrayList<Square> currentPlacement;

    private GameBoard()
    {
        
    }

    public static void initialize()
    {
        squareGrid = new ArrayList<Square>();
        currentPlacement = new ArrayList<Square>();
        for(ScrabbleBoardEnum boardSquare:ScrabbleBoardEnum.values())
        {
            squareGrid.add(new Square(boardSquare.getPosX(),boardSquare.getPosY(),
            boardSquare.getLetterMultiplier(),boardSquare.getWordMultiplier()));
        }

    }

    public static void viewBoard()
    {

    }

    public static boolean placeTileTo(int positionX,int positionY,Tile tile)
    {
        if(squareGrid.get((positionX-1)*15+(positionY-1)).isEmpty())
        {
            squareGrid.get((positionX-1)*15+(positionY-1)).setTileOnSquare(tile);
            currentPlacement.add(squareGrid.get((positionX-1)*15+(positionY-1)));
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void clearBoard()
    {
        squareGrid = new ArrayList<Square>();
        for(ScrabbleBoardEnum boardSquare:ScrabbleBoardEnum.values())
        {
            squareGrid.add(new Square(boardSquare.getPosX(),boardSquare.getPosY(),
            boardSquare.getLetterMultiplier(),boardSquare.getWordMultiplier()));
        }
    }

    public static void clearCurrenPlacement()
    {
        currentPlacement = new ArrayList<Square>();
    }

    public static int calculateLastPlacementScore()
    {
        
    }

    public static void restoreTileToPlayer()
    {
        Iterator<Square> iterator = currentPlacement.iterator();
        Player player;
        while(iterator.hasNext())
        {
           player = Game.getCurrentPlayer();
           player.addTile(iterator.next().getTileOnSquare());
        }
        clearCurrenPlacement();
        
    }

    public static boolean removeTileFromBoard(int tileID)
    {
        boolean isSuccess = false;
        Square targetSquare;
        Iterator<Square> iterator = squareGrid.iterator();
        while(iterator.hasNext())
        {
            targetSquare = iterator.next();
           if(targetSquare.getTileOnSquare().getTileID()==tileID)
           {
               isSuccess = true;
               targetSquare.clearSquare();
           }
        }
        return isSuccess;
    }

}