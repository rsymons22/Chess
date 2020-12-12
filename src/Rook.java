import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Rook extends Piece {

    public Rook(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  
    }

    public ArrayList<RedDot> findMoves()
    {
        possibleMoves = new ArrayList<RedDot>();

        Piece[][] boardArray = board.getBoardArray();

        // up

        for(int row = getCurrentRow() - 1; row >= 0; row--) {
                Piece piece = boardArray[row][getCurrentColumn()];

                if(piece == null) { // rook sees empty space
                    possibleMoves.add(new RedDot(row, getCurrentColumn(), board));
                }
                else if(piece.getTeamColor() != teamColor) { // rook sees enemy piece
                    possibleMoves.add(new RedDot(row, getCurrentColumn(), board));
                    break; // break because rook stops when it would capture a piece
                }
                else { // rook sees a friendly piece
                    break; // break because rook cannot skip a friendly piece
                }
        }

        // down
        
        for(int row = getCurrentRow() + 1; row <= 7; row++) {
            //if(row <= 7) {
                Piece piece = boardArray[row][getCurrentColumn()];

                if(piece == null) { // rook sees empty space
                    possibleMoves.add(new RedDot(row, getCurrentColumn(), board));
                }
                else if(piece.getTeamColor() != teamColor) { // rook sees enemy piece
                    possibleMoves.add(new RedDot(row, getCurrentColumn(), board));
                    break; // break because rook stops when it captures a piece
                }
                else { // rook sees a friendly piece
                    break; // break because rook cannot skip a friendly piece
                }
            //}
        }

        // right 
        for(int column = getCurrentColumn() + 1; column <= 7; column++) {
            //if(row <= 7) {
                Piece piece = boardArray[getCurrentRow()][column];

                if(piece == null) { // rook sees empty space
                    possibleMoves.add(new RedDot(getCurrentRow(), column, board));
                }
                else if(piece.getTeamColor() != teamColor) { // rook sees enemy piece
                    possibleMoves.add(new RedDot(getCurrentRow(), column, board));
                    break; // break because rook stops when it captures a piece
                }
                else { // rook sees a friendly piece
                    break; // break because rook cannot skip a friendly piece
                }
            //}
        }
        
        // Left
        
        for(int column = getCurrentColumn() - 1; column >= 0; column--) {
                Piece piece = boardArray[getCurrentRow()][column];

                if(piece == null) { // rook sees empty space
                    possibleMoves.add(new RedDot(getCurrentRow(), column, board));
                }
                else if(piece.getTeamColor() != teamColor) {// rook sees enemy piece
                    possibleMoves.add(new RedDot(getCurrentRow(), column, board));
                    break; // break because rook stops when it captures a piece
                }
                else {// rook sees a friendly piece
                    break; // break because rook cannot skip a friendly piece
                }
        }
        
        return possibleMoves;
    }
}
