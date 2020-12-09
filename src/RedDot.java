import javafx.scene.image.ImageView;

public class RedDot {
    private int currentColumn;
    private int currentRow;

    private Board board;
    private ImageView image;

    public RedDot(int row, int column, Board board) {
        image = Board.initImage("redDot");
        board.getPane().getChildren().add(image);
        currentRow = row;
        currentColumn = column;
        this.board = board;
    }

    public void remove() {
        board.getPane().getChildren().remove(image);
    }

    public void setLocation() {
        
    }
}
