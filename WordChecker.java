package Scrabble;

import java.util.ArrayList;
import java.util.Iterator;

public class WordChecker
{
    private ArrayList<String> words;

    public WordChecker(ArrayList<String> words)
    {
        this.words = new ArrayList<String>();
        this.words = words;
    }

    public ArrayList<String> getWords()
    {
        return words;
    }

    public void updateLastWords()
    {
        words = GameBoard.formWord();
    }

    public boolean verifyLastWords()
    {
        boolean isOk = true;;
        Iterator<String> iterator = words.iterator();
        String targetWord;
        while(iterator.hasNext())
        {
            targetWord = iterator.next();
            if(Dictionary.isHaveWord(targetWord))
            {
                isOk = isOk && true;
            }
            else
            {
                isOk = isOk && false;
            }
        }
        return isOk;
    }
}