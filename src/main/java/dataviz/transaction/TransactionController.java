package dataviz.transaction;

import dataviz.util.SQLParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    @FXML
    private Tab undoTab;

    @FXML
    private TableView undoTableView, redoTableView, tableView;

    @FXML
    private TableColumn undoRIDCol, undoTIDCol, redoRIDCol, redoTIDCol;


    @FXML
    private TextArea tf1, tf2, tf3;

    @FXML
    private Button go1, go2, go3, commit1, commit2, commit3, rollback1, rollback2, rollback3;

    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners() {
        go1.setOnAction(event -> {
            System.out.println("clicked go1");
            String selected = tf1.getSelectedText();
            if (!selected.isEmpty()) {
                System.out.println("selected: " + selected);
                System.out.println(SQLParser.getType(selected.trim()));
            } else {
                System.out.println("selected is empty");
            }
        });
    }
}
