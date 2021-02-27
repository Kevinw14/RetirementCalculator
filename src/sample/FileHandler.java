package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/**
 * FileHandler class allows an application to write an object
 * and it's data to a file and read the file back into objects.
 *
 * FileHandler conforms to different types of objects through its generic.
 * as long as the object conforms to Serializable.
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class FileHandler<T extends Serializable> {

    private final FileChooser fileChooser;

    /**
     * Creates a FileHandler object and initializes
     * a FileChooser object that only allows .ser files
     * to be loaded.
     *
     */
    public FileHandler() {
        this.fileChooser = new FileChooser();
        this.fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.ser"));
    }

    /**
     * Reads the file that was chosen when FileChoose opened the window.
     * If an object can be created out of the file it will return the object.
     *
     * @return An <T> object is returned if no exceptions are thrown.
     * @throws IOException Throws if you attempt to read a file that's no longer available.
     * @throws ClassNotFoundException Throws if class can not be loaded from it's class path.
     */
    @SuppressWarnings("unchecked")
    public T load(Stage stage) throws IOException, ClassNotFoundException, NullPointerException {

        fileChooser.setTitle("Open");
        File file = fileChooser.showOpenDialog(stage);
        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        T object = (T) objectStream.readObject();

        objectStream.close();
        fileStream.close();

        return object;
    }

    /**
     * Saves the serialized object passed in to the file that was chosen
     * by the FileChooser window.
     *
     * @param object The serialized object that will be written to the file.
     * @throws IOException Throws if the streams was closed before writing was finished.
     */
    public void save(Stage stage, T object) throws IOException, NullPointerException {

        fileChooser.setTitle("Save");
        File file = fileChooser.showSaveDialog(stage);
        FileOutputStream fileStream = new FileOutputStream(file);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

        objectStream.writeObject(object);

        objectStream.close();
        fileStream.close();
    }
}
