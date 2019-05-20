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
        int different = t1.getValue() - t2.getValue();
        // if score is equal, compare by id
        if(different == 0) 
        {
            return t1.getId() - t2.getId();
        }
        return different;
    }
    
}
public class TileCollection 
{
    private TreeSet<Tile> tiles = new TreeSet<>(new TileComparator());
    private int minTiles;
    private int maxTiles;

    public TileCollection(int minTiles, int maxTiles) 
    {
        this.minTiles = minTiles;
        this.maxTiles = maxTiles;
    }
    public void showTiles()
    {
        Iterator<Tile> tileIterator = tiles.iterator();
        int count = 0;
        while(tileIterator.hasNext())
        {
            Tile currentTile = tileIterator.next();
            System.out.println(currentTile + " ");
            count++;
            if(count % 10 == 0)
                System.out.println("");
        }
        System.out.println("");
    }

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

    public boolean removeTile(int tileID) 
    {
    }

    public int getTileCount()
    {
        return tiles.size();
    }

    /**
     * Return randomly selected tile from the set
     * @return  randomly selected tile
     */
    public Tile getRandom()
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
                return tile;
            }
            i++;
        }
        return null;
    }
    
    public void clear()
    {
        tiles.clear();
    }
}
