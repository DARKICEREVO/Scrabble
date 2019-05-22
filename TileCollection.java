import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

/**
 * TileCollection
 */
/**
 * TileComparator class
 * used to tell how to compare 2 tiles for ordering purpose
 * (Require for TreeSet)
 *  Created by Puwit Yahom 17 March 2019
 */
class TileComparator implements Comparator<Tile>
{
    /**
     * compare 2 Tiles by their score (using t1 as reference point)
     * @param   t1  tile of interest (reference point)
     * @param   t2  tile for comparison
     * @return  negative integer, zero, or positive integer
     *          for telling if t1 is less than, equal, or greater
     *          than t2 respectively
     */
    @Override
    public int compare(Tile t1, Tile t2) 
    {
        return t1.getTileID() - t2.getTileID();
    }
    
}
/**
 * TileCollection class
 * serve as general collection of tiles for tile pool 
 * and tile rack (for player)
 * 
 * Created by Puwit Yahom 22 May 2019
 */
public class TileCollection 
{
    /** use treeset for auto sort  */
    private TreeSet<Tile> tiles = new TreeSet<>(new TileComparator());
    /** minimum number of tile possible */
    private int minTiles;
    /** maximum number of tile possible */
    private int maxTiles;
    
    /**
     * Contructor creates a new TileCollection by setting
     * boundary of number of tiles possible
     * @param minTiles minimum number of tile possible
     * @param maxTiles maximum number of tile possible
     */
    public TileCollection(int minTiles, int maxTiles) 
    {
        this.minTiles = minTiles;
        this.maxTiles = maxTiles;
    }


    /**
     * print all tiles in collection
     */
    public void showTiles()
    {
        Iterator<Tile> tileIterator = tiles.iterator();
        int count = 0;
        while(tileIterator.hasNext())
        {
            Tile currentTile = tileIterator.next();
            System.out.print("["+currentTile.getTileLetter()+" ");
            System.out.print(""+currentTile.getTileID()+"]");

            count++;
            if(count % 10 == 0)
                System.out.println("");
        }
        System.out.println("");
    }

    /**
     * add new tile to collection
     * @param tile tile to be added to collection
     * @return  true number of tiles has not reached maximum 
     *          false number of tiles is at maximum
     */
    public boolean addTile(Tile tile)
    {
        if(tiles.size() + 1 > maxTiles)
            return false;
        else
        {
            tiles.add(tile);
            return true;
        }
    }
    
    /**
     * 
     * @param tileID
     * @return
     */
    public boolean removeTile(int tileID) 
    {
        if(tiles.size() > 0)
        {
            return tiles.removeIf(tile -> (tile.getTileID() == tileID));
        }
        else
        {
            return false;
        }
    }

    public int getTileCount()
    {
        return tiles.size();
    }

    public Tile getTile(int tileID)
    {
        if(tiles.size() >= 1)
        {
            for(Tile tile : tiles)
            {
                if(tile.getTileID() == tileID)
                {
                    removeTile(tile.getTileID());
                    return tile;
                }
            }
        }
        return null;
    }

    public Tile getTile(String letter)
    {
        if(tiles.size() >= 1)
        {
            for(Tile tile : tiles)
            {
                if(tile.getTileLetter().equalsIgnoreCase(letter))
                {
                    removeTile(tile.getTileID());
                    return tile;
                }
            }
        }
        return null;
    }
    public Tile peekTile(int tileID)
    {
        if(tiles.size() >= 1)
        {
            for(Tile tile : tiles)
            {
                if(tile.getTileID() == tileID)
                {
                    return tile;
                }
            }
        }
        return null;
    }
    /**
     * Return randomly selected tile from the set
     * @return  randomly selected tile
     */
    public Tile getRandom()
    {
        if(tiles.size() >= 1)
        {
            /*
             *  randomly pick one number within range of set's size 
             *  using current time in millisec. for the seed for more randomness
             */
            int randomIndex =  new Random(System.currentTimeMillis()).nextInt(tiles.size());
            int i = 0;
            /*
            Iterate over all tiles in set to get
            the tile with index equal to randomly selected number
            */
            for (Tile tile : tiles) 
            {
                if(i == randomIndex)
                {
                    removeTile(tile.getTileID());
                    return tile;
                }
                i++;
            }
        }
        return null;
    }

    public void clear()
    {
        tiles.clear();
    }
}
