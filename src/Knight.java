import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Knight extends Piece {
    private Board board;
    private int teamColor;
    private int oppositeTeamColor;

    public Knight(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;
        this.teamColor = teamColor;

        if(teamColor == Constants.WHITE) {
            oppositeTeamColor = Constants.BLACK;
        } else {
            oppositeTeamColor = Constants.WHITE;
        }
    }

    public ArrayList<RedDot> showMoves()
    {
        ArrayList<RedDot> knightPossibleMoves = new ArrayList<RedDot>();

        Piece boardArray[][] = board.getBoardArray();

        if(boardArray[currentRow - 2][currentColumn + 1] == null ||
        (boardArray[currentRow - 2][currentColumn + 1].getTeamColor() == oppositeTeamColor))
        {
            if((currentRow + 1) <= 7 && (currentColumn >= 0)) {
                RedDot dot = new RedDot(currentRow - 2, currentColumn + 1, board);
                knightPossibleMoves.add(dot);
            }
        }

        if(boardArray[currentRow - 1][currentColumn + 2] == null ||
        (boardArray[currentRow - 1][currentColumn + 2].getTeamColor() == oppositeTeamColor))
        {
            RedDot dot = new RedDot(currentRow - 1, currentColumn + 2, board);
            knightPossibleMoves.add(dot);
        }

        if(boardArray[currentRow + 1][currentColumn + 2] == null ||
        (boardArray[currentRow + 1][currentColumn + 2].getTeamColor() == oppositeTeamColor))
        {
            RedDot dot = new RedDot(currentRow + 1, currentColumn + 2, board);
            knightPossibleMoves.add(dot);
        }

        if(boardArray[currentRow + 2][currentColumn + 1] == null ||
        (boardArray[currentRow + 2][currentColumn + 1].getTeamColor() == oppositeTeamColor))
        {
            RedDot dot = new RedDot(currentRow + 2, currentColumn + 1, board);
            knightPossibleMoves.add(dot);
        }

        if(boardArray[currentRow + 2][currentColumn - 1] == null ||
        (boardArray[currentRow + 2][currentColumn - 1].getTeamColor() == oppositeTeamColor))
        {
            RedDot dot = new RedDot(currentRow + 2, currentColumn - 1, board);
            knightPossibleMoves.add(dot);
        }

        if(boardArray[currentRow + 1][currentColumn - 2] == null ||
        (boardArray[currentRow + 1][currentColumn - 2].getTeamColor() == oppositeTeamColor))
        {
            RedDot dot = new RedDot(currentRow + 1, currentColumn - 2, board);
            knightPossibleMoves.add(dot);
        }

        if(boardArray[currentRow - 1][currentColumn - 2] == null ||
        (boardArray[currentRow - 1][currentColumn - 2].getTeamColor() == oppositeTeamColor))
        {
            RedDot dot = new RedDot(currentRow - 1, currentColumn - 2, board);
            knightPossibleMoves.add(dot);
        }

        if(boardArray[currentRow - 2][currentColumn - 1] == null ||
        (boardArray[currentRow - 2][currentColumn - 1].getTeamColor() == oppositeTeamColor))
        {
            RedDot dot = new RedDot(currentRow - 2, currentColumn - 1, board);
            knightPossibleMoves.add(dot);
        }

        return knightPossibleMoves;
    }

    public void move(int row, int column) 
    {
        changeLocation(row, column);
    }
}