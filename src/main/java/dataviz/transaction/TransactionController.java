package dataviz.transaction;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab undoTab;

    @FXML
    private AnchorPane undoAnchorPane, redoAnchorPane;

    @FXML
    private TableView undoTableView, redoTableView, tableView;

    @FXML
    private TableColumn undoRIDCol, undoTIDCol, redoRIDCol, redoTIDCol, c1, c2;

    @FXML
    private TextArea user1, user2, user3;

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
