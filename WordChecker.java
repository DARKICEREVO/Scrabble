

import java.util.ArrayList;
import java.util.Iterator;

public class WordChecker
{
    private static ArrayList<String> words = new ArrayList<String>();

    public WordChecker()
    {
       
    }

    public static ArrayList<String> getWords()
    {
        return words;
    }

    public static void updateLastWords()
    {
        words = GameBoard.formWord();
    }

    public static boolean verifyLastWords()
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