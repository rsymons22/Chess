import javafx.scene.image.ImageView;

public class Bishop extends Piece {
    private Board board;
    private int teamColor;

    public Bishop(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;
        this.teamColor = teamColor;
    }
}