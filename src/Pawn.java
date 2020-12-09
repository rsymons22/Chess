import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Pawn extends Piece {
    private boolean canSkip;
    private Board board;
    private int teamColor;

    public Pawn(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;
        canSkip = true;
        this.teamColor = teamColor;
    }

    public ArrayList<RedDot> showMoves() {
        ArrayList<RedDot> pawnPossibleMoves = new ArrayList<RedDot>();

        Piece[][] boardArray = board.getBoardArray();

        if(teamColor == Constants.BLACK) {

            if(canSkip == true) {

                if((boardArray[currentRow][currentColumn + 2] == null) && (boardArray[currentRow][currentColumn + 1] == null)) { // no piece at the space just 2 before the black pawn
                    RedDot dot2 = new RedDot(currentRow, currentColumn + 2, board);
                    pawnPossibleMoves.add(dot2);
                }
            }

            if(boardArray[currentRow][currentColumn + 1] == null) {// no piece at the space just one before the black pawn
                RedDot dot1 = new RedDot(currentRow, currentColumn + 1, board);
                pawnPossibleMoves.add(dot1);
            }

            if(boardArray[currentRow - 1][currentColumn + 1] != null && 
            (boardArray[currentRow - 1][currentColumn + 1].getTeamColor() == Constants.WHITE))
            {
                RedDot dot3 = new RedDot(currentRow - 1, currentColumn + 1, board);
                pawnPossibleMoves.add(dot3);
            }

            if(boardArray[currentRow + 1][currentColumn + 1] != null && 
            (boardArray[currentRow + 1][currentColumn + 1].getTeamColor() == Constants.WHITE))
            {
                RedDot dot4 = new RedDot(currentColumn + 1, currentRow + 1, board);
                pawnPossibleMoves.add(dot4);
            }
        }
        else
        {

            if(canSkip == true) {

                if((boardArray[currentRow][currentColumn - 2] == null) && (boardArray[currentRow][currentColumn - 1] == null)) { // no piece at the space just 2 before the black pawn
                    RedDot dot2 = new RedDot(currentRow, currentColumn - 2, board);
                    pawnPossibleMoves.add(dot2);
                }
            }

            if(boardArray[currentRow][currentColumn - 1] == null) {// no piece at the space just one before the black pawn
                RedDot dot1 = new RedDot(currentRow, currentColumn - 1, board);
                pawnPossibleMoves.add(dot1);
            }

            if(boardArray[currentRow + 1][currentColumn - 1] != null && 
            (boardArray[currentRow + 1][currentColumn - 1].getTeamColor() == Constants.BLACK))
            {
                RedDot dot3 = new RedDot(currentRow + 1, currentColumn - 1, board);
                pawnPossibleMoves.add(dot3);
            }

            if(boardArray[currentRow - 1][currentColumn - 1] != null && 
            (boardArray[currentRow - 1][currentColumn - 1].getTeamColor() == Constants.BLACK))
            {
                RedDot dot4 = new RedDot(currentColumn - 1, currentRow - 1, board);
                pawnPossibleMoves.add(dot4);
            }
        }

        return pawnPossibleMoves;
    }

    public void move(int row, int column) 
    {
        changeLocation(row, column);
        canSkip = false;
        
        if(teamColor == Constants.WHITE)
        {
            if(getRow() == 0)
            {
                board.changePawn(this, teamColor);
            }
        }
        else
        {
            if(getRow() == 7)
            {
                board.changePawn(this, teamColor);
            }
        }
    }
}
