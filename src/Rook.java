import javafx.scene.image.ImageView;

public class Rook extends Piece {
    private Board board;
    private int teamColor;

    public Rook(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;
        this.teamColor = teamColor;
    }
}
