public class Pawn extends Piece{
    private boolean canSkip;
    private Board board;
    private int teamColor;

    public Pawn(int teamColor, Board board, int startingRow, int startingColumn) {
        //super();

        this.board = board;
        canSkip = true;
        this.teamColor = teamColor;
    }
}
