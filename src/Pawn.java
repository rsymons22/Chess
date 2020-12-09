import javafx.scene.image.ImageView;

public class Pawn extends Piece {
    private boolean canSkip;
    private Board board;
    private int teamColor;

    public Pawn(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;
        canSkip = true;
        this.teamColor = teamColor;
    }
}
