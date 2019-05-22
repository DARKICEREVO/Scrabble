import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
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
    private static ArrayList<Player> skippedPlayers = new ArrayList<Player>();
    private static Queue<Player> orderedPlayers = new LinkedList<Player>();
    private static int endGameCounter;

    private Game() {}

    public static void initialize() 
    {
        TilePool.initialize();
        GameBoard.initialize();
        Dictionary.initialize();
}

    public static void startGame(int numberOfPlayer)
    {
        int i=0;
        TreeMap<Tile,Player> startTilePlayerMap = new TreeMap<Tile,Player>(new StartTilePlayerComparator());
        ArrayList<String> startLettersList = new ArrayList<String>();
        for(i=0;i<numberOfPlayer;i++)
        {
            //ask for player's name
            GameDisplay.displayMessage("What is player "+(i+1)+" name?");
            //wait for input
            String playerName = IOUtils.getString("");
            Player player = new Player(playerName);
            //check for duplicate name
            Tile startTile;
            do
            {
                startTile = player.getStartTile();
            }while(startLettersList.contains(startTile.getTileLetter()));
            startLettersList.add(startTile.getTileLetter());
            startTilePlayerMap.put(startTile, player); // need to give the tile back to pool
        }
        //re-ordering player
        for(Tile tile : startTilePlayerMap.keySet())
        {
            TilePool.addTile(tile);
            Player player = startTilePlayerMap.get(tile);
            player.fillRack();
            orderedPlayers.add(startTilePlayerMap.get(tile));

        }
        currentPlayer = orderedPlayers.remove();
        GameDisplay.displayMessage(currentPlayer.getPlayerName()+" is first player");
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
        skippedPlayers.removeIf(player -> player.getPlayerID() == currentPlayer.getPlayerID());
        currentPlayer = orderedPlayers.remove();
    }

    public static boolean takeTurn() 
    {
        boolean isTurnEnd = false;
        boolean isRemove = false;
        do
        {
            GameDisplay.showGeneralDisplay();
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
                            if(tile.getTileLetter().equals("-"))//blank
                            {
                                
                                GameDisplay.displayMessage("Blank tile is chosen");
                                String blankLetter = IOUtils.getString("Please enter A to Z> ");
                                while(!blankLetter.matches("[a-zA-Z]"))
                                {
                                    blankLetter = IOUtils.getString("Please enter A to Z> ");
                                }
                                tile.setShownLetter(blankLetter);

                            }
                            GameDisplay.displayMessageInline("at X =");
                            int positionX = IOUtils.getIntegerInRange("", 1, 15);
                            GameDisplay.displayMessageInline("at Y =");
                            int positionY = IOUtils.getIntegerInRange("", 1, 15);
                            isPlaced = GameBoard.placeTileTo(positionX, positionY, tile);
                            if(!isPlaced)
                                GameDisplay.displayMessage("Please select other square");
                        }while(!isPlaced);
                        tilesUsedCount++;
                        GameDisplay.showGeneralDisplay();
                        answer = IOUtils.getYesOrNo("More tile? (Y or N)> ");
                    }while(answer.equalsIgnoreCase("Y"));
                    boolean isPlaceCorrect = GameBoard.validatePlacement();
                    if(isPlaceCorrect)
                    {
                        challenge();
                        isTurnEnd = true;
                    }
                    else
                    {
                        isTurnEnd = false;
                    }
                    // GameDisplay.displayMessage("Your rack is filled.");
                    // GameDisplay.showCurrentPlayerRack();
                    break;
                case 2: // swap tiles
                    ArrayList<Tile> tiles = new ArrayList<Tile>();
                    do
                    {
                        tile = getTileFromPlayer();
                        tiles.add(tile);
                        GameDisplay.showGeneralDisplay();
                        for(Tile tileToBeSwapped : tiles)
                        {
                            GameDisplay.displayMessageInline("[ "+tileToBeSwapped.getTileLetter()+" ]");
                        }
                        GameDisplay.displayMessage("");
                        answer = IOUtils.getYesOrNo("More tile? (Y or N)> ");
                    }while(answer.equalsIgnoreCase("Y"));
                    currentPlayer.swapTile(tiles);
                    GameDisplay.displayMessage("Your new rack:");
                    GameDisplay.showCurrentPlayerRack();
                    IOUtils.getString("Press any key to continue...");
                    isTurnEnd = true;
                    break;
                case 3: // pass
                    answer = IOUtils.getYesOrNo("Are you sure? (Y or N)> ");
                    if(answer.equalsIgnoreCase("N"))
                    {
                        isTurnEnd = false;
                    }
                    else
                    {
                        GameDisplay.displayMessage(currentPlayer.getPlayerName()+" passed the turn");
                        isTurnEnd = true;
                    }
                    break;
                case 4: // quit
                    removePlayer();
                    isTurnEnd = true;
                    isRemove = true;
                    break;
            }
        }while(!isTurnEnd);
        boolean isEnd = isEnd();
        if(!isEnd && !isRemove)
        {
            nextTurn();
            return false;
        }
        else
        {
            return true;
        }
    }

    public static void nextTurn() 
    {
        //fill up CP tile
        currentPlayer.fillRack();
        orderedPlayers.add(currentPlayer);//enqueue CP to last of queue
        GameBoard.clearCurrenPlacement();
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
        String tileLetter;
        Tile tile;
        tileLetter = IOUtils.getString("Input Tile Letter> ");
        tile = currentPlayer.getTile(tileLetter); 
        while(tile==null)
        {
            GameDisplay.displayMessage("No such tile in your rack");
            tileLetter = IOUtils.getString("Input Tile Letter> ");
            tile = currentPlayer.getTile(tileLetter); 
        }
        return tile;
    }
    private static void challenge() 
    {
        String answer = IOUtils.getYesOrNo("Any player want to challenge? (Y or N)> ");
        if(answer.equalsIgnoreCase("Y"))
        {
            ArrayList<String> allPlayerName = orderedPlayers.stream()
                                                        .map(player->player.getPlayerName())
                                                        .collect(Collectors
                                                        .toCollection(ArrayList::new));
            boolean playerExists = false;
            Player skippedPlayer = null;
            while(!playerExists)
            {
                String playerName = IOUtils.getString("Input player name> ");
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
                int totalScore = GameBoard.calculateLastPlacementScore();
                currentPlayer.updateScore(totalScore);
                skippedPlayers.add(skippedPlayer);
            }
            else
            {
                GameBoard.restoreTileToPlayer();
            }
        }
    }
    public static void main(String[] args) 
    {
        GameDisplay.displayMessage("How many people?");
        int numberOfPlayers = IOUtils.getIntegerInRange("", 2, 4);
        Game.initialize();
        Game.startGame(numberOfPlayers);
        boolean isGameEnd = false;
        do
        {
            isGameEnd = Game.takeTurn();
            System.out.println("End turn");
        }while(!isGameEnd);
    }   
}