package model.strategy;

/**
 * Table must be [4][2] ! otherwise throws NPE. Read constructor doc for details.
 */
public class ScoringTable {

    public int[][] table;

    /**
     * @param table first index should always represent index for possibilities, second is the index of playe
     * in order 00 01 10 11 represented decimally (00 = 0, 01 = 1, 10 = 2, 11 = 3). Zero is cooperation and one is betrayal.
     * Example: [3][1] stores score for second player, in case of "11" combination = both players betrayed.
     */
    public ScoringTable(int[][] table)  {
        int tmp;
        for (int i = 0; i<4; i++) {
            for (int j = 0; j<2; j++) {
                  tmp = table.length;   //  IF THIS CAUSED NULLPTR, YOUR TABLE IS NOT [2][4], READ JAVADOC !
            }
        }
        this.table = table;
    }


    public void print() {
        for (int j = 0; j<4; j++) {
           System.out.println(String.format("%2s", Integer.toBinaryString(j)).replace(' ', '0') + " | " +  table[j][0] + " " + table[j][1]);
        }
    }
}
