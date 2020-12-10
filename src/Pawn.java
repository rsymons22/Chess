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
        ArrayList<RedDot> possibleMoves = new ArrayList<RedDot>();

        Piece[][] boardArray = board.getBoardArray();

        if(teamColor == Constants.BLACK) {

            if(canSkip == true) {

                if((boardArray[currentRow + 2][currentColumn] == null) && (boardArray[currentRow + 1][currentColumn] == null)) { // no piece at the space just 2 before the black pawn
                    RedDot dot2 = new RedDot(currentRow + 2, currentColumn, board);
                    possibleMoves.add(dot2);
                }
            }

            if(boardArray[currentRow + 1][currentColumn] == null) {// no piece at the space just one before the black pawn
                System.out.println("Found position at: " + (currentRow + 1) + ", " + currentColumn);
                RedDot dot1 = new RedDot(currentRow + 1, currentColumn, board);
                possibleMoves.add(dot1);
            }

            if(currentColumn != 0 &&
                boardArray[currentRow + 1][currentColumn - 1] != null && 
            (boardArray[currentRow + 1][currentColumn - 1].getTeamColor() == Constants.WHITE))
            {
                System.out.println("Found enemy piece to attack: " + (currentRow + 1) + ", " + (currentColumn - 1));
                RedDot dot3 = new RedDot(currentRow + 1, currentColumn - 1, board);
                possibleMoves.add(dot3);
            }

            if(currentColumn != 7 &&
                boardArray[currentRow + 1][currentColumn + 1] != null && 
            (boardArray[currentRow + 1][currentColumn + 1].getTeamColor() == Constants.WHITE))
            {
                System.out.println("Found enemy piece to attack: " + (currentRow + 1) + ", " + (currentColumn + 1));
                RedDot dot4 = new RedDot(currentColumn + 1, currentRow + 1, board);
                possibleMoves.add(dot4);
            }
        }
        else
        {

            if(canSkip == true) {

                if((boardArray[currentRow - 2][currentColumn] == null) && (boardArray[currentRow - 1][currentColumn] == null)) { // no piece at the space just 2 before the black pawn
                    RedDot dot2 = new RedDot(currentRow - 2, currentColumn, board);
                    possibleMoves.add(dot2);
                }
            }

            if(boardArray[currentRow - 1][currentColumn] == null) {// no piece at the space just one before the black pawn
                System.out.println("Found position at: " + (currentRow - 1) + ", " + currentColumn);
                RedDot dot1 = new RedDot(currentRow - 1, currentColumn, board);
                possibleMoves.add(dot1);
            }

            if(currentColumn != 7 &&
              (boardArray[currentRow - 1][currentColumn + 1] != null) && 
              (boardArray[currentRow - 1][currentColumn + 1].getTeamColor() == Constants.BLACK))
            {
                System.out.println("Found enemy piece to attack: " + (currentRow - 1) + ", " + (currentColumn + 1));
                RedDot dot3 = new RedDot(currentRow - 1, currentColumn + 1, board);
                possibleMoves.add(dot3);
            }

            if(currentColumn != 0 &&
              (boardArray[currentRow - 1][currentColumn - 1] != null) && 
              (boardArray[currentRow - 1][currentColumn - 1].getTeamColor() == Constants.BLACK))
            {
                System.out.println("Found enemy piece to attack: " + (currentRow - 1) + ", " + (currentColumn - 1));
                RedDot dot4 = new RedDot(currentRow - 1, currentColumn - 1, board);
                possibleMoves.add(dot4);
            }
        }

        return possibleMoves;
    }

    public void move(int row, int column) 
    {
        changeLocation(row, column, this);
        canSkip = false;
        
        if(getRow() == 0 && teamColor == Constants.WHITE)
        {
            board.changePawn(this, teamColor);
        }

        if(getRow() == 7 && teamColor == Constants.BLACK)
        {
            board.changePawn(this, teamColor);
        }
    }
}
