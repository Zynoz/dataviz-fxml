package dataviz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    public static void alert(Alert.AlertType alertType, String text, String header) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.setTitle(alertType.name());
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/base_view.fxml"));
        primaryStage.setTitle("DataViz");
        primaryStage.setScene(new Scene(root, 750, 600));
//        Button btn = TransactionController.getInstance().getGo1();
//        KeyCombination kc = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
//        Mnemonic mn = new Mnemonic(btn, kc);
//        primaryStage.getScene().addMnemonic(mn);
        primaryStage.show();
    }
}
