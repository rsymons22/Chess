import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public abstract class Piece {
    protected int currentRow;
    protected int currentColumn;
    
    private boolean isCaptured;
    protected ImageView pieceImage;

    protected Board board;
    protected int teamColor;
    protected ArrayList<RedDot> possibleMoves;
    protected boolean isPhased;
    private Piece enemyPiecePhased;
    private int enemyPieceRow;
    private int enemyPieceColumn;

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

    public ArrayList<RedDot> findMoves() {
        return null;
    }
    
    public void move(int row, int column) {
        
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pieceImage);
        transition.setFromX(pieceImage.getTranslateX());
        transition.setFromY(pieceImage.getTranslateY());
        transition.setToX(pieceImage.getTranslateX() + ((column - currentColumn) * 60));
        transition.setToY(pieceImage.getTranslateY() + ((row - currentRow) * 60));

        transition.playFromStart();
        
        changeLocation(row, column, this);
    }
    
    public void changeLocation(int row, int column, Piece piece) {
        //pieceImage.setLayoutX(column * 60);
        //pieceImage.setLayoutY(row * 60);

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
    
    public void phasePiece(int phasedRow, int phasedColumn)
    {

        isPhased = true;
        if (phasedRow != -1) {
            enemyPiecePhased = board.getBoardArray()[phasedRow][phasedColumn];
            if(enemyPiecePhased != null) { // Is actually a piece
                enemyPiecePhased.isCaptured = true;
                enemyPiecePhased.currentColumn = -1;
                enemyPiecePhased.currentRow = -1;
                System.out.println("Phasing enemy piece: " + enemyPiecePhased + "to: " + enemyPiecePhased.getRow() + ", " + enemyPiecePhased.getColumn());
            }

            board.getBoardArray()[phasedRow][phasedColumn] = this;
            System.out.println("Phasing " + this + " to " + phasedRow + ", " + phasedColumn);

            enemyPieceRow = phasedRow;
            enemyPieceColumn = phasedColumn;
        }
        board.getBoardArray()[currentRow][currentColumn] = null;

    //     if(enemyPiecePhased != null) {
    //         System.out.println("Enemy piece phased: " + enemyPiecePhased + " at " + enemyPieceRow + ", " + enemyPieceColumn);
    //         System.out.println("New location for piece is: " + enemyPiecePhased.getRow() + ", " + enemyPiecePhased.getColumn());
    //         //System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
    //         //board.printBoardArray();
    //         //System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
    //    }
    }
    
    public void unPhasePiece()
    {

        isPhased = false;
        board.getBoardArray()[currentRow][currentColumn] = this;
        System.out.println("Unphasing: " + this);
        //if(enemyPieceRow != -1)
        board.getBoardArray()[enemyPieceRow][enemyPieceColumn] = enemyPiecePhased;
        if(enemyPiecePhased != null) {
            enemyPiecePhased.isCaptured = false;
            enemyPiecePhased.currentRow = enemyPieceRow;
            enemyPiecePhased.currentColumn = enemyPieceColumn;
        }
    }

    public boolean isPhased() {
        return isPhased;
    }
}
