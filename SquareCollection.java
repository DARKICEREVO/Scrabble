import java.util.ArrayList;
import java.util.Iterator;

public class SquareCollection
{
    private ArrayList<Square> word;

    public SquareCollection(ArrayList<Square> word)
    {
        this.word = word;
        calculateScore();
    }

    /**
     * @return the word
     */
    public ArrayList<Square> getWord() 
    {
        return word;
    }
    /**
     * @param word the word to set
     */
    public void setWord(ArrayList<Square> word) 
    {
        this.word = word;
        calculateScore();
    }

    public void addSquareToWord(Square square)
    {
        word.add(square);
        calculateScore();
    }

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

    public void setSquareEffectOff()
    {
        Iterator<Square> squareIterator = word.iterator();
        Square targetSquare;
        while(squareIterator.hasNext())
        {
            targetSquare = squareIterator.next();
            targetSquare.usedEffect();
        }
       
    }

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