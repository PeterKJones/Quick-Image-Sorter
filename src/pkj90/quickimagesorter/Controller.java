package pkj90.quickimagesorter;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;


public class Controller {
    private Stage primaryStage;

    public void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    public void initializeControls(){
        //place things here if you need to set a control to a certain state from the start. May become irrelevant.
    }


    public void setSource(ActionEvent actionEvent) {

        Button sourceBtn = (Button)primaryStage.getScene().lookup("#sourceBtn");
        HBox sourceBox = (HBox)primaryStage.getScene().lookup("#sourceHBox");


        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a Source Directory");
        File defaultDirectory = new File(".");
        directoryChooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = directoryChooser.showDialog(primaryStage);

        if (selectedDirectory == null)
            System.out.println("No directory chosen.");
        else if (selectedDirectory.exists() == true)
        {
            String path = selectedDirectory.getPath();
            System.out.println("Directory chosen!");
            Label sourcePath = (Label)primaryStage.getScene().lookup("#sourcePath");
            VBox hotkeyVBox = (VBox) primaryStage.getScene().lookup("#hotkeyVBox");
            sourcePath.setText(path.substring(path.lastIndexOf("/") + 1).substring(path.lastIndexOf("\\") + 1));

        }
        else
            System.out.println("No directory found");

    }
}
