import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Chess extends Application{

    public void start(Stage primaryStage) {
        
        BorderPane pane = new BorderPane();

        TextField textField = new TextField("White Turn");
        textField.setFont(Font.font(null, FontWeight.BOLD, 15));
        textField.setEditable(false);  
        textField.setFocusTraversable(false);

        pane.setBottom(textField);

        Scene scene = new Scene(pane, 480, 520);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        new Board(pane, scene, primaryStage, textField);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
