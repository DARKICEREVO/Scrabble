/**
 * Tile.java
 * This class represents a single tile with score and letter
 * contains all tiles of the game
 * created by Patipon Petchtone 59070501049
 * and        Puwit Yahom       59070501059
 * Date: 20 May 2019
 */
public class Tile 
{
    /** ID of tile */
    private int tileID;
    /** Letter on tile */
    private String tileLetter;
    /** Score of tile */
    private int tileValue;
    /** Letter of tile that actually show on board (in case of blank tile) */
    private String shownLetter;
    /**
     * Tile constuctor for creating one tile with id,letter, and score
     * @param id        id of tile
     * @param letter    letter on tile
     * @param value     score of tile
     */
    public Tile(int id,String letter,int value) 
    {
        tileID = id;
        tileLetter = new String(letter);
        shownLetter = new String(letter);
        tileValue = value;
    }

    /**
     * Get ID of tile
     * @return the tileID
     */
    public int getTileID() 
    {
        return tileID;
    }
    /**
     * Get letter of tile
     * @return the tileLetter
     */
    public String getTileLetter() 
    {
        return tileLetter;
    }
    /**
     * Get score of tile
     * @return the tileValue
     */
    public int getTileValue() 
    {
        return tileValue;
    }

    /**
     * Set letter to be shown on board (in case of blank tile)
     * @param shownLetter the letter to be shown
     */
    public void setShownLetter(String shownLetter) 
    {
        this.shownLetter = shownLetter;
    }
    /**
     * Get letter to be shown on board
     * @return letter to be shown
     */
    public String getShownLetter() 
    {
        return shownLetter;
    }
    
}