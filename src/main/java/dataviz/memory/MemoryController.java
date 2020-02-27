package dataviz.memory;

import dataviz.transaction.TableEntry;
import dataviz.transaction.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class MemoryController implements Initializable {

    private static MemoryController memoryController;

    private ObservableList<TableEntry> entries = FXCollections.observableArrayList();

    @FXML
    private TextArea user1, user2, user3;

    @FXML
    private ScrollBar scrollBar1, scrollBar2;

    @FXML
    private Label labelSGA, labelHDD;

    public MemoryController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memoryController = this;
//        addListeners();
//        setupTableView();
    }

    public static MemoryController getInstance() {
        return memoryController;
    }

}
