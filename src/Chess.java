import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.Action;

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

    private static BorderPane pane;
    private static Scene scene;
    private static Stage primaryStage;
    private static TextField textField;
    private static Board board;
    private static ActionBar actionBar;

    public void start(Stage primaryStage) {

        pane = new BorderPane();
        BorderPane boardPane = new BorderPane();
        BorderPane actionPane = new BorderPane();

        textField = new TextField("White Turn");
        textField.setFont(Font.font(null, FontWeight.BOLD, 15));
        textField.setEditable(false);
        textField.setFocusTraversable(false);

        actionBar = new ActionBar(primaryStage);

        actionPane.setTop(textField);
        actionPane.setBottom(actionBar.getHBox());

        pane.setTop(boardPane);
        pane.setBottom(actionPane);

        scene = new Scene(pane, 480, 540);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Chess.primaryStage = primaryStage;

        board = new Board(boardPane, scene, primaryStage, textField, actionBar);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public static Board getBoard() {
        return board;
    }

    public static void resetBoard() {
        textField.setText("White Turn");
        board.setToDefaults(pane, scene, primaryStage, textField);
    }

}
