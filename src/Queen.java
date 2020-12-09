import javafx.scene.image.ImageView;

public class Queen extends Piece {
    private Board board;
    private int teamColor;

    public Queen(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;
        this.teamColor = teamColor;
    }
}
