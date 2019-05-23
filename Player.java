import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
/**
 * Player.java
 * this class represent each player of the game
 * created by Patipon Petchtone 59070501049
 * and        Puwit Yahom       59070501059
 * Date: 20 May 2019
 */
public class Player
{
    /**ID of Player */
    private int playerID;
    /**name of player */
    private String playerName;
    /**tiles on player hand or rack */
    private TileCollection playerTiles;
    /** score of player */
    private int score;
    /** allplayer in the game */
    private static HashMap<String,Player> allPlayers = new HashMap<String,Player>();
    /** counter used to count and give id to player */
    private static int counter = 1;

    public Player(String playerName)
    {
        this.playerID = counter;
        this.playerName = playerName;
        playerTiles = new TileCollection(0,7);
        this.score = 0;
        allPlayers.put(playerName,this);
        counter++;
    }
    /**
     * get id of player
     * @return id of player
     */
    public int getPlayerID()
    {
        return playerID;
    }
    /**
     * get name of player
     * @return name of player
     */
    public String getPlayerName()
    {
        return playerName;
    }
    /**
     * get score of player
     * @return score of player
     */
    public int getScore()
    {
        return score;
    }
    /**
     * get all player name as string
     * @return all player names 
     */
    public static ArrayList<String> getAllPlayersName() 
    {
        return allPlayers.keySet().stream()
                            .collect(Collectors
                            .toCollection(ArrayList::new));
    }
    /**
     * get player by using name as identifier
     * @param name name of the player
     * @return player object
     */
    public static Player getPlayerByName(String name)
    {
        return allPlayers.get(name);
    }
    /**
     * remove player by using name as identifier
     * @param name name of player that you need to remove
     */
    public static void removePlayerByName(String name)
    {
        allPlayers.remove(name);
    }
    /**
     * update score of player
     * @param score score that need to add more
     */
    public void updateScore(int score)
    {
        this.score = this.score + score;
    }
    /**
     * reset score of player to zero
     */
    public void clearScore()
    {
        score = 0;
    }
    /**
     * add tile to player rack or hand
     * @param tile tile that need to add
     * @return true if it can add ,otherwise false 
     */
    public boolean addTile(Tile tile)
    {
        boolean isComplete;
        isComplete = playerTiles.addTile(tile);
        return isComplete;
    }
    /**
     * get tile count of player
     * @return number of tile in player hand or rack
     */
    public int getPlayerTilesCount()
    {
        return playerTiles.getTileCount();
    }
    /**
     * get tile by using id as identifier
     * @param tileID id of the tile
     * @return tile corresponding to id 
     */
    public Tile getTile(int tileID)
    {
        return playerTiles.getTile(tileID);
    }
    /**
     * get tile by using letter as identifier
     * @param letter letter of the tile
     * @return tile corresponding to letter
     */
    public Tile getTile(String letter)
    {
        return playerTiles.getTile(letter);
    }
    /**
     * show tile in player's hand or rack
     */
    public void showTile()
    {
        playerTiles.showTiles();
    }
    /**
     * get a random tile for player in the first time
     * @return random tile from pool
     */
    public Tile getStartTile()
    {
        Tile tempStartTile;
        tempStartTile = TilePool.selectRandomTile();
        return tempStartTile;
        
    }
    /**
     * select random tile from pool
     * @param numberOfTiles number of tile that you need to pick from pool
     */
    public void selectTiles(int numberOfTiles)
    {
        int i;
        for(i=0 ;i < numberOfTiles ;i++)
        {
            playerTiles.addTile(TilePool.selectRandomTile());
        }
    }
    /**
     * swap tile in player hand or rack with tilepool
     * @param tiles tile that player want to swap it with pool
     */
    public void swapTile(ArrayList<Tile> tiles)
    {
        selectTiles(tiles.size());
        for(Tile tile:tiles)
        {
            TilePool.addTile(tile);
        }
    }
    /**
     * fill rack until full
     */
    public void fillRack()
    {
        int tileRemain = 7-playerTiles.getTileCount();
        if(TilePool.getTileCount()>=tileRemain)
        {
            selectTiles(tileRemain);
            
        }
        else
        {
            selectTiles(TilePool.getTileCount());
            
        }
        
    }

}