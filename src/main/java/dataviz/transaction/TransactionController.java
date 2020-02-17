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

    private static TransactionController transactionController;

    private ObservableList<TableEntry> entries = FXCollections.observableArrayList();

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

    public static TransactionController getInstance() {
        return transactionController;
    }

    public void initialize(URL location, ResourceBundle resources) {
        transactionController = this;
        addListeners();
        setupTableView();
    }

    private void addListeners() {
        go1.setOnAction(event -> {
            System.out.println("IT WORKS");
            String selected = tf1.getSelectedText();
            if (!selected.isEmpty()) {
                try {
                    String out = SQLParser.getSQLFromSingleString(selected.trim()).getSql();
                    System.out.println(out);
                    entries.add(SQLParser.getInsertData(out));
                    updateTableView();
                } catch (SQLException e) {
                    Main.alert(Alert.AlertType.ERROR, e.getMessage(), e.getClass().getSimpleName());
                }
            } else {
                Main.alert(Alert.AlertType.WARNING, "No SQL Statement selected", "SQL Error");
            }
        });
    }

    private void setupTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, Double>("currentAmount"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<TableEntry, Double>("maxAmount"));
        contentTable.getItems().setAll(entries);
    }

    public void insertTestEntries() {
        entries.add(new TableEntry(1, "Maxi", 10.0, 10.0));
        entries.add(new TableEntry(2, "Isabella", 5.0, 10.0));
        entries.add(new TableEntry(3, "Emilia", 16.6, 17.6));
        entries.add(new TableEntry(4, "Emma", 6.0, 10.0));
        System.out.println("inserted");
    }

    public void updateTableView() {
        contentTable.getItems().setAll(entries);
    }

    public Button getGo1() {
        return go1;
    }
}
