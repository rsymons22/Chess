public class Knight extends Piece{
    private Board board;
    private int teamColor;

    public Knight(int teamColor, Board board, int startingRow, int startingColumn) {
        //super();

        this.board = board;
        this.teamColor = teamColor;
    }
}