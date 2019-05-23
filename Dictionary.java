import java.util.HashMap;
/**
 * Dictionary.java
 * this class represent a dictionary of scrabble game. it can find word and meaning
 * created by Patipon Petchtone 59070501049
 * and        Puwit Yahom       59070501059
 * Date: 20 May 2019
 */
public class Dictionary 
{
    private static HashMap<String,String> dictionary;

    private Dictionary()
    {
        
    }
    /**
     * used to initial the dictionary by add it to hashmap
     */
    public static void initialize()
    {
        String line;
        String word,mean;
        TextFileReader reader = new TextFileReader();
        dictionary = new HashMap<String,String>();
        reader.open("Collins Scrabble Words (2015) with definitions.txt");
        reader.getNextLine();
        reader.getNextLine();
        do
        {
            line = reader.getNextLine();
            if(line == null)
            {
                break;
            }
            String[]splitter = line.split("\t",2);
            word = new String(splitter[0]);
            mean = new String(splitter[1]);
            dictionary.put(word,mean);
        }while(line!= null);
        reader.close();
    }
    /**
     * used to check the word compare with scrabble dictionary
     * @param word input word that need to check
     * @return boolean true when word is in dictionary , otherwise false
     */
    public static boolean isHaveWord(String word)
    {
        return dictionary.containsKey(word.toUpperCase());
    }
    /**
     * get meaning of the word
     * @param word word input
     * @return meaning of word
     */
    public static String getMeaning(String word)
    {
        return dictionary.get(word);
    }
}