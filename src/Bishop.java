public class Bishop extends Piece{
    private Board board;
    private int teamColor;

    public Bishop(int teamColor, Board board, int startingRow, int startingColumn) {
        //super();

        this.board = board;
        this.teamColor = teamColor;
    }
}