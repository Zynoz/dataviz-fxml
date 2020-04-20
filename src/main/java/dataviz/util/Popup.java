package dataviz.util;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.tuple.Pair;

public class Popup {

    static boolean answer;
    static String len;
    static String hoe;


    public static Pair display() {

        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("This is a pop up window");

        Label label1 = new Label("Do you want to go to the movies?");

        Button okButton = new Button("Ok");
        Button cancelbutton = new Button("Cancel");

        Label length = new Label("Laenge: ");
        Label height = new Label("Hoehe: ");

        TextField laenge = new TextField();
        TextField breite = new TextField();


        okButton.setOnAction(e ->
                {
                    len = "";
                    popupwindow.close();
                }
        );

        cancelbutton.setOnAction(e ->
                {
                    answer = false;

                    popupwindow.close();
                }
        );

        HBox layout = new HBox(10);

        layout.getChildren().addAll();

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

        return Pair.of(laenge.getText(), breite.getText());
    }

}

