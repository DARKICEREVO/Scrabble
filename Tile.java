/**
 * Tile
 */
public class Tile 
{
    private int tileID;
    private String tileLetter;
    private int tileValue;
    private String shownLetter;
    public Tile(int id,String letter,int value) 
    {
        tileID = id;
        tileLetter = new String(letter);
        shownLetter = new String(letter);
        tileValue = value;
    }

    /**
     * @return the tileID
     */
    public int getTileID() 
    {
        return tileID;
    }
    /**
     * @return the tileLetter
     */
    public String getTileLetter() 
    {
        return tileLetter;
    }
    /**
     * @return the tileValue
     */
    public int getTileValue() 
    {
        return tileValue;
    }

    /**
     * @param shownLetter the shownLetter to set
     */
    public void setShownLetter(String shownLetter) 
    {
        this.shownLetter = shownLetter;
    }
    /**
     * @return the shownLetter
     */
    public String getShownLetter() 
    {
        return shownLetter;
    }
    
}