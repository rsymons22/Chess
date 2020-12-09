import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Piece {
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

        System.out.println("Creating piece at: " + row + ", " + column);
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
    
    public void move(int column, int row) {}
    
    public void changeLocation(int row, int column) {
        pieceImage.setLayoutX(column * 60);
        pieceImage.setLayoutY(row * 60);
        currentRow = row;
        currentColumn = column;
    }
    
    public void removePiece()
    {
        board.getPane().getChildren().remove(pieceImage);
        isCaptured = true;
    }
    
    public boolean isCaptured()
    {
        return isCaptured;
    }
    
    public void phasePiece(int column, int row)
    {
        tempRow = currentRow;
        tempColumn = currentColumn;
        
        currentRow = row;
        currentColumn = column;
    }
    
    public void unPhasePiece()
    {
        currentRow = tempRow;
        currentColumn = tempColumn;
    }
}
