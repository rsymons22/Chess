import java.util.ArrayList;

import javafx.scene.image.ImageView;

public abstract class Piece {
    protected int currentRow;
    protected int currentColumn;

    private int tempRow;
    private int tempColumn;

    private boolean isCaptured;

    private Board board;

    private int teamColor;

    private ImageView pieceImage;

    public Piece(int teamColor, Board board, int row, int column, ImageView pieceImage) {
        currentRow = row;
        currentColumn = column;
        this.board = board;
        this.teamColor = teamColor;

        pieceImage.setLayoutX(column * 60); // Each board square and piece is 60 pixels long. 
        pieceImage.setLayoutY(row * 60);
        board.getPane().getChildren().add(pieceImage);
        this.pieceImage = pieceImage;
    }

    public int getRow() {
        return currentRow;
    }
    
    public int getColumn() {
        return currentColumn;
    }

    public int getTeamColor()
    {
        return teamColor;
    }

    public ArrayList<RedDot> showMoves() {
        return null;
    }
    
    public void move(int row, int column) {
        changeLocation(row, column, this);
    }
    
    public void changeLocation(int row, int column, Piece piece) {
        pieceImage.setLayoutX(column * 60);
        pieceImage.setLayoutY(row * 60);

        System.out.println("Piece changeLocation, setting piece at: " + currentRow + ", " + currentColumn);
        board.getBoardArray()[currentRow][currentColumn] = null;
        board.getBoardArray()[row][column] = piece;

        currentRow = row;
        currentColumn = column;
    }
    
    public void removePiece()
    {
        System.out.println("Removing piece: " + pieceImage + " at " + currentRow + ", " + currentColumn);
        board.getPane().getChildren().remove(pieceImage);
        isCaptured = true;
        board.getBoardArray()[currentRow][currentColumn] = null;

    }
    
    public boolean isCaptured()
    {
        return isCaptured;
    }
    
    public void phasePiece(int row, int column)
    {
        System.out.println("phasing piece at: " + row + ", " + column);
        tempRow = currentRow;
        tempColumn = currentColumn;
        
        currentRow = row;
        currentColumn = column;

        if(row == -1 || column == -1) {
            board.getBoardArray()[tempRow][tempColumn] = null;
        } else {
            board.getBoardArray()[currentRow][currentColumn] = this;
        }
    }
    
    public void unPhasePiece()
    {
        
        if(currentRow != -1 && currentColumn != -1) {
            board.getBoardArray()[currentRow][currentColumn] = null;
        }

        currentRow = tempRow;
        currentColumn = tempColumn;

        System.out.println("unphasing piece at: " + currentRow + ", " + currentColumn);
        board.getBoardArray()[currentRow][currentColumn] = this;
    }
}
