import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Queen extends Piece {
    private Board board;
    private int teamColor;

    public Queen(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;
        this.teamColor = teamColor;
    }

    public ArrayList<RedDot> showMoves()
    {
        ArrayList<RedDot> possibleMoves = new ArrayList<RedDot>();

        Piece[][] boardArray = board.getBoardArray();

        // up

        for(int row = currentRow - 1; row >= 0; row--) {
                Piece piece = boardArray[row][currentColumn];

                if(piece == null) { // rook sees empty space
                    possibleMoves.add(new RedDot(row, currentColumn, board));
                }
                else if(piece.getTeamColor() != teamColor) { // rook sees enemy piece
                    possibleMoves.add(new RedDot(row, currentColumn, board));
                    break; // break because rook stops when it would capture a piece
                }
                else { // rook sees a friendly piece
                    break; // break because rook cannot skip a friendly piece
                }
        }

        // down
        
        for(int row = currentRow + 1; row <= 7; row++) {
            //if(row <= 7) {
                Piece piece = boardArray[row][currentColumn];

                if(piece == null) { // rook sees empty space
                    possibleMoves.add(new RedDot(row, currentColumn, board));
                }
                else if(piece.getTeamColor() != teamColor) { // rook sees enemy piece
                    possibleMoves.add(new RedDot(row, currentColumn, board));
                    break; // break because rook stops when it captures a piece
                }
                else { // rook sees a friendly piece
                    break; // break because rook cannot skip a friendly piece
                }
            //}
        }

        // right 
        for(int column = currentColumn + 1; column <= 7; column++) {
            //if(row <= 7) {
                Piece piece = boardArray[currentRow][column];

                if(piece == null) { // rook sees empty space
                    possibleMoves.add(new RedDot(currentRow, column, board));
                }
                else if(piece.getTeamColor() != teamColor) { // rook sees enemy piece
                    possibleMoves.add(new RedDot(currentRow, column, board));
                    break; // break because rook stops when it captures a piece
                }
                else { // rook sees a friendly piece
                    break; // break because rook cannot skip a friendly piece
                }
            //}
        }
        
        // Left
        
        for(int column = currentColumn - 1; column >= 0; column--) {
                Piece piece = boardArray[currentRow][column];

                if(piece == null) { // rook sees empty space
                    possibleMoves.add(new RedDot(currentRow, column, board));
                }
                else if(piece.getTeamColor() != teamColor) {// rook sees enemy piece
                    possibleMoves.add(new RedDot(currentRow, column, board));
                    break; // break because rook stops when it captures a piece
                }
                else {// rook sees a friendly piece
                    break; // break because rook cannot skip a friendly piece
                }
        }

        int columnUpLeft = this.getColumn() - 1;
        int columnDownRight = this.getColumn() + 1;
        int rowUpRight = this.getRow() - 1;
        int rowDownLeft = this.getRow() + 1;

        // up left
        for(int row = currentRow - 1; row >= 0; row--) {
            if(columnUpLeft >= 0)
            if(boardArray[row][columnUpLeft] == null) // bishop sees empty space
            {
                RedDot dot = new RedDot(row, columnUpLeft, board);
                possibleMoves.add(dot);
            }
            else if(boardArray[row][columnUpLeft].getTeamColor() != teamColor) // bishop sees enemy piece
            {
                RedDot dot = new RedDot(row, columnUpLeft, board);
                possibleMoves.add(dot);
                break; // break because bishop stops when it captures a piece
            }
            else // bishop sees a friendly piece
            {
                break; // break because bishop cannot skip a friendly piece
            }
            columnUpLeft--;
        }

        // down right

        
        for(int row = currentRow + 1; row <= 7; row++)
        {
            if(columnDownRight <= 7)
            if(boardArray[row][columnDownRight] == null) // bishop sees empty space
            {
                RedDot dot = new RedDot(row, columnDownRight, board);
                possibleMoves.add(dot);
            }
            else if(boardArray[row][columnDownRight].getTeamColor() != teamColor) // bishop sees enemy piece
            {
                RedDot dot = new RedDot(row, columnDownRight, board);
                possibleMoves.add(dot);
                break; // break because bishop stops when it captures a piece
            }
            else // bishop sees a friendly piece
            {
                break; // break because bishop cannot skip a friendly piece
            }
            columnDownRight++;
        }

        // up right
        for(int column = currentColumn - 1; column >= 0; column--) 
        {
            if(rowDownLeft <= 7)
            if(boardArray[rowDownLeft][column] == null) 
            {
                RedDot dot = new RedDot(rowDownLeft, column, board);
                possibleMoves.add(dot);
            }
            else if(boardArray[rowDownLeft][column].getTeamColor() != teamColor) 
            {
                RedDot dot = new RedDot(rowDownLeft, column, board);
                possibleMoves.add(dot);
                break; 
            }
            else 
            {
                break; 
            }
            rowDownLeft++;
        }

        // down left

        for(int column = currentColumn + 1; column <= 7; column++) 
        {
            if(rowUpRight >= 0)
            if(boardArray[rowUpRight][column] == null) 
            {
                RedDot dot = new RedDot(rowUpRight, column, board);
                possibleMoves.add(dot);
            }
            else if(boardArray[rowUpRight][column].getTeamColor() != teamColor) 
            {
                RedDot dot = new RedDot(rowUpRight, column, board);
                possibleMoves.add(dot);
                break; 
            }
            else 
            {
                break; 
            }
            rowUpRight--;
        }
        
        return possibleMoves;
    }
}
