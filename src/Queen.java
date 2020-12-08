public class Queen extends Piece{
    private Board board;
    private int teamColor;

    public Queen(int teamColor, Board board, int startingRow, int startingColumn) {
        //super();

        this.board = board;
        this.teamColor = teamColor;
    }
}
