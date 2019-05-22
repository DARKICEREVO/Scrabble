
public class Square
{
    private int positionX;
    private int positionY;
    private Tile tileOnSquare;
    private int letterMultiplier;
    private int wordMultiplier;
    private boolean isEffectOn;
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
     * @return the positionX
     */
    public int getPositionX() 
    {
        return positionX;
    }
    /**
     * @return the positionY
     */
    public int getPositionY() 
    {
        return positionY;
    }
    /**
     * @param tileOnSquare the tileOnSquare to set
     */
    public void setTileOnSquare(Tile tileOnSquare) 
    {
        this.tileOnSquare = tileOnSquare;
        calculateCurrentScore();
    }

    /**
     * @return the tileOnSquare
     */
    public Tile getTileOnSquare() 
    {
        Tile tile;
        tile = tileOnSquare;
        tileOnSquare = null;
        return tile;
    }

    /**
     * @return the tileOnSquare
     */
    public Tile peekTileOnSquare() 
    {
        Tile tile;
        tile = tileOnSquare;
        return tile;
    }

    /**
     * @return the letterMultiplier
     */
    public int getLetterMultiplier() 
    {
        return letterMultiplier;
    }

    /**
     * @return the wordMultiplier
     */
    public int getWordMultiplier() 
    {
        return wordMultiplier;
    }

    /**
     * @return the currentScore
     */
    public int getCurrentScore() {
        return currentScore;
    }

    public boolean isEffectOn()
    {
        return isEffectOn;
    }

    public void usedEffect()
    {
        isEffectOn = false;
        calculateCurrentScore();
    }

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

    public void clearSquare()
    {
        tileOnSquare = null;
    }

    private void calculateCurrentScore()
    {
        if(tileOnSquare == null)
        {
            
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