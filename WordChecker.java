package Scrabble;

import java.util.ArrayList;

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

    public void updateLastWord()
    {
        words = GameBoard.formWord();
    }

    public boolean verifyLastWord()
    {
        
    }
}