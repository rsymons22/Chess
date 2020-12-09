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

    public int getColumn() {
        return currentColumn;
    }

    public int getRow() {
        return currentRow;
    }

    public void remove() {
        board.getPane().getChildren().remove(image);
    }

    public void setLocation() {
        image.setLayoutX((currentColumn * 60) + 20); // as of 12/8 not sure why + 20, was in og code, probably due to dot being low in png
        image.setLayoutY((currentRow * 60) + 20);
    }

    public void printLocation() {
        System.out.println("row: " + currentRow + ", column: " +  currentColumn);
    }
}
