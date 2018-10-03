package pkj90.quickimagesorter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import java.io.File;
import java.util.ArrayList;


public class Controller {
    private Stage primaryStage;

    @FXML private Label sourcePath;

    private ArrayList<HotkeyButton> hotBtnArr = new ArrayList<HotkeyButton>();
    private HotkeyButton focusHotkey; //Hotkey button currently being configured
    @FXML private HotkeyButton confHotBtn1;
    @FXML private HotkeyButton confHotBtn2;
    @FXML private HotkeyButton confHotBtn3;
    @FXML private HotkeyButton confHotBtn4;
    @FXML private HotkeyButton confHotBtn5;
    @FXML private HotkeyButton confHotBtn6;
    @FXML private HotkeyButton confHotBtn7;
    @FXML private HotkeyButton confHotBtn8;
    @FXML private HotkeyButton confHotBtn9;
    @FXML private Label statusText;
    @FXML private ImageView focusImage;

    public void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    public void setupListeners(Stage primaryStage)
    {
        hotBtnArr.add(confHotBtn1);
        hotBtnArr.add(confHotBtn2);
        hotBtnArr.add(confHotBtn3);
        hotBtnArr.add(confHotBtn4);
        hotBtnArr.add(confHotBtn5);
        hotBtnArr.add(confHotBtn6);
        hotBtnArr.add(confHotBtn7);
        hotBtnArr.add(confHotBtn8);
        hotBtnArr.add(confHotBtn9);


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode().isLetterKey() || key.getCode().isDigitKey() || key.getCode().isKeypadKey() && key.getCode() != KeyCode.ESCAPE)
            {
                if (focusHotkey != null)
                {
                    hotBtnArr.forEach((i)-> {
                        if (key.getCode() == i.getKeyCode())
                        {
                            focusHotkey.resetText();
                            statusText.setText("That key is already bound to something else.");
                            focusHotkey = null;
                        }
                    });
                    if (focusHotkey != null) //this is not redundant.
                    {
                        focusHotkey.setKeyCode(key.getCode());
                        focusHotkey.setText(key.getText().toUpperCase());
                        statusText.setText("Hotkey bound to " + key.getText().toUpperCase());
                        focusHotkey = null;
                    }
                }
            }
            else if (key.getCode() == KeyCode.ESCAPE)
            {
                focusHotkey.resetText();
                focusHotkey = null;
            }
            else if (focusHotkey != null)
            {
                statusText.setText("That key cannot be bound. Please choose another.");
                focusHotkey.resetText();
                focusHotkey = null;
            }
        });
    }


    public void setSource(ActionEvent actionEvent) {
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
            sourcePath.setText(path.substring(path.lastIndexOf("/") + 1).substring(path.lastIndexOf("\\") + 1));
            SourceManager sourceManager = new SourceManager(this, selectedDirectory);
        }
        else
            System.out.println("No directory found");

    }

    public void configureHotkeyBtn(ActionEvent actionEvent) {
        HotkeyButton clickedButton = (HotkeyButton)actionEvent.getSource();
        if (focusHotkey == clickedButton)
        {
            focusHotkey.resetText();
            focusHotkey = null;
        }
        else if (focusHotkey != null)
        {
            focusHotkey.resetText();
            focusHotkey = clickedButton;
            focusHotkey.setText("...");
        }
        else
        {
            focusHotkey = clickedButton;
            focusHotkey.setText("...");
        }
    }

    public void setFocusImage(Image image){
        focusImage.setImage(image);
    }

}
