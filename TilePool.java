/**
 * TilePool
 */
public class TilePool 
{

    private TileCollection tilePool;
    
    private TilePool() {}

    private void initialize()
    {

    }
    
    public boolean addTile(Tile tile) 
    {
        return tilePool.addTile(tile);
    }

    public int getTileCount() 
    {
        return tilePool.getTileCount();
    }

    public Tile selectRandomTile()
    {
        return tilePool.getRandom();
    }

    public void showTile()
    {
        tilePool.showTiles();
    }
    
}