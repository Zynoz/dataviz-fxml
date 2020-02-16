package dataviz.base;

import dataviz.Main;
import dataviz.transaction.TransactionController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {

    @FXML
    public MenuItem reset;

    @FXML
    public MenuItem about;

    @FXML
    private MenuItem close;

    @FXML
    private MenuItem testInsert;

    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners() {
        close.setOnAction(e -> Platform.exit());
        about.setOnAction(e -> Main.alert(Alert.AlertType.INFORMATION, "Created by:\n\tDenoise Stancov\n\tDenis Pelz\n\tAbraham Sedra\n\tMaximilian Moser\n\nVersion:\n\t0.1 pre-alpha", "About this Application"));
        testInsert.setOnAction(event -> {
            TransactionController.getInstance().insertTestEntries();
            TransactionController.getInstance().updateTableView();
        });
    }
}
