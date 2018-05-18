public class Board{
    private Square[][] board = new Square[9][9];
    /*
     * Board made 9x9
     * filled by a 2d int array premade
     */
    public Board(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                board[i][j] = new Square(arr[i][j], i, j);
            }
        }
    }
    public boolean isFilled(){
        for(int i = 0; i < board.length; i ++)
            for(int j = 0; j < board[i].length; j++)
                if(board[i][j].getVal() == 0)
                    return false;
        return true;
    }
    public String toString(){
        String phrase = "";
        for(Square[] row : board){
            for(Square x : row)
                phrase += x + " ";
            phrase += "\n";
        }
        return phrase;
    }
    public Square getSquare(int x, int y){
        return board[x][y];
    }
    public Square[] row(int x){
        return board[x];
    }
    public Square[] column(int x){
        Square[] arr = new Square[9];
        for(int i = 0; i < 9; i++){
            arr[i] = board[i][x];
        }
        return arr;
    }
    public Square[] square(int row, int column){
        Square[] arr = new Square[9];
        int count = 0;
        int rAdd = row/3;
        int cAdd = column/3;
        for(int r = rAdd*3; r < 3 + rAdd*3; r++){
            for(int c = cAdd*3; c < 3 + cAdd*3; c++){
                arr[count] = board[r][c];
                count++;
            }
        }
        return arr;
    }
    public boolean isValid(Square x){
        if(x.getVal() == 0)
            return false;
        int count = 0;
        for(Square i : row(x.getRow())){
            if(x.getVal() == i.getVal()){
                count++;
            }
        }
        if(count > 1)
            return false;
        count = 0;
        for(Square i : column(x.getCol())){
            if(x.getVal() == i.getVal()){
                count++;
            }
        }
        if(count > 1)
            return false;
        count = 0;
        for(Square i : square(x.getRow(), x.getCol())){
            if(x.getVal() == i.getVal()){
                count++;
            }
        }
        if(count > 1)
            return false;
        return true;
    }
}