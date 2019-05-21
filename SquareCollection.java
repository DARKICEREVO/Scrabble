import java.util.ArrayList;

public class SquareCollection
{
    private ArrayList<Square> word;

    public SquareCollection(ArrayList<Square> word)
    {
        this.word = word;
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
    }

    public void addSquareToWord(Square square)
    {
        word.add(square);
    }
}