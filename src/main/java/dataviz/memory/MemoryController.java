package dataviz.memory;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MemoryController {

    @FXML
    private TextArea user1, user2, user3;

    @FXML
    private ScrollBar scrollBar1, scrollBar2;

    @FXML
    private Label labelSGA, labelHDD;

}
