package pkj90.quickimagesorter;

import javafx.scene.image.Image;

import java.io.File;

public class SourceManager
{
    private Controller controller;
    private File directory;
    private String[] EXTENSIONS = new String[]{
            "png","jpg","bmp"
    };



    SourceManager(Controller cont, File dir){
        controller = cont;
        directory = dir;

        for (File f : directory.listFiles())
        {
            String imagePath = f.getPath();
            System.out.println("file:"+imagePath);
            if (imagePath.endsWith("jpg")){
                System.out.println(f.toString());
                cont.setFocusImage(new Image(f.toURI().toString()));
            }
            else
                System.out.println("found nothing so far");

        }
    }
}
