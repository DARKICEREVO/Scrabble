/**
 * Square.java
 * Represent each square in boardgame.this include calculation of each square
 * Created by Patipon Petchtone 17 May 2019
 */
public class Square
{
    /** position x of square in grid */
    private int positionX;
    /** position y of square in grid */
    private int positionY;
    /** tile on square */
    private Tile tileOnSquare;
    /** letter multiplier of square default = 1 */
    private int letterMultiplier;
    /** word multiplier of square default = 1 */
    private int wordMultiplier;
    /** is Effect now effective */
    private boolean isEffectOn;
    /** current score of square */
    private int currentScore;

    public Square(int posX,int posY,int letterMultiplier,int wordMultiplier)
    {
        this.positionX = posX;
        this.positionY = posY;
        this.letterMultiplier = letterMultiplier;
        this.wordMultiplier = wordMultiplier;
        this.isEffectOn = true;
    }

    /**
     * get position x of square
     * @return the positionX
     */
    public int getPositionX() 
    {
        return positionX;
    }
    /**
     * get position y of square
     * @return the positionY
     */
    public int getPositionY() 
    {
        return positionY;
    }
    /**
     * set tile on square
     * @param tileOnSquare the tileOnSquare to set
     */
    public void setTileOnSquare(Tile tileOnSquare) 
    {
        this.tileOnSquare = tileOnSquare;
        calculateCurrentScore();
    }

    /**
     * get tile on square and remove tile from square
     * @return the tileOnSquare
     */
    public Tile getTileOnSquare() 
    {
        Tile tile;
        tile = tileOnSquare;
        tileOnSquare = null;
        currentScore = 0;
        return tile;
    }

    /**
     * peek(see) tile on square without remove it from square
     * @return the tileOnSquare
     */
    public Tile peekTileOnSquare() 
    {
        Tile tile;
        tile = tileOnSquare;
        return tile;
    }

    /**
     * get letterMultiplier of square
     * @return the letterMultiplier
     */
    public int getLetterMultiplier() 
    {
        return letterMultiplier;
    }

    /**
     * get wordMultiplier of square
     * @return the wordMultiplier
     */
    public int getWordMultiplier() 
    {
        return wordMultiplier;
    }

    /**
     * get current score 
     * @return the currentScore
     */
    public int getCurrentScore() {
        return currentScore;
    }
    /**
     * is effect of word and letter multiplier now active
     * @return true if it's active,otherwise false
     */
    public boolean isEffectOn()
    {
        return isEffectOn;
    }
    /**
     * change status of effect to inactive
     */
    public void useEffect()
    {
        isEffectOn = false;
        calculateCurrentScore();
    }
    /**
     * is square have tile or not ?
     * @return true if it's empty ,otherwise false .
     */
    public boolean isEmpty()
    {
        if(tileOnSquare == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * clear tile from square
     */
    public void clearSquare()
    {
        tileOnSquare = null;
        currentScore = 0;
    }
    /**
     * calculate current score of square by using tile and multiplier
     */
    private void calculateCurrentScore()
    {
        if(tileOnSquare == null)
        {
            currentScore = 0;
        }
        else if (isEffectOn)
        {
            currentScore = tileOnSquare.getTileValue() * letterMultiplier;
        }
        else
        {
            currentScore = tileOnSquare.getTileValue();
        }
        
    }
}