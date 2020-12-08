public class King extends Piece{
    private Board board;
    private int teamColor;

    public King(int teamColor, Board board, int startingRow, int startingColumn) {
        //super();

        this.board = board;
        this.teamColor = teamColor;
    }
}