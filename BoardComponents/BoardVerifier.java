package BoardComponents;


import Options.Options;
import java.util.Objects;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class BoardVerifier {
    private final boolean[][] board;
    private final Integer boardSize;

    // variables for checking if a board[row][col] can be chosen as part of a ship.
    private boolean horizontalBool;
    private boolean verticalBool;


    private Integer minRow;
    private Integer maxRow;
    private Integer minCol;
    private Integer maxCol;



    public BoardVerifier(Options option) {
        this.boardSize = option.getBoardSize();
        this.board = new boolean[this.boardSize][this.boardSize];
        this.minRow = -1;
        this.maxRow = -1;
        this.minCol = -1;
        this.maxCol = -1;
    }

    public boolean updateBoolBoard(Integer row, Integer col, Integer placingLeft){
        if (this.checkPlacing(row, col, placingLeft)){
            this.board[row][col] = true;
            if (placingLeft == 1){
                this.minRow = -1;
                this.maxRow = -1;
                this.minCol = -1;
                this.maxCol = -1;
            }
            return true;
        }
        else return false;
    }



    boolean checkPlacing(Integer row, Integer col, Integer placingLeft) {
        if (placingLeft == 0 || this.board[row][col]) {
            return false;
        }
        if (this.minCol == -1) {
            this.horizontalBool = this.setHorizontalBool(row, col, placingLeft);
            this.verticalBool = this.setVerticalBool(row, col, placingLeft);
            if (this.verticalBool || this.horizontalBool){
                this.minCol = col;
                this.maxCol = col;
                this.maxRow = row;
                this.minRow = row;
                return true;
            }
            else{
                return false;
            }

        } else {
            if (Objects.equals(row, this.maxRow) && (Objects.equals(row, this.minRow))) {
                if (this.horizontalBool) {
                    if (Objects.equals(col, this.minCol - 1)) {
                        this.minCol--;
                        return true;
                    }
                    else if (Objects.equals(col, this.maxCol + 1)) {
                        this.maxCol++;
                        return true;
                    } else return false;
                }
            } else if (Objects.equals(col, this.maxCol) && (Objects.equals(col, this.minCol))) {
                if (this.verticalBool) {
                    if (Objects.equals(row, this.minRow - 1)) {
                        this.minRow--;
                        return true;
                    } else if (Objects.equals(row, this.maxRow + 1)) {
                        this.maxRow++;
                        return true;
                    }
                    else return false;
                }
            }
            return  false;
        }
    }


    private boolean setHorizontalBool(Integer row, Integer col, Integer placingLeft){
        int sums = 0;
        for (int i = col; i < min(boardSize, col + placingLeft); i++) {
            if (!this.board[row][i]){
                sums++;
            }
            else{
                break;
            }
        }
        for (int i = col-1; i > max(-1,col - placingLeft); i--) {
            if (!this.board[row][i]){
                sums++;
            }
            else{
                break;
            }
        }
        return sums>=placingLeft;
    }

    private boolean setVerticalBool(Integer row, Integer col, Integer placingLeft){
        int sums = 0;
        for (int i = row; i < min(boardSize,row + placingLeft); i++) {
            if (!this.board[i][col]){
                sums++;
            }
            else{
                break;
            }
        }
        for (int i = row-1; i > max(-1,row - placingLeft); i--) {
            if (!this.board[i][col]){
                sums++;
            }
            else{
                break;
            }
        }
        return sums>= placingLeft;
    }

    public boolean[][] getBoard(){
        return this.board;
    }
}
