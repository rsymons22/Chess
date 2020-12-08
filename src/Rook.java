public class Rook extends Piece{
    private Board board;
    private int teamColor;

    public Rook(int teamColor, Board board, int startingRow, int startingColumn) {
        //super();

        this.board = board;
        this.teamColor = teamColor;
    }
}
