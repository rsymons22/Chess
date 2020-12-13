import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public abstract class Piece {    
    private boolean isCaptured;
    protected ImageView pieceImage;

    protected Board board;
    protected int teamColor;
    protected ArrayList<RedDot> possibleMoves;
    protected boolean isPhased;
    private Piece enemyPiecePhased;
    private int enemyPieceRow;
    private int enemyPieceColumn;

    private int beforePhasedRow;
    private int beforePhasedColumn;

    public Piece(int teamColor, Board board, int row, int column, ImageView pieceImage) {
        this.board = board;
        this.teamColor = teamColor;

        pieceImage.setLayoutX(column * 60); // Each board square and piece is 60 pixels long. 
        pieceImage.setLayoutY((row * 60));
        board.getPane().getChildren().add(pieceImage);
        this.pieceImage = pieceImage;
    }

    public int getCurrentRow() {
        Piece[][] boardArray = board.getBoardArray();

        for(int r = 0; r < boardArray.length; r++) {
            for(int c = 0; c < boardArray[r].length; c++) {
                if(boardArray[r][c] != null && boardArray[r][c].equals(this)) {
                    return r;
                }
            }
        }

        return -1;
    }

    public int getCurrentColumn() {
        Piece[][] boardArray = board.getBoardArray();

        for(int r = 0; r < boardArray.length; r++) {
            for(int c = 0; c < boardArray[r].length; c++) {
                if(boardArray[r][c] != null && boardArray[r][c].equals(this)) {
                    return c;
                }
            }
        }

        return -1;
    }

    public int getTeamColor() {
        return teamColor;
    }

    public ArrayList<RedDot> findMoves() {
        return null;
    }
    
    public void move(int row, int column) {

        board.getMoveList().add(formatMove(row, column));
        
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), pieceImage);
        transition.setFromX(pieceImage.getTranslateX());
        transition.setFromY(pieceImage.getTranslateY());
        transition.setToX(pieceImage.getTranslateX() + ((column - getCurrentColumn()) * 60));
        transition.setToY(pieceImage.getTranslateY() + ((row - getCurrentRow()) * 60));

        transition.playFromStart();

        changeLocation(row, column, this);
    }

    private String formatMove(int row, int column) {
        String teamColor;
        String pieceType = this.getClass().getName();

        if(this.teamColor == Constants.WHITE) {
            teamColor = "White";
        } else {
            teamColor = "Black";
        }

        return teamColor + " " + pieceType + " to " + (row + 1) + ", " + (column + 1); 
    }
    
    public void changeLocation(int row, int column, Piece piece) {
        //pieceImage.setLayoutX(column * 60);
        //pieceImage.setLayoutY(row * 60);

        //System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
        //board.printBoardArray();
        //System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
        //System.out.println("Piece changeLocation, setting piece at: " + getCurrentRow() + ", " + getCurrentColumn());
        board.getBoardArray()[getCurrentRow()][getCurrentColumn()] = null;
        board.getBoardArray()[row][column] = piece;
    }
    
    public void removePiece()
    {
        //System.out.println("Removing piece: " + pieceImage + " at " + getCurrentRow() + ", " + getCurrentColumn());
        board.getPane().getChildren().remove(pieceImage);
        isCaptured = true;
        board.getBoardArray()[getCurrentRow()][getCurrentColumn()] = null;

    }
    
    public boolean isCaptured()
    {
        return isCaptured;
    }
    
    public void phasePiece(int phasedRow, int phasedColumn)
    {

        isPhased = true;
        enemyPiecePhased = board.getBoardArray()[phasedRow][phasedColumn];
        if(enemyPiecePhased != null) { // Is actually a piece
            enemyPiecePhased.isCaptured = true;
            board.getBoardArray()[enemyPiecePhased.getCurrentRow()][enemyPiecePhased.getCurrentColumn()] = null;
            //System.out.println("Phasing enemy piece: " + enemyPiecePhased + "to: " + enemyPiecePhased.getRow() + ", " + enemyPiecePhased.getColumn());
        }

            board.getBoardArray()[phasedRow][phasedColumn] = this;
            //System.out.println("Phasing " + this + " to " + phasedRow + ", " + phasedColumn);

            enemyPieceRow = phasedRow;
            enemyPieceColumn = phasedColumn;

            beforePhasedRow = getCurrentRow();
            beforePhasedColumn = getCurrentColumn();

        board.getBoardArray()[getCurrentRow()][getCurrentColumn()] = null;
    }
    
    public void unPhasePiece()
    {

        isPhased = false;
        board.getBoardArray()[beforePhasedRow][beforePhasedColumn] = this;
        //System.out.println("Unphasing: " + this);
        //if(enemyPieceRow != -1)
        board.getBoardArray()[enemyPieceRow][enemyPieceColumn] = enemyPiecePhased;
        if(enemyPiecePhased != null) {
            enemyPiecePhased.isCaptured = false;
        }
    }

    public boolean isPhased() {
        return isPhased;
    }

    // Give the player an option of piece change instead of just queen
    public void changePawn(Piece piece, int teamColor) {
    //     System.out.println("Change pawn");

    //     String[] pieces = {"Queen", "Knight", "Rook", "Bishop"};
    //     ListView<String> lv = new ListView<> (FXCollections.observableArrayList(pieces));
    //     lv.setPrefSize(100, 100);
    //     lv.setLayoutX(getCurrentColumn() * Constants.PIECE_LENGTH);
    //     lv.setLayoutY(getCurrentRow() * Constants.PIECE_LENGTH);
    //     lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    //     board.getPane().getChildren().add(lv);
    }
}
