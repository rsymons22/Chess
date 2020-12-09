import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Rook extends Piece {
    private Board board;
    private int oppositeTeamColor;

    public Rook(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;

        if(teamColor == Constants.WHITE) {
            oppositeTeamColor = Constants.BLACK;
        } else {
            oppositeTeamColor = Constants.WHITE;
        }
    }

    public ArrayList<RedDot> showMoves()
    {
        ArrayList<RedDot> rookPossibleMoves = new ArrayList<RedDot>();

        Piece[][] boardArray = board.getBoardArray();

        // up

        for(int i = this.getColumn() - 1; i >= 0; i--) {
            Piece piece = boardArray[i][currentColumn];

            if(piece == null) { // rook sees empty space
                RedDot dot = new RedDot(i, currentColumn, board);
                rookPossibleMoves.add(dot);
            }
            else if(piece.getTeamColor() == oppositeTeamColor) { // rook sees enemy piece
                RedDot dot = new RedDot(i, currentColumn, board);
                rookPossibleMoves.add(dot);
                break; // break because rook stops when it would capture a piece
            }
            else { // rook sees a friendly piece
                break; // break because rook cannot skip a friendly piece
            }
        }

        // down
        
        for(int i = this.getColumn() + 1; i <= 8; i++) {
            Piece piece = boardArray[i][currentColumn];

            if(piece == null) { // rook sees empty space
                RedDot dot = new RedDot(i, currentColumn, board);
                rookPossibleMoves.add(dot);
            }
            else if(piece.getTeamColor() == oppositeTeamColor) { // rook sees enemy piece
                RedDot dot = new RedDot(i, currentColumn, board);
                rookPossibleMoves.add(dot);
                break; // break because rook stops when it captures a piece
            }
            else { // rook sees a friendly piece
                break; // break because rook cannot skip a friendly piece
            }
        }

        // right 
        
        for(int i = this.getRow() + 1; i <= 8; i++) {
            Piece piece = boardArray[currentRow][i];

            if(piece == null) { // rook sees empty space
                RedDot dot = new RedDot(currentRow, i, board);
                rookPossibleMoves.add(dot);
            }
            else if(piece.getTeamColor() == oppositeTeamColor) { // rook sees enemy piece
                RedDot dot = new RedDot(currentRow, i, board);
                rookPossibleMoves.add(dot);
                break; // break because rook stops when it captures a piece
            }
            else { // rook sees a friendly piece
                break; // break because rook cannot skip a friendly piece
            }
        }
        
        // left
        
        for(int i = this.getRow() - 1; i >= 0; i--) {
            Piece piece = boardArray[currentRow][i];

            if(piece == null) { // rook sees empty space
                RedDot dot = new RedDot(currentRow, i, board);
                rookPossibleMoves.add(dot);
            }
            else if(piece.getTeamColor() == oppositeTeamColor) {// rook sees enemy piece
                RedDot dot = new RedDot(currentRow, i, board);
                rookPossibleMoves.add(dot);
                break; // break because rook stops when it captures a piece
            }
            else {// rook sees a friendly piece
                break; // break because rook cannot skip a friendly piece
            }
        }
        
        return rookPossibleMoves;
    }

    public void move(int row, int column) {
        changeLocation(row, column);
    }
}
