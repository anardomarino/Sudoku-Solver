import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;
public class Solver{
    static Scanner sc = new Scanner(System.in);
    public static void finish(Square[] arr){ // fix
        int numFree = 0;
        for(Square x : arr)
            if(x.getVal() == 0)
                numFree++;
        for(int i = 1; i < 10; i++){
            int count = 0;
            for(Square x : arr){
                if(x.getMarkOffs().contains(i) && x.getVal() == 0){
                    count ++;
                }
            }
            if(count != numFree-1)
                continue;
            for(int j = 0; j < arr.length; j++){
                if(!arr[j].getMarkOffs().contains(i) && arr[j].getVal() == 0){
                    arr[j].setVal(i);
                    return;
                }
            }
        }
    }
    public static void recurse(Board board){
        ArrayList<Square> empty = new ArrayList<Square>();
        for(int i = 0; i < 9; i ++)
            for(int j = 0; j < 9; j ++)
                if(board.getSquare(i,j).getVal() == 0)
                    empty.add(board.getSquare(i,j));
        if(empty.size() == 0)
            return;
        straighten(empty, board);
        while(!board.isValid(empty.get(empty.size()-1))){
            empty.get(empty.size()-1).inc();
        }
    }
    public static void straighten(ArrayList<Square> empty, Board board){
        while(!board.isFilled()){
            empty.get(0).inc();
            if(empty.get(0).getVal() == 0)
                return;
            if(!board.isValid(empty.get(0)))
                continue;
            ArrayList<Square> temp = new ArrayList<Square>();
            for(int i = 1; i < empty.size(); i++)
                temp.add(empty.get(i));
            straighten(temp, board);
        }
    }
    public static void main(String[] args){
        int[][] temp = new int[9][9];
        for(int i = 0; i < 9; i ++)
            for(int j = 0; j < 9; j++)
                temp[i][j] = sc.nextInt();
        Board board = new Board(temp);
        System.out.println();
        int tries = 0;
        while(!board.isFilled()){
            tries ++;
            int keep = Square.changes;
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    if(board.getSquare(i, j).getVal() == 0){
                        for(Square k : board.row(i)){
                            board.getSquare(i, j).markOff(k.getVal());
                        }
                        for(Square k : board.column(j)){
                            board.getSquare(i, j).markOff(k.getVal());
                        }
                        for(Square k : board.square(i, j)){
                            board.getSquare(i, j).markOff(k.getVal());
                        }
                        board.getSquare(i, j).mark();
                    }
                }
            }
            if(Square.changes == keep)
                break;
        }
        if(!board.isFilled()){
            System.out.println("Recursing...");
            recurse(board);
            
            System.out.println("Recursed");
            System.out.println();
        }
        System.out.println(board);
    }
}