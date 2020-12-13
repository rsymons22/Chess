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
                try {
                if(((getCurrentRow() + 2) <= 7) &&
                   (boardArray[getCurrentRow() + 2][getCurrentColumn()] == null) && 
                   (boardArray[getCurrentRow() + 1][getCurrentColumn()] == null)) 
                { // no piece at the space just 2 before the black pawn
                    possibleMoves.add(new RedDot(getCurrentRow() + 2, getCurrentColumn(), board));
                }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(this + " went out of bounds");
                }
            }

            if(((getCurrentRow() + 1) <= 7) &&
               (boardArray[getCurrentRow() + 1][getCurrentColumn()] == null)) 
            {// no piece at the space just one before the black pawn
                possibleMoves.add(new RedDot(getCurrentRow() + 1, getCurrentColumn(), board));
            }

            if(((getCurrentRow() + 1) <= 7) && 
               (getCurrentColumn() - 1) >= 0 &&
               (boardArray[getCurrentRow() + 1][getCurrentColumn() - 1] != null) && 
               (boardArray[getCurrentRow() + 1][getCurrentColumn() - 1].getTeamColor() == Constants.WHITE))
            {
                //System.out.println("Found enemy piece to attack: " + (getCurrentRow() + 1) + ", " + (getCurrentColumn() - 1));
                RedDot dot3 = new RedDot(getCurrentRow() + 1, getCurrentColumn() - 1, board);
                possibleMoves.add(dot3);
            }

            if((getCurrentRow() + 1) <= 7 && (getCurrentColumn() + 1) <= 7 &&
                boardArray[getCurrentRow() + 1][getCurrentColumn() + 1] != null && 
            (boardArray[getCurrentRow() + 1][getCurrentColumn() + 1].getTeamColor() == Constants.WHITE))
            {
                //System.out.println("Found enemy piece to attack: " + (getCurrentRow() + 1) + ", " + (getCurrentColumn() + 1));
                RedDot dot4 = new RedDot(getCurrentRow() + 1, getCurrentColumn() + 1, board);
                possibleMoves.add(dot4);
            }
        }
        else
        {

            if(canSkip) {

                //System.out.println("Checking: " + (getCurrentRow() - 2) + ", " + getCurrentColumn() + " and " + (getCurrentRow() - 1) + ", " + getCurrentColumn());
                if(((getCurrentRow() - 2) >= 0 &&
                    boardArray[getCurrentRow() - 2][getCurrentColumn()] == null) && (boardArray[getCurrentRow() - 1][getCurrentColumn()] == null)) { // no piece at the space just 2 before the black pawn
                    RedDot dot2 = new RedDot(getCurrentRow() - 2, getCurrentColumn(), board);
                    possibleMoves.add(dot2);
                }
            }

            if((getCurrentRow() - 1) >= 0 &&
                boardArray[getCurrentRow() - 1][getCurrentColumn()] == null) {// no piece at the space just one before the black pawn
                //System.out.println("Found position at: " + (getCurrentRow() - 1) + ", " + getCurrentColumn());
                RedDot dot1 = new RedDot(getCurrentRow() - 1, getCurrentColumn(), board);
                possibleMoves.add(dot1);
            }

            if((getCurrentRow() - 1) >= 0 && (getCurrentColumn() + 1) <= 7 &&
              (boardArray[getCurrentRow() - 1][getCurrentColumn() + 1] != null) && 
              (boardArray[getCurrentRow() - 1][getCurrentColumn() + 1].getTeamColor() == Constants.BLACK))
            {
                //System.out.println("Found enemy piece to attack: " + (getCurrentRow() - 1) + ", " + (getCurrentColumn() + 1));
                RedDot dot3 = new RedDot(getCurrentRow() - 1, getCurrentColumn() + 1, board);
                possibleMoves.add(dot3);
            }

            if((getCurrentRow() - 1) >= 0 && (getCurrentColumn() - 1) >= 0 &&
              (boardArray[getCurrentRow() - 1][getCurrentColumn() - 1] != null) && 
              (boardArray[getCurrentRow() - 1][getCurrentColumn() - 1].getTeamColor() == Constants.BLACK))
            {
                //System.out.println("Found enemy piece to attack: " + (getCurrentRow() - 1) + ", " + (getCurrentColumn() - 1));
                RedDot dot4 = new RedDot(getCurrentRow() - 1, getCurrentColumn() - 1, board);
                possibleMoves.add(dot4);
            }
        }

        return possibleMoves;
    }

    public void move(int row, int column) 
    {
        super.move(row, column);
        canSkip = false;
        
        if(getCurrentRow() == 0 && teamColor == Constants.WHITE)
        {
            changePawn(this, teamColor);
        }

        if(getCurrentRow() == 7 && teamColor == Constants.BLACK)
        {
            changePawn(this, teamColor);
        }
    }
}
