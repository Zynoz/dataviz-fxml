package dataviz.base;

import dataviz.Main;
import dataviz.exception.SQLException;
import dataviz.index.IndexController;
import dataviz.locking.LockingController;
import dataviz.memory.MemoryController;
import dataviz.process.ProcessController;
import dataviz.transaction.TransactionController;
import dataviz.util.SQLParser;
import dataviz.util.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {

    @FXML
    public MenuItem reset;

    @FXML
    public MenuItem about;

    @FXML
    public MenuItem textImport;

    @FXML
    public MenuItem csvImport;

    @FXML
    private TransactionController transactionController;

    @FXML
    private MemoryController memoryController;

    @FXML
    private ProcessController processController;

    @FXML
    private IndexController indexController;

    @FXML
    private LockingController lockingController;

    @FXML
    private MenuItem close;

    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void loadSQL() {
        //todo check if file content is ok
        checkIfFileContentIsOk(Util.getFile("sql", "C:\\scratch"));
    }

    private void loadCSV() {
        System.out.println(Util.getListFromCSV(Util.getFile("csv", "C:\\scratch")));
    }

    private void checkIfFileContentIsOk(File file) {
        try {
            //todo validate file content
            System.out.println(SQLParser.getSQL(Util.getList(file)));
            Main.alert(Alert.AlertType.INFORMATION, "SQL imported successfully!", "Success!");
        } catch (SQLException | IOException e) {
            Main.alert(Alert.AlertType.ERROR, "SQL import failed:\n\n\t" + e.getMessage(), "Error!");
        }
    }

    private void addListeners() {
        textImport.setOnAction(e -> loadSQL());
        csvImport.setOnAction(e -> loadCSV());
        close.setOnAction(e -> Platform.exit());
        about.setOnAction(e -> Main.alert(Alert.AlertType.INFORMATION, "Created by:\n\tDennis Stancov\n\tDenis Pelz\n\tAbraham Sedra\n\tMaximilian Moser\n\nVersion:\n\t0.1 pre-alpha", "About this Application"));
    }
}
