package dataviz.base;

import dataviz.Main;
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
import javafx.stage.FileChooser;

import java.io.File;
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

    private File getFile(String extension) {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("C:\\"));

        return fileChooser.showOpenDialog(null);
    }

    private void addListeners() {
        textImport.setOnAction(e -> getFile("C:\\test.txt"));
        close.setOnAction(e -> Platform.exit());
        about.setOnAction(e -> Main.alert(Alert.AlertType.INFORMATION, "Created by:\n\tDenoise Stancov\n\tDenis Pelz\n\tAbraham Sedra\n\tMaximilian Moser\n\nVersion:\n\t0.1 pre-alpha", "About this Application"));
    }
}
