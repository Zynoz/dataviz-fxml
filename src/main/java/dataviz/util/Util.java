package dataviz.util;

import dataviz.exception.SQLException;
import javafx.stage.FileChooser;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class Util {
    public static File getFile(String fileFormat, String initialPath) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Files", "*." + fileFormat);
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(initialPath));

        return fileChooser.showOpenDialog(null);
    }

    public static List<String> getList(File file) throws IOException, SQLException {
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        if (list.isEmpty()) {
            throw new SQLException("File is empty!");
        }
        return list;
    }

    //todo gt fragen, was er genau will...
    public static List<String> getListFromCSV(File file) {
        return null;
    }
}
