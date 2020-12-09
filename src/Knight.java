import javafx.scene.image.ImageView;

public class Knight extends Piece {
    private Board board;
    private int teamColor;

    public Knight(int teamColor, Board board, int startingRow, int startingColumn, ImageView pieceImage) {
        super(teamColor, board, startingRow, startingColumn, pieceImage);  

        this.board = board;
        this.teamColor = teamColor;
    }
}