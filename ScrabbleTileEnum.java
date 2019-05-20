/**
 * ScrabbleTile enum
 * enum file for initialize tile in the game
 * Created by Puwit Yahom 17 March 2019
 */
public enum ScrabbleTileEnum
{
    /*
    Create enum for all tiles in game
    with corresponding score and their duplicates
    */
    BLANK("-",0,2),
    A("A",1,9),
    B("B",3,2),
    C("C",3,2),
    D("D",2,4),
    E("E",1,12),
    F("F",4,2),
    G("G",2,3),
    H("H",4,2),
    I("I",1,9),
    J("J",8,1),
    K("K",5,1),
    L("L",1,4),
    M("M",3,2),
    N("N",1,6),
    O("O",1,8),
    P("P",3,2),
    Q("Q",10,1),
    R("R",1,6),
    S("S",1,4),
    T("T",1,6),
    U("U",1,4),
    V("V",4,2),
    W("W",4,2),
    X("X",8,1),
    Y("Y",4,2),
    Z("Z",10,1);
    //Letter of the tile
    private String letter;
    //Score of the tile
    private int value;
    //Number of duplication
    private int count;
    /**
     * Constructor set letter,score, 
     * and number of duplication for each tile
     * @param letter    letter of the tile
     * @param value     score of the tile
     * @param count     number of duplication
     */
    ScrabbleTileEnum(String letter,int value,int count) 
    {
        this.letter = letter;
        this.value = value;
        this.count = count;
    } 
    /**
     * Return letter of the tile
     * @return letter of the tile
     */
    public String getLetter() 
    {
        return letter;
    }
    /**
     * Return score of the tile
     * @return score of the tile
     */
    public int getValue() 
    {
        return value;
    }
    /**
     * Return number of duplication of the tile
     * @return number of duplication
     */
    public int getCount() 
    {
        return count;
    }
}