package task1.software1_c482_qkm2_task1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * This is the main class that opens when the application starts
 * the javaDocs will be in a folder named JavaDocs located /Software1_C482_QKM2_Task1/JavaDocs
 *<p>
 * <b>FUTURE ENHANCEMENT:</b> I would tie this to a SQL database so the information could be sent shared better.<br>
 * I would also combine the Add part and add product form and use another radio button to choose what is being added.
 *</p>
 * <p>
 * <b>RUNTIME ERROR:</b> I ran into an issue at the start of this project and couldn't get<br>
 * the close button to work and realized I misspelled the object.
 *</p>
 */
public class Main extends Application {

    @Override
    public void start(Stage HomeScreen) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
        HomeScreen.setTitle("");
        HomeScreen.setScene(scene);
        HomeScreen.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static class UniqueIDs {
        private int count = 0;
        private Set<Integer> usedIds;

        public UniqueIDs() {
            usedIds = new HashSet<>();
        }

        public int generateUniqueId() {
            int id = ++count;
            while (usedIds.contains(id)) {
                id = ++count;
            }
            usedIds.add(id);
            return id;
        }

        public int outputUid() {
            UniqueIDs uniqueIDs = new UniqueIDs();

            int Uid = uniqueIDs.generateUniqueId();
            return Uid;
        }
    }
}