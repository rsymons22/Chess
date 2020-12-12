import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class King extends Piece {

    public King(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  
    }

    public ArrayList<RedDot> findMoves()
    {
        possibleMoves = new ArrayList<RedDot>();

        Piece[][] boardArray = board.getBoardArray();
        
        if(getCurrentRow() - 1 >= 0 && getCurrentColumn() + 1 <= 7) {
            kingMove(getCurrentRow() - 1, getCurrentColumn() + 1, boardArray);
        }
        
        if(getCurrentColumn() + 1 <= 7) {
            kingMove(getCurrentRow(), getCurrentColumn() + 1, boardArray);
        }
        
        if(getCurrentRow() + 1 <= 7 && getCurrentColumn() + 1 <= 7) {
            kingMove(getCurrentRow() + 1, getCurrentColumn() + 1, boardArray);
        }
        
        if(getCurrentRow() + 1 <= 7) {
            kingMove(getCurrentRow() + 1, getCurrentColumn(), boardArray);
        }
        
        if(getCurrentRow() + 1 <= 7 && getCurrentColumn() - 1 >= 0) {
            kingMove(getCurrentRow() + 1, getCurrentColumn() - 1, boardArray);
        }
        
        if(getCurrentColumn() - 1 >= 0) {
            kingMove(getCurrentRow(), getCurrentColumn() - 1, boardArray);
        }
        
        if(getCurrentRow() - 1 >= 0 && getCurrentColumn() - 1 >= 0) {
            kingMove(getCurrentRow() - 1, getCurrentColumn() - 1, boardArray);
        }
        
        if(getCurrentRow() - 1 >= 0) {
            kingMove(getCurrentRow() - 1, getCurrentColumn(), boardArray);
        }
        
        return possibleMoves;
    }

    private void kingMove(int row, int column, Piece[][] boardArray) {
        if(boardArray[row][column] == null ||
          (boardArray[row][column].getTeamColor() != teamColor))
        {
            possibleMoves.add(new RedDot(row, column, board));
        }
    }
}