package dataviz.transaction;

import dataviz.Main;
import dataviz.exception.SQLException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    private static final Logger log = LogManager.getLogger(TransactionController.class.getSimpleName());

    private static TransactionController transactionController;
    @FXML
    public TableColumn<TableEntry, Long> idColumn;

    private final ObservableList<TableEntry> entries = FXCollections.observableArrayList();

    private TransactionManager tm;
    private final ObservableList<UndoTableEntry> undoTableEntries = FXCollections.observableArrayList();
    @FXML
    private TableView<UndoTableEntry> undoTable;

    @FXML
    private TableColumn<UndoTableEntry, String> undoRIDCol, undoTIDCol, undoSQLCol;

    @FXML
    private TableView<RedoTableEntry> redoTable;

    @FXML
    private TableView<TableEntry> contentTable;

    @FXML
    private TableColumn<TableEntry, String> nameColumn;

    @FXML
    private TableColumn<TableEntry, Double> amountColumn;

    @FXML
    private TableColumn<TableEntry, Double> maxColumn;

    @FXML
    private TableColumn redoRIDCol, redoTIDCol;

    @FXML
    private TextArea tf1, tf2, tf3;

    @FXML
    private Button go1, go2, go3, commit1, commit2, commit3, rollback1, rollback2, rollback3;

    public static TransactionController getInstance() {
        return transactionController;
    }

    public void initialize(URL location, ResourceBundle resources) {
        log.debug("initializing...");
        transactionController = this;
        tm = TransactionManager.getInstance();
        addListeners();
        setupTableView();
        setupUndoTableView();
    }

    private void setupUndoTableView() {
        undoRIDCol.setCellValueFactory(new PropertyValueFactory<>("rowId"));
        undoTIDCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().gettId().gettId()));
        undoSQLCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSqlStatement().getSql()));
    }

    private void addListeners() {
        go1.setOnAction(event -> {
            String selected = tf1.getSelectedText();
            if (!selected.isEmpty()) {
                try {
                    tm.executeStatement(selected.trim());
                    updateTableView();
                } catch (SQLException e) {
                    log.error(e.getMessage());
                    Main.alert(Alert.AlertType.ERROR, e.getMessage(), e.getClass().getSimpleName());
                }
            } else {
                log.error("No SQL Statement selected");
                Main.alert(Alert.AlertType.WARNING, "No SQL Statement selected", "SQL Error");
            }
        });

        commit1.setOnAction(event -> {
            try {
                tm.executeStatement("commit;");
                updateUndoTableView();
            } catch (SQLException e) {
                log.error(e.getMessage());
                Main.alert(Alert.AlertType.ERROR, e.getMessage(), e.getClass().getSimpleName());
            }
        });
    }

    private void setupTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("currentAmount"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<>("maxAmount"));
        contentTable.getItems().setAll(entries);
    }

    public void updateTableView() {
        contentTable.getItems().setAll(entries);
    }

    public void addToTableView(List<TableEntry> tableEntries) {
        contentTable.getItems().addAll(tableEntries);
        updateTableView();
    }

    public void addToTableView(TableEntry tableEntry) {
        entries.add(tableEntry);
        updateTableView();
    }

    public void removeFromTableView(List<TableEntry> tableEntries) {
        contentTable.getItems().removeAll(tableEntries);
        updateTableView();
    }

    public void removeFromTableView(TableEntry delete) {
        entries.removeIf(te -> te.equals(delete));
        updateTableView();
    }

    public void updateTableView(TableEntry update) {
        for (TableEntry te : entries) {
            if (te.getId() == update.getId()) {
                te.setName(update.getName());
                te.setCurrentAmount(update.getCurrentAmount());
                te.setMaxAmount(update.getMaxAmount());
            }
        }

        updateTableView();
    }

    public void addToUndoTableView(UndoTableEntry undoEntry) {
        log.info("adding: " + undoEntry.toString());
        undoTableEntries.add(undoEntry);
        updateUndoTableView();
    }

    public void updateUndoTableView() {
        undoTable.getItems().setAll(undoTableEntries);
    }

    public void clearUndoTableView() {
        undoTableEntries.clear();
        updateUndoTableView();
    }

    public Button getGo1() {
        return go1;
    }

    public ObservableList<TableEntry> getEntries() {
        return entries;
    }

    public void addToUndoLog(UndoTableEntry undoTableEntry) {
        log.debug("added " + undoTableEntry + " to undo log");
        undoTableEntries.add(undoTableEntry);
    }
}
