/**
 * TilePool.java
 * This class represents bag of tile in Scrabble game that
 * contains all tiles of the game
 * created by Patipon Petchtone 59070501049
 * and        Puwit Yahom       59070501059
 * Date: 20 May 2019
 */
public class TilePool 
{
    /** Group of tiles */
    private static TileCollection tilePool = new TileCollection(0, 100);
    
    /**
     * Prevent Construction of instance (singleton)
     */
    private TilePool() {}
    
    /**
     * Register all tiles in Scrabble game using enum
     */
    public static void initialize()
    {
        //Clear previous tiles first
        tilePool.clear();
        /*
        Enumarate through all tiles (A-Z + Blank)
        */
        int count=0;
        for (ScrabbleTileEnum enumTile : ScrabbleTileEnum.values()) 
        {
            /*
             * Loop over each alphabet for its duplication
             * (e.g. Tile "A" got 9 duplicates)
             */
            for (int tileCount = 0; tileCount < enumTile.getCount(); tileCount++) 
            {
                tilePool.addTile(new Tile(count,enumTile.getLetter(),enumTile.getValue()));
                count++; //use increment id
            }
        }
    }
    /**
     * Add new tile to the pool
     * @param tile tile to be added
     * @return  true if tile count is in boundary, 
     *          false otherwise
     */
    public static boolean addTile(Tile tile) 
    {
        return tilePool.addTile(tile);
    }

    /**
     * Get number of tiles left in the pool
     * @return  number of tiles in the pool
     */
    public static int getTileCount() 
    {
        return tilePool.getTileCount();
    }

    /**
     * Get random tile from the pool
     * @return  tile that is randomly selected
     */
    public static Tile selectRandomTile()
    {
        return tilePool.getRandom();
    }
    
    /**
     * Show all tile in pool
     */
    public static void showTile()
    {
        tilePool.showTiles();
    }
    
}