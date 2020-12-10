import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Knight extends Piece {
    private Board board;
    private int teamColor;
    private ArrayList<RedDot> possibleMoves;

    public Knight(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;
        this.teamColor = teamColor;
    }

    public ArrayList<RedDot> showMoves()
    {
        possibleMoves = new ArrayList<RedDot>();

        Piece boardArray[][] = board.getBoardArray();

        if(currentRow - 2 >= 0 && currentColumn + 1 <= 7) {
            knightMove(currentRow - 2, currentColumn + 1, boardArray);
        }

        if(currentRow - 1 >= 0 && currentColumn + 2 <= 7) {
            knightMove(currentRow - 1, currentColumn + 2, boardArray);
        }

        if(currentRow + 1 <= 7 && currentColumn + 2 <= 7) {
            knightMove(currentRow + 1, currentColumn + 2, boardArray);
        }

        if(currentRow + 2 <= 7 && currentColumn + 1 <= 7) {
            knightMove(currentRow + 2, currentColumn + 1, boardArray);
        }

        if(currentRow + 2 <= 7 && currentColumn - 1 >= 0) {
            knightMove(currentRow + 2, currentColumn - 1, boardArray);
        }

        if(currentRow + 1 <= 7 && currentColumn - 2 >= 0) {
            knightMove(currentRow + 1, currentColumn - 2, boardArray);
        }

        if(currentRow - 1 >= 0 && currentColumn - 2 >= 0) {
            knightMove(currentRow - 1, currentColumn - 2, boardArray);
        }

        if(currentRow - 2 >= 0 && currentColumn - 1 >= 0) {
            knightMove(currentRow - 2, currentColumn - 1, boardArray);
        }

        return possibleMoves;
    }

    private void knightMove(int row, int column, Piece[][] boardArray) {
        if(boardArray[row][column] == null ||
          (boardArray[row][column].getTeamColor() != teamColor))
        {
            possibleMoves.add(new RedDot(row, column, board));
        }
    }
}