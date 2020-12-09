import javafx.scene.image.ImageView;

public class Piece {
    protected int currentRow;
    protected int currentColumn;

    private int tempRow;
    private int tempColumn;

    private boolean isCaptured;

    private Board board;

    private int teamColor;

    public Piece(int teamColor, Board board, int row, int column, ImageView pieceImage) {
        currentRow = row;
        currentColumn = column;
        this.board = board;
        this.teamColor = teamColor;

        System.out.println("Creating piece at: " + row + ", " + column);
        pieceImage.setX(column * 60);
        pieceImage.setY(row * 60);
        board.getPane().getChildren().add(pieceImage);
    }
}
