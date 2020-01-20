package dataviz;

import javafx.stage.FileChooser;

import java.io.File;

public class Util {
    public static File getFile(String fileFormat, String initialPath) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*." + fileFormat);
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(initialPath));

        return fileChooser.showOpenDialog(null);
    }
}
