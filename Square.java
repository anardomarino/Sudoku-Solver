import java.util.ArrayList;
/**
 * Single square of a sudoku puzzle
 *
 * @Anthony Nardomarino
 */
public class Square
{
    private int value, row, col;
    private ArrayList<Integer> markOffs = new ArrayList<Integer>();
    public static int changes = 0;
    public Square(int val, int wid, int hei){
        value = val;
        row = wid;
        col = hei;
    }
    
    public ArrayList<Integer> getMarkOffs(){
        return markOffs;
    }

    public int getVal(){
        return value;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    /*
     *  mark a square 1-9
     */
    public void mark(){
        int count = 0;
        int num = 0;
        for(int i = 1; i < 10; i++){
            if(!markOffs.contains(i)){
                count++;
                num = i;
            }
        }
        if(count == 1){
            value = num;
            changes++;
        }
    }
    
    public void setVal(int x){
        value = x;
        changes++;
    }
    
    public String toString(){
        return Integer.toString(value);
    }
    
    public void markOff(int x){
        markOffs.add(x);
    }
    
    public void inc(){
        value++;
        if(value == 10)
            value = 0;
    }
}
