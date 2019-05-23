import java.util.ArrayList;
import java.util.Iterator;

/**
 * SquareCollection.java
 * This class represents a group of square(or a word)
 * contains all tiles of the game
 * created by Patipon Petchtone 59070501049
 * and        Puwit Yahom       59070501059
 * Date: 20 May 2019
 */
public class SquareCollection
{
    /** Group of square  */
    private ArrayList<Square> word;

    /**
     * Square constructor to form a word
     * @param word  Group of square to be form into word
     */
    public SquareCollection(ArrayList<Square> word)
    {
        this.word = word;
        calculateScore();
    }

    /**
     * Get collection of square (Word)
     * @return the word
     */
    public ArrayList<Square> getWord() 
    {
        return word;
    }
    /**
     * Set collection of square (Word) 
     * @param word the word to set
     */
    public void setWord(ArrayList<Square> word) 
    {
        this.word = word;
        calculateScore();
    }

    /**
     * Add square to collection
     * @param square square to be added
     */
    public void addSquareToWord(Square square)
    {
        word.add(square);
        calculateScore();
    }
    /**
     * Calculate total score of word 
     * @return  total score of word
     */
    public int calculateScore()
    {
        int score=0;
        int wordMultiplier = 1;
        Iterator<Square> squareIterator = word.iterator();
        Square targetSquare;
        while(squareIterator.hasNext())
        {
            targetSquare = squareIterator.next();
            score = score + targetSquare.getCurrentScore();
            if(targetSquare.isEffectOn())
            {
                wordMultiplier = wordMultiplier * targetSquare.getWordMultiplier();
            }
        }
        score = score * wordMultiplier;
        return score;
    }
    /**
     * Unset multiplier of square after use
     */
    public void setSquareEffectOff()
    {
        Iterator<Square> squareIterator = word.iterator();
        Square targetSquare;
        while(squareIterator.hasNext())
        {
            targetSquare = squareIterator.next();
            targetSquare.useEffect();
        }
       
    }
    /**
     * Return collection as string (or return word itself)
     * @return  String of word
     */
    public String getStringOfCollection()
    {
        String text = new String();
        Iterator<Square> squareIterator = word.iterator();
        Square targetSquare;
        while(squareIterator.hasNext())
        {
            targetSquare = squareIterator.next();
            text+=targetSquare.peekTileOnSquare().getTileLetter();
        }
        return text;
    }
}