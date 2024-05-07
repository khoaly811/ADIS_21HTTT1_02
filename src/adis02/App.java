package adis02;

import java.io.IOException;
import DataAccessLayer.DataConnection;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends javafx.application.Application {
    private static Scene scene;

    @Override
    public void init() throws Exception {
        DataConnection.connect();
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("../views/loginPage"));
        stage.setScene(scene);
        // stage.setMaximized(true);
    
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
