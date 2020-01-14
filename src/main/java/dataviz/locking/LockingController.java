package dataviz.locking;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class LockingController {

    @FXML
    private TextArea user1, user2, user3;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn c1, c2;

    @FXML
    private Circle circle1, circle2, circle3, circle4;

    @FXML
    private Polygon polygon1, polygon2, polygon3, polygon4;

    @FXML
    private Line line1, line2;

    @FXML
    private ScrollBar scrollBar1;

    @FXML
    private Label labelDeadlock, labelLock;

    @FXML
    private Font fontDeadlock, fontLock;

    @FXML
    private ImageView imageView1, imageView2, imageView3;

    @FXML
    private Image image1, image2, image3;

}
