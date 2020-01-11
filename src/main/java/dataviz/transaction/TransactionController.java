package dataviz.transaction;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private Label counter;

    private int count = 0;

    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners() {
        button1.setOnAction(e -> counter.setText(String.valueOf(++count)));
    }
}
