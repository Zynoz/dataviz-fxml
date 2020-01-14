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
    private GridPane gridPane1, gridPane2;

    @FXML
    private ColumnConstraints colConstraint1, colConstraint2, colConstraint3, colConstraint4;

    @FXML
    private RowConstraints rowConstraint1, rowConstraint2, rowConstraint3, rowConstraint4;

    @FXML
    private ScrollBar scrollBar1, scrollBar2;

    @FXML
    private Label labelSGA, labelHDD;

    @FXML
    private Insets insetLeft1, insetLeft2;

}
