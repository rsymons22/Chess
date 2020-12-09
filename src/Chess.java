import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Chess extends Application{

    public void start(Stage primaryStage) {
        
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 480, 480);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Board board = new Board(pane, scene, primaryStage);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
