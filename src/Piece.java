import java.util.ArrayList;

import javafx.scene.image.ImageView;

public abstract class Piece {
    protected int currentRow;
    protected int currentColumn;
    private int tempRow;
    private int tempColumn;

    private boolean isCaptured;
    private ImageView pieceImage;

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
    
    public void phasePiece(int phasedRow, int phasedColumn)
    {
        // isPhased = true;
        // if(row != -1)
        //     System.out.println("phasing " + board.getBoardArray()[currentRow][currentColumn] + " to: " + row + ", " + column);
        // tempRow = currentRow;
        // tempColumn = currentColumn;
        
        // currentRow = row;
        // currentColumn = column;

        // if(row != -1)
        //     enemyPiecePhased = board.getBoardArray()[row][column];
        

        // if(row == -1 || column == -1) {
        //     board.getBoardArray()[tempRow][tempColumn] = null;
        // } else {
        //     board.getBoardArray()[tempRow][tempColumn] = null;
        //     board.getBoardArray()[currentRow][currentColumn] = this;
        // }

        isPhased = true;
        if (phasedRow != -1) {
            enemyPiecePhased = board.getBoardArray()[phasedRow][phasedColumn];
            board.getBoardArray()[phasedRow][phasedColumn] = this;
            System.out.println("Phasing " + this + " to " + phasedRow + ", " + phasedColumn);

            enemyPieceRow = phasedRow;
            enemyPieceColumn = phasedColumn;
        }
        board.getBoardArray()[currentRow][currentColumn] = null;

        if(enemyPiecePhased != null) {
            System.out.println("Enemy piece phased: " + enemyPiecePhased + " at " + enemyPieceRow + ", " + enemyPieceColumn);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
            board.printBoardArray();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
       }
    }
    
    public void unPhasePiece()
    {
        // isPhased = false;
        // if(currentRow != -1 && currentColumn != -1) {
        //     board.getBoardArray()[currentRow][currentColumn] = null;
        // }

        // if(enemyPiecePhased != null) {
        //     System.out.println("Enemy piece unphased: " + enemyPiecePhased);
        // }
        // board.getBoardArray()[tempRow][tempColumn] = enemyPiecePhased;
        // System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
        // board.printBoardArray();
        // System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");

        // currentRow = tempRow;
        // currentColumn = tempColumn;

        // System.out.println("unphasing piece that was at: " + currentRow + ", " + currentColumn);
        // board.getBoardArray()[currentRow][currentColumn] = this;

        isPhased = false;
        board.getBoardArray()[currentRow][currentColumn] = this;
        if(enemyPieceRow != -1)
            board.getBoardArray()[enemyPieceRow][enemyPieceColumn] = enemyPiecePhased;
    }

    public boolean isPhased() {
        return isPhased;
    }
}
