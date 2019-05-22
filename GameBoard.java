import java.util.*;
/** 
 * GameBoard 
 * represent Board of Scrabble game with fuction to form and calculate words
 * Created By Patipon Petchtone 59070501049 CPE
 * 
 * Board Indexing
 *   X  1   2   3   4   5
 *  Y
 * 
 *  1   A   N   T
 *    
 *  2           O
 * 
 *  3       U   N   I   T
 * 
 *  4           E
*/
public class GameBoard
{
    /** structure of square on Board */
    private static ArrayList<Square> squareGrid;
    /** Collection of square placement that player in that turn play */
    private static ArrayList<Square> currentPlacement;
    /** Group of square collection that need to use in calculate score section */
    private static ArrayList<SquareCollection> words;

    private GameBoard()
    {
        
    }
    /**
     * Initial procedure to make the scrabble board
     * 
     */
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
    private static String getSquareString(int positionX,int positionY)
    {
        int index = (positionX-1)*15+(positionY-1);
        Square square = squareGrid.get(index);
        int letterMultiplier = square.getLetterMultiplier();
        int wordMultiplier = square.getWordMultiplier();
        Tile tile = square.peekTileOnSquare();
        String thingOnSquare = new String();
        if(tile == null)
        {
            if(positionX == 8 && positionY == 8)
            {
                thingOnSquare = new String("<S>");
            }
            else if(letterMultiplier==1 && wordMultiplier == 1)
            {
                thingOnSquare = new String(" ");
            }
            else if(letterMultiplier == 1) // it is word mul
            {
                switch (wordMultiplier) 
                {
                    case 2:
                        thingOnSquare = new String("2W");
                        break;
                    case 3:
                        thingOnSquare = new String("3W");
                        break;
                }
            }
            else if(wordMultiplier == 1) // it is letter mul
            {
                switch (letterMultiplier) 
                {
                    case 2:
                        thingOnSquare = new String("2L");
                        break;
                    case 3:
                        thingOnSquare = new String("3L");
                        break;
                }
            }
        }
        else
        {
            thingOnSquare = tile.getTileLetter();
        }
        return thingOnSquare;
    }
    /**
     * show square layout and every tile on the board
     * 
     */
    public static void viewBoard()
    {
        for (int i = 1; i <= 15; i++) 
        {
            for (int j = 0; j < 15; j++) 
            {
                System.out.print("+-----");    
            }
            System.out.println("+");    

            for (int j = 1; j <= 15; j++) 
            {
                String letterOnBoard = getSquareString(i, j);
                // String letterOnBoard = new String(" ");
                if(letterOnBoard.length() == 1)
                {
                    System.out.print("|  "+letterOnBoard+"  ");
                }
                else if(letterOnBoard.length() == 2)
                {
                    System.out.print("| "+letterOnBoard.charAt(0)+" "+letterOnBoard.charAt(1)+" ");
                }
                else
                {
                    System.out.print("| "+letterOnBoard+" ");
                }

            }
            System.out.println("|");    

        }
        for (int j = 0; j < 15; j++) 
        {
            System.out.print("+-----");    
        }
        System.out.println("+"); 
    }
    /**
     * place tile to board at positionX positionY 
     * @param positionX position X of board
     * @param positionY position Y of board
     * @param tile letter tile 
     * @return boolean that tell you can place on that position. true if can,otherwise false.
     */
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
    /**
     * clear board re initial board but didn't reset placement.
     * 
     */
    public static void clearBoard()
    {
        squareGrid = new ArrayList<Square>();
        for(ScrabbleBoardEnum boardSquare:ScrabbleBoardEnum.values())
        {
            squareGrid.add(new Square(boardSquare.getPosX(),boardSquare.getPosY(),
            boardSquare.getLetterMultiplier(),boardSquare.getWordMultiplier()));
        }
    }
    /**
     * clear current placement 
     */
    public static void clearCurrenPlacement()
    {
        currentPlacement = new ArrayList<Square>();
    }
    /**
     * clear words that use in score calculation;
     */
    public static void clearWords()
    {
        words = null;
    }
    /**
     * check the current placement.are in the same alignment.
     * @return true if currentplacement is aligned,otherwise false
     */
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
        if(isAlign)
        {
            return true;
        }
        else
        {
            restoreTileToPlayer();
            return false;
        }
        
    }
    /**
     * try to form the word that have made from player placement
     * @return arraylist of word that make from current placement
     */
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
            if(!wordList.contains(tmpConcatenatorX)&&tmpConcatenatorX.length()!=1)
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
            if(!wordList.contains(tmpConcatenatorY)&&tmpConcatenatorY.length()!=1)
            {
                wordList.add(tmpConcatenatorY);
                words.add(new SquareCollection(newWords));
            }

        }
        return wordList;
    }
    /**
     * calculate score from words
     * @return score of placement
     */
    public static int calculateLastPlacementScore()
    {
        int totalScore=0;
        Iterator<SquareCollection> wordIterator = words.iterator();
        SquareCollection targetCollection;
        while(wordIterator.hasNext())
        {
            targetCollection = wordIterator.next();
            totalScore = totalScore + targetCollection.calculateScore();
        }
        wordIterator = words.iterator();
        while(wordIterator.hasNext())
        {
            targetCollection = wordIterator.next();
            targetCollection.setSquareEffectOff();
        }
        return totalScore;
    }
    /**
     * return tile in currentplacement back to player hand
     */
    public static void restoreTileToPlayer()
    {
        Iterator<Square> iterator = currentPlacement.iterator();
        Player player = Game.getCurrentPlayer();
        while(iterator.hasNext())
        {
           player.addTile(iterator.next().getTileOnSquare());
        }
        clearCurrenPlacement();
        
    }
    /**
     * remove tile that have ID same with parameter out from board
     * @param tileID id of the tile you need to remove from board
     * @return true if success,otherwise false
     */
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