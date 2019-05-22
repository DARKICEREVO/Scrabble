import java.util.HashMap;

public class Dictionary 
{
    private static HashMap<String,String> dictionary;

    private Dictionary()
    {
        
    }

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
            String[]splitter = line.split(" ",2);
            word = new String(splitter[0]);
            mean = new String(splitter[1]);
            dictionary.put(word,mean);
        }while(line!= null);
        reader.close();
    }

    public static boolean isHaveWord(String word)
    {
        return dictionary.containsKey(word.toUpperCase());
    }

    public static String getMeaning(String word)
    {
        return dictionary.get(word);
    }
}