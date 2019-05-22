import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Player
{
    private int playerID;
    private String playerName;
    private TileCollection playerTiles;
    private int score;
    private static HashMap<String,Player> allPlayer = new HashMap<String,Player>();
    private static int counter = 1;

    public Player(String playerName)
    {
        this.playerID = counter;
        this.playerName = playerName;
        playerTiles = new TileCollection(0,7);
        this.score = 0;
        allPlayer.put(playerName,this);
        counter++;
    }
    
    public int getPlayerID()
    {
        return playerID;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public int getScore()
    {
        return score;
    }

    public static ArrayList<String> getAllPlayersName() 
    {
        return allPlayer.keySet().stream()
                            .collect(Collectors
                            .toCollection(ArrayList::new));
    }
    public static Player getPlayerByName(String name)
    {
        return allPlayer.get(name);
    }

    public static void removePlayerByName(String name)
    {
        allPlayer.remove(name);
    }

    public void updateScore(int score)
    {
        this.score = this.score + score;
    }

    public void clearScore()
    {
        score = 0;
    }

    public boolean addTile(Tile tile)
    {
        boolean isComplete;
        isComplete = playerTiles.addTile(tile);
        return isComplete;
    }

    public int getPlayerTilesCount()
    {
        return playerTiles.getTileCount();
    }

    public Tile getTile(int tileID)
    {
        return playerTiles.getTile(tileID);
    }

    public Tile getTile(String letter)
    {
        return playerTiles.getTile(letter);
    }

    public void showTile()
    {
        playerTiles.showTiles();
    }

    public Tile getStartTile()
    {
        Tile tempStartTile;
        tempStartTile = TilePool.selectRandomTile();
        return tempStartTile;
        
    }

    public void selectTiles(int numberOfTile)
    {
        int i;
        for(i=0 ;i < numberOfTile ;i++)
        {
            playerTiles.addTile(TilePool.selectRandomTile());
        }
    }
    public void swapTile(ArrayList<Tile> tiles)
    {
        selectTiles(tiles.size());
        for(Tile tile:tiles)
        {
            TilePool.addTile(tile);
        }
    }

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