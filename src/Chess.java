import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Chess extends Application {

    private BorderPane pane;
    private TextField textField;

    public void start(Stage primaryStage) {

        pane = new BorderPane();
        BorderPane boardPane = new BorderPane();
        BorderPane actionPane = new BorderPane();

        textField = new TextField("White Turn");
        textField.setFont(Font.font(null, FontWeight.BOLD, 15));
        textField.setEditable(false);
        textField.setFocusTraversable(false);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5));

        actionPane.setTop(textField);
        actionPane.setBottom(hBox);

        pane.setTop(boardPane);
        pane.setBottom(actionPane);

        Scene scene = new Scene(pane, 480, 540);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Board board = new Board(boardPane, scene, textField, null);

        initButtons(hBox, primaryStage, board);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private static void initButtons(HBox hBox, Stage primaryStage, Board board) {
        Button quitButton = new Button("Quit");
        Button importBoardButton = new Button("Import Board");
        Button exportMovesButton = new Button("Export Moves");

        hBox.getChildren().addAll(quitButton, importBoardButton, exportMovesButton);

        quitButton.setOnAction(e -> {
            Alert alert = new Alert(AlertType.NONE, "Are you sure you want to quit?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Chess");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                primaryStage.close();
            }

        });

        FileChooser fileChooser = new FileChooser();

        importBoardButton.setOnAction(e -> {

            String[][] boardArray = new String[8][8]; 

            // Prefix (W or B) is team color
            // Suffix (P, Kn, R, B, Q, Ki) is piece type

            try {
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                if(selectedFile != null) { // Would be null if user canceled file explorer
                    Scanner sc = new Scanner(selectedFile);
                    int row = 0;
                    while(sc.hasNextLine()) {
                        boardArray[row] = sc.nextLine().split(",");
                        row++;
                    }
                    sc.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            
        });

        exportMovesButton.setOnAction(e -> {
            System.out.println("Writing to file");
            try {
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                FileWriter writer;
                writer = new FileWriter(selectedFile.getPath());
            
                for(String str: board.getMoveList()) {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            Alert alert = new Alert(AlertType.NONE, "Moves exported", ButtonType.OK);
            alert.setTitle("Chess");
            alert.showAndWait();

        });

        
    }

    private void formatBoard(String[][] boardArray) {
        for(int r = 0; r < boardArray.length; r++) {
            for(int c = 0; c < boardArray[r].length; c++) {
                
            }
        }
    }

    private void resetBoard(Piece[][] boardPieces) {
        //new Board(pane, textField, boardPieces);
    }

}
