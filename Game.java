package Scrabble;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StartTilePlayerComparator implements Comparator<Tile>
{

    @Override
    public int compare(Tile t1, Tile t2) 
    {
        return t1.getTileID() - t2.getTileID();
    }

}
/**
 * Game
 */
public class Game 
{

    private static Player currentPlayer;
    private static ArrayList<Player> skippedPlayers;
    private static Queue<Player> orderedPlayers;
    private static int endGameCounter;

    private Game() {}

    public static void initialize() 
    {
        TilePool.initialize();
    }

    public static void startGame(int numberOfPlayer)
    {
        int i=0;
        int closestIndex=0;
        TreeMap<Tile,Player> startTilePlayerMap = new TreeMap<Tile,Player>(new StartTilePlayerComparator());
        boolean isSameTileOccur = false; 
        do
        {
            Tile closetTile = null;
            for(i=0;i<numberOfPlayer;i++)
            {
                //ask for player's name
                GameDisplay.displayMessage("What is player "+(i+1)+" name?");
                //wait for input
                String playerName = IOUtils.getString("");
                Player player = new Player(i,playerName);
                startTilePlayerMap.put(player.getStartTile(), player);
            }
            Stream<Tile> startTilesStream = startTilePlayerMap.keySet().stream();
            Stream<String> startLetters = startTilesStream.map(tile -> tile.getTileLetter());
            ArrayList<String> startLettersList = startLetters
                                                            .collect(Collectors
                                                            .toCollection(ArrayList::new));
            for(String letter: startLettersList)
            {
                startLettersList.remove(letter);
                if (startLettersList.contains(letter)) 
                {
                    isSameTileOccur = true;
                    break;    
                }
            }
        }while(isSameTileOccur);
        //re-ordering player
        for(Player player : startTilePlayerMap.values())
        {
            orderedPlayers.add(player);
        }
        currentPlayer = orderedPlayers.peek();
        GameDisplay.displayMessage(currentPlayer+" is first player");
    }

    /**
     * @return the currentPlayer
     */
    public static Player getCurrentPlayer() 
    {
        return currentPlayer;
    }

    public static void removePlayer() 
    {
        orderedPlayers.remove(currentPlayer);
        skippedPlayers.removeIf(player -> player.getPlayerID() == currentPlayer.getPlayerID());
        nextTurn();
    }

    public static void takeTurn() 
    {
        GameDisplay.displayMessage("What do you want to do?");
        GameDisplay.displayMessage("1)Place a word");
        GameDisplay.displayMessage("2)Swap tile(s)");
        GameDisplay.displayMessage("3)Pass the turn");
        GameDisplay.displayMessage("4)Quit the game");
        GameDisplay.displayMessageInline(currentPlayer.getPlayerName()+"> ");
        int playerChoice = IOUtils.getIntegerInRange("", 1, 4);
        Tile tile;
        String answer;
        switch (playerChoice) 
        {
            case 1: // place a word
                int tilesUsedCount = 0;
                do
                {
                    tile = getTileFromPlayer();
                    boolean isPlaced = false;
                    do
                    {
                        int positionX = IOUtils.getIntegerInRange("", 1, 255);
                        int positionY = IOUtils.getIntegerInRange("", 1, 255);
                        isPlaced = GameBoard.placeTileTo(positionX, positionY, tile);
                        if(!isPlaced)
                            GameDisplay.displayMessage("Please select other square");
                    }while(!isPlaced);
                    tilesUsedCount++;
                    GameDisplay.displayMessageInline("More tile? (Y or N)> ");
                    answer = IOUtils.getString("");
                }while(answer.equals("Y"));

                int totalScore = GameBoard.calculateLastPlacementScore();
                currentPlayer.updateScore(totalScore);
                challenge();
                break;
            case 2: // swap tiles
                ArrayList<Integer> tilesID = new ArrayList<Integer>();
                do
                {
                    tile = getTileFromPlayer();
                    tilesID.add(tile.getTileID());//may be we can just send Tile instance?
                    GameDisplay.displayMessageInline("More tile? (Y or N)> ");
                    answer = IOUtils.getString("");
                }while(answer.equals("Y"));
                int[] tilesIDIntArray = tilesID.stream()
                                            .mapToInt(Integer::intValue)
                                            .toArray(); 
                currentPlayer.swapTile(tilesIDIntArray);
                break;
            case 3: // pass
                GameDisplay.displayMessage(currentPlayer.getPlayerName()+" passed the turn");
                break;
            case 4: // quit
                removePlayer();
                break;
        }
        boolean isEnd = isEnd();
        if(!isEnd)
            nextTurn();
    }

    public static void nextTurn() 
    {
        //fill up CP tile
        while(currentPlayer.getPlayerTilesCount() < 7)
        {
            Tile newTile = TilePool.selectRandomTile();
            if(newTile==null)
                break;
            currentPlayer.addTile(newTile);
        }
        orderedPlayers.add(currentPlayer);//enqueue CP to last of queue
        currentPlayer = orderedPlayers.remove(); //dequeue next player
        
    }
    
    private static boolean isEnd() 
    {
        if(TilePool.getTileCount() == 0)
        {
            for(Player player : orderedPlayers)
            {
                if(player.getPlayerTilesCount() == 0)
                    return true;
            }
        }
        if(endGameCounter == 6)
        {
            return true;
        }
        return false;
    }
    private static Tile getTileFromPlayer()
    {
        int tileID;
        Tile tile;
        GameDisplay.displayMessageInline("Input Tile ID> ");
        tileID = IOUtils.getIntegerInRange("", 0, 99);
        tile = currentPlayer.getTile(tileID); 
        while(tile==null)
        {
            GameDisplay.displayMessage("No such tile in your rack");
            tileID = IOUtils.getIntegerInRange("", 0, 99);
            tile = currentPlayer.getTile(tileID); 
        }
        return tile;
    }
    private static void challenge() 
    {
        GameDisplay.displayMessage("Any player want to challenge?");
        ArrayList<String> allPlayerName = orderedPlayers.stream()
                                                    .map(player->player.getPlayerName())
                                                    .collect(Collectors
                                                    .toCollection(ArrayList::new));
        boolean playerExists = false;
        Player skippedPlayer = null;
        while(!playerExists)
        {
            GameDisplay.displayMessageInline("Input player name> ");
            String playerName = IOUtils.getString("");
            for (Player player : orderedPlayers) 
            {
                if (player.getPlayerName().equalsIgnoreCase(playerName))
                {
                    skippedPlayer = player;
                    playerExists = true;
                    break;
                }
            }
        }

        boolean isInDictionary = WordChecker.verifyLastWords();
        if(isInDictionary)
        {
            skippedPlayers.add(skippedPlayer);
        }
        else
        {
            int reverseScore = GameBoard.calculateLastPlacementScore();
            currentPlayer.updateScore(-reverseScore);
            GameBoard.restoreTileToPlayer();
        }

    }
    
}