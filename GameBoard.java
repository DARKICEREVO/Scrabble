
import java.util.*;

public class GameBoard
{
    private static ArrayList<Square> squareGrid;
    
    private static ArrayList<Square> currentPlacement;

    private static ArrayList<SquareCollection> words;

    private GameBoard()
    {
        
    }

    public static void initialize()
    {
        squareGrid = new ArrayList<Square>();
        currentPlacement = new ArrayList<Square>();
        words = new ArrayList<SquareCollection>();
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

    public static boolean validatePlacement()
    {
        Iterator<Square> iterator = currentPlacement.iterator();
        Square targetSquare;
        boolean isAlign=true;
        boolean alignX=true,alignY=true;
        int mainX,mainY;
        mainX = currentPlacement.get(0).getPositionX();
        mainY = currentPlacement.get(0).getPositionY();
        while(iterator.hasNext())
        {
            targetSquare = iterator.next();
            if(targetSquare.getPositionX()==mainX)
            {
                alignX = alignX && true;
            }
            else
            {
                alignX = alignX && false;
            }
            if(targetSquare.getPositionY()==mainY)
            {
                alignY = alignY && true;
            }
            else
            {
                alignY = alignY && false;
            }
        }
        isAlign = alignX || alignY;
        return isAlign;
        
    }

    public static ArrayList<String> formWord()
    {
        Square targetSquare;
        int startX,startY,currentX,currentY;
        int wordPointerX,wordPointerY;
        ArrayList<String> wordList =  new ArrayList<String>();
        Iterator<Square> iterator = currentPlacement.iterator();
        while(iterator.hasNext())
        {
            String tmpConcatenatorX = new String();
            String tmpConcatenatorY = new String();
            ArrayList<Square> newWords = new ArrayList<Square>();
            targetSquare = iterator.next();
            startX = targetSquare.getPositionX();
            startY = targetSquare.getPositionY();
            currentX = startX;
            currentY = startY;
            while(currentX!=1&&!squareGrid.get((currentX-1)*15+(startY-1)).isEmpty())
            {
                currentX --;
            }
            while(currentY!=1&&!squareGrid.get((startX-1)*15+(currentY-1)).isEmpty())
            {
                currentY --;
            }
            if(currentX==1&&!squareGrid.get((currentX-1)*15+(startY-1)).isEmpty())
            {
                wordPointerX = currentX;
            }
            else
            {
                wordPointerX = currentX + 1;
            }
            if(currentY==1&&!squareGrid.get((startX-1)*15+(currentY-1)).isEmpty())
            {
                wordPointerY = currentY;
            }
            else
            {
                wordPointerY = currentY + 1;
            }
            while(squareGrid.get((wordPointerX-1)*15+(startY-1)).isEmpty())
            {
               
               tmpConcatenatorX+=squareGrid.get((wordPointerX-1)*15+(startY-1)).peekTileOnSquare().getTileLetter();
               newWords.add(squareGrid.get((wordPointerX-1)*15+(startY-1)));
               wordPointerX++;
            }
            if(!wordList.contains(tmpConcatenatorX))
            {
                wordList.add(tmpConcatenatorX);
                words.add(new SquareCollection(newWords));
            }
            newWords = new ArrayList<Square>();
            while(squareGrid.get((startX-1)*15+(wordPointerY-1)).isEmpty())
            {
               
               tmpConcatenatorY+=squareGrid.get((startX-1)*15+(wordPointerY-1)).peekTileOnSquare().getTileLetter();
               newWords.add(squareGrid.get((startX-1)*15+(wordPointerY-1)));
               wordPointerY++;
            }
            if(!wordList.contains(tmpConcatenatorY))
            {
                wordList.add(tmpConcatenatorY);
                words.add(new SquareCollection(newWords));
            }

        }
        return wordList;
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