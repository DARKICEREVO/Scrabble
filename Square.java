
public class Square
{
    private int positionX;
    private int positionY;
    private Tile tileOnSquare;
    private int letterMultiplier;
    private int wordMultiplier;

    public Square()
    {

    }

    /**
     * @param tileOnSquare the tileOnSquare to set
     */
    public void setTileOnSquare(Tile tileOnSquare) {
        this.tileOnSquare = tileOnSquare;
    }

    /**
     * @return the tileOnSquare
     */
    public Tile getTileOnSquare() {
        return tileOnSquare;
    }

    /**
     * @return the letterMultiplier
     */
    public int getLetterMultiplier() {
        return letterMultiplier;
    }

    /**
     * @return the wordMultiplier
     */
    public int getWordMultiplier() {
        return wordMultiplier;
    }

    public boolean isEmpty()
    {

    }

    public void clearSquare()
    {
        
    }

}