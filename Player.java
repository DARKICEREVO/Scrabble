import java.util.ArrayList;

public class Player
{
    private int playerID;
    private String playerName;
    private TileCollection playerTiles;
    private int score;

    public Player(int playerID,String playerName)
    {
        this.playerID = playerID;
        this.playerName = playerName;
        playerTiles = new TileCollection();
        this.score = 0;

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
        // we can't do that here we dont have getTile
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

    public void swapTile(int[] tilesID)
    {
        int i;
        ArrayList<Tile> tmpCollection = new ArrayList<Tile>();
        for(i=0 ;i< tilesID.length ;i++)
        {
            tmpCollection.add(playerTiles.getTile(tilesID[i]));
        }
        selectTiles(tilesID.length);
        for(i=0 ;i< tilesID.length ;i++)
        {
            TilePool.addTile(tmpCollection.get(i));
        }
    }

}