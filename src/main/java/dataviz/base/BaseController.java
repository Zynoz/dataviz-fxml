package dataviz.base;

import dataviz.Main;
import dataviz.Util;
import dataviz.index.IndexController;
import dataviz.locking.LockingController;
import dataviz.memory.MemoryController;
import dataviz.process.ProcessController;
import dataviz.transaction.TransactionController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        checkIfFileContentIsOk(Util.getFile("txt", "C:"));
    }

    private void checkIfFileContentIsOk(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //todo validate file content
            Main.alert(Alert.AlertType.INFORMATION, "SQL imported successfully!", "Success!");
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            Main.alert(Alert.AlertType.ERROR, "SQL import failed!\n" + e.getMessage(), "Error!");
        }
    }

    private void addListeners() {
        textImport.setOnAction(e -> loadSQL());
        close.setOnAction(e -> Platform.exit());
        about.setOnAction(e -> Main.alert(Alert.AlertType.INFORMATION, "Created by:\n\tDenoise Stancov\n\tDenis Pelz\n\tAbraham Sedra\n\tMaximilian Moser\n\nVersion:\n\t0.1 pre-alpha", "About this Application"));
    }
}
