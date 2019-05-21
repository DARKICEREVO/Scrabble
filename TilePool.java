/**
 * TilePool
 */
public class TilePool 
{

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
    public static boolean addTile(Tile tile) 
    {
        return tilePool.addTile(tile);
    }

    public static int getTileCount() 
    {
        return tilePool.getTileCount();
    }

    public static Tile selectRandomTile()
    {
        return tilePool.getRandom();
    }

    public static void showTile()
    {
        tilePool.showTiles();
    }
    
}