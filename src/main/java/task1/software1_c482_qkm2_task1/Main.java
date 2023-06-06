package task1.software1_c482_qkm2_task1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This is the main class that opens when the application starts<br>
 * the javaDocs will be in a folder named JavaDocs located /Software1_C482_QKM2_Task1/JavaDocs
 *<p>
 * <b>FUTURE ENHANCEMENT:</b> I would tie this to a SQL database so the information could be sent shared better.<br>
 * I would also combine the Add part and add product form and use another radio button to choose what is being added.
 *</p>
 * <p>
 * <b>RUNTIME ERROR:</b> I ran into an issue at the start of this project and couldn't get<br>
 * the close button to work and realized I misspelled the object.<br>
 * I also couldn't get the id field to automate so i just incremented the value by 1 everytime it was called.
 *</p>
 */
public class Main extends Application {

    /**
     * This method opens the main form when the application is started.
     * @param HomeScreen
     * @throws IOException
     */
    @Override
    public void start(Stage HomeScreen) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
        HomeScreen.setTitle("");
        HomeScreen.setScene(scene);
        HomeScreen.show();
    }

    /**
     * this launches the application.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * this class sets if the application has been initialized yet.
     */
    public static class ProgramInitialized{

        public static boolean opened;

        /**
         * this returns the boolean opened.
         * @return
         */
        public static boolean getStatus(){
            return opened;
        }
    }

    /**
     * this class will assign the UniqueIDs for both the parts and products.
     */
    public static class UniqueIDs {
        private static int count;

        /**
         * when called it will return the next id value for both the parts and the products.
         * @return
         */
        public static int setID(){
            count++;
            return count;
        }

        /*

        !!!! This did not work I tried to randomize the id field. !!!!

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
         */
    }
}