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
        
        if(currentRow - 1 >= 0 && currentColumn + 1 <= 7) {
            kingMove(currentRow - 1, currentColumn + 1, boardArray);
        }
        
        if(currentColumn + 1 <= 7) {
            kingMove(currentRow, currentColumn + 1, boardArray);
        }
        
        if(currentRow + 1 <= 7 && currentColumn + 1 <= 7) {
            kingMove(currentRow + 1, currentColumn + 1, boardArray);
        }
        
        if(currentRow + 1 <= 7) {
            kingMove(currentRow + 1, currentColumn, boardArray);
        }
        
        if(currentRow + 1 <= 7 && currentColumn - 1 >= 0) {
            kingMove(currentRow + 1, currentColumn - 1, boardArray);
        }
        
        if(currentColumn - 1 >= 0) {
            kingMove(currentRow, currentColumn - 1, boardArray);
        }
        
        if(currentRow - 1 >= 0 && currentColumn - 1 >= 0) {
            kingMove(currentRow - 1, currentColumn - 1, boardArray);
        }
        
        if(currentRow - 1 >= 0) {
            kingMove(currentRow - 1, currentColumn, boardArray);
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