package dataviz.base;

import dataviz.Main;
import dataviz.util.SQLParser;
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
    private MenuItem regexInfo;

    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners() {
        close.setOnAction(e -> Platform.exit());
        about.setOnAction(e -> Main.alert(Alert.AlertType.INFORMATION, "Created by:\n\tDeonisie Stancov\n\tDenis Pelz\n\tAbraham Sedra\n\tMaximilian Moser\n\nVersion:\n\t0.1 pre-alpha", "About this Application"));
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT").append("\n").append(SQLParser.INSERT_PATTERN.toString()).append("\n\n").append("UPDATE").append("\n").append(SQLParser.UPDATE_PATTERN.toString()).append("\n\n").append("DELETE").append("\n").append(SQLParser.DELETE_PATTERN.toString());
        regexInfo.setOnAction(e -> {
            Main.alert(Alert.AlertType.INFORMATION, sb.toString(), "SQL Code must match one of the following patterns!");
        });
    }
}
