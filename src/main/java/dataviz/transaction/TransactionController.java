package dataviz.transaction;

import dataviz.Main;
import dataviz.exception.SQLException;
import dataviz.util.SQLParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    @FXML
    private Tab undoTab;

    @FXML
    private TableView undoTableView, redoTableView;

    @FXML
    private TableView<TableEntry> contentTable;

    @FXML
    private TableColumn<TableEntry, Long> idColumn;

    @FXML
    private TableColumn<TableEntry, String> nameColumn;

    @FXML
    private TableColumn<TableEntry, Double> amountColumn;

    @FXML
    private TableColumn<TableEntry, Double> maxColumn;

    @FXML
    private TableColumn undoRIDCol, undoTIDCol, redoRIDCol, redoTIDCol;


    @FXML
    private TextArea tf1, tf2, tf3;

    @FXML
    private Button go1, go2, go3, commit1, commit2, commit3, rollback1, rollback2, rollback3;

    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
        setupTableView();
    }

    private void addListeners() {
        go1.setOnAction(event -> {
            String selected = tf1.getSelectedText();
            if (!selected.isEmpty()) {
                try {
                    System.out.println(SQLParser.getSQLFromString(selected.trim()));
                } catch (SQLException e) {
                    Main.alert(Alert.AlertType.ERROR, e.getMessage(), e.getClass().getSimpleName());
                }
            } else {
                Main.alert(Alert.AlertType.WARNING, "No SQL Statement selected", "SQL Error");
            }
        });
    }

    private void setupTableView() {
        ObservableList<TableEntry> entries = FXCollections.observableArrayList();
        entries.add(new TableEntry(1, "Maxi", 10.0, 10.0));
        idColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, Double>("currentAmount"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, Double>("maxAmount"));
        contentTable.getItems().setAll(entries);
    }
}
