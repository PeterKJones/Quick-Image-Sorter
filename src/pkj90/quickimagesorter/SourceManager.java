package pkj90.quickimagesorter;

import javafx.scene.image.Image;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

public class SourceManager
{
    private Controller controller;
    private File directory;
    private final String[] EXTENSIONS = new String[]{
            "png","jpg","bmp"
    };

    private ArrayList<String> imageList = new ArrayList<>();
    private ArrayList<String> deletedImages = new ArrayList<>();



    SourceManager(Controller cont, File dir){
        controller = cont;
        directory = dir;

        for (File f : directory.listFiles())
        {
            String imagePath = f.getPath();
            System.out.println("Checking file: "+imagePath);
            if (isImage(imagePath))
            {
                System.out.println("Adding file: "+imagePath);
                imageList.add(f.toURI().toString());
            }
        }

        if (!imageList.isEmpty())
            cont.setFocusImage(new Image(imageList.get(0)));
    }

    private boolean isImage(String image){
        String path = FilenameUtils.getExtension(image);
        System.out.println("Path: " + path);
        for (String i : EXTENSIONS)
        {
            if (i.compareTo(path) == 0)
            {
                System.out.println(path + " is a match!");
                return true;
            }
        }
        return false;
    }
}
