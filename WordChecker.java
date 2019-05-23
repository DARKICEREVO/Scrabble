import java.util.ArrayList;
import java.util.Iterator;
/**
 * WordChecker.java
 * Class for check if word is in the dictionary
 * created by Patipon Petchtone 59070501049
 * and        Puwit Yahom       59070501059
 * Date: 20 May 2019
 */
public class WordChecker
{
    /** List of word from last placement on the board*/
    private static ArrayList<String> words = new ArrayList<String>();

    /**Prevent instantiate for singleton */
    private WordChecker()
    {
    }

    /**
     * Retreive list of word from last placement
     * @return list of word from last placement
     */
    public static ArrayList<String> getWords()
    {
        return words;
    }

    /**
     * Use to set new list of word
     * @param newWords word from last placement
     */
    public static void updateLastWords(ArrayList<String> newWords)
    {
        words = newWords;
    }

    /**
     * Verify if all word in list is in Scrabble's dictionary
     * @return true if word is in dictionary,
     *          false otherwise
     */
    public static boolean verifyLastWords()
    {
        boolean isOk = true;
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