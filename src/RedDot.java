import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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

    public int getColumn() {
        return currentColumn;
    }

    public int getRow() {
        return currentRow;
    }

    public void remove(Pane pane) {
        pane.getChildren().remove(image);
    }

    public void setLocation() {
        image.setTranslateX((currentColumn * 60) + 20); // + 20 due to the red dot being low
        image.setTranslateY((currentRow * 60) + 20);
    }

    public void printLocation() {
        System.out.println("row: " + currentRow + ", column: " +  currentColumn);
    }
}
