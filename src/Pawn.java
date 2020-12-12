import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Pawn extends Piece {
    private boolean canSkip;

    public Pawn(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  
        canSkip = true;
    }

    public ArrayList<RedDot> findMoves() {
        possibleMoves = new ArrayList<RedDot>();

        Piece[][] boardArray = board.getBoardArray();

        if(teamColor == Constants.BLACK) {

            if(canSkip) {

                if(((currentRow + 2) <= 7) &&
                   (boardArray[currentRow + 2][currentColumn] == null) && 
                   (boardArray[currentRow + 1][currentColumn] == null)) 
                { // no piece at the space just 2 before the black pawn
                    possibleMoves.add(new RedDot(currentRow + 2, currentColumn, board));
                }
            }

            if(((currentRow + 1) <= 7) &&
               (boardArray[currentRow + 1][currentColumn] == null)) 
            {// no piece at the space just one before the black pawn
                possibleMoves.add(new RedDot(currentRow + 1, currentColumn, board));
            }

            if(((currentRow + 1) <= 7) && 
               (currentColumn - 1) >= 0 &&
               (boardArray[currentRow + 1][currentColumn - 1] != null) && 
               (boardArray[currentRow + 1][currentColumn - 1].getTeamColor() == Constants.WHITE))
            {
                //System.out.println("Found enemy piece to attack: " + (currentRow + 1) + ", " + (currentColumn - 1));
                RedDot dot3 = new RedDot(currentRow + 1, currentColumn - 1, board);
                possibleMoves.add(dot3);
            }

            if((currentRow + 1) <= 7 && (currentColumn + 1) <= 7 &&
                boardArray[currentRow + 1][currentColumn + 1] != null && 
            (boardArray[currentRow + 1][currentColumn + 1].getTeamColor() == Constants.WHITE))
            {
                //System.out.println("Found enemy piece to attack: " + (currentRow + 1) + ", " + (currentColumn + 1));
                RedDot dot4 = new RedDot(currentRow + 1, currentColumn + 1, board);
                possibleMoves.add(dot4);
            }
        }
        else
        {

            if(canSkip) {

                //System.out.println("Checking: " + (currentRow - 2) + ", " + currentColumn + " and " + (currentRow - 1) + ", " + currentColumn);
                if(((currentRow - 2) >= 0 &&
                    boardArray[currentRow - 2][currentColumn] == null) && (boardArray[currentRow - 1][currentColumn] == null)) { // no piece at the space just 2 before the black pawn
                    RedDot dot2 = new RedDot(currentRow - 2, currentColumn, board);
                    possibleMoves.add(dot2);
                }
            }

            if((currentRow - 1) >= 0 &&
                boardArray[currentRow - 1][currentColumn] == null) {// no piece at the space just one before the black pawn
                //System.out.println("Found position at: " + (currentRow - 1) + ", " + currentColumn);
                RedDot dot1 = new RedDot(currentRow - 1, currentColumn, board);
                possibleMoves.add(dot1);
            }

            if((currentRow - 1) >= 0 && (currentColumn + 1) <= 7 &&
              (boardArray[currentRow - 1][currentColumn + 1] != null) && 
              (boardArray[currentRow - 1][currentColumn + 1].getTeamColor() == Constants.BLACK))
            {
                //System.out.println("Found enemy piece to attack: " + (currentRow - 1) + ", " + (currentColumn + 1));
                RedDot dot3 = new RedDot(currentRow - 1, currentColumn + 1, board);
                possibleMoves.add(dot3);
            }

            if((currentRow - 1) >= 0 && (currentColumn - 1) >= 0 &&
              (boardArray[currentRow - 1][currentColumn - 1] != null) && 
              (boardArray[currentRow - 1][currentColumn - 1].getTeamColor() == Constants.BLACK))
            {
                //System.out.println("Found enemy piece to attack: " + (currentRow - 1) + ", " + (currentColumn - 1));
                RedDot dot4 = new RedDot(currentRow - 1, currentColumn - 1, board);
                possibleMoves.add(dot4);
            }
        }

        return possibleMoves;
    }

    public void move(int row, int column) 
    {
        super.move(row, column);
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
